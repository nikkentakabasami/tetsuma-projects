/*
Класс OLDemo, позволяющий удобно создавать карту.
*/

import * as olu from "./ol-demo-utils.js";
import { DebugInfoControl, CurrentZoomControl } from "./ol-controls2.js"

import {
  defaultVectorStyle, defaultSelectStyle,
  defaultStyleFunction, defaultSelectStyleFunction
} from './ol-demo-styles.js';


export { OLDemo, defaultSelectStyleFunction, defaultSelectStyle, defaultVectorStyle };



const olDemoDefaultOptions = {
  withVectorLayer: true,
  withTileLayer: true,
  withFeatues: true,
  debug: false,  //показывает контрол с отладочной инфой
  withDefaultControls: true,

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

  debugInfoControl;
  currentZoomControl;

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

    if (this.options.withFeatues) {
      olu.createDemoVectorSource2(this);
    } else {
      this.vectorSource = new ol.source.Vector();
    }


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
  createControls() {
    //шкала, показывающая текущий маштаб
    let scaleLine = new ol.control.ScaleLine({
    });

    return ol.control.defaults.defaults({
      attribution: false,
      rotate: true,
      zoom: false
    }).extend([scaleLine]);
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

    if (this.options.debug) {
      //панель для показа отладочной информации
      this.debugInfoControl = new DebugInfoControl();
      this.map.addControl(this.debugInfoControl);
      this.debugInfoControl.addShowBaseDebugInfoHandler();
    }

    if (this.options.withDefaultControls) {
      //Аналог Zoom. Но содержит панель для показа текущего зума
      this.currentZoomControl = new CurrentZoomControl();
      this.map.addControl(this.currentZoomControl);
      this.currentZoomControl.init();
    }





  }


  //Вспомогательные глобальные переменные для удобства отладки
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




