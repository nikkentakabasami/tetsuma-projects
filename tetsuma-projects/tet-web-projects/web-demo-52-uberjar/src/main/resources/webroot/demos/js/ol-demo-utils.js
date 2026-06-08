

import { demoGeojsonObject1 } from './ol-demo-data.js';
import * as olds from './ol-demo-styles.js';


export function logFeature(f){
	
	let name = f.getProperties().name;
	
	log("id:",f.getId(),", name:"+name);
	
}

//вывод координаты в лог
export function logCoord(coord){
	
	
	let r = coord.map(el=>fcp(el)).join(", ");
	
	if (coord.length==2){
		let c2 = ol.proj.toLonLat(coord);
		r+="["+c2.map(el=>el.toLocaleString("en", {maximumFractionDigits: 1})).join(", ")+"]";
	}
	
	log(r);
}

//форматирование дробного числа, с округлением
function fcp(d, mfd = 0){
	return d.toLocaleString("ru", {maximumFractionDigits: mfd});
}



export function addShowCoordHandler(olDemo) {
	
	//При двойном клике - показываем координаты
	olDemo.map.on('dblclick', function(evt) {
		if (evt.originalEvent.ctrlKey){
			let coord = olDemo.map.getCoordinateFromPixel(evt.pixel);
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

//граници швейцарии
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



