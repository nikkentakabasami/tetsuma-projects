import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as olt from "./ol-template.js";


let selectorsData1 = {
  t1() {

    createMap_Image();
    log(createMap_Image);

  },
  t2() {

    createMap_ImageWMS();
    log(createMap_ImageWMS);


  },
}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: () => {
      //olt.createUniversalMap();
    },
  };
}








let map;


function destroyMap() {
  $("#map").empty();
  map = null;
}


function createMap_Image() {
  destroyMap();

	//Создание карты на основе картинки
	
  //по размеру картинки
  const extent = [0, 0, 1024, 968];

  const projection = new ol.proj.Projection({
    code: 'my-image-projection',
    units: 'pixels',
    extent: extent,
  });

  let layer1 = new ol.layer.Image({
    source: new ol.source.ImageStatic({
      url: 'https://imgs.xkcd.com/comics/online_communities.png',
      projection: projection,
      imageExtent: extent,
    }),
  });

  map = new ol.Map({
    target: 'map',
    layers: [layer1],
    view: new ol.View({
      projection: projection,
      center: ol.extent.getCenter(extent),
      zoom: 2,
      maxZoom: 8,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);

}



function createMap_ImageWMS() {
  destroyMap();

	/*
	Создание карты на основе картинки из WMS сервера
	работает фигово - сервера блочат.
	
	WMS (Web Map Service) — это стандартный протокол OGC для предоставления картографических изображений.
  Он позволяет получать статичные или динамичные изображения карт.

	Работа WMS:
	Клиент отправляет запрос.
	Сервер возвращает изображение (обычно в форматах PNG, JPEG, GIF или TIFF).
	Можно запрашивать разные слои, стили, размеры и проекции.	
	
	Обязательные параметры:
	SERVICE=WMS
	REQUEST=GetMap
	LAYERS=layer_name
	CRS=EPSG:XXXX (или SRS=EPSG:XXXX, зависит от версии)
	BBOX= (границы области)
	WIDTH, HEIGHT (размер изображения)
	FORMAT (например, image/png)	
	*/
	
	

	//определяем новую проекцию
  proj4.defs(
    'EPSG:21781',
    '+proj=somerc +lat_0=46.95240555555556 +lon_0=7.439583333333333 +k_0=1 ' +
    '+x_0=600000 +y_0=200000 +ellps=bessel ' +
    '+towgs84=660.077,13.551,369.344,2.484,1.783,2.939,5.66 +units=m +no_defs',
  );
	
	//регистрируем её в OL
  ol.proj.proj4.register(proj4);

  let projection1 = new ol.proj.Projection({
    code: 'EPSG:21781',
    extent: [485869.5728, 76443.1884, 837076.5648, 299941.7864],
  });


  const extent = [420000, 30000, 900000, 350000];

  let source1 = new ol.source.ImageWMS({
    url: 'https://wms.geo.admin.ch/',
    //		url: '../images/wms.geo.admin.jpeg',
    crossOrigin: 'anonymous',
    params: {
      'LAYERS': 'ch.swisstopo.pixelkarte-farbe-pk1000.noscale',
      'FORMAT': 'image/jpeg',
    },
    serverType: 'mapserver',
  });

  let layer1 = new ol.layer.Image({
    extent: extent,
    source: source1
  });

  let source2 = new ol.source.ImageWMS({
    url: 'https://wms.geo.admin.ch/',
    crossOrigin: 'anonymous',
    params: { 'LAYERS': 'ch.bafu.hydroweb-warnkarte_national' },
    serverType: 'mapserver',
  });

  let layer2 = new ol.layer.Image({
    extent: extent,
    source: source2
  });

  map = new ol.Map({
    target: 'map',
    layers: [layer1,layer2],
    view: new ol.View({
      projection: projection1,
      center: ol.proj.fromLonLat([8.23, 46.86], projection1),
      extent: extent,
      zoom: 2,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);

}


function createMap_ImageWMS2() {
  destroyMap();


  const extent = [0, 0, 10000, 10000];

  let source1 = new ol.source.ImageWMS({
    url: 'https://wms.jpl.nasa.gov/wms.cgi',
    //		url: '../images/wms.geo.admin.jpeg',
    crossOrigin: 'anonymous',
    params: {
      'LAYERS': 'modis,global_mosaic',
      'FORMAT': 'image/jpeg',
    },
    serverType: 'mapserver',
  });

  let layer1 = new ol.layer.Image({
    extent: extent,
    source: source1
  });

  map = new ol.Map({
    target: 'map',
    layers: [layer1],
    view: new ol.View({
//      projection: projection1,
//      center: ol.proj.fromLonLat([8.23, 46.86], projection1),
      extent: extent,
      zoom: 2,
    }),
    interactions: ol.interaction.defaults.defaults({
      doubleClickZoom: false,
    }),
  });

  //При двойном клике - показываем координаты
  olu.addShowCoordHandler(map);

}






/*
let l1 = new ol.layer.Image({
  extent: [-13884991, 2870341, -7455066, 6338219],
  source: new ol.source.ImageWMS({
    //        url: 'https://ahocevar.com/geoserver/wms',
    url: '../images/topp-states.png',

    params: { 'LAYERS': 'topp:states' },
    ratio: 1,
    serverType: 'geoserver',
  }),
});

map.addLayer(l1);
*/