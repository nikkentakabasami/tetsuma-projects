
let selectorsData1 = {


  t1() {
  },
  t2() {
  },
  t3() {
  },



}












function initVectorLayer() {

  vectorSource = new ol.source.Vector({
    features: new ol.format.GeoJSON().readFeatures(demoGeojsonObject),
//		features: new ol.format.GeoJSON().readFeatures(testData),
		
				
		
  });

  vectorLayer = new ol.layer.Vector({
    source: vectorSource,
    //    background: '#1a2b39',
    style: defaultStyleFunction
  });


}







function getBriefDemoOptions() {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
  };
}




function initMap() {

  tileLayer = new ol.layer.Tile({
    source: new ol.source.OSM(),
  });


  initVectorLayer();

  const select = new ol.interaction.Select({
    style: defaultSelectStyleFunction,
  });



  mapView = new ol.View({
    center: [0, 0],
    zoom: 1,
  });

  map = new ol.Map({
    target: 'map',
    layers: [
      tileLayer,
      vectorLayer,
    ],
    view: mapView,
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
      shiftDragZoom: false,
      pinchRotate: false
    }).extend([select]),

  });


  //При двойном клике - показываем координаты
  map.on('dblclick', function(evt) {
    let coord = map.getCoordinateFromPixel(evt.pixel);
    coord = [Math.round(coord[0]), Math.round(coord[1])];
    logCoord(coord);
  });









}






