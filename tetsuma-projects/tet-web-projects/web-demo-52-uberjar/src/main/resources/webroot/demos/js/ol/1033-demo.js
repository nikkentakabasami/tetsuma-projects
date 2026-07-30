import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";
import * as olds from './ol-demo-styles.js';


/**
 * Примеры создания векторных слоёв.
 */


let olDemo;

//объявляем глобальные переменные
"testLayer,testSource".split(",").forEach(name => window[name] = null);


let selectorsData1 = {

  testFeatures() {
    /*
    Простейший векторный слой.
    Добавление на него тестовых фич.
    */

    testSource = new ol.source.Vector({
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: old.defaultVectorStyle
    });
    map.addLayer(testLayer);

    let features = Object.values(demodata.testFeatures);
    testSource.addFeatures(features);
  },


	

	postrender() {
		
		//создаём тестовый слой
		selectorsData1.testFeatures();

		//прорисовка доп. геометрий через ol.render.VectorContext
	  testLayer.on('postrender', event => {

			const vectorContext = ol.render.getVectorContext(event);
			
			//дорисовываем линию
			vectorContext.setStyle(olds.defaultVectorStyle);
			vectorContext.drawGeometry(
			  new ol.geom.LineString([
			    [0, 0],
			    [1e7, 1e7],
			  ]),
			);

			map.render();
			
	  });

	},		
	
	
	
	
	
  GeoJSON() {
    /*
    ol.format.GeoJSON
		Формат для считывания фич в формате GeoJSON

		---Опции---

		defaultDataProjection
		  Default is EPSG:4326.

		featureProjection

		---Методы---

		readFeature(source)
		readFeatures(source)
		readGeometry(source)		
    */

    testSource = new ol.source.Vector({
      url: '../data/switzerland.geojson',
      format: new ol.format.GeoJSON(),
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      background: 'rgba(255, 179, 179, 0.1)',
      style: old.defaultVectorStyle
    });

    map.addLayer(testLayer);
  },

  GeoJSON2() {
    /*
    ol.format.GeoJSON
    */
    let features = new ol.format.GeoJSON().readFeatures(demodata.demoGeojsonObject1);

    testSource = new ol.source.Vector({
      features: features
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: old.defaultVectorStyle
    });
    map.addLayer(testLayer);
  },

  TopoJSON() {
    /*
    ol.format.TopoJSON
    */
    testSource = new ol.source.Vector({
      url: '../data/world-110m.json',
      format: new ol.format.TopoJSON({
        layers: ['countries'],
      }),
      overlaps: false,
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      //style: old.defaultVectorStyle,
      style: {
        'stroke-color': 'red',
        'stroke-width': 2,
      },
    });
    map.addLayer(testLayer);
  },


  KML() {
    /*
    ol.format.KML
    Загрузка землятресений.
    */


    testSource = new ol.source.Vector({
      url: '../data/2012_Earthquakes_Mag5.kml',
      format: new ol.format.KML({
        extractStyles: false,
      }),
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: EarthquakesStyleFunction
    });

    map.addLayer(testLayer);

    log(EarthquakesStyleFunction);

  },

  Heatmap() {
    /*
    ol.layer.Heatmap
    Слой, который прорисовывает векторные данные в виде карты температур.
  	
    */

    testSource = new ol.source.Vector({
      url: '../data/2012_Earthquakes_Mag5.kml',
      format: new ol.format.KML({
        extractStyles: false,
      }),
    });

    testLayer = new ol.layer.Heatmap({
      source: testSource,
      blur: 15,
      radius: 15,
      weight: function(feature) {
        const name = feature.get('name');
        const magnitude = parseFloat(name.substr(2));
        return magnitude - 5;
      },
    });

    map.addLayer(testLayer);
  },



  RegularShape() {
    /*
    */

    const shaft = new ol.style.RegularShape({
      points: 2,
      radius: 5,
      stroke: new ol.style.Stroke({
        width: 2,
        color: 'black',
      }),
      rotateWithView: true,
    });

    const head = new ol.style.RegularShape({
      points: 3,
      radius: 5,
      fill: new ol.style.Fill({
        color: 'black',
      }),
      rotateWithView: true,
    });

    const styles = [new ol.style.Style({image: shaft}), new ol.style.Style({image: head})];		


    testSource = new ol.source.Vector({
    });

    testLayer = new ol.layer.Vector({
      source: testSource,
      style: old.defaultVectorStyle,
      style: function(feature) {
        const wind = feature.get('wind');
        // rotate arrow away from wind origin
        const angle = ((wind.deg - 180) * Math.PI) / 180;
        const scale = wind.speed / 10;
        shaft.setScale([1, scale]);
        shaft.setRotation(angle);
        head.setDisplacement([
          0,
          head.getRadius() / 2 + shaft.getRadius() * scale,
        ]);
        head.setRotation(angle);
        return styles;
      },
    });
    map.addLayer(testLayer);


    fetch('../data/weather.json')
      .then(function(response) {
        return response.json();
      })
      .then(function(data) {
        const features = [];
        data.list.forEach(function(report) {
          const feature = new ol.Feature(
            new ol.geom.Point(ol.proj.fromLonLat([report.coord.lon, report.coord.lat])),
          );
          feature.setProperties(report);
          features.push(feature);
        });
        testSource.addFeatures(features);
        map.getView().fit(testSource.getExtent());
      });


  },




}



function EarthquakesStyleFunction(feature, resolution) {


  const name = feature.get('name');
  const magnitude = parseFloat(name.substr(2));
  const radius = 5 + 20 * (magnitude - 5);

  let style = new ol.style.Style({

    image: new ol.style.RegularShape({
      radius: radius,
      radius2: 8,
      points: 5,
      angle: Math.PI,
      fill: new ol.style.Fill({
        color: 'rgba(255, 153, 0, 0.8)',
      }),
      stroke: new ol.style.Stroke({
        color: 'rgba(255, 204, 0, 0.2)',
        width: 1,
      }),
    }),

    text: new ol.style.Text({
      text: magnitude.toString(),
      fill: new ol.style.Fill({
        color: '#fff',
      }),
      stroke: new ol.style.Stroke({
        color: 'rgba(0, 0, 0, 0.6)',
        width: 3,
      }),
    }),
  });

  return style;
}







class MyOLDemo extends old.OLDemo {

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();
    olu.addShowCoordHandler(this.map);
  }


}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: false,
    withTileLayer: true,
    debug: true
  });

  olDemo.initMap();


}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    selectedOption: "postrender",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    initFunction: initMap,
    beforeExec: () => {
    },
    afterSelectChange: () => {
      if (testLayer) {
        map.removeLayer(testLayer);
        testLayer.dispose();
        testLayer = null;
      }

    }
  };
}






