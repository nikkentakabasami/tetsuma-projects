package ru.tet.syntax;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_Numbers2 extends DemoBase {

	@Override
	public Object fixResultValue(Object value) {
		value = super.fixResultValue(value);

		if (value instanceof Integer i) {
			String bs = Integer.toBinaryString(i);
			value = bs + ": " + String.valueOf(i);
		}

		return value;

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

		int i1 = 7;
		int i2 = 21;
		int i3 = -55;

		r.s1 = i1;
		r.s2 = i2;
		r.s3 = i3;
		
		r.s4 = i1 << 2;
		r.s5 = i2 >> 3;
		r.s6 = i3 >> 3;
		r.s7 = i3 >>> 3;
//		r.s8 = (byte)(-55) >>> 3;

		//сдвиг над байтом
		r.s8 = ((-55 & 0xFF) >>> 3);		
		
		
	}

	public void test2() throws Exception {

		/*
		Битовые операции:
		
		~A	Битовое инвертирование
		A & B	AND
		A | B	OR
		A ^ B	XOR
		 */

		int i1 = 7;
		int i2 = 21;

		r.s1 = i1;
		r.s2 = i2;
		r.s3 = i1 & i2;
		r.s4 = i1 | i2;
		r.s5 = i1 ^ i2;
		r.s6 = ~i1;
		
	}

	public void test3() throws Exception {
	}

	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_Numbers2.class);
	}

}
