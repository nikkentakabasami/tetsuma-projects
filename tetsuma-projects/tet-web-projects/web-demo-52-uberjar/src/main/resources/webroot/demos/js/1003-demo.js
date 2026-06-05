
import * as old from './ol-demo-base2.js';
import * as olu from "./ol-demo-utils.js";

import {RotateNorthControl} from './1003-controls.js';

let olDemo;

let selectorsData1 = {
  t1() {
		//повернуть карту наискосок
		map.getView().setRotation(Math.PI / 2.6);
		
  },
  t2() {
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



  initMap() {
    super.initMap();

  }

  createControls() {
    let rnc = new RotateNorthControl();
    return ol.control.defaults.defaults().extend([rnc]);
  }




}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });

  olDemo.initMap();

}




















/*
let selectorsData1 = {


  t1() {
  	
    //повернуть карту наискосок
    map.getView().setRotation(Math.PI / 2.6);
  },
  t2() {
  },
  t3() {
  },



}




function getBriefDemoOptions() {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
  };
}




function initMap() {

  //	initMapBasic();

  tileLayer = new ol.layer.Tile({
    source: new ol.source.OSM(),
  });

  mapView = new ol.View({
    center: [0, 0],
    zoom: 1,
  });

  let rnc = new RotateNorthControl();

  map = new ol.Map({
    target: 'map',
    layers: [
      tileLayer,
    ],
    view: mapView,
    controls: ol.control.defaults.defaults().extend([rnc]),

  });




}

*/
