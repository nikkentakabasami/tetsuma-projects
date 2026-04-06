package ru.tet.st;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import ru.tet.annotations.SUJournal;
import ru.tet.javax.annotation.processing.SuProcessorLog;
import ru.tet.st.SuJournalModel.ClassProperty;

/**
 * Сканирует класс, помеченный аннотацией SUJournal. Выдаёт его модель
 * SuJournalModel, которая используется для генерации исходников на основе
 * st-шаблонов
 * 
 * @author tetsuma
 *
 */
public class SuTypeElementParser {

	public static final String ORM_FILES_SUFFIX = "Model";

	public static List<ClassProperty> makeAccessibleFieldsList(TypeElement typeElement, SuProcessorLog logger) {

		List<ClassProperty> result = new LinkedList<ClassProperty>();

		List<? extends Element> enclosedElements = typeElement.getEnclosedElements();

		for (Element element : enclosedElements) {

			if (element.getKind() != ElementKind.FIELD) {
				continue;
			}

			if (element.getModifiers().contains(Modifier.STATIC)) {
				continue;
			}

			ClassProperty property = new ClassProperty();

			property.propertyName = element.getSimpleName().toString();

			String s = element.asType().toString();
			int ind = s.lastIndexOf('.');
			if (ind >= 0) {
				s = s.substring(ind + 1);
			}
			property.type = s;

			property.init();
//			property.modifiers = element.getModifiers().stream().map(m->m.name()).collect(Collectors.joining(","));

			result.add(property);

			logger.log("found field " + property.toString());

		}
		return result;
	}

	public static SuJournalModel makeConverterModel(TypeElement typeElement, SuProcessorLog logger) throws IOException {

		SuJournalModel model = new SuJournalModel();

		model.modelClassName = typeElement.getSimpleName().toString();
		model.modelFullClassName = typeElement.getQualifiedName().toString();

		SUJournal journalAnnotation = typeElement.getAnnotation(SUJournal.class);

		model.dtoFullClassName = journalAnnotation.dtoFullClassName();
		model.dtoClassName = model.dtoFullClassName.substring(model.dtoFullClassName.lastIndexOf('.') + 1);

		model.propertyList = makeAccessibleFieldsList(typeElement, logger);

		model.templatesFolder = journalAnnotation.templatesFolder();

		SuClasspathResourceScanner scanner = new SuClasspathResourceScanner();
		model.templateNames = scanner.getResourceFilesForClassesDir(logger.getClassesDir(), model.templatesFolder);

		String entityName = model.modelClassName;
		if (entityName.endsWith(ORM_FILES_SUFFIX)) {
			entityName = entityName.substring(0, entityName.length() - ORM_FILES_SUFFIX.length());
		}
		model.entity = entityName;

		return model;

	}

}
