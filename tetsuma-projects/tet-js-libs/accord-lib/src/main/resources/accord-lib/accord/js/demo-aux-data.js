/**
 * Вспомогательные данные, строки, объекты, функции.
 * 
 */

//для выделения элементов
let boldTag = "<b></b>";
let testHtmlSnippet = "<b>appended text</b>";
let greenBorderDivSnippet = '<div class="green-border"></div>';


let testUrl = "http://api.github.com/zen";
let testUrl2 = "http://api.github.com/zen?p1=777&p2=888#4";

let testString1 = "  Seishun tte a to iu ma to koto.\t";

let namesString = 'Маша, Петя, Марина, Василий';

let jsonString1 = '{ "name": "Вася", "age": 35, "isAdmin": false, "friends": [10,11,12,13] }';


let testArray1 = ["Яблоко", "Апельсин", "Слива", "Груша", "Финик", "Вишня"];
let testArray2 = new Array(11, 3, 5, 2, 7, 9, 13, 3, 33);
let testArray3 = Array.from({ length: 9 }, (el, index) => index);	// [0, 1, 2, 3...]





let testMap1 = new Map([
  [1, "a"],
  [2, "b"],
  [3, "c"],
]);



let testObject1 = {
    id: 123,
    name: "bob",
	age: 30,
	active: true,
}

let karma = Symbol("karma");
testObject1[karma] = "lawful evil";


let testEvent1 = {
  title: "Конференция",
  date: "сегодня",
  user: {
	id: 50,
	name: "Patrik"
  },
  id: 123
};

const particleObject = {
  name: "electron",
  size: 345,
  hasWeight: true,
};



function heavyTask() {
    let result = 0;
    for (let i = 0;i < 1000000;i++) {
        result += Math.sqrt(i);
    }
    return result;
}
