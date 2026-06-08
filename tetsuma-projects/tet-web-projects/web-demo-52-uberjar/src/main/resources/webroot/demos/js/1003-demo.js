
import * as old from './ol-demo-base.js';
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


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });

  olDemo.initMap();
	
	log(RotateNorthControl);
	

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



