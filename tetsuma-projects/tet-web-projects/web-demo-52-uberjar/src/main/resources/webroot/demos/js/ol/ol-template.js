import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import { DebugInfoControl, CurrentZoomControl } from "./ol-controls2.js"


/*
Шаблон-пример создания карты с тайловым и векторным слоями, всеми контролами и интеракшенами.
*/

export let map;

//controls
export let scaleLine;
export let debugInfoControl;
export let currentZoomControl;

export let select, dragBox, modify, draw, snap;
let counter = 1;

let vectorLayer, vectorSource;



export function destroyUniversalMap(){
	$("#map").empty();
	map = null;
}




export function createSimpleMap() {
	destroyUniversalMap();

	//тайловый слой
	let tileSource = new ol.source.OSM();

	let tileLayer = new ol.layer.Tile({
	  source: tileSource
	});

	map = new ol.Map({
	  target: 'map',
	  layers: [tileLayer],
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




export function createUniversalMap() {
	destroyUniversalMap();

  //тайловый слой
  let tileSource = new ol.source.OSM();

  let tileLayer = new ol.layer.Tile({
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


  let mapView = new ol.View({
    center: [705_191, 5_781_298],
    zoom: 5,
  });


  let defaultInteractions = ol.interaction.defaults.defaults({
    doubleClickZoom: false,
    shiftDragZoom: false,
    pinchRotate: false
  });


  //шкала, показывающая текущий маштаб
  scaleLine = new ol.control.ScaleLine({});

  //кнопка полноэкранного режима
  let fs = new ol.control.FullScreen();

  let defaultControls = ol.control.defaults.defaults({
    attribution: false,
    rotate: true,
    zoom: false
  }).extend([scaleLine, fs]);

  map = new ol.Map({
    target: 'map',
    layers: [tileLayer, vectorLayer],
    view: mapView,
    interactions: defaultInteractions,
    controls: defaultControls,
  });

  addAllControls();
  addVectorInteractions();
  addModifyInteraction();
  //	addDrawInteraction("Polygon");

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);




}

export function addAllControls() {

  //панель для показа отладочной информации
  debugInfoControl = new DebugInfoControl();
  map.addControl(debugInfoControl);
  debugInfoControl.addShowBaseDebugInfoHandler();

  //Аналог Zoom. Но содержит панель для показа текущего зума
  currentZoomControl = new CurrentZoomControl();
  map.addControl(currentZoomControl);
  currentZoomControl.init();

}


export function addVectorInteractions() {

  select = new ol.interaction.Select({
    //можно выбирать несколько фич
    multi: true,
    //можно выделять все фичи
    filter: function(feature) {
      return true;
    },
    //стиль с подкраской красным
    style: function(feature) {
      return old.defaultSelectStyle;
    },
  });
  //чтобы удобнее выделять
  select.setHitTolerance(5);

  select.on("select", e => {
    console.log("selected:", e.selected.length);
  });
  map.addInteraction(select);



  dragBox = new ol.interaction.DragBox({
    //рисовать область только если нажат Ctrl
    condition: ol.events.condition.platformModifierKeyOnly,
  });

  dragBox.on('boxend', e => {
    const boxExtent = dragBox.getGeometry().getExtent();
    const boxFeatures = vectorSource.getFeaturesInExtent(boxExtent);

    //выделяем все фичи в области
    boxFeatures.forEach((feature) => {
      select.selectFeature(feature);
    });

  });
  dragBox.on('boxstart', () => {
    select.clearSelection();
  });
  map.addInteraction(dragBox);


  //организует прилипание новых точек к существующим
  snap = new ol.interaction.Snap({ source: vectorSource });
  map.addInteraction(snap);






}




export function addModifyInteraction() {

  modify = new ol.interaction.Modify({
    features: select.getFeatures(),
    deleteCondition: event => {
      //удалять вершины при нажатии shift+click
      return ol.events.condition.shiftKeyOnly(event) && ol.events.condition.singleClick(event);
    },
    insertVertexCondition: event => {
      //не добавлять новые вершины
      return ol.events.condition.never(event);
    },


  });


  map.addInteraction(modify);

}

export function addDrawInteraction(type) {

  draw = new ol.interaction.Draw({
    source: vectorSource,
		//LineString,Polygon,Circle,Point		
    type: type,
    //рисовать только при нажатом Ctrl
    condition: ol.events.condition.platformModifierKeyOnly,
  });
  draw.on("drawend", e => {
    //Задаём id для созданных фич
    e.feature.setId(counter++);
  });
  map.addInteraction(draw);


}



