package ru.tet.ehcache;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class CaheDemo_java_persistent_cache extends DemoBase {

	PersistentCacheManager cacheManager;

	private Cache<Integer, Integer> cache1;

	List<Integer> keys;

	@AuxTest
	@Override
	protected void doInit() throws Exception {

		CacheConfiguration<Integer, Integer> cacheConfiguration =
				CacheConfigurationBuilder
						.newCacheConfigurationBuilder(Integer.class, Integer.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder()
										.heap(10, EntryUnit.ENTRIES)
										.disk(10, MemoryUnit.MB, true))
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,
								TimeUnit.SECONDS)))
						.build();

		cacheManager =
				CacheManagerBuilder.newCacheManagerBuilder()
						.with(CacheManagerBuilder.persistence("target/squaredValue"))
						.withCache("persistent-cache", cacheConfiguration)
						.build(true);

		//init тут не нужен
		//cacheManager.init();

		cache1 = cacheManager.getCache("persistent-cache", Integer.class, Integer.class);

	}

	public int getSquareValueOfNumber(int input) {
		if (cache1.containsKey(input)) {
			return cache1.get(input);
		}

		log2("Calculating " + input + "^2.");

		int squaredValue = (int) Math.pow(input, 2);
		cache1.put(input, squaredValue);

		return squaredValue;
	}

	@Override
	public void test1() throws Exception {

		keys =
				IntStream.range(10, 30)
						.boxed()
						.collect(Collectors.toList());

		keys.forEach(i -> {
			log2(i + "^2=" + getSquareValueOfNumber(i));
		});


		log2Splitter();
		log2("second run.");
		Collections.shuffle(keys);
		keys.forEach(i -> {
			log2(i + "^2=" + getSquareValueOfNumber(i));
		});

		/*
		List<Integer> keys = new ArrayList<>();
		for (int i = 10; i < 30; i++) {
			keys.add(i);
			log2(i+"^2=" + getSquareValueOfNumber(i));
		}
		
		log2("\n\nsecond run.");
		for (int i = 30; i > 10; i--) {
			log2("i^2=" + getSquareValueOfNumber(i));
		}
		*/

	}

	@Override
	public void test2() throws Exception {
		log2("cache data.");

		Collections.shuffle(keys);
		keys.forEach(i -> {
			log2(i + "^2=" + getSquareValueOfNumber(i));
		});
		
		log2Splitter();
		for (int i = 25; i < 35; i++) {
			log2(i+"^2=" + getSquareValueOfNumber(i));
		}
		

	}

	@Override
	public void test3() throws Exception {
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button("fill and check cache");
		addTest2Button("read cache data");
		addTest3Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(CaheDemo_java_persistent_cache.class);
	}
}
