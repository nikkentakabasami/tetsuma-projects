import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import {DistanceMeasure} from "./1008-measure.js";


let olDemo;
let measure;


let selectorsData1 = {
	t1(){
		measure.setActive(true);
		
	},
	t2(){
	},
}

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

class MyOLDemo extends old.OLDemo {
	
	createView() {
	  this.mapView = new ol.View({
	    center: [0, 0],
	    zoom: 1,
	  });
	}

	initMap() {
	  super.initMap();


		measure = new DistanceMeasure(this.map);		
		
		
	}
	

}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
		debug: true
  });

  olDemo.initMap();
	

}








