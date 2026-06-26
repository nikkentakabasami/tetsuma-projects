import * as olu from "./ol-demo-utils.js";


let measureStyle = new ol.style.Style({
  fill: new ol.style.Fill({
    color: 'rgba(255, 255, 255, 0.2)'
  }),
  stroke: new ol.style.Stroke({
    color: '#ffcc33',
    width: 2
  }),
  image: new ol.style.Circle({
    radius: 7,
    fill: new ol.style.Fill({
      color: '#ffcc33'
    })
  })
});

let drawStyle = new ol.style.Style({
  fill: new ol.style.Fill({
    color: 'rgba(255, 255, 255, 0.2)'
  }),
  stroke: new ol.style.Stroke({
    color: 'rgba(0, 0, 0, 0.5)',
    lineDash: [10, 10],
    width: 2
  }),
  image: new ol.style.Circle({
    radius: 5,
    stroke: new ol.style.Stroke({
      color: 'rgba(0, 0, 0, 0.7)'
    }),
    fill: new ol.style.Fill({
      color: 'rgba(255, 255, 255, 0.2)'
    })
  })
});


/**
 * interaction для измерения расстояний.
 */
export class DistanceMeasureInteraction extends ol.interaction.Interaction {

  vectorSource;
  vectorLayer;
  geomChangeListenerKey;

  drawInteraction;
  tooltipOverlay;

  activated = false;


  setMap(map) {
    if (!map) {
      this.setActive(false);
    }
    super.setMap(map);
    this.reload();
  }

  setActive(v) {
    super.setActive(v);
    this.reload();
  }

  reload() {
    let map = this.getMap();
    if (!map) {
      return;
    }

    if (this.getActive()) {

      if (!this.activated) {
        map.addLayer(this.vectorLayer);
        map.addInteraction(this.drawInteraction);
        //        map.addOverlay(this.tooltipOverlay);
        this.activated = true;
      }

    } else {
      if (this.activated) {
        map.removeInteraction(this.drawInteraction);
        map.removeOverlay(this.tooltipOverlay);
        map.removeLayer(this.vectorLayer);
        this.vectorSource.clear();
        this.activated = false;
      }
    }

  }


  constructor(options) {
    super();

    options = options ? options : {};


    //добавляем отдельный слой для измерителя		
    this.vectorSource = new ol.source.Vector();
    this.vectorLayer = new ol.layer.Vector({
      source: this.vectorSource,
      style: measureStyle,
    });

    this.createTooltipOverlay();
    this.createDrawInteraction();
  }

  createTooltipOverlay() {
    let el = document.createElement('div');
    el.className = 'tooltip-measure';
    this.tooltipOverlay = new ol.Overlay({
      element: el,
      offset: [0, -10],
      stopEvent: false,
      positioning: 'bottom-center'
    });
  }


  createDrawInteraction() {
    //Создаём интеракшн, рисующий линии
    this.drawInteraction = new ol.interaction.Draw({
      source: this.vectorSource,
      type: 'LineString',
      style: drawStyle
    });

    //При начале рисования
    this.drawInteraction.on('drawstart', evt => {
      this.vectorSource.clear();

      if (!this.tooltipOverlay.getMap()) {
        map.addOverlay(this.tooltipOverlay);
      }


      //При изменении создаваемой линии - обновляем положение (и содержимое) tooltipOverlay
      this.geomChangeListenerKey = evt.feature.getGeometry().on('change', evt => {
        var geom = evt.target;
        let tooltipCoord = geom.getLastCoordinate();

        let s = makeLineLengthStr(geom);

        this.tooltipOverlay.getElement().innerHTML = s;
        this.tooltipOverlay.setPosition(tooltipCoord);
      });


    }); //on drawstart


    //При окончании рисования
    this.drawInteraction.on('drawend', () => {

      //Убираем обработчик, меняющий положение оверлея measureTooltip
      ol.Observable.unByKey(this.geomChangeListenerKey);
    });  //on drawend
  }



}

var makeLineLengthStr = function(lineGeom) {
  var coords = lineGeom.getCoordinates();

  var lastSectionLength = olu.calcDistance(coords[coords.length - 1], coords[coords.length - 2]);
  var lastSectionLengthStr = formatLinerLength(lastSectionLength);

  if (coords.length == 2) {
    return lastSectionLengthStr;
  } else {

    var l = ol.sphere.getLength(lineGeom);
    var fullLengthStr = formatLinerLength(l);
    return "S=" + fullLengthStr + ", L=" + lastSectionLengthStr;

  }
};

function formatLinerLength(length) {
  var output;
  if (length > 100) {
    output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'км';
  } else {
    output = (Math.round(length * 100) / 100) + ' ' + 'м';
  }
  return output;
}



