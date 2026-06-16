//--------------------------------------------------------------------
//-----------фишка с измерением расстояний на карте--------------
//--------------------------------------------------------------------



var distanceMeasurementMode = false;

/**
 * Currently drawn feature.
 * 
 * @type {ol.Feature}
 */
var sketch;


var draw;


//overlay, содержащий метку, показывающую длину текущего отрезка.
var measureTooltip;

//Элемент этого оверлея
var measureTooltipElement;



var mdVectorSource = new ol.source.Vector();

var mdVectorLayer = new ol.layer.Vector({
  source : mdVectorSource,
  style : new ol.style.Style({
    fill : new ol.style.Fill({
      color : 'rgba(255, 255, 255, 0.2)'
    }),
    stroke : new ol.style.Stroke({
      color : '#ffcc33',
      width : 2
    }),
    image : new ol.style.Circle({
      radius : 7,
      fill : new ol.style.Fill({
        color : '#ffcc33'
      })
    })
  })
});







function addLinerInteraction() {

  //Создаём оверлеи (для показа текущей длины линии и для показа подсказки)
  createMeasureTooltip();
  createHelpTooltip();
  
  
  //Создаём интеракшн, рисующий линии
  draw = new ol.interaction.Draw({
    source : mdVectorSource,
    type : 'LineString',
    style : new ol.style.Style({
      fill : new ol.style.Fill({
        color : 'rgba(255, 255, 255, 0.2)'
      }),
      stroke : new ol.style.Stroke({
        color : 'rgba(0, 0, 0, 0.5)',
        lineDash : [ 10, 10 ],
        width : 2
      }),
      image : new ol.style.Circle({
        radius : 5,
        stroke : new ol.style.Stroke({
          color : 'rgba(0, 0, 0, 0.7)'
        }),
        fill : new ol.style.Fill({
          color : 'rgba(255, 255, 255, 0.2)'
        })
      })
    })
  });
  map.addInteraction(draw);


  var listener;
  
  //При начале рисования
  draw.on('drawstart', function(evt) {

    if (!distanceMeasurementMode) {
      return;
    }
    mdVectorSource.clear();

    // set sketch
    sketch = evt.feature;
    
    var tooltipCoord = evt.coordinate;

    //костыль (почему то фиксируется топ)
//    $(".tooltip-measure").parent().css("top","")    
    
    //При изменении создаваемой линии - обновляем положение (и содержимое) оверлея с текущей длиной линии
    listener = sketch.getGeometry().on('change', function(evt) {
      var geom = evt.target;
      var output;
      if (geom instanceof ol.geom.Polygon) {
        //не сделано
        output = formatArea(geom);
        tooltipCoord = geom.getInteriorPoint().getCoordinates();
      } else if (geom instanceof ol.geom.LineString) {
        output = makeLineLengthStr(geom);
        tooltipCoord = geom.getLastCoordinate();
      }
      measureTooltipElement.innerHTML = output;
      measureTooltip.setPosition(tooltipCoord);
      
//      console.log('mt'+tooltipCoord)
      
    });
  }, this); //on drawstart

  
  //При окончании рисования
  draw.on('drawend', function() {

    if (!distanceMeasurementMode) {
      return;
    }

    //закрепляем элемент с длиной линии
    measureTooltipElement.className = 'tooltip tooltip-static';
    measureTooltip.setOffset([ 0, -7 ]);
    
    // unset sketch
    sketch = null;
    
    //Убираем обработчик, меняющий положение оверлея measureTooltip
    ol.Observable.unByKey(listener);
  }, this);  //on drawend

} //addInteraction()



function removeLinerInteraction() {
  removeHelpTooltip();
  removeMeasureTooltip();
  map.removeInteraction(draw);
  
  mdVectorSource.clear();
}




function createMeasureTooltip() {
  if (measureTooltipElement) {
    measureTooltipElement.parentNode.removeChild(measureTooltipElement);
  }
  measureTooltipElement = document.createElement('div');
  measureTooltipElement.className = 'tooltip tooltip-measure';
  measureTooltip = new ol.Overlay({
    element: measureTooltipElement,
    offset: [0, -15],
    positioning: 'bottom-center'
  });
  map.addOverlay(measureTooltip);
}

function removeMeasureTooltip() {

  if (measureTooltip) {
    map.removeOverlay(measureTooltip);
  }
  
  if (measureTooltipElement) {
    measureTooltipElement.parentNode.removeChild(measureTooltipElement);
  }
  
  measureTooltipElement = null;
  measureTooltip = null;
}




/**
 * Format length output.
 * 
 * @param {ol.geom.LineString}
 *          line The line.
 * @return {string} The formatted length.
 */
var makeLineLengthStr = function(line) {
  
  
  var coords = line.getCoordinates();

  var lastSectionLength = calcDistance(coords[coords.length-1],coords[coords.length-2]);
  var lastSectionLengthStr = formatLinerLength(lastSectionLength);
  
  if (coords.length==2){
    return lastSectionLengthStr;
    
//    var l = calcDistance(coords[0],coords[1]);
//    return formatLinerLength(lastSectionLength);
  } else {
    
    var l = ol.Sphere.getLength(line);
    var fullLengthStr = formatLinerLength(l);
    return "S="+fullLengthStr+", L="+lastSectionLengthStr;
    
  }
  
/*  
  var length = ol.Sphere.getLength(line);
  var output;
  if (length > 100) {
    output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'км';
  } else {
    output = (Math.round(length * 100) / 100) + ' ' + 'м';
  }
  return output;
  */
  
};

function formatLinerLength(length){
  var output;
  if (length > 100) {
    output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'км';
  } else {
    output = (Math.round(length * 100) / 100) + ' ' + 'м';
  }
  return output;
  
}



//сейчас не используются, так как режим панорамирования убрали
function setPanoramMode(panoramMode){

  if (dragPanInteraction) {
    if (panoramMode){
      map.removeInteraction(dragPanInteraction);
    } else {
      map.addInteraction(dragPanInteraction);
    }
  }
  
  if (dragZoomInteraction) {
    if (panoramMode){
      map.addInteraction(dragZoomInteraction);
    } else {
      map.removeInteraction(dragZoomInteraction);
    }
  }
  
}
  




$(document).ready(function() {


  setTimeout(function(){
    map.addLayer(mdVectorLayer);
  },200);
  

  // линейка с маштабом
  map.addControl(new ol.control.ScaleLine({
    units : 'metric'
  }));

  $("#btn-liner").click(function(e) {
    distanceMeasurementMode = !$("#btn-liner").hasClass("active");

    if (distanceMeasurementMode) {
      addLinerInteraction();
    } else {
      removeLinerInteraction();
    }
  });

  
});  






