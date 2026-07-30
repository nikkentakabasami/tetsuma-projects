import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

let olDemo;


let selectorsData1 = {
  ol_desc: `
/*
ol.Overlay
  Элемент для показа над картой.
  Похож на Control, но в отличии от него привязан к географической координате.

Опции:

id 	

element

offset
  сдвиг элемента в пикселях, относительно координаты.
  (default [0, 0]) 	

position
  начальная координата

positioning
  Позиционирование (default 'top-left') 	

stopEvent
  (default true)
  Останавливать передачу событий в map

insertFirst 	
  (default true)

autoPan 	PanIntoViewOptions | boolean (defaults to false) 	

className
  CSS class
	Назначается на оверлей, а не на элемент!

	
Методы: геттеры/сеттеры для опций

getElement()
getPosition()
getPositioning()	
		

*/
`,

  add_overlay1() {
    //добавление ссылки в локацию на карте
    olDemo.addOverlay1();
    log(olDemo.addOverlay1);

    //оверлей можно получить по id		
    let ol = map.getOverlayById("vienna_overlay");
    ol.setPosition([1_786_681, 4_975_265])


  },
  add_overlay2() {
    olDemo.addOverlay2();
    log(olDemo.addOverlay2);
  },
  add_overlay3() {
    olDemo.addOverlay3();
    log(olDemo.addOverlay3);
  },
  add_overlay4() {
    olDemo.addOverlay4();
    log(olDemo.addOverlay4);
  },
}


class MyOLDemo extends old.OLDemo {

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();

    olu.addShowCoordHandler(this.map);

  }

  addOverlay1() {

    let element = $('<a id="vienna" target="_blank" href="./Vienna">Vienna</a>').get(0);


    //показ ссылки
    this.ol1 = new ol.Overlay({
      id: "vienna_overlay",
      position: [2_148_583, 6_126_159],
      element: element
    });
    this.map.addOverlay(this.ol1);

  }

  addOverlay2() {
    //показ координаты при ctrl+click
    let element = $('<div id="ol2"></div>').get(0);

    this.ol2 = new ol.Overlay({
      element: element,
      positioning: 'bottom-center',
      id: "show_wgs_ol"
    });


    this.map.on('click', event => {

      if (!event.originalEvent.ctrlKey) {
        if (this.ol2.getMap()) {
          this.map.removeOverlay(this.ol2);
        }
        return;
      }
      if (!this.ol2.getMap()) {
        this.map.addOverlay(this.ol2);
      }

      var coord = event.coordinate;
      var coordString = ol.coordinate.toStringXY(coord);

      var degrees = ol.proj.toLonLat(coord);
      var hdms = ol.coordinate.toStringHDMS(degrees);

      var element = this.ol2.getElement();
      element.innerHTML = "(" + coordString + ") <br>" + hdms;

      this.ol2.setPosition(coord);
    });

  }


  addOverlay3() {
    //Кружок-пометка на карту
    let element = $('<div id="marker"></div>').get(0);

    this.ol3 = new ol.Overlay({
      position: [2_112_359, 6_849_366],
      className: "marker111",
      element: element
    });

    this.map.addOverlay(this.ol3);
  }


  addOverlay4() {
    //показывает координату при клике.

    let element = $('<div id="fetureTooltipOverlay"></div>').get(0);

    this.ol4 = new ol.Overlay({
      element: element,
      positioning: 'bottom-left',
      stopEvent: false,
      offset: [10, -10]
    });
    this.map.addOverlay(this.ol4);


    this.map.on('click', event => {
      var coord = event.coordinate;
      this.ol4.getElement().innerHTML = ol.coordinate.toStringXY(coord, 2);
      this.ol4.setPosition(coord);
    });
  }


}







function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: false,
    withTileLayer: true,
    debug: true
  });

  olDemo.initMap();


}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "add_overlay2",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}






