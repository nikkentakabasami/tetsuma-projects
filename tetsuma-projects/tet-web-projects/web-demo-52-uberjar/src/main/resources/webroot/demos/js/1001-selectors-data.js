
import {OLDemo} from './ol-demo-base.js';


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
  	
    onFocusOnly (default false)
      карта активна только когда в фокусе. 	
    doubleClickZoom (default true)
      зумить при двойном клике. 	
    keyboard (default true)
      можно использовать клавиатуру. Вроде нужен фокус ввода.. 	
    mouseWheelZoom (default true)
		
    shiftDragZoom (default true)
		
    zoomDuration
      время zoom-анимации в миллисекундах 	
  	
    */
	 log(olDemoGlobal.createInteractions);


  },


  select_options() {

    /*
    ol.interaction.Select
      позволяет выделять векторные фичи. По умолчанию они выделяются при клике на них.

    Опции:
		
		сondition (default singleClick)
		  выделение

		toggleCondition
		  добавление/удаление из выделения (default shiftKeyOnly)
			То есть через shift+click можно выбрать несколько фич.
							
    layers - массив слоёв, фичи из которых нужно выделить. Или можно задать функцию фильтрации.

    style - стиль выбранных фич. По умолчанию делает линии голубыми, а закраску прозрачно-белой.

    multi - Можно ли выбирать несколько фич (defaults to false) 	

    features - коллекция, в которой будут храниться выделенные фичи.

    filter - функция фильтрации, определяющая, какие фичи можно выделять
  	
    События:
    select
		  при изменении выделения.
			Если выбрано несколько фич - будет вызван несколько раз! 
  	
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
    olDemoGlobal.select.clearSelection();

  },
  selectAll() {

    //выделить всё		
    vectorSource.getFeatures().forEach(f => {
      olDemoGlobal.select.selectFeature(f);
    });

  },
  DragBox() {
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
		  (default mouseActionButton)
    	
    	
    События:
    boxstart
    boxdrag
    boxend - окончание прорисовки.
  	
    Методы DragBox:

    getGeometry()
      geometry прорисованной области.

    setActive(active)
      позволяет деактивировать DragBox
  	
    */
    log(olDemoGlobal.createDragBox);


  },
  Modify() {
    /*
    ol.interaction.Modify
      Модификация существующих фич.
    Умеет перетаскивать вершины.
  	
    condition
      добавление/перемещение вершин  (default primaryAction)

    deleteCondition
      удаление вершин (default: alt+click)
  	
    insertVertexCondition 	Condition | 
      Вставка новой вершины (default always)
    	
    pixelTolerance
      чувствительность к изменениям. (default 10) 		
  	
    style
      стиль для точки модификации
      	
    source
      vectorSource с фичами, которые можно модифицировать
  	
    features
      коллекция фич, которые можно модифицировать
  	

    filter
      функция, решающая, можно ли модифицировать фичу.

    События:
      modifystart	
      modifyend

    Методы:
  	
    insertPoint(coordinate)
    removePoint(coordinate)
  	
    */
    log(olDemoGlobal.createModify);

  },










}
