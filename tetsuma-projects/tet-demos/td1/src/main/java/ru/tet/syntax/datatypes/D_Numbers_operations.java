package ru.tet.syntax.datatypes;

import ru.tet.aux.swing.DemoBase;

public class D_Numbers_operations extends DemoBase {

	@Override
	public Object fixResultValue(Object value) throws Exception {

		if (value instanceof Integer i) {
			String bs = Integer.toBinaryString(i);
			value = bs + ": " + String.valueOf(i);
			return value;
		}

		return super.fixResultValue(value);

	}

	public void test1() throws Exception {
		/*
		Битовые операции
		
		Сдвиги
		
		n<<p
		сдвиг влево на p позиций;
		
		n>>p
		знаковый сдвиг вправо на p позиций;
		(если число отрицательное - левые биты будут заполняться единицами, иначе нулями)
		
		n>>>p
		беззнаковый сдвиг вправо
		(всегда будет иметь место дополнение нулями слева)
		
		При сдвиге происходит преобразование числа в формат int, сдвиг и обратное преобразование с усечением. Поэтому может работать некорректно.
		
		 */

		logEval(
				7,
				21,
				-55,

				7 << 2,
				21 >> 3,
				-55 >> 3,
				-55 >>> 3,

				//сдвиг над байтом
				((-55 & 0xFF) >>> 3));

	}

	public void test2() throws Exception {

		/*
		Битовые операции:
		
		~A	побитовое инвертирование
		A & B	побитовое AND
		A | B	побитовое OR
		A ^ B	побитовое XOR
		 */

		logEval(
				7,
				21,

				7 & 21,
				7 | 21,
				7 ^ 21,
				~7
				);


	}

	public void test3() throws Exception {
		/*
		Арифметические операторы
		+ — сложение
		- — вычитание
		* — умножение
		/ — целочисленное деление
		% — остаток от деления (модуль)
		 */

		r.s1 = 10 / 3;
		r.s2 = 10 % 3;

		/*
		++ — префиксный или постфиксный инкремент
		-- — префиксный или постфиксный декремент
		 */

		int x = 10, y = 10, z = 10;

		r.s3 = x++;
		r.s4 = ++y;
		r.s5 = --z;

		/*
		=
		Присваивание.
		Вычисляется с права на лево
		 */

		//Задать всем переменным одинаковое значение:
		x = y = z = 10; //аналог: х = (у = (z = 10))
		log2(x, y, z);

		/*
		Составное присваивание:
		+=, -=, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=
		 */

		x += 1; //11
		y &= 3; //2 (1010 & 11 = 10)
		z <<= 2; //40
		log2(x, y, z);

		/*
		Операции одного приоритета выполняются слева на право
		 */
		x = 5;

		r.s6 = x + (x = 3); //8
		r.s7 = (x = 3) + x; //6

		/*
		Операции сравнения: ==, !=, >, <, >=, <=
		 */

	}

	public void test4() throws Exception {

		/*
		Логический тип
		
		&& — логическое И
		|| — логическое ИЛИ
		! — логическое НЕ
		
		 */
		boolean b1 = true, b2 = false;

		r.s1 = !b1;
		r.s2 = b1 || b2;
		r.s3 = b1 && b2;

	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_Numbers_operations.class);
	}

}
