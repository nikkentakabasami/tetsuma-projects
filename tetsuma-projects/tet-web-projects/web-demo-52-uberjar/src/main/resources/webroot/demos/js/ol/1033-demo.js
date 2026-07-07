import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";


/**
 * Примеры создания векторных слоёв.
 */


let olDemo;

//объявляем глобальные переменные
"testLayer,testSource".split(",").forEach(name => window[name] = null);


let selectorsData1 = {


  layer1() {
    /*
    ol.format.GeoJSON
    */


    testSource = new ol.source.Vector({
      url: 'misc/switzerland.geojson',
      format: new ol.format.GeoJSON(),
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      background: 'rgba(255, 179, 179, 0.1)',
      style: old.defaultVectorStyle
    });

    map.addLayer(testLayer);

  },




  TopoJSON() {
    /*
    */


    testSource = new ol.source.Vector({
      url: '../data/world-110m.json',
      format: new ol.format.TopoJSON({
        layers: ['countries'],
      }),
			overlaps: false,
    });



        testLayer = new ol.layer.Vector({
          source: testSource,
          //style: old.defaultVectorStyle,
					style: {
					    'stroke-color': 'red',
					    'stroke-width': 2,
					  },					
        });

    map.addLayer(testLayer);

  },





  layer2() {
    /*
    ol.format.GeoJSON
    */
    let features = new ol.format.GeoJSON().readFeatures(demodata.demoGeojsonObject1);

    testSource = new ol.source.Vector({
      features: features
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: old.defaultVectorStyle
    });
    map.addLayer(testLayer);
  },


  layer3() {
    /*
    simple vector
    */

    testSource = new ol.source.Vector({
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: old.defaultVectorStyle
    });
    map.addLayer(testLayer);

    let features = Object.values(demodata.testFeatures);
    testSource.addFeatures(features);


  },




  KML() {
    /*
    ol.format.KML
    */


    testSource = new ol.source.Vector({
      url: '../data/2012_Earthquakes_Mag5.kml',
      format: new ol.format.KML({
        extractStyles: false,
      }),
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: EarthquakesStyleFunction
    });

    map.addLayer(testLayer);

    log(EarthquakesStyleFunction);

  },


  Heatmap() {
    /*
    ol.layer.Heatmap
    прорисовывает векторные данные в виде карты температур.
  	
    */

    testSource = new ol.source.Vector({
      url: '../data/2012_Earthquakes_Mag5.kml',
      format: new ol.format.KML({
        extractStyles: false,
      }),
    });

    testLayer = new ol.layer.Heatmap({
      source: testSource,
      blur: 15,
      radius: 15,
      weight: function(feature) {
        const name = feature.get('name');
        const magnitude = parseFloat(name.substr(2));
        return magnitude - 5;
      },
    });

    map.addLayer(testLayer);

  },






}



function EarthquakesStyleFunction(feature, resolution) {


  const name = feature.get('name');
  const magnitude = parseFloat(name.substr(2));
  const radius = 5 + 20 * (magnitude - 5);

  let style = new ol.style.Style({

    image: new ol.style.RegularShape({
      radius: radius,
      radius2: 8,
      points: 5,
      angle: Math.PI,
      fill: new ol.style.Fill({
        color: 'rgba(255, 153, 0, 0.8)',
      }),
      stroke: new ol.style.Stroke({
        color: 'rgba(255, 204, 0, 0.2)',
        width: 1,
      }),
    }),

    text: new ol.style.Text({
      text: magnitude.toString(),
      fill: new ol.style.Fill({
        color: '#fff',
      }),
      stroke: new ol.style.Stroke({
        color: 'rgba(0, 0, 0, 0.6)',
        width: 3,
      }),
    }),
  });

  return style;
}







class MyOLDemo extends old.OLDemo {

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();
    olu.addShowCoordHandler(this.map);
  }


}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: false,
    withTileLayer: true,
    debug: true
  });

  olDemo.initMap();


}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    selectedOption: "TopoJSON",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    initFunction: initMap,
    beforeExec: () => {
    },
    afterSelectChange: () => {
      if (testLayer) {
        map.removeLayer(testLayer);
        testLayer = null;
      }

    }
  };
}






