

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



export class DistanceMeasure {

  map;
  vectorSource;
  vectorLayer;
  draw;
  sketch;
  geomChangeListener;

  tooltipCoord;

  _active = false;


  constructor(map) {

    this.map = map;
    this.vectorSource = new ol.source.Vector();

    this.vectorLayer = new ol.layer.Vector({
      source: this.vectorSource,
      style: measureStyle
    });

		this.createDrawInteraction();
		
  }


  setActive(v) {
    this._active = v;
		
		if (v){
			this.map.addInteraction(this.draw);
		} else {
			this.map.removeInteraction(this.draw);
		}
		


  }



  createDrawInteraction() {

    //Создаём оверлеи (для показа текущей длины линии и для показа подсказки)
    //	  createMeasureTooltip();
    //	  createHelpTooltip();


    //Создаём интеракшн, рисующий линии
    this.draw = new ol.interaction.Draw({
      source: this.vectorSource,
      type: 'LineString',
      style: drawStyle
    });



    //При начале рисования
    this.draw.on('drawstart', evt => {

      this.vectorSource.clear();
      this.sketch = evt.feature;
      this.tooltipCoord = evt.coordinate;


      //При изменении создаваемой линии - обновляем положение (и содержимое) оверлея с текущей длиной линии
      this.geomChangeListener = this.sketch.getGeometry().on('change', function(evt) {
        var geom = evt.target;
        this.tooltipCoord = geom.getLastCoordinate();


        /*				
        let output = makeLineLengthStr(geom);
      	
        measureTooltipElement.innerHTML = output;
        measureTooltip.setPosition(this.tooltipCoord);
        */

      });


    }); //on drawstart


    //При окончании рисования
    this.draw.on('drawend', function() {

      /*
      //закрепляем элемент с длиной линии
      measureTooltipElement.className = 'tooltip tooltip-static';
      measureTooltip.setOffset([0, -7]);
*/

      // unset sketch
      this.sketch = null;

      //Убираем обработчик, меняющий положение оверлея measureTooltip
      ol.Observable.unByKey(this.geomChangeListener);
    });  //on drawend

  }






}








