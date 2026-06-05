

let equipmentLayer;
let selectionLayer;
let map;
let selectedFeatures = [];
let objectFeatures = [];



let currentZoom;
let currentExtent;

//--------------------------------------------------------------------
//----------------------------Стиль для выделенных объектов---------------------
//--------------------------------------------------------------------


let equipmentFill = new ol.style.Fill({
  color : 'rgba(0,0,255,0.05)'
});
let equipmentStroke = new ol.style.Stroke({
  color : 'rgba(0,0,0,0)',
  width : 1
});

// Стиль, применяемый для выделенных объектов.
let equipmentStyle = new ol.style.Style({
  fill : equipmentFill,
  stroke : equipmentStroke
});

let borderStyle = new ol.style.Style({
  stroke : new ol.style.Stroke({
    color : 'rgba(0,0,0,1)',
    width : 1
  })
});

let selectionFill = new ol.style.Fill({
  color : 'rgba(255,128,128,0.5)'
});
let selectionStroke = new ol.style.Stroke({
  color : 'rgba(255,0,0,0.4)',
  width : 4
});

// Стиль, применяемый для выделенных объектов.
let selectionStyle = new ol.style.Style({
  fill : selectionFill,
  stroke : selectionStroke
});

let getSelectionStyle = function(feature, resolution) {
  return selectionStyle;
}

let getEquipmentStyle = function(feature, resolution) {
  if (feature.getId()=="image_border"){
    return borderStyle;
  }
  return equipmentStyle;
}






//инициализация мапа для просмотра схемы
function initPsSchemeMap(extent, svgUrl){
  
  currentExtent = extent;
  
  let projection = new ol.proj.Projection({
    code: 'xkcd-image',
    units: 'pixels',
    extent: extent
  });
   
  
  //слой для выделения фич.
  equipmentLayer = new ol.layer.Vector({
    source : new ol.source.Vector({}),
    style : getEquipmentStyle
  });
  
  selectionLayer = new ol.layer.Vector({
    source : new ol.source.Vector({}),
    style : getSelectionStyle
  });
  
  
  map = new ol.Map({
    layers: [
      new ol.layer.Image({
        source: new ol.source.ImageStatic({
          url: svgUrl,
          projection: projection,
          imageExtent: extent
        })
      }),
      selectionLayer,
      equipmentLayer
    ],

    
    
    target: 'map',
    view: new ol.View({
      projection: projection,
      center: ol.extent.getCenter(extent),
      zoom: 2,
      maxZoom: 8,
			minZoom: -4,
    }),
		interactions : ol.interaction.defaults({
		  doubleClickZoom : false,
		  dragPan : false,
		  shiftDragZoom : false,
		  pinchRotate : false
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
  

  //При двойном клике - показываем координаты
  map.on('dblclick', function(evt) {
    let coord = map.getCoordinateFromPixel(evt.pixel);

    coord = [Math.round(coord[0]), Math.round(coord[1])];
    let coord2 = [Math.round(coord[0]), Math.round(extent[3]-coord[1])];
    console.log(coord+" actual:"+coord2);
		
    $("#coord-span").text(coord+" actual:"+coord2);
    
  });

  
  
}




function initPsSchemeTab(){
  
  let schemeUrl = '../images/example9.svg';
	initPsSchemeMap([0, 0, 400, 650], schemeUrl);

  
  
  $("#ps-scheme-map").empty();

  
}



$(document).ready(function(){
  
  initPsSchemeTab()

  //Прячем-показываем активные объекты при изменении чекбукса
  $("#show-active-objects").change(function(event){
    let val = $("#show-active-objects").prop("checked");
    equipmentLayer.setVisible(val);
  });
  
  
  
});
