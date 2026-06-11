import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import { selectorsData1 } from "./1006-selectors.js"
import { DebugInfoControl, CurrentZoomControl } from "./ol-controls2.js"



let olDemo;


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: false,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}

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

  initMap() {
    super.initMap();



    //линейка с текущим маштабом
    this.scaleLine = new ol.control.ScaleLine({
      //      units: 'metric',
      bar: false,
      minWidth: 128,
      maxWidth: 256,

    })
    map.addControl(this.scaleLine);

    //ссылка на источник текущего тайлового слоя
    let attribution = new ol.control.Attribution({
      collapsible: false,
    });
    map.addControl(attribution);


		//миникарта большего маштаба в левом нижнем углу
    const overviewMap = new ol.control.OverviewMap({
      layers: [
        new ol.layer.Tile({
          source: this.tileSource,
        }),
      ],
    });
		map.addControl(overviewMap);




  }

	createControls() {
		
		let scaleLine = new ol.control.ScaleLine({
		});
		let fs = new ol.control.FullScreen();
		
	  return ol.control.defaults.defaults({
	    attribution: false,
	    rotate: false,
	    zoom: false
	  }).extend([scaleLine, fs]);
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








