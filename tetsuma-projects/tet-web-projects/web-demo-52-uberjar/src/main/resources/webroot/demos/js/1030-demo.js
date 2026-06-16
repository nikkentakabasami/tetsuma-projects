import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as olt from "./ol-template.js";


let selectorsData1 = {
  t1() {

    let f1 = new ol.Feature(new ol.geom.Point([640_950, 5_567_518]));
    vectorSource.addFeature(f1);


  },
  t2() {

    let l1 = new ol.layer.Image({
      extent: [-13884991, 2870341, -7455066, 6338219],
      source: new ol.source.ImageWMS({
//        url: 'https://ahocevar.com/geoserver/wms',
				url: '../images/topp-states.png',
				
        params: { 'LAYERS': 'topp:states' },
        ratio: 1,
        serverType: 'geoserver',
      }),
    });

    map.addLayer(l1);


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
    beforeExec: () => {
      destroyMap();
      createMap();
    },
    initFunction: () => {
      createMap();
      //olt.createUniversalMap();
    },
  };
}








let map, vectorLayer, vectorSource;


function destroyMap() {
  $("#map").empty();
  map = null;
  vectorLayer = null;
  vectorSource = null;
}



function createMap() {
  destroyMap();

  //тайловый слой
  let tileSource = new ol.source.OSM();

  let tileLayer = new ol.layer.Tile({
    source: tileSource
  });

  vectorSource = new ol.source.Vector({
    url: 'misc/switzerland.geojson',
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
      center: [-11_408_273, 5_479_657],
      zoom: 5,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);



}


