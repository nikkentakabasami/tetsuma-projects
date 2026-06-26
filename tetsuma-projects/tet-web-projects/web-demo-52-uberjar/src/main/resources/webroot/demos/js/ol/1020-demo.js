
let ob1, event1, ext1, ext2, projection1, coll1, coll2;


function demoEventListener(event) {
  log2("event:", event.type);
}





let selectorsData1 = {


  Observable: `
/*
ol.Observable
  Основа всех базовых классов. 
	Содержит поддержку событий.

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

unByKey(key)
  удаляет обработчик по ключу, который получается через on()

	
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
      log2("event:", event.type);
    });

    let k1 = ob1.on(["et1", "et2"], event => {
      log2("event:", event.type);
    });
    ob1.once("et1", event => {
      log2("once event:", event.type);
    });

    ob1.changed();

    ob1.dispatchEvent("et1");
    ob1.dispatchEvent("et1");
    ob1.dispatchEvent("et2");

    log2hr();

    //удаляем обработчик
    ol.Observable.unByKey(k1);
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
  Атрибуты объекта

set(key, value, opt_silent)
setProperties(values, opt_silent)
unset(key, opt_silent)

*/
	
ob1 = new ol.Object(); !

//генерирует уникальный id для объекта
ol.util.getUid(ob1);

//properties

ob1.on("propertychange",demoEventListener); !

ob1.set("p1","hello");
ob1.set("p2",747);

ob1.getKeys();

ob1.getProperties();

ob1.get("p1");

`,

  Object2() {
    ob1 = new ol.Object();

    //кидается при изменении property
    ob1.on("propertychange", event => {
      log2("propertychange:", event.key);
    });
    ob1.set("p1", "hello");


  },




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

  condition() {
    /*
    ol.events.condition
      Стандартные функции-фильтры для событий.
      Определяют, при каких событиях будет срабатывать действие.
    	
    always
    never
  
    noModifierKeys
    altKeyOnly
    altShiftKeysOnly
    shiftKeyOnly
    platformModifierKeyOnly
  
    platformModifierKey	- ctrl
  
    pointerMove
     
    primaryAction
    click
    singleClick
    doubleClick
  
    focus
    mouseOnly
      событие мыши    	
    */

    //Пример: рисовать область только если нажат Ctrl	
    let dragBox = new ol.interaction.DragBox({
      condition: ol.events.condition.platformModifierKeyOnly
    });

    let modify = new ol.interaction.Modify({
      deleteCondition: event => {
        //удалять вершины при нажатии shift+click
        return ol.events.condition.shiftKeyOnly(event) && ol.events.condition.singleClick(event);
      },
      insertVertexCondition: event => {
        //не добавлять новые вершины
        return ol.events.condition.never(event);
      },

    });

  },


  Collection: `
/*
ol.Collection
  обёртка вокруг массива.
	Расширяет Object, добавляет события.
	
new Collection(array, options)

options:

unique
  (default false) 	
  Элементы должны быть уникальны, не повторяться.
	
	
События:

add
change:length
remove

on(type, listener)
once(type, listener)
un(type, listener)
dispatchEvent(event)	
  поддержка событий

getArray()
item(index)
getLength()	
  получение данных

Изменение данных:	

push(elem)
  вставить в конец.

insertAt(index, elem)
setAt(index, elem)

extend(arr)
  добавляет элементы

forEach(f)

	
clear()

remove(elem)
removeAt(index)
  
pop()
  удалить из конца

*/

call1 = new ol.Collection(); !

call1.on("add",demoEventListener); !

call1.push(123); !
call1.push("ichi"); !
call1.push("ni"); !

call1.getLength();

call1.item(1);

call1.getArray();

//добавление нескольких элементов
call1.extend([6,7,8]); !


//properties
call1.set("myprop","seven");
call1.getKeys();


`,







}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    //selectedOption: "Collection",
    debugMode: false,
    logObjectsAsJson: true,
    customFormatter: formatCoord,
    initFunction: () => {
    }

  };
}
