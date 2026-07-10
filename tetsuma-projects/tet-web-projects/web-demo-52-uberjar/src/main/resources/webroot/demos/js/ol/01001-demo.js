import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as olt from "./ol-template.js";
import { DebugInfoControl } from "./ol-controls2.js"


//объявляем глобальные переменные
"testLayer,map,vectorLayer,vectorSource,tileLayer,tileSource,debugInfoControl".split(",").forEach(name => window[name] = null);


let selectorsData1 = {
  t1() {
    let f1 = new ol.Feature(new ol.geom.Point([640_950, 5_567_518]));
    vectorSource.addFeature(f1);
		debugInfoControl.setLines("added pont!");
		
		
  },
  t2() {
    testLayer = new ol.layer.Tile({
      source: new ol.source.OSM(),
      className: 'bw',  //сделаем стиль чёрно-белым
    });
    map.addLayer(testLayer);
  },
	doc1:`
/*
*/
`,
	doc2:`
/*
*/
`,
	doc3:`
/*
*/
`,
	
}




function destroyMap() {
  $("#map").empty();
  map = null;
  vectorLayer = null;
  vectorSource = null;
}



function createMap() {
  destroyMap();

  //тайловый слой
  tileSource = new ol.source.OSM();

  tileLayer = new ol.layer.Tile({
    source: tileSource
  });

  vectorSource = new ol.source.Vector({
    url: '../data/switzerland.geojson',
    format: new ol.format.GeoJSON(),
  });

  //векторный слой
  vectorLayer = new ol.layer.Vector({
    source: vectorSource,
    style: old.defaultVectorStyle
  });


  map = new ol.Map({
    target: 'map',
    layers: [tileLayer, vectorLayer],
    view: new ol.View({
      center: [845_697, 5_927_579],
      zoom: 5,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);

	debugInfoControl = new DebugInfoControl();
	map.addControl(debugInfoControl);
	debugInfoControl.addShowBaseDebugInfoHandler();


}



window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    beforeExec: () => {
      //      destroyMap();
      //      createMap();
    },
    afterSelectChange: () => {
			if (debugInfoControl){
				debugInfoControl.clear();
			}
      if (testLayer) {
        map.removeLayer(testLayer);
				testLayer.dispose();
        testLayer = null;
      }
    },
    initFunction: () => {
      createMap();
      //olt.createUniversalMap();
    },
  };
}
