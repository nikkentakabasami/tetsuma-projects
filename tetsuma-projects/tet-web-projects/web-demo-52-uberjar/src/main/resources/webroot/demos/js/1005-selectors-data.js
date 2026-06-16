
export let selectorsData1 = {

	view1() {
	/*
	ol.View
	  2D вид карты.
	  Содержит координаты центра, зум, проекцию.


	События:
	
	change:center
	change:resolution
	change:rotation
	  кидаются при изменении properties: center,resolution,rotation
		Кидаются очень часто, лучше использовать map.on('moveend')
	 
	*/
	log(olDemoGlobal.createView);
	},
	options() {
		/*
		Опции:
			
		projection	
		  Проекция. 
		  Default is EPSG:3857 (Spherical Mercator).

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
		log(olDemoGlobal.createView);
		
	},

  get_set_methods: `
/*
Для основных опций имеются соотетствующие get/set-методы!
*/	

mapView.getResolution();

mapView.getResolutionForZoom(5);

mapView.getCenter();
mapView.getZoom();
mapView.setRotation(0.5);
		
`,
  adjustCenter() {
    /*
    adjustCenter(deltaCoordinate)
		  Сдвигает центр на относительную координату
    */
    mapView.adjustCenter([50000, 50000]);

  },
  adjustResolution() {
    /*
    adjustResolution(ratio, anchor)
      умножает текущий resolution на ratio
    */
    mapView.adjustResolution(2);
  },
  adjustRotation() {
    /*
    adjustRotation(delta, anchor) 	
      Поворачивает на заданный угол в радианах
    */
    mapView.adjustRotation(1);
  },

  adjustZoom() {
    /*
    adjustZoom(delta, anchor);
    */
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
      Center on coordinate and view position.
    */
    const feature = vectorSource.getFeatures()[1];
    const point = feature.getGeometry();
    const size = map.getSize();
    mapView.centerOn(point.getCoordinates(), size, [570, 500]);
  },


  fit1() {
		/*
	  fit(geometryOrExtent, options)
		  Показать на карте заданную геометрию или экстент
		*/
		
    const feature = vectorSource.getFeatures()[0];
    const polygon = feature.getGeometry();

    //позиционировать карту так, чтобы показывался заданная геометрия, с заданными опциями
    mapView.fit(polygon, { padding: [170, 50, 30, 150] });

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

mapView.getResolutionForZoom(5);

mapView.getResolution();

	
`,

  view4: `
`,








  setZoom() {

    const zoom = mapView.getZoom();
    mapView.setZoom(zoom + 1);




  },

}
