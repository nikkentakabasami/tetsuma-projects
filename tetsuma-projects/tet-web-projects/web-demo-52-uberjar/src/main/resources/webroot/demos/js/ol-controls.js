//--------------------------------------------------------------------
//-----------различные элементы управления на карте--------------
//работает только для третьей версии!!!
//--------------------------------------------------------------------



function onRuControlOkButtonClick(){
  if (window.selectObjectCallback!=null){
    
    var result = new Object()

    result.selectedObjectGUID = window.selectedObjectGuid;
    result.selectedObjectType = window.selectedObject.type;
    result.selectedObjectName = window.selectedObject.name;
    
    if (window.selectedEquipment){
      result.selectedElementGUID = window.selectedEquipmentGuid;
      
      if (window.selectedEquipment.type){
        result.selectedElementType = window.selectedEquipment.type;
      } else if (window.selectedEquipment.pillarsCount) {
        //опоры
        result.selectedElementType = 2153;
      }
      
      result.selectedElementName = window.selectedEquipment.name;
    }
    
    result.sourceObjectId = window.sourceId;
    result.sourceObjectGUID = window.sourceGuid;
    result.sourceObjectName = window.sourceName;
    result.sourceObjectType = window.sourceType;
    
    
    result.powerCenterObjectGUID = window.powerCenterGuid;
    result.powerCenterObjectName = window.powerCenterName;
    result.powerCenterObjectId = window.powerCenterId;
    result.powerCenterObjectType = window.powerCenterType;
    
    result.powerFiderGUID = window.powerCenterLineGuid;
    result.powerFiderName = window.powerCenterLineName;
    result.powerFiderType = window.powerCenterLineType;
    result.powerFiderId = window.powerCenterLineId;

    
    
    
    
    result.selectedObjectId = window.selectedObjectId;
    result.selectedElementId = window.selectedEquipmentId;
    
    
    result.selectedFider04Id = window.selectedFider04Id;
    result.selectedFider04GUID = window.selectedFider04GUID;
    result.selectedFider04Name = window.selectedFider04Name;
    result.selectedFider04Type = window.selectedFider04Type;
    
    
    
    
/*
        window.sourceId = data.sourceId;
        window.sourceName = data.sourceName;
        window.sourceGuid = data.sourceGuid;
        window.sourceType = data.sourceType;
        
        
 * 
 */    
    
    
    
    
    
    result.traceLog = window.traceLog;
    
    window.selectObjectCallback(result);
  }
}



function onRuSelect(){
  var val = $("#substationRus").val();
  
  //ищем источник питания
//  window.towerid = val;
  
  window.selectedEquipmentId = parseInt(val);
  
  window.selectedEquipment = new Object();
  window.selectedEquipment.type = 120;
  window.selectedEquipment.name = $("#substationRus option:selected").text();
  
  
  
//  $("#selectedObjectSourceInfo").html("");
  clearObjectSourceInfo();
  
//  showSourceForSelectedObject();
  
  
  $("#okButton").prop("disabled",true);
  $("#loadPCButton").prop("disabled",val<=0);
  
  
}



function initOlControls(){

	  window.app = {};
	  var app = window.app;

		
		
	  //панель для показа текущего зума
	  app.CurrentZoomControl = function(opt_options) {

	    var options = opt_options || {};

	    var element = document.createElement('div');
	    element.className = 'current-zoom';
	    element.innerHTML = '10';
	    
	    
	    ol.control.Control.call(this, {
	      element: element,
	      target: options.target
	    });

	  };
	  ol.inherits(app.CurrentZoomControl, ol.control.Control);
	  
	  
	  //Панель, используемая в режиме печати
	  app.InfoControl = function(opt_options) {

	    var options = opt_options || {};

	    var element = document.createElement('div');
	    element.className = 'info-control';
	    element.innerHTML = 'Выберите область печати мышью (удерживая Ctrl)<br><button id="printRegion" class="btn btn-default" disabled>Печатать</button>';
	    
	    ol.control.Control.call(this, {
	      element: element,
	      target: options.target
	    });

	  };
	  ol.inherits(app.InfoControl, ol.control.Control);
	  

	  //Панель, показывающая длинну отрисовываемой линии
	  //Используется в режиме создания тех. решения
	  app.TSInfoControl = function(opt_options) {
	    var options = opt_options || {};


	    var element = document.createElement('div');
	    element.className = 'ts-info-control';
	    element.innerHTML = '';
	    
	    ol.control.Control.call(this, {
	      element: element,
	      target: options.target
	    });

	  };
	  ol.inherits(app.TSInfoControl, ol.control.Control);

	  
	  
	  //Панель для показа выбранных объектов и выбора РУ
	  //Используется в режиме выбора точки подключения
	  app.RuControl = function(opt_options) {
	    var options = opt_options || {};

	    //disabled="disabled"
	    var element = document.createElement('div');
	//    element.className = 'ru-control';
	    element.className = 'ru-control xy-selectable';
	    
	    var rusPanelCode = '<div id="rusPanel"><select id="substationRus" name="substationRus" class="form-control"></select> </div>';
	    var okButtonCode = '<br><button id="okButton" class="btn btn-default" onclick="onRuControlOkButtonClick();"><span class="glyphicon glyphicon-ok text-primary" style="font-size: 16px"></span> Выбрать объект</button>';

	    //кнопка загрузки ЦП
	//    var loadPCButtonCode = '<br><button id="loadPCButton" class="btn btn-default" onclick="onRuControlLoadPCButtonClick();"><span class="glyphicon glyphicon-link text-primary" style="font-size: 16px"></span>Вычислить центр питания</button>';
	    
	    element.innerHTML = 'Точка подключения: <span id="selectedObjectInfo"></span> '+rusPanelCode+'<div id="selectedObjectSourceInfo"></div>'+okButtonCode;
	    
	    ol.control.Control.call(this, {
	      element: element,
	      target: options.target
	    });

	  };
	  ol.inherits(app.RuControl, ol.control.Control);
	  	
	
}




