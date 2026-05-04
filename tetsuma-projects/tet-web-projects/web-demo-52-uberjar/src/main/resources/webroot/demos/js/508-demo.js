
let d1, d2, m1, m2;


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

	
	
	Date_declaration: `

		//Создание Date
	
		
		d1 = new Date();	//текущая дата
	
		//new Date(year, month, day, hours, minutes, seconds, milliseconds)
		d1 = new Date(2014, 11, 31, 12, 30, 0);	//31.12.2014 12:30:00
		d1 = new Date(2011, 0, 1, 0, 0, 0, 0); 	//01.01.2011  (январь - нулевой месяц)
		d1 = new Date(2011, 0, 1); 				//то же самое
		d1 = new Date(2011, 0, 32); 				//Автоисправление - 1 февраля 2011!
	
		//new Date(milliseconds)
		d1 = new Date(1241777298123);				//milliseconds since 1970/01/01
	
		//new Date(dateString)
		d2 = new Date("May 21, 1958 10:15 AM");
		d2 = new Date("2020-05-12T23:50:21.817Z");		//json формат

		//сравнение дат
		d1>d2;	
			
		//копирование дат
		d2 = new Date(d1);
		d2 = new Date(d1.getTime());
		
		
	
	`,
	
	Date_parts: `
	
		d1 = new Date();
	
		//Получение компонентов даты
		d1.getFullYear();
				
		d1.getMonth();  //Месяц (0..11)
		d1.getDate();	//Число месяца (1..)
		d1.getDay();	//номер дня в неделе (0- воскресенье, 6 - суббота)
		d1.getTime();	//число миллисекунд, прошедших с 1 января 1970
		d1.getHours();
		d1.getMinutes();
		d1.getSeconds();
		d1.getMilliseconds();
				
		d1.getTimezoneOffset();	//Возвращает разницу между местным и UTC-временем, в минутах.
	
		
		//Установка компонентов даты
		d1.setFullYear(2015,0,5);	!
		d1.setMonth(2);	!
		d1.setDate(100);	!
		d1.setMinutes(0);	!
		d1.setSeconds(0);	!
		d1.setMilliseconds(0);	!
		d1;
								
		//setHours(hour [, min, sec, ms])
		d1.setHours(1,2,3,4);	!
		d1;
		
		d1.setTime(1433754206311);	!		
		d1;		
		
	`,

	
		
		
	Date_format: `
		d1 = new Date();

		//Форматирование вручную:
		accordUtils.formatDate;
		
		accordUtils.formatDate(d1);
		
		accordUtils.formatTime;
		
		accordUtils.formatTime(d1);
		
		//Форматирование через moment.js
		moment(d1).format('DD.MM.YYYY, hh:mm:ss');
					
		//Парсинг вручную		
		accordUtils.parseDate;

		accordUtils.parseDate('05.07.2007');	
	
	`,
	
	
	moment_create: `
		//Создание moment
			
		m1 = moment();	//получение текущей даты
				
		//задание даты строкой в формате ISO 8601		
		moment("2013-11-08");
		moment("2013-11");
		moment("2013-312");
		moment("20131108");
		moment("2013");
		moment("2013-11-08 09:30");
		moment("2013-11-08 09:30:26");
		moment("2013-11-08 09:30:26.123");
			
		//задание даты строкой в формате RFC 2822		
		moment("6 Mar 17 21:22 UT");
		moment("6 Mar 2017 21:22:23 GMT");
				
		//задание даты строкой в заданном формате		
		moment("12-25-1995", "MM-DD-YYYY");
		moment("12/25/1995", "MM-DD-YYYY");
		moment("24 12 2019 09:15:00", "DD MM YYYY hh:mm:ss", true);
		moment("19.12.2025, 11:48:52", "DD.MM.YYYY, hh:mm:ss");
		moment("12-25-1995", ["MM-DD-YYYY", "YYYY-MM-DD"]);	//можно задать несколько форматов
				
		//задание даты объектом
		moment({ hour:15, minute:10 });
		moment({ y    :2010, M     :3, d   :5, h    :15, m      :10, s      :3, ms          :123});
		moment({ year :2010, month :3, day :5, hour :15, minute :10, second :3, millisecond :123});
				
		//задание даты массивом в формате [year, month, day, hour, minute, second, millisecond]		
		moment([2010, 1, 14, 15, 25, 50, 125]);
				
		//клонирование		
		moment(m1);
		m1.clone();	
	
	`,
	
	moment_misc: `
		m1 = moment();
		m2 = moment("2013-02-08");
				
		//связь с Date
		moment(new Date());  //задание даты объектом Date
		m1.toDate();	// Получаем объект Date
				
		m1.format();	//показ локального времени
		m1.utc().format();	//переключение в формат UTC (всемирное время)
		m1.format("DD.MM.YYYY, hh:mm:ss");	//форматирование времени своим форматом
		m1.calendar();	//форматирует время так, чтобы показывать его относительно заданной даты
		m1.valueOf();	//вывод миллисекунд прошедших с Unix Epoch
				
		//получение частей даты		
		m1.year();
		m1.month();
		m1.date();
		m1.hour();
		m1.minute();
		m1.second();
		m1.millisecond();
		m1.day();   //dayOfWeek
		m1.dayOfYear();
				
		//задание частей даты
		m1.year(2025).month(2).date(11).hour(2);
		m1
				
		//задание частей даты методом set
		m1.set("year", 2013).set("month", 1);
				
		//модификация даты
		m1.subtract(10, "day").add(1, "year");
				
		//сравнение дат
		moment.max(m1, m2);
		moment.min(m1, m2);
	
	`,
	
}




function formatDateTimeMoment(date) {
	var m = moment(date);
	return m.format('DD.MM.YYYY, hh:mm:ss');
}




$(() => {

		initBriefDemo(	{
			demoType: DT_SELECT_NO_WP,
			workPanelTemplate: 0,
			selectorsData: selectorsData1,
			selectedOption: "Date_2",
			lfMode: true,
			autoscrollLog2: false,
			initFunction(){
				
			},
		});		
	
});






