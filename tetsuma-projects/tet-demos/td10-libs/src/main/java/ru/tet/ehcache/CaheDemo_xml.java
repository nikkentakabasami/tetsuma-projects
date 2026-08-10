package ru.tet.ehcache;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Set;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

/**
 * Тестируем конфигурирование кешей через xml
 * конфигурация через xml требует jaxb
 * 
 */
public class CaheDemo_xml extends DemoBase {


	CacheManager cacheManager;
	
	Cache<String, String> cache1;
	
//	WarodaiDictionaryReader reader;

	Set<String> keySet;

	@AuxTest
	@Override
	protected void doInit() throws Exception {
		
		URL myUrl = getClass().getResource("/ehcache_files/ehcache-config1.xml");
		Configuration xmlConfig = new XmlConfiguration(myUrl); 
		cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);		
		cacheManager.init();
		
	}
	
	@Override
	public void test1() throws Exception {
    
		cache1 = cacheManager.getCache("c2", String.class, String.class);

		DecimalFormat format = new DecimalFormat("#0.##");
		
		
		for (int i = 0; i < 100; i++) {
			String val = format.format(Math.random()*100); 
			cache1.put(String.valueOf(i), val);
			
		}
    
		
    
	}

	@Override
	public void test2() throws Exception {
		
		for (int i = 0; i < 100; i++) {
			String key = String.valueOf(i);
			String val = cache1.get(key);
			log2(key,": ",val);
		}
		
		
	}

	@Override
	public void test3() throws Exception {
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button("write data to cache");
		addTest2Button("read from cache");
		addTest3Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(CaheDemo_xml.class);
	}
}
