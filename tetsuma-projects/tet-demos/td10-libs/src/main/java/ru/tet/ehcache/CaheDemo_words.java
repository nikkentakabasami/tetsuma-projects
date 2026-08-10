package ru.tet.ehcache;

import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.xml.XmlConfiguration;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;
import ru.tet.data.WarodaiDictionaryReader;
import ru.tet.warodai.DWordModel;

/**
 * 
 * 
 */
public class CaheDemo_words extends DemoBase {

	
	enum CacheConfig {
		XML, JAVA, JAVA_PERSISTENT
	};
	
	CacheManager cacheManager;

	Cache<String, DWordModel> wordsCache;

	Set<String> keySet;
	
	

	@AuxTest
	void initCacheManager(CacheConfig mode) throws Exception {

		if (cacheManager != null) {
			cacheManager.close();
			cacheManager = null;
		}

		if (mode==CacheConfig.XML) {
			//конфигурация через xml требует jaxb
			URL myUrl = getClass().getResource("/ehcache_files/ehcache-words.xml");
			Configuration xmlConfig = new XmlConfiguration(myUrl);
			cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
			cacheManager.init();

		} else if (mode==CacheConfig.JAVA) {
			//java-конфигурация (c хранением данных в куче)
			cacheManager =
					CacheManagerBuilder
							.newCacheManagerBuilder().build();
			cacheManager.init();

			CacheConfiguration<String, DWordModel> cacheConfiguration =
					CacheConfigurationBuilder
					.newCacheConfigurationBuilder(String.class, DWordModel.class,
									ResourcePoolsBuilder.newResourcePoolsBuilder()
											.heap(10, EntryUnit.ENTRIES)
											.offheap(1, MemoryUnit.MB)  //минимум 1МБ
									)
							.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,
									TimeUnit.SECONDS)))
							.build();

			cacheManager.createCache("wordsCache", cacheConfiguration);
			
			
			
		} else if (mode==CacheConfig.JAVA_PERSISTENT) { 

			//java-конфигурация (c хранением данных в куче и на диске)
			CacheConfiguration<String, DWordModel> cacheConfiguration =
					CacheConfigurationBuilder
							.newCacheConfigurationBuilder(String.class, DWordModel.class,
									ResourcePoolsBuilder.newResourcePoolsBuilder()
											.heap(10, EntryUnit.ENTRIES)
											.disk(10, MemoryUnit.MB, true))
							.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,
									TimeUnit.SECONDS)))
							.build();

			cacheManager =
					CacheManagerBuilder.newCacheManagerBuilder()
							.with(CacheManagerBuilder.persistence("target/mywords2"))
							.withCache("wordsCache", cacheConfiguration)
							.build(true);

		}

		wordsCache = cacheManager.getCache("wordsCache", String.class, DWordModel.class);
		
		WarodaiDictionaryReader reader = new WarodaiDictionaryReader();
		Map<String, DWordModel> dictionary = reader.readDictionary(0, 0);
		System.out.println(dictionary.size());
		keySet = dictionary.keySet();

		//записываем данные в кеш
		for (String key : dictionary.keySet()) {
			wordsCache.put(key, dictionary.get(key));
		}
		log2("readen",keySet.size(),"words");
		
		
	}
	
	@Override
	public void test1() throws Exception {
		initCacheManager(CacheConfig.JAVA);
	}

	@Override
	public void test2() throws Exception {

		//Проверяем, какие данные сохранились в кеше
		for (String key : keySet) {
			DWordModel word = wordsCache.get(key);
			String val = word != null ? word.getPronunciation() : "-";

			log2(key + ": " + val);
		}

	}

	@Override
	public void test3() throws Exception {
		initCacheManager(CacheConfig.JAVA_PERSISTENT);
	}

	@Override
	public void beforeClose() throws Exception {
		if (cacheManager != null) {
			cacheManager.close();
		}
	}
	
	
	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button("create heap cache");
		addTest2Button("check cache");
		addTest3Button("create persistent cache");
	}

	public static void main(String[] args) {
		DemoBase.run(CaheDemo_words.class, 1);
	}

}
