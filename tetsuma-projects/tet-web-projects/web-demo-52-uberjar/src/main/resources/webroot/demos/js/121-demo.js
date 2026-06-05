

let map;
let svgExtent;





//создание карты, на основе заданной svg-картинки
function makeMap(svg) {

  svgExtent = [0, 0, svg.width.baseVal.value, svg.height.baseVal.value];
//	svgExtent = [0,0,400,650];
//	svgExtent = [0,0,2000,4000];
	
  svgWidth = svgExtent[2];
  svgHeight = svgExtent[3];

  svgHalfWidth = svgExtent[2] / 2;
  svgHalfHeight = svgExtent[3] / 2;


  var projection = new ol.proj.Projection({
    code: 'xkcd-image',
    units: 'pixels',
    extent: svgExtent
  });

  view = new ol.View({

//    center: [-782, 541],
		center: [0, 0],

    extent: [-svgHalfWidth - 50, -svgHalfHeight - 50, svgHalfWidth + 50, svgHalfHeight + 50],
    //    extent: svgExtent,

    projection: projection,
    zoom: -4,
		minZoom: -8,
  });


  map = new ol.Map({
    target: 'map',
    view: view,
		interactions : ol.interaction.defaults({
		  doubleClickZoom : false,
		  shiftDragZoom : false,
		  pinchRotate : false
		}),
  });

	/*
	controls: ol.control.defaults().extend(
	    [
	     ]),  
	new app.CurrentZoomControl(),
	new app.InfoControl(),
	new app.RuControl(),
	new app.TSInfoControl()
	*/
	
  //слой с SVG
  svgContainer = document.createElement('div');
  svgContainer.style.width = svgWidth + 'px';
  svgContainer.style.height = svgHeight + 'px';
  svgContainer.style.transformOrigin = 'top left';
  svgContainer.className = 'svg-layer';
  svgContainer.ownerDocument.importNode(svg);
  svgContainer.appendChild(svg);

  var svgLayer = new ol.layer.Layer({
    render: function(frameState) {
      var scale = 1 / frameState.viewState.resolution;
      var center = frameState.viewState.center;

      let frameHalfWidth = frameState.size[0] / 2;
      let frameHalfHeight = frameState.size[1] / 2;


      var cssTransform = ol.transform.composeCssTransform(
        frameHalfWidth, frameHalfHeight,
        scale, scale,
        0,
        -center[0] - svgHalfWidth, center[1] - svgHalfHeight);

      svgContainer.style.transform = cssTransform;
      svgContainer.style.opacity = this.getOpacity();

      return svgContainer;
    }
  });


	map.addLayer(svgLayer);

	/*
	map.on('dblclick', onDblClick);
	map.on('click', onClick);
	map.on('pointermove', onPointerMove);
	map.on('moveend', onMoveEnd);
*/	

}





$(document).ready(function(){
  
//	initOlControls();
	
	
	let svgUrl = '../images/example9.svg';

  //загружаем svg-картинку
  var xhr = new XMLHttpRequest();
  xhr.open('GET', svgUrl);
  
  xhr.addEventListener('load', function() {
    var svg = xhr.responseXML.documentElement;
    //создаём карту
    makeMap(svg);
    
  });
  xhr.send();
  


  

});











