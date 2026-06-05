/*
Основные вспомогательные методы и переменные для демок openlayers.
*/


//export { logCoord } from "./ol-demo-utils.js";

import * as olu from "./ol-demo-utils.js";



import {
  defaultVectorStyle, defaultSelectStyle,
  defaultStyleFunction, defaultSelectStyleFunction
} from './ol-demo-styles2.js';


export { OLDemo, defaultSelectStyleFunction, defaultSelectStyle };



const olDemoDefaultOptions = {
  withVectorLayer: true,
  withTileLayer: true,

}



class OLDemo {

  map;
  mapView;

  tileLayer;
  tileSource;

  vectorLayer;
  vectorSource;
  vectorStyle;

  options;

  constructor(options) {

    this.options = $.extend({}, olDemoDefaultOptions, options);


  }


  //тайловый слой по умолчанию
  createTileLayer() {
    this.tileSource = new ol.source.OSM();

    this.tileLayer = new ol.layer.Tile({
      source: this.tileSource
    });
  }

  //Источник данных для векторного слоя по умолчанию
  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }

  //векторный слой по умолчанию
  createVectorLayer() {

    this.createVectorSource();

    this.vectorLayer = new ol.layer.Vector({
      source: this.vectorSource,
      //background: 'rgba(255, 179, 179, 0.1)',  //можно задать цвет на задний фон
      style: this.getVectorStyle
    });
  }

  //функция, возвращающая стиль для векторного слоя
  getVectorStyle() {
    return defaultVectorStyle;
  }


  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

	//задание interaction при создании карты
  createInteractions() {

    return ol.interaction.defaults.defaults({
      doubleClickZoom: false,
      shiftDragZoom: false,
      pinchRotate: false
    });

  }

	//задание control при создании карты
	createControls(){
		return ol.control.defaults.defaults();
	}
	

  initMap() {

    if (this.options.withVectorLayer) {
      this.createVectorLayer();
    }
    if (this.options.withTileLayer) {
      this.createTileLayer();
    }

    this.createView();


    //добавляем векторный слой, если он создан
    let layers = [];

    if (this.tileLayer) {
      layers.push(this.tileLayer);
    }
    if (this.vectorLayer) {
      layers.push(this.vectorLayer);
    }

    this.map = new ol.Map({
      target: 'map',
      layers: layers,
      view: this.mapView,
      interactions: this.createInteractions(),
			controls: this.createControls(),
    });

    this.initGlobalVars();

  }


  //Вспомогательные переменные для удобства отладки
  initGlobalVars() {

    window.olDemoGlobal = this;

    window.map = this.map;
    window.mapView = this.mapView;

    window.tileLayer = this.tileLayer;
    window.tileSource = this.tileSource;

    window.vectorLayer = this.vectorLayer;
    window.vectorSource = this.vectorSource;
    window.vectorStyle = this.vectorStyle;

  }



}




