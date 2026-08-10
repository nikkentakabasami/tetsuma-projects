для тестирования различных java-библиотек.


Про библиотеку ehcache.

Потестировал её.
Кэш напоминает Map. В нём хранятся пары ключ-значение.
Конфигурировать его проще всего через xml:

URL myUrl = getClass().getResource("/ehcache_files/ehcache-words.xml");
Configuration xmlConfig = new XmlConfiguration(myUrl); 
cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);		
cacheManager.init();
		
----------------------------

Файл с конфигурациями (ehcache-words.xml) выглядит примерно так:
В данном случае в куче будут хранится последние 10 объектов.
Всё остальное, до мегабайта, будет хранится на диске, по пути "target/mywords".
И задано время жизни объектов: ttl - time-to-live. 
Через 60 секунд они будут удалены из кеша.

  <!-- С хранением данных на диске  -->
	<persistence directory="target/mywords" />

	<cache alias="wordsCache">
		<key-type>java.lang.String</key-type>
		<value-type>ru.tet.warodai.DWordModel</value-type>
		<expiry>
		  <ttl unit="seconds">60</ttl>
		</expiry>
		
		<resources>
			<heap unit="entries">10</heap>
			<disk unit="MB">1</disk>
		</resources>
	</cache>

----------------------------

//Вот мы получаем кеш
wordsCache = cacheManager.getCache("wordsCache2", String.class, DWordModel.class);

//записываем данные в кеш
wordsCache.put("key1", myWord1);
wordsCache.put("key2", myWord2);



//позже получаем данные
DWordModel word = wordsCache.get("key1");
if (word==null){
  word = dao.getWord(...);
}

------------
Объекты, хранящиеся в кеше, должны быть Serializable.

@Data
@NoArgsConstructor
public class DWordModel implements Serializable {

	Integer id;
	String code;
	String kanaWriting;




		