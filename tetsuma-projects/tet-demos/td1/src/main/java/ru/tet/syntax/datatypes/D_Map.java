package ru.tet.syntax.datatypes;

import java.util.HashMap;
import java.util.Map;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_Map extends DemoBase {

	public void test1() throws Exception {
		/*
		V compute(K key, BiFunction<K,V,V> func)
		Позволяет задавать, менять, добавлять, удалять элементы.
		
		func возвращает новое значение (для изменения/добавления).
		Если возвращает null - элемент будет удалён.
		
		 */
		Map<String, Integer> map1 = DemoAuxDataSamples.makeSalariesMap();

		//изменение значения
		Integer newVal1 = map1.compute("bob", (k, v) -> v == null ? 123 : v + 123);

		map1.compute("bill", (k, v) -> v == null ? 123 : v + 123);

		//удаление
		Integer newVal2 = map1.compute("John", (k, v) -> null);

		//добавление
		Integer newVal3 = map1.compute("Alen", (k, v) -> 777);

		logEval1(
				newVal1,
				newVal2,
				newVal3,
				map1);

	}

	public void test2() throws Exception {
		/*
		
		 */

		Map<String, Integer> map1 = DemoAuxDataSamples.makeSalariesMap();

		//изменение значения
		map1.computeIfPresent("bob", (k, v) -> v + 123);

		//ничего не добавит
		map1.computeIfPresent("Alen", (k, v) -> 999);

		map1.computeIfAbsent("bill", k -> 777);

		//не изменит
		map1.computeIfAbsent("bill", k -> 888);

		logEval1(
				map1);

	}

	public void test3() throws Exception {
		/*
		V	merge(K key, V value, BiFunction<V,V,V> func)
		Если в мапе нет ключа key - просто выполняет put(key,value)
		Иначе использует функцию func для мерджинга значений.
		
		 */
		Map<Integer, String> map1 = DemoAuxDataSamples.makeMap1(5);

		//будет добавлен
		map1.merge(7, "merge 7", (a, b) -> a.concat("|").concat(b));

		//будет объединён функцией
		map1.merge(4, "merge 4", (a, b) -> a.concat("|").concat(b));

		logEval1(
				map1);

	}

	public void test4() throws Exception {
		/*
		
		 */
		Map<Integer, String> map1 = HashMap.newHashMap(7);
		
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_Map.class);
	}

}
