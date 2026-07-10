import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";
import { selectorsData1 } from './1030-selectors.js'


let olDemo;


class MyOLDemo extends old.OLDemo {



  createVectorLayer() {
    olu.createDemoVectorSource1(this);

    this.vectorLayer = new ol.layer.Vector({
      source: this.vectorSource,
      style: this.getVectorStyle
    });
  }


  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.vectorSource.addFeature(demodata.testFeatures.pointFeature);
    this.vectorSource.addFeature(demodata.testFeatures.lineFeature);

    this.select.on("select", e => {
      clearLog();

      if (e.selected.length) {
        olu.logFeature(e.selected[0]);
      }

    });
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
    //selectedOption: "Tile",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    initFunction: initMap,
    beforeExec: () => {
    },
    afterSelectChange: () => {
			/*
			map.getLayers().clear();
			*/
      if (testLayer) {
				
        map.removeLayer(testLayer);
				testLayer.dispose();
        testLayer = null;
      }

    }
  };
}





