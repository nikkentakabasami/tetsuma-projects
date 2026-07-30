import { DebugInfoControl, CurrentZoomControl } from "./ol-controls2.js"

import { RotateNorthControl } from './1006-controls.js';
import * as olu from "./ol-demo-utils.js";


//объявляем глобальные переменные
"testControl".split(",").forEach(name => window[name] = null);


export let selectorsData1 = {
  default_controls() {

    /*
    ol.control.defaults.defaults()
      Элементы управления по умолчанию
  	
    attribution: true
    attributionOptions
      показывать инфу по карте в правом нижнем углу
        boolean (defaults to true) 	

    rotate: true
    rotateOptions 	

    zoom: true
    zoomOptions
      кнопки с зумом 	
  	
  	
    */
    log(olDemoGlobal.createControls);


  },
  control() {
    /*
    ol.control.Control
      Видимый виджет с DOM-элементом на экране.
      Основа всех контролов, расширяет BaseObject.

    Опции:
  	
    element
      HTMLElement
    	
    render
      функция, которая вызывается когда контрол должен быть перерисован. 	

    target
      куда добавить element, если не на viewport
    	
    Методы:
  	
    getMap()	
  	

    Стандартные контролы:
  	
    Attribution
      ссылка на источник текущего тайлового слоя
  	
    ScaleLine
      линейка с текущим маштабом
  	
    FullScreen
      кнопка для перехода в полноэкранный режим

    OverviewMap
      миникарта большего маштаба в левом нижнем углу
    	
    Zoom
      кнопки для зума
  	
    */
    log(CurrentZoomControl)

  },

  Attribution() {
    /*
     ol.control.Attribution
       ссылка на источник текущего тайлового слоя
     */
    testControl = new ol.control.Attribution({
      collapsible: false,
    });
    map.addControl(testControl);
  },
  OverviewMap() {
    /*
    ol.control.OverviewMap
      миникарта большего маштаба в левом нижнем углу
    */
    testControl = new ol.control.OverviewMap({
      layers: [
        new ol.layer.Tile({
          source: olDemoGlobal.tileSource,
        }),
      ],
    });
    map.addControl(testControl);
  },


  ScaleLine() {
    /*
    ol.control.ScaleLine
      линейка с текущим маштабом.
      По умолчанию показывается в левом нижнем углу.
  	
    Опции:
  	
    bar
      показать полосу, вместо линейки
     	
    className
      свой css класс вместо стандартного ol-scale-line
  	
    minWidth
    (default 64) 	

    maxWidth
  	
    units
        (default 'metric')
    */
    //линейка с текущим маштабом
    testControl = new ol.control.ScaleLine({
      //      units: 'metric',
      bar: false,
      minWidth: 128,
      maxWidth: 256,

    })
    map.addControl(testControl);


  },

  ZoomToExtent() {
    /*
    ol.control.ZoomToExtent
    Кнопка для быстрого перемещения в заданный регион
    */

    testControl = new ol.control.ZoomToExtent({
      extent: [
        4076072,
        7450792,
        4300910,
        7554077
      ],
      label: "М",
      tipLabel: "Переместиться в Москву",
    })

    map.addControl(testControl);

  },
  MousePosition() {
		/*
		ol.control.FullScreen
		  Кнопка для перехода в полноэкранный режим.
		*/
		
    testControl = new ol.control.FullScreen();
    map.addControl(testControl);
  },



  MousePosition() {
    /*
    ol.control.MousePosition
    Показывает координату под курсором мыши.
     
    ---Опции:---
     
    className 	
    coordinateFormat
    функция форматирования 	

    projection
    Проекция показываемых координат. 	

    target
    Свой элемент, для показа координат 	
    */

    testControl = new ol.control.MousePosition({
      coordinateFormat: ol.coordinate.createStringXY(2),
      projection: "EPSG:4326",
      className: "mouse-position",
    });
    map.addControl(testControl);

  },

  MousePosition2() {
    /*
		ol.control.MousePosition
    Кастомный формат.
    */

    testControl = new ol.control.MousePosition({
      coordinateFormat: (coord) => {
        return olu.formatCoord(coord);
      },
      className: "mouse-position",
      //		  className: "control-coordinates ol-unselectable ol-control",
      //		  target: document.querySelector("#map .ol-overlaycontainer-stopevent"),
    });
    map.addControl(testControl);

  },

  DebugInfoControl() {

    /*
    DebugInfoControl
      Кастомный контрол для показа отладочной инфы.
    */

    testControl = new DebugInfoControl();
    map.addControl(testControl);
    testControl.addShowBaseDebugInfoHandler();
    testControl.setLines("hello", "there");
    log(DebugInfoControl)


  },
  CurrentZoomControl() {
    /*
    CurrentZoomControl
      Кастомный аналог Zoom. 
      Но содержит панель для показа текущего зума
    */
    testControl = new CurrentZoomControl();
    map.addControl(testControl);
    testControl.init();

    log(CurrentZoomControl)


  },

  custom_control() {

    //custom control
    testControl = new RotateNorthControl();
    olDemoGlobal.map.addControl(testControl);

    log(testControl);

  },


}

