Набор демок для тестирования разнообразных стандартных классов и пакетов.


А так же основных мавеновских плагинов.

---------------------------------
1)maven-resources-plugin:copy-resources
  тестируем копирование файлов в заданную папку:

<outputDirectory>${basedir}/target/my_resources/${testProp}_${testProp2}</outputDirectory>
попадает в:
/td1/target/my_resources/jv_20_accord


---------------------------------
2)build-helper-maven-plugin:bsh-property
  Задаёт значение проперти testProp
  

---------------------------------
3)assembly-pom.xml
  для тестирования maven-assembly-plugin

mvn -f assembly-pom.xml -P assembly1 clean assembly:single
  Сборки jar-with-dependencies и bin. В форматах dir и zip. 
  Без фильтрации.

mvn -f assembly-pom.xml -P assembly2 clean assembly:single
  Сборка distribution2.xml
   С фильтрацией файла NOTICE.txt.

---------------------------------
td3-processor
  при сборке используются процессоры оттуда.
  Генерируют исходники и логи.


-----------------------------
pom_res.xml
pom_res2.xml
  тестирование maven-resources-plugin.

mvn clean initialize -f pom_res2.xml


