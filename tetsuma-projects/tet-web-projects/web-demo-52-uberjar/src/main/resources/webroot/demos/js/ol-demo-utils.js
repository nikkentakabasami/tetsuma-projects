

import { demoGeojsonObject1 } from './ol-demo-data2.js';


//вывод координаты в лог
export function logCoord(coord){
	log("[",fcp(coord[0]),",",fcp(coord[1]));
}

//форматирование дробного числа, с округлением
function fcp(d){
	return d.toLocaleString("ru", {maximumFractionDigits: 0});
}



//-----------тестовые источники данных для векторных слоёв--------------------


export function createDemoVectorSource1(olDemo) {

  let features = new ol.format.GeoJSON().readFeatures(demoGeojsonObject1);

  olDemo.vectorSource = new ol.source.Vector({
    features: features
  });
}

export function createDemoVectorSource2(olDemo) {

  olDemo.vectorSource = new ol.source.Vector({
		url: 'misc/switzerland.geojson',
		format: new ol.format.GeoJSON(),
  });
}



