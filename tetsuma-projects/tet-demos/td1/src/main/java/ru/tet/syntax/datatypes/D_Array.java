package ru.tet.syntax.datatypes;

import java.util.Arrays;
import java.util.Spliterator.OfInt;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.tet.aux.swing.DemoBase;

public class D_Array extends DemoBase {

	@Override
	public Object fixResultValue(Object value) throws Exception {
		if (value.getClass().isArray()) {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(value);
			return json;
		}
		return super.fixResultValue(value);
	}

	public void test1() throws Exception {
		/*
		
		 */

		//2 способа объявления массивов:
		int[] a1;
		int a2[];

		//Создание массивов
		a1 = new int[] { 7, 8, 9 };
		a2 = new int[5];

		int[] a3 = { 1, 2, 3 }; //в объявлении переменной "new int[]" можно пропустить
		Integer[] a5 = new Integer[5];
		String[] a7 = new String[] { "Winter", "Spring", "Summer" };

		logEval1(
				//При инициализации массивы заполняются значениями по умолчанию: нулями, null, false
				a2,
				a5);

	}

	public void test2() throws Exception {
		//Многомерные массивы

		//двумерный массив на 64 элемента
		int[][] a1 = new int[8][8];

		int[][] a2 = { { 18, 28, 18 }, { 28, 45, 90 }, { 45, 3, 14 } };

		int[][][] a3 = new int[2][2][4];

		int[][][] a4 = new int[5][][];

		a4[0] = new int[6][];
		a4[0][0] = new int[7];

		logEval1(
				a3,
				a4);

	}

	public void test3() throws Exception {
		/*
		Arrays - методы
		 */

		int[] a1 = { 1, 5, 4, 3, 7 };
		int[][] a2 = { { 18, 28, 18 }, { 28, 45, 90 }, { 45, 3, 14 } };
		int[] a1_copy;
		int[] a1_copy2;

		logEval1(
				Arrays.toString(a1),
				Arrays.toString(a2),
				Arrays.deepToString(a2),
				expr(() -> {
					Arrays.sort(a1);
					return a1;
				}),

				//ищем число 5
				Arrays.binarySearch(a1, 5),

				//ищем отсутствующее число 0
				Arrays.binarySearch(a1, 0),

				//копия массива с длиной 4
				a1_copy = Arrays.copyOf(a1, 4),
				Arrays.equals(a1, a1_copy),

				a1_copy2 = Arrays.copyOfRange(a1, 1, 4),

				expr(() -> {
					//заполнение массива
					Arrays.fill(a1, 7);
					return a1;
				}),
				expr(() -> {
					//заполнение массива генератором
					Arrays.setAll(a1, cell_ind -> {
						return (cell_ind + 1) * 3;
					});
					return a1;
				})

		);

	}

	public void test4() throws Exception {
		/*
		Arrays
		Spliterator - позволяет организовать итерацию, последовательно или параллельно
		 */
		int[] a1 = { 1, 5, 4, 3, 7 };
		logEval1(
				a1);

		log2Splitter();
		OfInt spliterator = Arrays.spliterator(a1);
		while (spliterator.tryAdvance((int v) -> {
			log2(v);
		}))
			;

		log2Splitter();
		//итерация в разбивке на 2 части
		spliterator = Arrays.spliterator(a1);
		OfInt spliterator2 = spliterator.trySplit();
		while (spliterator2.tryAdvance((int v) -> {
			log2("s2", v);
		}))
			;
		while (spliterator.tryAdvance((int v) -> {
			log2("s1", v);
		}))
			;

	}

	@Override
	public void test5() throws Exception {

	}

	public static void main(String[] args) {
		DemoBase.run(D_Array.class);
	}

}
