
import {olDemo} from "./1001-demo.js";


export const selectorsData1 = {
  interaction_basics() {
    /*
    interaction
      объекты, меняющие поведение карты. Добавляют новый функционал.

    Их можно добавить при создании карты:		
    map = new ol.Map({
      ...
      interactions: ol.interaction.defaults.defaults({doubleClickZoom: false}}).extend([select, dragBox]);
    });		

    Либо позже:		
    map.addInteraction(select);
  	
		
		
		ol.interaction.defaults
		  Набор действий по умолчанию. Его можно сконфигурировать опциями:
		
		onFocusOnly - карта активна только когда в фокусе. (default false) 	
		doubleClickZoom - зумить при двойном клике. boolean (default true) 	
		keyboard - можно использовать клавиатуру (default true) Вроде нужен фокус ввода.. 	
		mouseWheelZoom - (default true) 	
		shiftDragZoom - (default true) 	
		zoomDuration - время zoom-анимации в миллисекундах 	
		
		
    */


  },

	
  select_options() {

    /*
    ol.interaction.Select
      позволяет выделять векторные фичи. По умолчанию они выделяются при клике на них.

    Опции:
    layers - массив слоёв, фичи из которых нужно выделить. Или можно задать функцию фильтрации.

    style - стиль выбранных фич. По умолчанию делает линии голубыми, а закраску прозрачно-белой.

    multi - Можно ли выбирать несколько фич (defaults to false) 	

    features - коллекция, в которой будут храниться выделенные фичи.

    filter - функция фильтрации, определяющая, какие фичи можно выделять
		
		События:
		select - при изменении выделения 
		
    */
		log(olDemoGlobal.createSelect);
  },
  select_methods() {
		/*

		Методы Select:

		selectFeature(feature)
		  выделение

		clearSelection()
		deselectFeature(feature)
		  снятие выделение

		toggleFeature(feature)
		  переключает выделение
		  
		getFeatures()
		  выделенные фичи (коллекция)

		setActive(active)
		  позволяет деактивировать select

		setHitTolerance(hitTolerance)
		  чувствительность к выделению в пикселях. (default 0)			
		*/		
		
		//снять выделение
		olDemo.select.clearSelection();
		
  },
  selectAll() {

		//выделить всё		
		vectorSource.getFeatures().forEach(f=>{
			olDemo.select.selectFeature(f);
		});
		
  },
  t5() {
		/*
		ol.interaction.DragBox
		  Позволяет рисовать прямоугольную область (при нажатии на Ctrl).
		Используется для выделения или зума.
		
		Опции:
		className
		  CSS-класс области
			(defaults 'ol-dragbox')
			

		minArea
		  минимальная площать области
		 	(defaults 64) 	

		onBoxEnd
		  обработчик, вызываемый прямо перед событием boxend

		condition
		  функция, решающая, обрабатывать ли MapBrowserEvent 	
			
			
	  События:
		boxstart
		boxdrag
		boxend - окончание прорисовки.
		*/
		
		
  },
  t6() {
		/*
		getGeometry()
		  geometry прорисованной области.
		
		setActive(active)
		
		
		*/
		
		
  },



}
