
export let selectorsData1 = {

  view1() {
    /*
    ol.View
      2D вид карты.
      Содержит координаты центра, зум, проекцию.
    */
    log(olDemoGlobal.createView);
  },

  events() {
    /*
    События View:

    change:center
    change:resolution
    change:rotation
      кидаются при изменении properties: center,resolution,rotation
      Кидаются очень часто, лучше использовать map.on('moveend')
     
    */

    mapView.on(["change:center", "change:resolution", "change:rotation"], event => {
      console.log(event.type);
    });

  },

	view_options:`
/*
Опции View:
	
projection	
  Проекция. 
  Default is EPSG:3857 (web mercator).

center
  Координаты центра

zoom
  Зум

rotation
  Вращение в радианах

resolution	number
  Начальное разрешение.
  Задаётся в метрах(map units) на пиксель.

maxZoom	number
(default: 28)

minZoom	number
(default: 0)
  	
extent		ol.Extent
  Регион, за пределы которого не должен выйти центр карты.

enableRotation
(default: true)

	
maxResolution	number	
default: 40075016.68557849 / 256 = 156543

minResolution	number
  Будет равным maxResolution/ 2^28 = 0.0005831682455839253.


zoomFactor	number	
  Используется для вычисления разрешения.
 
*/
olDemoGlobal.createView
`,


  get_set_methods: `
/*
Для основных опций имеются соотетствующие get/set-методы!
*/	

mapView.getKeys();

mapView.getResolution();
mapView.getResolutionForZoom(5);

mapView.getCenter();
mapView.getZoom();

mapView.getProjection().getCode();


mapView.setRotation(0.5);
		
`,

  setZoom() {
    //zoom
    const zoom = mapView.getZoom();
    mapView.setZoom(zoom + 1);
  },

  extent() {
		
		//extent - Регион, за пределы которого не должен выйти центр карты.
    mapView = new ol.View({
      center: [328627.563458, 5921296.662223],
      zoom: 8,
      extent: [-572513.341856, 5211017.966314, 916327.095083, 6636950.728974],
    });
		map.setView(mapView);
  },



  adjustMethods: `
/*
adjust-методы меняют основные параметры на дельту:

adjustCenter(deltaCoordinate)
  Сдвигает центр на относительную координату

adjustResolution(ratio, anchor)
  умножает текущий resolution на ratio

djustRotation(delta, anchor) 	
  Поворачивает на заданный угол в радианах

adjustZoom(delta, anchor);
*/
mapView.adjustCenter([50000, 50000]);
`,

  adjustResolution() {
    //отдаление в 2 раза
    mapView.adjustResolution(2);
  },
  adjustRotation() {
    //поворот на радиан
    mapView.adjustRotation(1);
  },

  adjustZoom() {
    //зум в 2 раза
    mapView.adjustZoom(2);
  },
  animate() {
    /*
    animate(var_args)
      Задать новые параметры вида с анимацией
    */

    mapView.animate({
      zoom: 5,
      center: [0, 0],
      duration: 200,
      easing: ol.easing.easeOut,
    });

  },

  centerOn() {
    /*
    centerOn(coordinate, size, position)
      Показывает регион, размером size, по заданной координате coordinate.
      С относительным сдвигом position.
      Не меняет zoom.
    */

    //Показать точку pointFeature со сдвигом в 100 пикселей
    mapView.centerOn(pointFeature.getGeometry().getCoordinates(), map.getSize(), [100, 100]);
  },


  fit1() {
    /*
    fit(geometryOrExtent, options)
      Показать на карте заданную геометрию или экстент
    Опции:
  	
    padding - [top, right, bottom, left]
    maxZoom 
    duration - длительность анимации
    	
    	
    */

    //позиционировать карту так, чтобы показывался заданная геометрия, с заданными опциями
    mapView.fit(polygonFeature.getGeometry(), { padding: [170, 50, 30, 150] });

  },
  fit2() {
    const feature = vectorSource.getFeatures()[1];
    const point = feature.getGeometry();

    //точка будет показана в центре карты
    mapView.fit(point, { padding: [170, 50, 30, 150], minResolution: 50 });

  },



  calc_methods: `
/*
calculateExtent(size)
  вычисляет экстент для заданного размера в пикселях
	
*/	

mapView.calculateExtent();

mapView.calculateExtent([100,100]);
	
`,



}
