import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as olt from "./ol-template.js";
import { DebugInfoControl } from "./ol-controls2.js"


//объявляем глобальные переменные
"canvasElement,vectorContext".split(",").forEach(name => window[name] = null);

//let canvasElement,vectorContext;


let circleImage = new ol.style.Circle({
  radius: 5,
  fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
  stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
});

//стиль для полигона
let selStyle = new ol.style.Style({
  stroke: new ol.style.Stroke({
    color: 'rgb(0, 200, 0)',
    width: 3,
  }),
  fill: new ol.style.Fill({
    color: 'rgba(0, 200, 0, 0.2)',
  }),
  image: circleImage,
});



let selectorsData1 = {
  toContext() {
    /*
    ol.render.toContext(context, options) {CanvasImmediateRenderer}
		  Создаёт VectorContext, привязанный к заданному элементу canvas.
			Это позволяет тестировать прорисовку фич.
			
			
		ol.render.getVectorContext(event)
		
		
		
		
		
		
			
    */


    log(createMap);
  },


vectorContextt1() {

		
		/*
		setStyle(style)
		
		drawCircle(geometry)
		drawFeature(feature, style)
		drawGeometry(geometry)
				
		*/
		
    vectorContext.setStyle(selStyle);

    vectorContext.drawGeometry(
      new ol.geom.LineString([
        [10, 10],
        [90, 90],
      ]),
    );
    vectorContext.drawGeometry(
      new ol.geom.Polygon([
        [
          [2, 2],
          [98, 2],
          [2, 98],
          [2, 2],
        ],
      ]),
    );
    vectorContext.drawGeometry(new ol.geom.Point([88, 88]));






  },
  t2() {
  },
  doc1: `
/*
*/
`,
  doc2: `
/*
*/
`,
  doc3: `
/*
*/
`,

}




function destroyMap() {
}








//window.createMap = function () {
function createMap() {
  destroyMap();

  let $map = $("#map");
  $(`<canvas id="canvas"></canvas>`).appendTo($map);

  canvasElement = document.getElementById('canvas');
  vectorContext = ol.render.toContext(canvasElement.getContext('2d'), { 
		size: [200, 200],
//		pixelRatio: 10,
	 });

}



window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    beforeExec: () => {
    },
    afterSelectChange: () => {
    },
    initFunction: () => {
      createMap();
    },
  };
}
