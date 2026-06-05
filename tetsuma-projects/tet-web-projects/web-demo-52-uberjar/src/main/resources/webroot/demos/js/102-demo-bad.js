
let svgLayer;

let $currentZoomControl;

let currentZoom;
let currentExtent;
let svgContainer;


let drawInteraction;


let map;



$(() => {

  currentExtent = [0, 0, 4200, 2300];

  let width = currentExtent[2];
  let height = currentExtent[3];

  let hw = currentExtent[2] / 2;
  let hh = currentExtent[3] / 2;

  let projection = new ol.proj.Projection({
    code: 'xkcd-image',
    units: 'pixels',
    extent: currentExtent
  });


  let view = new ol.View({
    center: [-782, 541],

    //    extent: [-2500, -1200, 2500, 1200],
    extent: [-hw, -hh, hw, hh],
    //    extent: [0, 0, 4200, 2300],
    projection: projection,
    zoom: 6
  });


  let vectorSource = new ol.source.Vector({ wrapX: false });
  let vectorLayer = new ol.layer.Vector({
    source: vectorSource
  });
  vectorSource.addFeature(new ol.Feature(new ol.geom.Circle([0, 0], 100)));


  let map = new ol.Map({
    target: 'map',
    layers: [vectorLayer],
    view: view,
    /*
    interactions : ol.interaction.defaults({
      doubleClickZoom : false
//      dragPan : false,
//      shiftDragZoom : false,
//      pinchRotate : false
    })
    */


  });


  //При двойном клике - показываем координаты
  map.on('dblclick', function(evt) {
    let coord = map.getCoordinateFromPixel(evt.pixel);

		coord = [Math.round(coord[0]), Math.round(coord[1])];
		
    let fixedCoord = [Math.round(hw + coord[0]), Math.round(hh - coord[1])];

    console.log(fixedCoord," actual:",coord);
		
    $("#coord-span").text(fixedCoord);

    if (evt.originalEvent.ctrlKey) {
      let val = $("#objectType").val();

      let $use = document.createElementNS('http://www.w3.org/2000/svg', 'use');
      $use.setAttribute('x', "" + fixedCoord[0]);
      $use.setAttribute('y', "" + fixedCoord[1]);
      $use.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', "#" + val);

      $("svg").append($use);
//      console.log($use);

    }



  });



  svgContainer = document.createElement('div');
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '../images/template3.svg');

  xhr.addEventListener('load', function() {
    let svg = xhr.responseXML.documentElement;
    svgContainer.ownerDocument.importNode(svg);
    svgContainer.appendChild(svg);
  });
  xhr.send();


  svgContainer.style.width = width + 'px';
  svgContainer.style.height = height + 'px';
  svgContainer.style.transformOrigin = 'top left';
  svgContainer.className = 'svg-layer';

  map.addLayer(new ol.layer.Layer({
    render: function(frameState) {
      let scale = 1 / frameState.viewState.resolution;
      let center = frameState.viewState.center;

      //размеры карты (элемента #map) - не меняются
      let size = frameState.size;
      //      console.log("scale:"+scale+"  center:"+center+"  size:"+size);


      let cssTransform = ol.transform.composeCssTransform(
        size[0] / 2, size[1] / 2,
        scale, scale,
        0,
        -center[0] - width / 2, center[1] - height / 2);

      svgContainer.style.transform = cssTransform;
      svgContainer.style.opacity = this.getOpacity();
      return svgContainer;
    }
  }));
	/*
*/

  drawInteraction = new ol.interaction.Draw({
    source: vectorSource,
    type: "LineString"
  });
  //  map.addInteraction(drawInteraction);	

});






