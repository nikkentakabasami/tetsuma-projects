package ru.tet.javax.annotation.processing;

import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.google.auto.service.AutoService;

import ru.tet.annotations.SUJournal;
import ru.tet.st.SuJournalModel;
import ru.tet.st.SuJournalTemplateWriter;
import ru.tet.st.SuJournalTemplateWriter.SuGeneratedFile;
import ru.tet.st.SuTypeElementParser;

/**
 * Процессор аннотаций, используемый при сборке приложения. 
 * Генерирует подборку исходников, позволяющий легко и быстро сделать журнал для orm-классов, помеченных аннотацией SUJournal.
 * Генерируемые файлы имеют чисто вспомогательную функцию - они не будут компилироваться и подключаться в classpath.
 * 
 * @author tetsuma
 *
 */
@SupportedAnnotationTypes("ru.tet.annotations.SUJournal")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SUJournalAnnotationProcessor extends AbstractProcessor {

	SuProcessorLog logger;
	
	@Override
		public synchronized void init(ProcessingEnvironment processingEnv) {
			super.init(processingEnv);
			logger = new SuProcessorLog(processingEnv, "SUJournalAnnotationProcessorLog.txt");
			
		}
	
	
	
	
	
	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

		try {
			Set<? extends Element> convertibleElements = roundEnv.getElementsAnnotatedWith(SUJournal.class);

			logger.log("start processing SUJournal annotations. Found "+convertibleElements.size()+" elements");
			for (final Element element : convertibleElements) {
				
				// просматриваем только классы
				if (!(element instanceof TypeElement)) {
					continue;
				}

				TypeElement typeElement = (TypeElement) element;
				

				String modelClassName = typeElement.getSimpleName().toString();
				logger.log("found model class " + modelClassName);
				
				
		    
				
				
				SuJournalModel converterModel = SuTypeElementParser.makeConverterModel(typeElement,logger);
				List<SuGeneratedFile> files = SuJournalTemplateWriter.makeJournalCode2(converterModel);
				
//				String converterCode = SuJournalTemplateWriter.makeConverterCode(converterModel);
	
				
				for(SuGeneratedFile file:files) {
					
				  
					// создаём файл конвертера
					final FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, file.getPackagePath(), file.getFileName());  
					Writer writer = fileObject.openWriter();
					writer.append(file.getCode());
					writer.close();
					
				}
				

			}//for

		} catch (final Exception ex) {
			logger.log(ex);
		}

		return true;
	}
	
	

	
	
}
