import * as old from './ol-demo-base2.js';
import * as olu from "./ol-demo-utils.js";

let olDemo;

let selectorsData1 = {
	t1(){
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
	
  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }
	
	createView() {
	  this.mapView = new ol.View({
	    center: [0, 0],
	    zoom: 1,
	  });
	}

	initMap() {
		super.initMap();
		
	}	
	

}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });

  olDemo.initMap();

}








