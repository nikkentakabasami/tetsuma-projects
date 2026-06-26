import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

//объявляем глобальные переменные
"testLayer".split(",").forEach(name => window[name] = null);




export let selectorsData1 = {


  Layer() {
    /*
    ol.layer.Layer
  	
    ---Опции---
  	
    map 	
      Задаёт слой как покрытие поверх карты.
    Карта не будет содержать этот слой в своей коллекции слоёв.
      Удобно для временных слоёв.
  	
  	
    className
    CSS class name for layer element
  	
    background
    Цвет заднего плана
  	
    vectorLayer.setBackground("rgba(255,0,0,0.1)");
      	
    opacity
    непрозрачность (default 1) 	

    visible
    (default true) 	
  	
    zIndex

    extent
    область, в которой будет прорисовываться сетка

    minResolution 	
    maxResolution 	
    minZoom 	
    maxZoom 	
    зумы, в которой будет прорисовываться сетка
  	
    source
    источник данных 	

    render
    RenderFunction
    Своя функция прорисовки, возвращающая HTML element.

    ---События---
      	
    change:visible (ObjectEvent)
    postrender
    prerender
  	

    BaseImageLayer
    BaseTileLayer
    BaseVectorLayer
     
    */

    testLayer = new ol.layer.Tile({
      source: new ol.source.OSM(),
      //className: 'bw',  //сделаем стиль чёрно-белым
			className: 'blur'  //размытость
			
			
    });
    map.addLayer(testLayer);

  },
  VectorLayer() {
    /*
    ol.layer.Vector
  	
    ---Опции---
  	
    style
    Стиль слоя

  	
  	
  	
    */
    log(olDemoGlobal.createVectorLayer);


  },

  graticule() {
    /*
    ol.Graticule
      Рисует координатную сетку поверх карты.
    	
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
    форматер меток

    lonLabelPosition  (defaults to 0) 	
    latLabelPosition  (defaults to 1) 	
    Положение меток в ячйках: (0..1)

    lonLabelStyle
    latLabelStyle
    Стиль меток: (new Text()) 	

  	
  	
  	
    	
    */

    let strokeStyle = new ol.style.Stroke({
      color: 'rgba(255,120,0,0.9)',
      width: 2,
      lineDash: [0.5, 4],
    });

    testLayer = new ol.layer.Graticule({
      strokeStyle: strokeStyle,
      showLabels: true,
      extent: [-1_168_908, 2_669_028, 6_211_154, 8_952_054],
      targetSize: 50,  //размер ячеек
      wrapX: false,
      lonLabelFormatter: (p1) => {
        return p1;
      },
      background: 'rgba(255, 179, 179, 0.1)',  //цвет на задний фон
    });

    map.addLayer(testLayer);

  },
  Tile() {
    /*
    ol.layer.Tile
    Для источников, предоставляющих пререндеренные изображения-плитки для заданного зума.
  	
    preload
    Default 0
    Загружать тайлы низкого разрешения
  	
  	
    */


    testLayer = new ol.layer.Tile({
      source: new ol.source.TileDebug(),
    });
    map.addLayer(testLayer);



  },
  XYZ() {
    /*
    ttt
    */

    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "https://server.arcgisonline.com/ArcGIS/rest/services/" + "World_Topo_Map/MapServer/tile/{z}/{y}/{x}",
      }),
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
}

