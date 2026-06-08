import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

//import { selectorsData1 } from "./1001-selectors-data.js";


export let olDemo;
let counter = 1;

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}

function initMap() {
  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });
  olDemo.initMap();
}






export class MyOLDemo extends old.OLDemo {

  draw;

  createVectorSource() {
		olDemo.vectorSource = new ol.source.Vector({
		});
		
//    olu.createDemoVectorSource1(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 2,
      constrainRotation: 16,
    });
  }

  initMap() {
    super.initMap();


    this.addDrawInteraction("Point");
//		olu.addSelectInteractions(this);


  }

  removeDrawInteraction() {
    if (this.draw) {
      this.map.removeInteraction(this.draw);
      this.map.removeInteraction(this.snap);
      this.draw = null;
      this.snap = null;
    }
  }

  addDrawInteraction(type) {
    this.removeDrawInteraction();
    this.draw = new ol.interaction.Draw({
      source: this.vectorSource,
      type: type,
			//рисовать только при нажатом Ctrl
			condition: ol.events.condition.platformModifierKeyOnly,
    });
		this.draw.on("drawend",e=>{
			//Задаём id для созданных фич
			e.feature.setId(counter++);
		});
		
		
    this.map.addInteraction(this.draw);

    //организует прилипание новых точек к существующим, при рисовании
    this.snap = new ol.interaction.Snap({ source: this.vectorSource });
    this.map.addInteraction(this.snap);


  }



}



let selectorsData1 = {

  t1() {
    /*
    ol.interaction.Draw
      Умеет создавать новые фичи

		Параметры:
    type
      Тип рисуемых фич ('Point', 'LineString', 'Polygon', 'MultiPoint', 'MultiLineString', 'MultiPolygon' or 'Circle').

    features
      Коллекция, в которую будут складываться новые фичи.

    source
      Источник, в который будут закидываться новые фичи.

    geometryFunction
      Вызывается при изменении координат фичи.
    Может использоваться чтобы задать свои координаты.
		
		
		maxPoints 	
		minPoints
		
		style
		  стиль фич рисования.
			
			
		condition

		События:
		
		drawstart
		drawend
				
			
    */

		log(olDemo.addDrawInteraction);
  },
  t2() {

  },
  t3() {

  },




  setPoint() {
    olDemo.addDrawInteraction("Point");
  },
  setLine() {
    olDemo.addDrawInteraction("LineString");
  },
  setPolygon() {
    olDemo.addDrawInteraction("Polygon");
  },
  setCircle() {
    olDemo.addDrawInteraction("Circle");
  },
}


