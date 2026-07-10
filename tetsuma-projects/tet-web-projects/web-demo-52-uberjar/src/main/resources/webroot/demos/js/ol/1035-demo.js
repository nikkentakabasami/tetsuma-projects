import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import * as olds from './ol-demo-styles.js';

import {drawArrows,drawTestFeatures} from './1035-arrows.js';

//объявляем глобальные переменные
"canvasElement,vectorContext,cc,f1,f2,f3".split(",").forEach(name => window[name] = null);


let selectorsData1 = {
  toContext() {
    /*
    ol.render.toContext(context, options) {CanvasImmediateRenderer}
      Создаёт VectorContext, привязанный к заданному элементу canvas.
      Это позволяет тестировать стили и прорисовку фич.
	
    ol.render.getVectorContext(event)
    */

    log(createMap);
  },


  vectorContextt1() {
    /*
    ol.render.VectorContext
    умеет рисовать геометрии на canvas.
  	
    setStyle(style)
  	
    drawCircle(geometry)
    drawFeature(feature, style)
    drawGeometry(geometry)
    */
    vectorContext.setStyle(olds.defaultVectorStyle);

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
  drawFeature() {
    /*
    ol.render.VectorContext

    drawFeature(feature, style)
    */

    //многоугольник
    f1 = new ol.Feature(new ol.geom.Polygon([
      [
        [10, 10],
        [98, 10],
        [10, 98],
        [10, 10],
      ],
    ]));

    f2 = new ol.Feature(new ol.geom.LineString([[50, 100], [200, 100], [50, 200], [200, 200]]));

    vectorContext.drawFeature(f1, olds.defaultVectorStyle);
    vectorContext.drawFeature(f2, olds.defaultVectorStyle);

  },

  drawArrows() {
		drawArrows(vectorContext);
		log(drawArrows);
  },

	drawStars() {
		drawTestFeatures(vectorContext);
		log(drawTestFeatures);
		
		
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
  cc = canvasElement.getContext('2d');
  vectorContext = ol.render.toContext(cc, {
    size: [400, 400],
    //		pixelRatio: 10,
  });
	drawGrid();

}

function drawGrid(){
	if (!cc)
	  return;
	cc.clearRect(0, 0, canvasElement.width, canvasElement.height);
	cc.beginPath();
	for (let y = 50;y < 400;y += 50) {
			cc.moveTo(0, y);
			cc.lineTo(400, y);
			cc.moveTo(y, 0);
			cc.lineTo(y, 400);
	}
	cc.strokeStyle = "#e6e6e6";
	cc.stroke();	  
	
}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    selectedOption: "drawArrows",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    beforeExec: () => {
    },
    afterSelectChange: () => {
			//рисуем сетку
			drawGrid();

    },
    initFunction: () => {
      createMap();
    },
  };
}
