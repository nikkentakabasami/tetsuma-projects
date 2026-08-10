package ru.tet.ehcache;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.ehcache.PersistentUserManagedCache;
import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.LocalPersistenceService;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.ehcache.impl.persistence.DefaultLocalPersistenceService;

import ru.tet.aux.AuxTest;
import ru.tet.aux.swing.DemoBase;

/**
	UserManagedCache
	  Не обрабатывается CacheManager-ом
	Хорошо подходит для простых короткоживущих кешей.
 */
public class CaheDemo_UserManagedCache extends DemoBase {

	enum CacheConfig {
		SIMPLE, PERSISTENT
	};

	UserManagedCache<Integer, Integer> cache1;

	@AuxTest
	void initCacheManager(CacheConfig mode) throws Exception {

		if (mode == CacheConfig.SIMPLE) {
			cache1 =
					UserManagedCacheBuilder
							.newUserManagedCacheBuilder(Integer.class, Integer.class)
							.build(false);

			cache1.init();

		} else if (mode == CacheConfig.PERSISTENT) {

			//с хранением данных на диске
			LocalPersistenceService persistenceService =
					new DefaultLocalPersistenceService(new DefaultPersistenceConfiguration(new File("target/umc")));

			cache1 =
					UserManagedCacheBuilder.newUserManagedCacheBuilder(Integer.class, Integer.class)
							.with(new UserManagedPersistenceContext<Integer, Integer>("cache-name", persistenceService))
							.withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder()
									.heap(10L, EntryUnit.ENTRIES)
									.disk(10L, MemoryUnit.MB, true))
							.build(true);

		}

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

		initCacheManager(CacheConfig.SIMPLE);

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

		cache1.close();

	}

	@Override
	public void test2() throws Exception {
		initCacheManager(CacheConfig.PERSISTENT);

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
		cache1.close();
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
		DemoBase.run(CaheDemo_UserManagedCache.class);
	}

}
