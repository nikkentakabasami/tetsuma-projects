import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import { DistanceMeasure } from "./1008-measure.js";
import { DistanceMeasureInteraction } from "./1008-measure2.js";




let olDemo;


let selectorsData1 = {
  t1() {

    //DistanceMeasure - кастомный компонент для удобного измерения расстояний
    olDemo.measure.setActive(true);
    log(DistanceMeasure);

  },
  t2() {
    olDemo.measure.setActive(false);
    log(DistanceMeasure);
  },

  measureInteraction1() {
		olDemo.measureInteraction = new DistanceMeasureInteraction();
		olDemo.map.addInteraction(olDemo.measureInteraction);
  },

	measureInteraction2() {
		olDemoGlobal.measureInteraction.setActive(false);
	},
	measureInteraction3() {
		olDemoGlobal.measureInteraction.setActive(true);
	},
	measureInteraction4() {
		olDemo.map.removeInteraction(olDemo.measureInteraction);
	},



}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    selectedOption: "measureInteraction1",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}

class MyOLDemo extends old.OLDemo {

  measure;
  measureInteraction;


  createView() {
    this.mapView = new ol.View({
      center: [871_399, 5_630_237],
      zoom: 5,
    });
  }

  initMap() {
    super.initMap();


    this.measure = new DistanceMeasure(this.map);
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








