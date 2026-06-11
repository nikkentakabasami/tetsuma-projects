import {DebugInfoControl,CurrentZoomControl} from "./ol-controls2.js"


export let selectorsData1 = {
  default_controls() {
		
		/*
		ol.control.defaults.defaults()
		  Элементы управления по умолчанию
		
		attribution: true
		attributionOptions
		  показывать инфу по карте в правом нижнем углу
		 	boolean (defaults to true) 	

		rotate: true
		rotateOptions 	

		zoom: true
		zoomOptions
		  кнопки с зумом 	
		
		
		*/
		log(olDemoGlobal.createControls);
		
		
  },
  control() {
		/*
		ol.control.Control
		  Видимый виджет с DOM-элементом на экране.
			Основа всех контролов, расширяет BaseObject.

		Опции:
		
		element
		  HTMLElement
			
		render
		  функция, которая вызывается когда контрол должен быть перерисован. 	

		target
		  куда добавить element, если не на viewport
			
		Методы:
		
		getMap()	
		

		Стандартные контролы:
		
		Attribution
		  ссылка на источник текущего тайлового слоя
		
		ScaleLine
		  линейка с текущим маштабом
		
		FullScreen
		  кнопка для перехода в полноэкранный режим

		OverviewMap
		  миникарта большего маштаба в левом нижнем углу
			
		Zoom
		  кнопки для зума
		
		*/
		log(CurrentZoomControl)
		log(olDemoGlobal.initMap);
		
		
  },
  DebugInfoControl() {
		
		/*
		DebugInfoControl
		  Кастомный контрол для показа отладочной инфы.
		*/
		log(DebugInfoControl)
		log(olDemoGlobal.initMap);
		
		olDemoGlobal.debugInfoControl.setLines("hello","there");
		
		
  },
  CurrentZoomControl() {
		/*
		CurrentZoomControl
		  Кастомный аналог Zoom. 
			Но содержит панель для показа текущего зума
		*/
		log(CurrentZoomControl)
		log(olDemoGlobal.initMap);
		
		
  },
  ScaleLine() {
		/*
		ScaleLine
		  линейка с текущим маштабом.
			По умолчанию показывается в левом нижнем углу.
		
		Опции:
		
		bar
		  показать полосу, вместо линейки
		 	
		className
		  свой css класс вместо стандартного ol-scale-line
		
		minWidth
		(default 64) 	

		maxWidth
		
	  units
		 	(default 'metric')
		*/
		
		
  },
  t6() {
  },
}

