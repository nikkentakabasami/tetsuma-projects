


import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import { selectorsData1 } from './1005-selectors-data.js'

let olDemo;




window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
    moduleMode: true
  };
}



class MyOLDemo extends old.OLDemo {

  createView() {
    this.mapView = new ol.View({
      center: [877350, 6000000],
      zoom: 5,
      //      projection: 'EPSG:4326',
      projection: 'EPSG:3857',
    });

    this.mapView.on(["change:center", "change:resolution", "change:rotation"], event => {
      console.log(event.type);
    });


  }


  initMap() {
    super.initMap();

    olu.addShowCoordHandler(this);

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




