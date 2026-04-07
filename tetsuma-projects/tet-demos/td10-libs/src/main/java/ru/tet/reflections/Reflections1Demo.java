package ru.tet.reflections;

import java.util.Set;

import javax.swing.JFrame;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import ru.tet.aux.annot.TestAnnotation3;
import ru.tet.aux.swing.DemoBase;

public class Reflections1Demo extends DemoBase {

	@Override
	public void test1() throws Exception {

		//поиск классов, помеченных аннотацией
		String packageToScan = "ru.tet.samples";
		Reflections reflections = new Reflections(packageToScan, Scanners.TypesAnnotated);

		Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(TestAnnotation3.class);

		for (Class<?> clazz : serviceClasses) {
			log2(clazz.getName());
		}

	}

	@Override
	public void test2() throws Exception {

		//поиск подклассов
		String packageToScan = "ru.tet.samples";
		Reflections reflections = new Reflections(packageToScan);

		Set<Class<? extends JFrame>> subTypes = reflections.getSubTypesOf(JFrame.class);
		for(Class cl:subTypes) {
			log2(cl.getCanonicalName());
		}

		/*
другие способы создания
      Reflections reflections = new Reflections(ClasspathHelper.forPackage("my.package.prefix"), 
            new SubTypesScanner(), new TypesAnnotationScanner(), new FilterBuilder().include(...), ...);

       //or using the ConfigurationBuilder
       new Reflections(new ConfigurationBuilder()
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("my.project.prefix")))
            .setUrls(ClasspathHelper.forPackage("my.project.prefix"))
            .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner().filterResultsBy(optionalFilter), ...));

другие способы использования
   Set<String> properties =       reflections.getResources(Pattern.compile(".\*\.properties"));
   Set<Constructor> injectables = reflections.getConstructorsAnnotatedWith(javax.inject.Inject.class);
   Set<Method> deprecateds =      reflections.getMethodsAnnotatedWith(javax.ws.rs.Path.class);
   Set<Field> ids =               reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);		
   
       Set<Method> someMethods =      reflections.getMethodsMatchParams(long.class, int.class);
       Set<Method> voidMethods =      reflections.getMethodsReturn(void.class);
       Set<Method> pathParamMethods = reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
       Set<Method> floatToString =    reflections.getConverters(Float.class, String.class);
       List<String> parameterNames =  reflections.getMethodsParamNames(Method.class);

       Set<Member> fieldUsage =       reflections.getFieldUsage(Field.class);
       Set<Member> methodUsage =      reflections.getMethodUsage(Method.class);
       Set<Member> constructorUsage = reflections.getConstructorUsage(Constructor.class);   
   
		 */
		
		
		
		

	}
	
	@Override
	public void test3() throws Exception {
		
		
	}
	

	@Override
	protected void doInit() throws Exception {

		addTest1Button(null);
		addTest2Button(null);

	}

	public static void main(String[] args) {
		DemoBase.run(Reflections1Demo.class);
	}

}
