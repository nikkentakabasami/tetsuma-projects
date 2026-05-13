
let r;

let n1, n2, n3, n4;

let selectorsData1 = {

	
	scr1:`
	
	# ?? - Оператор нулевого слияния
	#   аналог nvl - проверка на null и undefined

	r = null ?? "default string";

	@
	let user = "Иван";
	r = user ?? "Аноним";		
	@

	@
	let age = undefined;
	r = age??32;		
	@

	
	`,
	scr2:`

	# оператор запятая
	#   выполняются все команды, возвращается результат последней
	# 
	n1=10,n2=33,n3=n1*n2;
	
	n4 = (n1=10,n2=33,n3=n1*n2);
	
	`,
	scr3:`
	
	# ?. - Опциональная цепочка  
	#   безопасное получение атрибутов и функций.
	#   останавливает вычисление и возвращает undefined, если часть перед ?. имеет значение undefined или null.
	#   нельзя использовать для присваивания значений!

	r = {}; 
	r?.address?.street;
	r?.["some attr"];

	
	//выполнит функцию, если она существует
	r.admin?.();

	r = null;
	r?.address;

	// Удалить r.name, если пользователь существует
	delete r?.name;
	
	`,
		

	s1(){
		
		// Деструктурирующее присваивание
		//  упрощённое создание переменных

		let options = {
		  title: "Menu",
		  width: 100,
		  height: 200
		};

		let user = {
		  id: 123,
		  name: "Bob"
		};
				
		// На основе объектов
		let {title, width, height} = options;
		log2("title, width, height:", title, width, height)
		
		let {width: w, height: h} = options;
		log2nl("w, h:", w, h)
		
		const { PI } = Math
		log2nl("PI:", PI)

		//с заданием значений по умолчанию (используются, если поле в объекте не найдено)
		let {id, name, age=16} = user;
		log2nl("id, name, age:", id, name, age)
				
	},	
	s2(){

		//Деструктуризация массивов
		let arr = [7,12,53]

		let [first, second] = arr;

		log2("first, second:", first, second);
				
		//меняет значения у переменных
		[first, second] = [second, first];

		log2nl("switched. first, second:", first, second);
		
		let [a, b, c] = "hello";

		log2nl("a, b, c:",a, b, c);
				
		
	},	
	s3(){
		//Деструктуризация массивов 2
		let arr = ["Julius", "Caesar", "Consul", "of the Roman Republic"];
		
		//если второй элемент не нужен
		let [firstName, , title] = arr;
		
		log2("firstName,title:",firstName,title);

		//записать оставшиеся элементы в массив
		let [name1, name2, ...rest] = arr;
		log2nl("name1, name2:",name1, name2);
		log2nl("rest:",rest);
		
		//с заданием значений по умолчанию (используются, если поле в объекте не найдено)
		let [name = "Guest", surname = "Anonymous"] = ["Julius"];
		log2nl("name,surname:",name,surname);
		
		
	},	
	s4(){
		
		
	},	
	s5(){
	},	
	

	
	
	
	
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "scr1",
		initFunction: ()=>{
			
		}
	});	
	
});







