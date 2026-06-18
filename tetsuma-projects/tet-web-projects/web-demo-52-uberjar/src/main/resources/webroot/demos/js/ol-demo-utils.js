

import { demoGeojsonObject1 } from './ol-demo-data.js';
import * as olds from './ol-demo-styles.js';





export function calcDistance(coord1, coord2, precision){
	
	coord1 = ol.proj.toLonLat(coord1);  
	coord2 = ol.proj.toLonLat(coord2);  
	let r = ol.sphere.getDistance(coord1,coord2)
  
  if (precision){
    var pv = Math.pow(10,precision);
    r = Math.round(r*pv)/pv;
  }

  return r;
}
  



export function logFeature(f){
	
	let name = f.getProperties().name;
	
	log("id:",f.getId(),", name:"+name);
	
}

//вывод координаты в лог
export function logCoord(coord){
	
	let r = formatCoord(coord);
	if (!r){
		return;
	}
	
	if (coord.length==2){
		let c2 = ol.proj.toLonLat(coord);
		r+="  wgs:"+formatCoord(c2,1);
	}
	
	log(r);
}

//делаем функцию глобальной
window.formatCoord = formatCoord;


export function formatCoord(coord, mfd = 0){
	if (!Array.isArray(coord) || coord.length>4){
		return null;
	}
	return "[ "+coord.map(el=>fcp(el,mfd)).join(", ")+" ]";
}



//форматирование координатного числа (округляем, добавляем разделение на группы)
function fcp(d, mfd = 0){
	if (!Number.isFinite(d)){
		return String(d);
	}
	
	//координата в градусах
	if (Math.abs(d)<180){
		return d.toLocaleString("en", {maximumFractionDigits: 4})
	}
	
	
	let r = d.toLocaleString("ru", {maximumFractionDigits: mfd});
	
	return r.replace(/\s/g, '_');
}



export function addShowCoordHandler(map) {
	
	//При двойном клике - показываем координаты
	map.on('dblclick', function(evt) {
		if (evt.originalEvent.ctrlKey){
			let coord = map.getCoordinateFromPixel(evt.pixel);
			logCoord(coord);
		}
		
	});
	
	
}








//-----------тестовые источники данных для векторных слоёв--------------------

//Создаёт набор простых геометрических фич
export function createDemoVectorSource1(olDemo) {

  let features = new ol.format.GeoJSON().readFeatures(demoGeojsonObject1);

  olDemo.vectorSource = new ol.source.Vector({
    features: features
  });
}

//границы швейцарии
export function createDemoVectorSource2(olDemo) {

  olDemo.vectorSource = new ol.source.Vector({
		url: 'misc/switzerland.geojson',
		format: new ol.format.GeoJSON(),
  });
}


//-----------interactions--------------------

export function addSelectInteractions(olDemo) {

	let select = new ol.interaction.Select({
	  //можно выбирать несколько фич
	  multi: false,
	  //стиль с подкраской красным
	  style: function(feature) {
	    return olds.defaultSelectStyle;
	  },
	});
	//чтобы удобнее выделять
	select.setHitTolerance(5);
	
	olDemo.select = select;
	olDemo.map.addInteraction(select);

	
	
	let dragBox = new ol.interaction.DragBox({
		//рисовать область только если нажат Ctrl
	  condition: ol.events.condition.platformModifierKeyOnly,
	});

	dragBox.on('boxend', e => {

	  const boxExtent = dragBox.getGeometry().getExtent();
	  const boxFeatures = vectorSource.getFeaturesInExtent(boxExtent);
	  boxFeatures.forEach((feature) => {
	    select.selectFeature(feature);
	  });

	});

	dragBox.on('boxstart', function() {
	  select.clearSelection();
	});
	
	olDemo.dragBox = dragBox;
	olDemo.map.addInteraction(dragBox);
		
}



