/*
Основные вспомогательные методы и переменные для демок openlayers.
*/


let map, mapView, tileLayer;

let vectorLayer, vectorSource, vectorStyle;

//вывод координаты в лог
function logCoord(coord){
	log("[",fcp(coord[0]),",",fcp(coord[1]));
}

//форматирование дробного числа, с округлением
function fcp(d){
	return d.toLocaleString("ru", {maximumFractionDigits: 0});
}


function initVectorLayer1() {

  vectorSource = new ol.source.Vector({
    url: 'misc/switzerland.geojson',
    format: new ol.format.GeoJSON(),
  });

  vectorStyle = {
    'fill-color': 'rgba(255, 255, 255, 0.6)',
    'stroke-width': 1,
    'stroke-color': '#319FD3',
    'circle-radius': 5,
    'circle-fill-color': 'rgba(255, 255, 255, 0.6)',
    'circle-stroke-width': 1,
    'circle-stroke-color': '#319FD3',
  };

  //похоже стиль можно задавать через css
  vectorLayer = new ol.layer.Vector({
    source: vectorSource,
    style: vectorStyle
  });

}


//Простейшая инициализация
function initMapBasic() {

	//по умолчанию используем OSM
	if (!tileLayer){
		tileLayer = new ol.layer.Tile({
		  source: new ol.source.OSM(),
		});
	}

	//добавляем векторный слой, если он создан
	let layers = [tileLayer];
	if (vectorLayer){
		layers.push(vectorLayer);
	}

	mapView = new ol.View({
	  center: [0, 0],
	  zoom: 1,
	});
	
  map = new ol.Map({
    target: 'map',
    layers: layers,
    view: mapView,
		interactions: ol.interaction.defaults.defaults({
		  doubleClickZoom: false,
		  shiftDragZoom: false,
		  pinchRotate: false
		}),

  });


}











