


import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import { selectorsData1 } from './1005-selectors-data.js'

let olDemo;




class MyOLDemo extends old.OLDemo {

  createView() {
    this.mapView = new ol.View({
      center: [877350, 6000000],
      zoom: 5,
      //projection: 'EPSG:4326',  //wgs
      projection: 'EPSG:3857',  //web mercator (default)
    });

  }


  initMap() {
    super.initMap();

    olu.addShowCoordHandler(this.map);

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


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "centerOn",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
    moduleMode: true
  };
}



