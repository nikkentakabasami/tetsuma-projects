
let ob1, event1, ext1, ext2, projection1;

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

		
		/*
		unByKey(key)
		  удаляет обработчик по ключу, который получается через on()
		*/
		
		
		
		
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

		log2("unByKey");
		
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
    ob1.set("p1", "hello");


  },




}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
//    selectedOption: "Projection",
    debugMode: false,
    logObjectsAsJson: true,
		customFormatter: formatCoord,
    initFunction: () => {
    }

  };
}
