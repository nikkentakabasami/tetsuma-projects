import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

let olDemo;

let hlLayer;

//объявляем глобальные переменные
"f1,f2,f3,f4".split(",").forEach(name => window[name] = null);

let selectorsData1 = {
  t1() {
    /*
		Добавим слой для выделений, будем подсвечивать в нём фичи, над которыми курсор.
    */
		
		//слой для выделений
		hlLayer = new ol.layer.Vector({
			map: map,
		  source: new ol.source.Vector(),
		  style: {
		    'stroke-color': 'rgb(255, 153, 51)',
		    'stroke-width': 2,
				'fill-color': 'rgba(255, 153, 51, 0.2)',
		  },
		});
		

    map.on('pointermove', function(evt) {
      if (evt.dragging) {
        return;
      }
      highlightFeature(evt.pixel);
    });

    map.on('click', function(evt) {
      highlightFeature(evt.pixel);
    });

  },
  t2() {
    /*
    */
  },
}



let highlight;
const highlightFeature = function(pixel) {
  vectorLayer.getFeatures(pixel).then(function(features) {
    const feature = features.length ? features[0] : null;

    if (feature !== highlight) {
      if (highlight) {
        hlLayer.getSource().removeFeature(highlight);
      }
      if (feature) {
        hlLayer.getSource().addFeature(feature);
      }
      highlight = feature;
			
			if (feature) {
			  olDemo.debugInfoControl.setLines(feature.getId());
			} else {
			  olDemo.debugInfoControl.clear();
			}
			
			
			
    }
  });
};






class MyOLDemo extends old.OLDemo {

  createVectorSource() {
    olu.createDemoVectorSource2(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [729_891, 5_659_451],
      zoom: 6,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.vectorSource.addFeature(demodata.testFeatures.pointFeature);
    this.vectorSource.addFeature(demodata.testFeatures.lineFeature);

    this.select.on("select", e => {
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





