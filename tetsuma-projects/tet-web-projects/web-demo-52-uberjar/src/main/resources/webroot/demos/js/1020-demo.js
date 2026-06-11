
let ob1, event1, ext1, ext2,projection1;

let selectorsData1 = {


  event: `
/*
ol.events.Event
  Основа для событий.
	Впрочем событиями могут быть и обычные строки.

type
target

preventDefault()
stopPropagation() 

*/

event1 = new ol.events.Event("et1");

event1.type

event1.target;

`,

		
		
  Observable: `
/*
ol.Observable
  Объект с событиями.

revision_	
  Версия объекта. Внутренний атрибут-счётчик.
	При каждой модификации объекта (вызове changed()), версия возрастает.

getRevision()
  Возвращает revision_.
		
changed()
  Increases the revision counter and dispatches a 'change' event.

dispatchEvent(event)
  Кидает событие, вызывает связанные с ним обработчики.
	event - BaseEvent | string

on(type, listener)
  Задание обработчиков событий.
	
once(type, listener)
  Задание обработчика, который сработает однократно

un(type, listener)
  Убирает обработчики.

*/

ob1 = new ol.Observable(); !

ob1.revision_

//увеличивает версию
ob1.changed();
ob1.getRevision();	

	
	`,



  Observable2() {

    ob1 = new ol.Observable();
    ob1.on("change", event => {
      log2("catched event:", event.type);
    });

    ob1.on(["et1", "et2"], event => {
      log2("catched event:", event.type);
    });
		ob1.once("et1", event => {
		  log2("catched once:", event.type);
		});
		
    ob1.changed();

    ob1.dispatchEvent("et1");
		ob1.dispatchEvent("et1");
		ob1.dispatchEvent("et2");


  },






  Object1: `
/*
ol.Object
  Основа всех объектов в OL (Collection, Feature, Control...).
	расширяет ol.Observable.
	Добавляет возможность задавать properties.


События:
propertychange	

Методы:
	
get(key)
getKeys() Array.<string>
getProperties() Object.<string, *>

set(key, value, opt_silent)
setProperties(values, opt_silent)
unset(key, opt_silent)
  Атрибуты объекта

*/
	
ob1 = new ol.Object(); !

//генерирует уникальный id для объекта
ol.util.getUid(ob1);

ob1.set("p1","hello");
ob1.set("p2",747);

ob1.getProperties();

ob1.get("p1");

	`,

  Object2() {
		ob1 = new ol.Object();
		
		//кидается при изменении property
		ob1.on("propertychange", event => {
		  log2("propertychange:", event.key);
		});
		ob1.set("p1","hello");


  },
	
	Extent1: `
/*
Extent
  Прямоугольный регион на карте.
  Массив из 4-х чисел: 2 склеенные координаты.
	[minx, miny, maxx, maxy]. Ось y направлена вверх!

ol.extent
  функции для работы с экстентами.

--Создание экстентов

createEmpty()
  Пустой экстент (содержащий в ячейках Infinity)

boundingExtent(coordArray)
  экстент, включающий в себя все заданные координаты.

getBottomLeft(extent)
getBottomRight(extent)
getTopLeft(extent)
getTopRight(extent)
getCenter(extent)
  координаты

getSize(ext1)
  Возвращает [width, height]

getHeight(extent)
getWidth(extent)	
  Размеры
	
ol.extent.getArea(ext1)
  площадь	
*/

//экстент
ext1 = [100,100, 200, 500]; !

//пустой экстент
ext2 = ol.extent.isEmpty([Infinity,Infinity,-Infinity,-Infinity])

//или его можно создать так
ext3 = ol.extent.createEmpty();

//экстент, включающий в себя все заданные координаты.
ol.extent.boundingExtent([[10,20],[100,5],[30,30]])

//координаты
ol.extent.getTopRight(ext1)
ol.extent.getBottomRight(ext1)
ol.extent.getCenter(ext1)

//размеры
ol.extent.getSize(ext1)
ol.extent.getHeight(ext1)
ol.extent.getWidth(ext1)
ol.extent.getArea(ext1)
	`,
	Extent2: `
/*
extend(extent1, extent2, opt_extent)
  объединение

getIntersection(extent1, extent2, opt_extent)
  пересечение	
	
containsCoordinate(extent, coordinate)
containsExtent(extent1, extent2)
containsXY(extent, x, y)
intersects(extent1, extent2)
equals(extent1, extent2)
isEmpty(extent)	
  Проверки
	
extend(extent1, extent2)
  записывает в extent1 объединение двух экстентов 
*/

ext1 = [100,100, 200, 200]; !
ext2 = [10,150, 300, 160]; !

//объединение двух экстентов
ol.extent.extend(ext1, ext2);

//пересечение
ol.extent.getIntersection(ext1, ext2);

//пустое пересечение
ext3 = ol.extent.getIntersection(ext1, [1,1,10,10]);
ol.extent.isEmpty(ext3);

//проверки
ol.extent.intersects(ext1, ext2);
ol.extent.isEmpty([Infinity,Infinity,-Infinity,-Infinity]);
ol.extent.containsCoordinate(ext1, [120,120]);
	`,	
	
	
	Projection: `
	/*
ol.proj.Projection
  как правило этот объект не надо создавать явно.
	Обычно он задаётся/получается строковым кодом.
	
опции:
code 	string 	
units 	
extent

Методы:

getCode()
getExtent()
  валидный экстент

getMetersPerUnit()
getUnits()
getWorldExtent()
isGlobal()
  покрывает весь мир
*/
	
//Web Mercator (Spherical Mercator, WGS 84 Web Mercator ) (EPSG:3857, EPSG:3785)
//  Система координат, основанная на проецировании земли как идеального шара на цилиндр.
//  Единицы имерения - псевдометры (на экваторе равен реальному метру)
//Самая распространённая система, используется по умолчанию
projection1 = ol.proj.get("EPSG:3857"); !


projection1.getCode();
projection1.getExtent();

projection1.getMetersPerUnit();
projection1.getUnits();
projection1.getWorldExtent();
projection1.isGlobal();

//World Geodetic System (WGS 84, EPSG:4326)
//  Единицы имерения - градусы
projection1 = ol.proj.get("EPSG:4326"); !

projection1.getCode();
projection1.getExtent();

projection1.getMetersPerUnit();
projection1.getUnits();
projection1.getWorldExtent();
projection1.isGlobal();
	
	
	`,
	ttt4: `
	`,

	
	
	
	
  test3() {
  },

}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: "Projection",
    debugMode: false,
	  logObjectsAsJson: true,
    initFunction: () => {
    }

  };
}
