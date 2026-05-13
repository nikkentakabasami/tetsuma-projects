

let n1, n2, n3, n4;

//показать число в 10-м и 2-м форматах
function fb(n){
	
	let bs;
	if (n>=0){
		bs = n.toString(2);
	} else {
		bs = (n>>>0).toString(2).padStart(32,'0');
	}
	
	return n.toString() + " (" + bs + ")";
}


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

	
	Number_declaration:	`
		//Все числа имеют один и тот же тип, и занимают 64 бита (double precision)
		
		//способы объявления чисел:
		n1=34.00;
		n1=34;
		n1=123e5;		//экспоненциальный формат
		n1=123e-5;
		//Можно использовать символ _ в качестве разделителя:
		n1=1_000_000_000;

		n1=0o377;     		//octal
		n1=0xFF;     		//hexadecimal
		n1 = 0b11111111;//binary
		
					
		//new Number() - возвращает объект Number, а не примитив
		n1 = new Number("123");
		n1 === 123;
		n1 == 123;
		n1 instanceof Number;
		typeof n1;

		//функция Number - конвертирует значение в число-примитив
		n2 = Number("123");
		n2 === 123;
		n2 instanceof Number;
		typeof n2

		Number(true);
		Number(false);
		Number(new Date());
		Number("10 20");
		
	`,
	
	
	Number_operations:`
	
	//деление
	10/3;
	
	//целочисленного деления нет, можно только так:
	Math.floor(10 / 3);
	
	//деление по модулю
	5 % 2;
	8 % 3;

	
	//возведение в степень
	2 ** 4;
	4 ** (1/2);

	//сравнение
	//При сравнении значений разных типов, используется числовое преобразование. Оно применяется к обоим значениям.
	
	'2' > 1;
	'2' < 7;
	'01' == 1;
	false == 0;
	true == 1;
	'' == false;

	//Значение undefined нельзя сравнивать (при преобразовании в число - undefined становится NaN):
	undefined > 0;
	undefined < 0;
	undefined == 0;

	//null при преобразовании в число - превращается в 0, но при сравнении чудит.
	null > 0;
	null == 0;
	null >= 0;

	
	`,
	
	
	Number_bit_operations:`
	# Побитовые операторы
	#   Побитовые операторы интерпретируют операнды как последовательность из 32 битов (нулей и единиц). 
	# Они производят операции, используя двоичное представление числа, и возвращают новую 
	# последовательность из 32 бит (число) в качестве результата.
	#
	
	//чтобы сменить знак числа - нужно инвертировать все биты и добавить 1
	fb(13);
	fb(-13);
	fb(-1);

	//Преобразовать число в бинарный вид можно так:
	n1 = -19;
	
	//бинарный вид со знаком
	(n1).toString(2);
	
	//истинный бинарный вид
	(n1>>>0).toString(2).padStart(32,'0');
	
	//парсинг:
	parseInt("-10011", 2)
	
	//парсинг истинного вида:
	(~ parseInt("11111111111111111111111111101101", 2))+1


	#
	# a & b Побитовое AND;
	# a | b Побитовое OR
	# a ^ b Побитовое XOR
	# ~a 	Побитовое NOT
	#

	fb(7 & 1);
	fb(4 | 8);
	fb(3 ^ 1);
	fb(~ 3);	
	
	#
	# a << b 	Сдвиг влево (умножение на 2^x)
	# a >> b	Знаковый сдвиг вправо (знак сохраняется)
	# a >>> b	Беззнаковый сдвиг вправо (освободившиеся старшие биты всегда заполняются нулями)
	#
		
	//Сдвиг влево 
	1 << 2;
	1 << 3;
	fb(13 << 1);
	fb(-13 << 1);

	//Знаковый сдвиг вправо
	fb(8 >> 2);
	fb(13 >> 2);
	fb(-13 >> 2);
	
	//Беззнаковый сдвиг вправо 
	fb(13 >>> 2);
	fb(-13 >>> 2);
	fb(-1>>>1);
	`,
	
	
	
	Number_format: `

	# Преобразования числа в строку
	#
	# num.toString(base)
	#   возвращает строковое представление числа num в системе счисления base (2-36)
	#
	255..toString();
	255..toString(16);
	255..toString(2);
	255..toString(36);
		
	# Функции на числе можно вызывать тремя способами:
	Number(123).toString();
	(123).toString();
	123..toString();

	//форматирование через функцию String		
	String(Math.PI);
					
	# num.toFixed(digits)
	#   округляет число до заданного числа чисел после точки
	#
	(3.2489).toFixed(2);

	# num.toPrecision(precision)
	#   округляет число до заданного числа чисел
	#
	(0.004).toPrecision(4);
	(60.1234).toPrecision(4);

	# num.toExponential(digits)
	#   округляет число и записывает его в экпоненциальной нотации
	#
	n1 = 9056.65612;
	n1.toExponential(2);
	n1.toExponential(4);
	n1.toExponential(6);
			
			
	# Number.toLocaleString([locales [, options]])
	#   форматирование через локаль и опции. См: Intl.NumberFormat
	#

	Math.PI.toLocaleString(	"ru", {maximumFractionDigits: 3});
	n1.toLocaleString();
	n1.toLocaleString("ru-RU", {style:"percent"})
	n1.toLocaleString("en-US", {style:"currency", currency:"USD"})
	`,		
	
	Number_parse:`
	
	# Парсинг строки в число
	#

	# Number.parseInt(string, radix)
	#   парсит строку и возвращает целое число
	# глобальная функция parseInt(string, radix) - её алиас
	Number.parseInt("10");
	Number.parseInt("10.00");
	Number.parseInt("10.33");
	Number.parseInt("34 45 66");
	Number.parseInt(" 60 ");
	Number.parseInt("40 years");
	Number.parseInt("He was 40");

	parseInt("10", 10);
	parseInt("010");
	parseInt("10", 8);
	parseInt("0x10");
	parseInt("10", 16);

	parseInt("11000", 2);
	
	//Number.parseFloat(string) - парсит число с плавающей точкой
	//глобальная функция parseFloat(string) - её алиас
	Number.parseFloat(10);
	Number.parseFloat("10");
	Number.parseFloat("10.33");
	Number.parseFloat("34 45 66");
	Number.parseFloat("He was 40");
	parseFloat("40.00");
	parseFloat(" 40 ");
	parseFloat("40 years");
	parseFloat("40H")
	parseFloat("H40");
	Number.parseFloat("нечисло");

	//преобразование строки в число унарным плюсом (лаконичный способ)
	testString2 = "22";
	n1 = +testString2;
	+"3.14";
	+"-100"
	+" "
	+"abc"
	
	
	`,

	Number_nan_infinity: `
	//Специальное числовое значение: NaN (ошибка вычислений).
	Number.parseFloat("нечисло");
	Number.NaN;
	NaN;
	NaN==NaN;
	0/0;
	Number.isNaN(0/0);
	Number.isNaN(Infinity);

	//Специальное числовое значение: Infinity (бесконечность)
	1/0;
	-1/0;
	1e500;
	Infinity;
	Infinity+5;
	Infinity > 12345;
	Number.POSITIVE_INFINITY;
	Number.NEGATIVE_INFINITY;

	Number.isFinite(1/0);
	Number.isFinite(NaN);

	//проверка на корректное числовое значение
	n1=12.34;
	!isNaN(parseFloat(n1)) && isFinite(n1);
	`,
	
	

		
	Number_misc: `
		//Неточные вычисления
		0.1 + 0.2;
		9999999999999999;
		
		//Причина в том, что число 0.1 в двоичной системе счисления - бесконечная дробь, 
		//Двоичное значение бесконечных дробей хранится только до 15 знака 
		(0.1).toFixed(20);
		//Чтобы отсечь ошибку, достаточно округления до 10-го знака
		+(0.1).toFixed(10);
		
		//difference between the smallest floating point number greater than 1 and 1
		Number.EPSILON;

		//числа за пределами этих пределов - теряют в точности
		Number.MAX_SAFE_INTEGER;
		Number.MIN_SAFE_INTEGER;								
		
						
		//самое большое и малое возможно число в js
		Number.MAX_VALUE;
		Number.MIN_VALUE;
		
		//Number.isInteger() - returns true if a value is an integer of the datatype Number.
		Number.isInteger(4-2);
		Number.isInteger(4/2);
		Number.isInteger(5-2);
		Number.isInteger(5/2);
	`,	
		
	BigInt: `
		//Способы создания BigInt
		n1 = 9007199254740995n;
		n2 = BigInt("9007199254740995");
		
		typeof n1;
		
		//можно записать в 2, 8, 16-ричных нотациях
		n1 = 256n;
		n2 = 0o400n;
		n3 = 0x100n;
		n4 = 0b100000000n;		
		
		
		//BigInt supports standard JavaScript arithmetic operators.
		n1*n2;
		
		//арифметические операции между BigInt и Number не разрешены
		//n1*5;

		//для них нужно конвертировать BigInt в Number
		Number(n1)*5;
				
		//поддерживают битовые операции
		n1 = 5n; // 0101
		n2 = 3n; // 0011

		(n1 & n2); //0001
		(n1 | n2); //0111
		(n1 ^ n2); //0110
		(~n1);     //1010
		
		n1 = 10n; // binary: 1010
		(n1 << 2n); //101000
		(n1 >> 1n); //0101		
		
		
	`,	
		
	Math: `
		
		//константы
		Math.E;
		Math.PI;
		Math.SQRT2;
			
		//округление
		Math.round(4.9);
		Math.round(4.2);
		
		//округление вверх
		Math.ceil(4.9);
		Math.ceil(4.2);
		Math.ceil(-4.2);		
	
		//округление вниз
		Math.floor(4.9);
		Math.floor(4.2);
		Math.floor(-4.2);		
		
		//получение целой части числа
		Math.trunc(4.9);
		Math.trunc(4.2);
		Math.trunc(-4.2);		
		
		//округление до заданного знака
		Math.round(Math.PI*100)/100;
	
		//Math.random() - Возвращает случайное число от 0 до 1 (не включая 1)
		Math.random();
		Math.floor(Math.random()*11);	//число до 11
		
	
		//E^x
		Math.exp(3);
			
	
		Math.sin(0);
		Math.sin(Math.PI/6);
		Math.sin(Math.PI/2);
		Math.sin(Math.PI);	
		
		Math.cos(0);
		Math.cos(Math.PI/6);
		Math.cos(Math.PI/2);
		Math.cos(Math.PI);	
	
		
		//Math.pow(x, y) returns x^y
		Math.pow(0, 1);
		Math.pow(3, 2);
		Math.pow(-3, 3);

		Math.sqrt(64);
		
		//положительное значение
		Math.abs(-4.7);
		
		Math.min(0, 150, 30, 20, -8, -200);
		Math.max(0, 150, 30, 20, -8, -200);
		
		//натуральный логарифм
		Math.log(Math.E);
		Math.log(10);
		Math.log(20);
		
		//десятичный логарифм
		Math.log10(2);
		Math.log10(10);
		Math.log10(100);
		
		//логарифм по заданному основанию
		Math.log(8)/Math.log(2);  //по основанию 2
		Math.log(90)/Math.log(3); //по основанию 3
	`,
	
}



$(() => {

	autoscrollLog2 = false;
	
	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP,
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		selectedOption: "Number_bit_operations",
		lfMode: true,
		autoscrollLog2: false,
		initFunction(){
			
		},
	});		
	
	
});

