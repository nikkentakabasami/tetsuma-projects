import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import { selectorsData1 } from "./1006-selectors.js"
import { DebugInfoControl, CurrentZoomControl } from "./ol-controls2.js"



let olDemo;



class MyOLDemo extends old.OLDemo {


  scaleLine;

  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }


  createControls() {

    let fs = new ol.control.FullScreen();

    return ol.control.defaults.defaults({
      attribution: false,
      rotate: true,
      zoom: false
    }).extend([fs]);
  }


  initMap() {
    super.initMap();
    olu.addShowCoordHandler(this.map);
  }





}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "MousePosition",
    autoscrollLog1: false,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
    afterSelectChange: () => {
      if (testControl) {
        map.removeControl(testControl);
        testControl = null;
      }

      //сносим все контролы
      //			map.getControls().clear()
    },


  };
}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
    debug: true,
    withDefaultControls: false,
  });

  olDemo.initMap();

}








