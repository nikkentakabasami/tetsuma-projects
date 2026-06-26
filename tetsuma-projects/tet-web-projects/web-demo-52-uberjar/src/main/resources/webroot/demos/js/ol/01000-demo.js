import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

let olDemo;

//объявляем глобальные переменные
"f1,f2,f3,f4".split(",").forEach(name => window[name] = null);

let selectorsData1 = {
  t1() {
    /*
    */
  },
  t2() {
    /*
    */
  },
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
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    initFunction: initMap,
    beforeExec: () => {
    },
		afterSelectChange: () => {
		},
  };
}





