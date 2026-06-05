
//координаты, полученные через геолокацию
let geolocationCoord = null;
let homeCoord;
let homeZoom;

let map;

proj4.defs('EPSG:3395', '+proj=merc +lon_0=0 +k=1 +x_0=0 +y_0=0 +datum=WGS84 +units=m +no_defs');
ol.proj.get('EPSG:3395').setExtent([-20037508.342789244, -20037508.342789244, 20037508.342789244, 20037508.342789244]);





$(() => {

  //пытаемся получить текущие координаты через геолокацию
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      geolocationCoord = ol.proj.transform([position.coords.longitude, position.coords.latitude], 'EPSG:4326', 'EPSG:3857');
    });
  }

  homeCoord = ol.proj.transform([52.275, 55.667], 'EPSG:4326', 'EPSG:3857');
  homeZoom = 14;

  startView = new ol.View({
    center: homeCoord,
    zoom: homeZoom,
    minZoom: 5
  });


  map = new ol.Map({
    //    layers : [ lineLayer, handlesLayer, substationsLayer, selectionLayer, auxLayer, printLayer],

    layers: [
      new ol.layer.Tile({
        source: new ol.source.OSM()
      })
    ],


    target: 'map',
    view: startView,
    interactions: ol.interaction.defaults({
      doubleClickZoom: false,
      //      dragPan: false,
      //      shiftDragZoom: false,
      //      pinchRotate: false
    }),

    /*  
  controls: ol.control.defaults().extend(
      [
       new app.CurrentZoomControl(),
       new app.InfoControl(),
       new app.RuControl(),
       new app.TSInfoControl()
       ]),  
       */
  });


  addMapHandlers();


});


let counter = 0;


function addMapHandlers() {

	//# События:
	//# click, dblclick
	//# movestart, moveend
	//# pointermove, pointerdrag
	//# precompose, postcompose, postrender
	
	//# Вспомогательные методы:
	//# let pixel = map.getEventPixel(event.originalEvent);
	//# let coord = map.getCoordinateFromPixel(pixel);
	
	
  //moveend
  //  Окончания зуминга, скроллинга..
  map.on('moveend', event => {
    var newZoom = map.getView().getZoom();
    console.log("moveend. currentZoom:", newZoom);
  });

	
	map.on('dblclick', event => {
		
		//положение мыши на карте в пикселях (относительно левого верхнего угла)
		let pixel = event.pixel;
		
		//или можно получить так
		//let pixel = map.getEventPixel(event.originalEvent);
		
		let coord = map.getCoordinateFromPixel(pixel);
		
	  console.log("dblclick, pixel=", pixel,"evt.pixel",event.pixel,  "coord=",coord);
	});	
	

	
  //pointermove
  //  Перемещение курсора
  map.on('pointermove', event => {
    if (event.dragging) {
      return;
    }
    let pixel = map.getEventPixel(event.originalEvent);

		if (counter++%100==0){
			console.log("pointermove. pixel:", pixel);
		}
		
		//let feature = map.forEachFeatureAtPixel(pixel, function(feature) {
		//  return feature;
		//});
		

  });

  //precompose
  //postcompose
  //до и после прорисовки.
  //Можно получить контекст канвы, дорисовать что нужно.
  map.on('postcompose', function(event) {
    let vectorContext = event.vectorContext;

		console.log("postcompose");
		
		
    //vectorContext.setStyle(imageStyle);
    //vectorContext.drawGeometry(new ol.geom.MultiPoint(coordinates));
    //map.render();
  });





}






