import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

//объявляем глобальные переменные
"testLayer".split(",").forEach(name => window[name] = null);




export let selectorsData1 = {


  Source: `

/*
ol.source.Source
  Основа источников данных слоёв.

Опции:

attributions
  панелька с информацией по слою

attributionsCollapsible
  (default true) 	

projection
  Проекция

state
  (default 'ready') 	
  Состояние: undefined, loading, ready, error

wrapX
  (default false) 	

interpolate
  (default false) 	


--------------	
	
ol.source.Image

ol.source.Tile
	ol/source/DataTile			
  	ol/source/ImageTile
		  ol.source.TileDebug
		
	ol.source.TileImage
	  ol.source.XYZ
  	  ol.source.OSM

ol.source.Vector
*/
`,
  Tile: `

/*
ol.source.Tile



tileGrid 	
  объект TileGrid - Параметры сетки

transition
key
zDirection

----------------------
ol.source.TileImage
  (A)Предоставляет картинки, разделённые на плитки

tileClass 	

tileLoadFunction

url

----------------------
ol.source.XYZ



ol.source.Tile
	ol/source/DataTile			
  	ol/source/ImageTile
		  ol.source.TileDebug
	ol.source.TileImage
	  ol.source.XYZ
  	  ol.source.OSM


*/


`,
  TileGrid: `

/*
new TileGrid(options)
  Параметры сетки

extent 	

minZoom 	

origin
координата нулевой точки 	

sizes
строк и столбцов для каждого зума

tileSize
размер тайла
Default [256, 256].



origins 	
tileSizes 	
resolutions

*/

`,
  Layer: `

/*
ol.layer.Layer
  Основа слоёв.

---Опции---

map 	
  Задаёт слой как покрытие поверх карты.
Карта не будет содержать этот слой в своей коллекции слоёв.
  Удобно для временных слоёв.

source
  источник данных 	
	
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
  зумы, в которые будет прорисовываться слой


render
  RenderFunction
  Своя функция прорисовки, возвращающая HTML element.

---События---
  	
change:visible (ObjectEvent)
postrender
prerender
 
*/


`,
  VectorLayer: `
/*
ol.layer.Vector
  Векторный слой, прорисовывающий фичи.

---Опции---

style
  Стили для фич

*/
olDemoGlobal.createVectorLayer
`,
  TileLayer: `
/*
ol.layer.Tile
  Слой для источников, предоставляющих пререндеренные изображения-плитки.

preload
  Default 0
  Загружать тайлы низкого разрешения


*/
`,

  TileLayerExample1() {

    testLayer = new ol.layer.Tile({
      source: new ol.source.OSM(),
      //className: 'bw',  //сделаем стиль чёрно-белым
      className: 'blur'  //размытость


    });
    map.addLayer(testLayer);

  },

  TileLayerExample2() {

    testLayer = new ol.layer.Tile({
      source: new ol.source.XYZ({
        url: "https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}",
      }),
    });
    map.addLayer(testLayer);


  },

  Cluster: `
/*
ol.source.Cluster
  Источник векторных данных, позволяющий кластеризовать фичи.
То есть при крупном маштабе, фичи, находящиеся близко, будут объединяться.
  Таким образом на крупном маштабе мы увидим несколько объектов, а на мелком - тысячи.
При этом одновременно не будет прорисовано слишком много фич.
  Умеет объединять только точки!

	
---Опции---	
		
source	ol.source.Vector
  Базовый векторынй источник данных.

distance	number
  Минимальное расстояние между кластерами в пикселях.
  Чем меньше - тем больше фич мы будем видеть на больших маштабах.
Default is 20.

minDistance
(default 0) 	
Минимальное расстояние между кластерными фичами.

geometryFunction	function
  Функция, которая принимает ol.Feature как аргумент и возвращает ol.geom.Point как cluster calculation point.
Если фичу не надо кластеризовать - нужно вернуть null. 
  По умолчанию работает только если нижестоящий источник содержит только точки.

*/
`,


  Cluster2() {

    //генерируем базовый источник данных
    let count = 2000;
    let features = new Array(count);
    let e = 4500000;
    for (let i = 0;i < count;++i) {
      let coordinates = [2 * e * Math.random() - e, 2 * e * Math.random() - e];
      features[i] = new ol.Feature(new ol.geom.Point(coordinates));
    }
    let source = new ol.source.Vector({
      features: features
    });

    let clusterSource = new ol.source.Cluster({
      distance: 50,
      source: source
    });

    let styleCache = {};
    testLayer = new ol.layer.Vector({
      source: clusterSource,
      style: function(feature) {
        const size = feature.get('features').length;
        let style = styleCache[size];
        if (!style) {
          style = new ol.style.Style({
            image: new ol.style.Circle({
              radius: 10,
              stroke: new ol.style.Stroke({
                color: '#fff',
              }),
              fill: new ol.style.Fill({
                color: '#3399CC',
              }),
            }),
            text: new ol.style.Text({
              text: size.toString(),
              fill: new ol.style.Fill({
                color: '#fff',
              }),
            }),
          });
          styleCache[size] = style;
        }
        return style;
      },
    });

    map.addLayer(testLayer);


  },



}

