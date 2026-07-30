import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

let olDemo;

//объявляем глобальные переменные
"testLayer".split(",").forEach(name => window[name] = null);

let selectorsData1 = {


  TileArcGISRest() {

    //работает отлично
    testLayer = new ol.layer.Tile({
      extent: [-13884991, 2870341, -7455066, 6338219],
      source: new ol.source.TileArcGISRest({
        url: 'https://sampleserver6.arcgisonline.com/ArcGIS/rest/services/USA/MapServer',
      }),
    });
    map.addLayer(testLayer);


  },

  ImageArcGISRest() {

    //работает отлично
    testLayer = new ol.layer.Image({
      source: new ol.source.ImageArcGISRest({
        ratio: 1,
        params: {},
        url: 'https://sampleserver6.arcgisonline.com/ArcGIS/rest/services/USA/MapServer',
      }),
    });
    map.addLayer(testLayer);
  },

  graticule: `
/*
ol.Graticule
  Слой, рисующий координатную сетку поверх карты.
	координатная сетка.
	
---Опции---

strokeStyle
  Стиль линий 	

targetSize
Размер ячеек сетки, в пикселях
(default 100)     	


showLabels
(defaults to false)
Рисовать метки с широтой/долготой каждой линии

lonLabelFormatter
latLabelFormatter 	
форматер меток (в функцию передаётся один параметр: градус)

lonLabelPosition  (defaults to 0) 	
latLabelPosition  (defaults to 1) 	
Положение меток в ячйках: (0..1)

lonLabelStyle
latLabelStyle
Стиль меток: (new Text()) 	
*/
`,


  graticuleSample() {

    let strokeStyle = new ol.style.Stroke({
      color: 'rgba(255,120,0,0.9)',
      width: 2,
      lineDash: [0.5, 4],
    });

    testLayer = new ol.layer.Graticule({
      strokeStyle: strokeStyle,
      showLabels: true,
      extent: [-1_168_908, 2_669_028, 6_211_154, 8_952_054],
      targetSize: 100,  //размер ячеек
      wrapX: false,
      lonLabelFormatter: (lonDegrees) => {
        let [lon] = ol.proj.fromLonLat([lonDegrees, 0]);
        return olu.fcp(lon);
      },
      background: 'rgba(255, 179, 179, 0.1)',  //цвет на задний фон
    });

    map.addLayer(testLayer);

  },


  OSM() {

    testLayer = new ol.layer.Tile({
      source: new ol.source.OSM(),
      //className: 'bw',  //сделаем стиль чёрно-белым
      className: 'blur'  //размытость


    });
    map.addLayer(testLayer);

  },

  XYZ() {
    /*
    ol.layer.Tile
    ol.source.XYZ
    */

    //gis
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}",
      }),
    });
    map.addLayer(testLayer);


  },


	TileDebugInfo:`
/*
ol.source.TileDebug
Источник псевдо-тайлов, прорисовывающий информацию по тайлам заданного источника.

---Опции---

source
Источник, из которого будут скопированы tileGrid, wrapX, zDirection

projection 	
tileGrid 	
wrapX
zDirection

template
(default 'z:{z} x:{x} y:{y}')

color
(default 'grey') 	
*/
`,

  TileDebug() {
    /*
    ol.source.TileDebug
    */

    testLayer = new ol.layer.Tile({
      source: new ol.source.TileDebug({
				source: tileLayer.getSource(),
//        template: 'z:{z} x:{x} y:{-y}',
//        projection: tileLayer.getSource().getProjection(),
//        tileGrid: tileLayer.getSource().getTileGrid(),
//        zDirection: 1,
      }),
    });
    map.addLayer(testLayer);


  },





  StadiaMaps() {
    /*
    ol.source.StadiaMaps
    Виснет, показывает бразилию
    */

    testLayer = new ol.layer.Tile({
      source: new ol.source.StadiaMaps({
//        layer: 'alidade_smooth_dark',
				layer: 'stamen_toner',
//        retina: true,
        // apiKey: 'OPTIONAL'
      }),
    });
    map.addLayer(testLayer);


  },




  XYZ2() {
    /*
    */

    //2gis
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "http://tile1.maps.2gis.com	tiles?x=${X}&y=${Y}&z=${Z}&v=4",
      }),
    });
    map.addLayer(testLayer);




  },


  XYZ5() {

    //osm
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "http://tile.openstreetmap.org	${Z}/${X}/${Y}.png",
      }),
    });
    map.addLayer(testLayer);
  },
  XYZ6() {
    //google-sat
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "",
      }),
    });
    map.addLayer(testLayer);
  },
  XYZ7() {
    //yandex-sat
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "http://sat.maps.yandex.net	tiles?l=sat&v=3.167.0&x={x}&y={y}&z={z}&lang=ru_RU",
        projection: 'EPSG:3395',
      }),
    });
    map.addLayer(testLayer);

  },
  XYZ8() {
    //yandex
    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "http://vec.maps.yandex.net	tiles?l=map&v=2.19.5&x={x}&y={y}&z={z}&lang=ru_RU",
        projection: 'EPSG:3395',
      }),
    });
    map.addLayer(testLayer);
  },
  XYZ9() {

    //виснет
    testLayer = new ol.layer.Tile({
      source: new ol.source.ImageTile({
        url:
          'https://{a-c}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png' +
          '?apikey=Your API key from https://www.thunderforest.com/docs/apikeys/ here',
      }),
    });
    map.addLayer(testLayer);


  },


  ttt() {

    //нужен ключ
    testLayer = new ol.layer.WebGLTile({
      source: new ol.source.ImageTile({
        url: 'https://api.maptiler.com/maps/outdoor-v2/256/{z}/{x}/{y}@2x.png?key=111'
      })
    });
    map.addLayer(testLayer);


  },




  OGCMapTile() {
    /*
    OGC (растровые тайлы)
    Виснет
    */
    testLayer = new ol.layer.Tile({
      source: new ol.source.OGCMapTile({
        url: 'https://maps.gnosis.earth/ogcapi/collections/blueMarble/map/tiles/WebMercatorQuad',
      }),
    });
    map.addLayer(testLayer);
  },
  OGCVectorTile() {
    /*
    Виснет
    */

    testLayer = new ol.layer.VectorTile({
      source: new ol.source.OGCVectorTile({
        url: 'https://maps.gnosis.earth/ogcapi/collections/NaturalEarth:cultural:ne_10m_admin_0_countries/tiles/WebMercatorQuad',
        format: new ol.format.MVT(),
      }),
      background: '#e2e3e3',
      style: {
        'stroke-width': 1,
        'stroke-color': '#8c8b8b',
        'fill-color': '#f7f7e9',
      },
    })

    map.addLayer(testLayer);
  },


  t1() {
    /*
    */
  },
  t2() {
    /*
    */
  },
}


class MyOLDemo extends old.OLDemo {

  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.vectorSource.addFeature(demodata.testFeatures.pointFeature);
    this.vectorSource.addFeature(demodata.testFeatures.lineFeature);

    this.select.on("select", e => {
      clearLog();

      if (e.selected.length) {
        olu.logFeature(e.selected[0]);
      }

    });
  }


}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
    debug: true
  });

  olDemo.initMap();


}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
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






