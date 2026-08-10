package ru.tet.ehcache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
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
public class CaheDemo_java_squaredNumber extends DemoBase {

	CacheManager cacheManager;

	private Cache<Integer, Integer> cache1;

	@AuxTest
	@Override
	protected void doInit() throws Exception {

		cacheManager =
				CacheManagerBuilder
						.newCacheManagerBuilder().build();
		cacheManager.init();

		CacheConfiguration<Integer, Integer> cacheConfiguration =
				CacheConfigurationBuilder
						.newCacheConfigurationBuilder(Integer.class, Integer.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder()
										.heap(10, EntryUnit.ENTRIES)
										.offheap(1, MemoryUnit.MB)  //минимум 1МБ
								)
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(10,
								TimeUnit.SECONDS)))
						.build();

		cache1 = cacheManager.createCache("squaredNumber", cacheConfiguration);

	}

	public Cache<Integer, Integer> getSquareNumberCacheFromCacheManager() {
		return cacheManager.getCache("squaredNumber", Integer.class, Integer.class);
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

		List<Integer> keys =
				IntStream.range(10, 30)
						.boxed()
						.collect(Collectors.toList());

		keys.forEach(i -> {
			log2(i + "^2=" + getSquareValueOfNumber(i));
		});

		Collections.shuffle(keys);

		log2Splitter();
		log2("second run.");
		keys.forEach(i -> {
			log2(i + "^2=" + getSquareValueOfNumber(i));
		});

	}

	@Override
	public void test2() throws Exception {
		log2("cache data.");

		//считываем данные
		for (int i = 10; i < 30; i++) {
			Integer val = cache1.get(i);
			log2(i, ": ", val);
		}

	}

	@Override
	public void test3() throws Exception {
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(CaheDemo_java_squaredNumber.class);
	}
}
