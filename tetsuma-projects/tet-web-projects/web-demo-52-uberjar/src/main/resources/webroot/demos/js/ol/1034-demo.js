import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import { DebugInfoControl } from "./ol-controls2.js"


//объявляем глобальные переменные
"testLayer,map,vectorLayer,vectorSource,tileLayer,tileSource,selectedFeature,debugInfoControl".split(",").forEach(name => window[name] = null);



let circleImage = new ol.style.Circle({
  radius: 5,
  fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
  stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
});

//стиль для полигона
let selStyle = new ol.style.Style({
  stroke: new ol.style.Stroke({
    color: 'rgb(0, 200, 0)',
    width: 3,
  }),
  fill: new ol.style.Fill({
    color: 'rgba(0, 200, 0, 0.2)',
  }),
  image: circleImage,
});



let selectorsData1 = {


  map_options: `
/*
ol.Map
главный объект карты

---Опции---

controls
interactions
layers
overlays

target	Element | string
  Контейнер для карты

view
  Вид (координаты, зум)


*/

log(createMap1);

`,
  map_events: `
/*
ol.Map

---События---

moveend
  Окончания зуминга, скроллинга..

pointermove
  Перемещение курсора

precompose 
postcompose
  до и после прорисовки.
  Можно получить контекст канвы, дорисовать что нужно.

click
dblclick

loadend
  Подгрузились доп. данные карты. 

change:layerGroup
change:size
postrender

*/
`,

  map_events_sample1() {
    map.on('pointermove', event => {
      if (event.dragging) {
        return;
      }

      //положение мыши на карте в пикселях (относительно левого верхнего угла)
      //let pixel = map.getEventPixel(event.originalEvent);
      let pixel = event.pixel;

      let tpFeature = null;
      map.forEachFeatureAtPixel(pixel, function(feature, layer) {
        tpFeature = feature;
      });
      if (selectedFeature != tpFeature) {
        if (selectedFeature) {
          selectedFeature.setStyle(null);
        }
        selectedFeature = tpFeature;

        if (selectedFeature) {
          selectedFeature.setStyle(selStyle);
        }
      }

    });

    map.on('dblclick', event => {
      let pixel = event.pixel;
      let coord = map.getCoordinateFromPixel(pixel);
      log("dblclick, pixel=", pixel, "evt.pixel", pixel, "coord=", coord);
    });

  },

  map_methods1: `
/*
ol.Map

---Методы---

getView()

getSize()
размеры в пикселях

addControl(control)
addInteraction(interaction)
addLayer(layer)
addOverlay(overlay) 

removeControl(control)
removeInteraction(interaction)
removeLayer(layer)
removeOverlay(overlay)


*/
`,

  map_methods2() {

    /*
    ol.Map
  
    ---Методы---
    getEventCoordinate(event)
    getEventPixel(event) 
    getCoordinateFromPixel(pixel)
  	
    */
    map.on('click', event => {
      let pixel = event.pixel;
      let coord1 = map.getCoordinateFromPixel(pixel);
      let coord2 = map.getEventCoordinate(event);

      log("click, pixel=", pixel, "coord1", coord1, "coord2=", coord2);
    });

  },







  checkFeatures() {

    /*

    ---Поиск фич под пикселем (опции одинаковые)---
    	
    hasFeatureAtPixel(pixel, options)
    Есть ли фичи в данной точке

    options.layerFilter
    Функция-фильтр слоёв. 

    options.hitTolerance
    (defaults to 0) 	
      	
    ------	
    getFeaturesAtPixel(pixel, options)
    Возвращает массив фич под пикселем.
  	
    ------	
    forEachFeatureAtPixel(pixel, callback, options)
      Выполняет коллбэк для каждой фичи под заданным пикселем.
      Возвращает значение последнего callback-а.
    Чтобы остановить обнаружение, callback должен вернуть значение.
  	
  	
    */

    map.on('click', event => {

      let hasFeature = map.hasFeatureAtPixel(event.pixel, {
        hitTolerance: 10
      });


      let features = map.getFeaturesAtPixel(event.pixel, {
        hitTolerance: 10
      });

      let feature = map.forEachFeatureAtPixel(event.pixel, function(feature, layer) {
        return feature;
      });

      debugInfoControl.clear();
			debugInfoControl.addLine("hasFeature: " + hasFeature);
			
      let ids = features.map(f => f.getId()).join(",");
      debugInfoControl.addLine("getFeaturesAtPixel: " + ids);
      if (feature) {
        debugInfoControl.addLine("forEachFeatureAtPixel: " + feature.getId());
      }

    });
  },

  forEachFeatureAtPixel() {


  },



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
      center: [845_697, 5_927_579],
      zoom: 5,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  debugInfoControl = new DebugInfoControl();
  map.addControl(debugInfoControl);
  debugInfoControl.addShowBaseDebugInfoHandler();

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);

}
window.createMap1 = createMap;


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "checkFeatures",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    beforeExec: () => {
      createMap();
    },
    afterSelectChange: () => {
      if (debugInfoControl) {
        debugInfoControl.clear();
      }
    },
    initFunction: () => {
      createMap();
    },
  };
}
