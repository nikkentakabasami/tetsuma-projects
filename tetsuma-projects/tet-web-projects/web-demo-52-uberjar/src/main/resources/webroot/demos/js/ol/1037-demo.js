import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

/**
 * стили
 */

let olDemo;

//объявляем глобальные переменные
"f1,f2,f3,f4,f5".split(",").forEach(name => window[name] = null);


let selectorsData1 = {

  flatStyle1: `
/*
ol.style.flat
  плоский стиль (style literal) с выражениями (expressions).
	Синтаксис выражений вдохновлён Mapbox GL Expressions, но набор свойств — OpenLayers‑овский.

-------------------
ColorExpression
Цвет.
Может задаваться кучей способов:

'fill-color': 'blue',
'fill-color': 'rgba(255, 153, 51, 0.2)',	//css выражение
'fill-color': [0, 255, 0],								//массив
'fill-color': [0, 255, 0, 0.5],
'fill-color': ['get', 'color'],						//выражение


-------------------
IconOrigin
Выравнивание картинок

'bottom-left'
'bottom-right'
'top-left'
'top-right'.

-----------------
IconAnchorUnits
Единица выравнивания иконок

'fraction'
'pixels'



*/


	



`,
  FlatCircle: `
/*
FlatCircle
  Стиль для прорисовки точек в виде кружков.
		 
circle-radius
circle-fill-color 	
circle-stroke-color 	
circle-stroke-width 	

circle-displacement
(defaults to [0,0]) 	

circle-scale
(defaults to 1)
example: [1,2]

circle-opacity 	
circle-rotation
*/

@
vectorLayer.setStyle({
  'circle-radius': 10,
  'circle-fill-color': [0, 255, 0],
  'circle-stroke-color': 'white',
  'circle-stroke-width': 2,
});
@
`,
  FlatFill: `
/*
FlatFill
  Закраска для полигонов

fill-color

ex: 'fill-color': 'rgba(255, 153, 51, 0.8)',

fill-pattern-src
картинка для закраски

fill-pattern-size
fill-pattern-offset
(defaults to [0, 0]) 	

fill-pattern-offset-origin
(defaults to 'top-left') 	
*/

@
vectorLayer.setStyle({
  'fill-pattern-src': '../images/Walkingwithstrangers.jpg',
});
@

`,
  FlatIcon: `
/*
FlatIcon
  Задание иконки для точек.

icon-src 	
icon-anchor
(defaults to [0.5, 0.5]) 	

icon-anchor-origin
(defaults to 'top-left') 	

icon-anchor-x-units
icon-anchor-y-units 	IconAnchorUnits
(defaults to 'fraction') 	

'fraction'
'pixels'
 
icon-color
icon-cross-origin
  атрибут crossOrigin для загруженный изображений.
	
icon-offset
(defaults to [0, 0]) 	
с какого пикселя брать изображение

icon-displacement
(defaults to [0,0])
смещение иконки на карте

icon-offset-origin
(defaults to 'top-left') 	

icon-opacity
(defaults to 1) 	

icon-scale
(defaults to 1) 	

icon-width 	
icon-height 	
icon-rotation

icon-rotate-with-view
 (defaults to false) 	

icon-size

*/
@
vectorLayer.setStyle({
	//'icon-src': '../images/dot.svg',
	'icon-src': '../images/icon.png',
	'icon-rotate-with-view': false,
	'icon-anchor': [0.5, 1],
	'icon-opacity': 0.8
	
	//'icon-color': 'lightyellow',
	//'icon-offset': [5,5],
});
@
`,

  FlatShape: `
/*
FlatShape
Стиль для прорисовки точек в виде геом. фигур.

shape-points
  количество вершин

shape-fill-color
shape-stroke-color
shape-stroke-width

shape-radius 	


shape-radius2 	
(defaults to 0) 	
Второй радиус - позволяет рисовать звёзды

shape-angle
shape-rotation

shape-displacement
shape-opacity 	

shape-scale
(defaults to 1) 	


*/
@
vectorLayer.setStyle({
  'shape-points': 4,
  'shape-radius': 14,
	'shape-radius2': 6,
  'shape-fill-color': 'blue',
	'shape-stroke-width': 2,
	'shape-stroke-color':'black',
});


@
`,
  FlatStroke: `
/*
FlatStroke
Стиль для линий и границ полигонов

stroke-color 	
stroke-width 	

stroke-offset
сдвиг вправо, относительно направления линии

stroke-pattern-src

*/
@
//задаём стиль через css
vectorLayer.setStyle({
  'stroke-color': 'rgb(255, 153, 51)',
  'stroke-width': 2,
});
@
`,
  FlatText: `
/*
FlatText
Добавляет текстовую надпись.

text-value 	
text-font
(defaults to '10px sans-serif') 	


text-stroke-color
text-stroke-width
text-fill-color 	

text-rotation
(defaults to 0) 	

text-offset-x
text-offset-y

text-scale 	


text-rotate-with-view
(defaults to false) 	


text-align
Possible values: 'left', 'right', 'center', 'end' or 'start'.
Default is 'center'

text-justify 	

text-baseline
(defaults to 'middle') 	
Possible values: 'bottom', 'top', 'middle', 'alphabetic', 'hanging', 'ideographic'.

text-padding
(defaults to [0, 0, 0, 0]) 	

*/
@
vectorLayer.setStyle({
	'icon-src': '../images/dot.svg',
	
	'text-value': ['get', 'name'],
	'text-font': '12px sans-serif',
	'text-fill-color': 'white',
	'text-stroke-color': 'gray',
	'text-stroke-width': 2,
	'text-offset-y': 10,
	'text-baseline': 'top',
	'text-align': 'left',
	'text-scale': 1.2
});
@
`,
  expression1: `
/*
ol.style.expressions 

['get', 'attributeName', ]


['*', value1, value2, ...]
['/', value1, value2]
['+', value1, value2, ...]
['-', value1, value2]
['^', value1, value2]
  математические выражения


['<', value1, value2]
['>=', value1, value2]
логические


['interpolate', interpolation, input, stop1, output1, ...stopN, outputN]
  Интерполяция значения из первого диапазона во второй
	
['concat','val1','val2'...]	
  склеивание строковых значений

Не поддерживаются:
['time']	время в секундах
['zoom']  текущий зум


		
*/
@

vectorLayer.setStyle({
	
	'circle-radius': 10,
	'circle-fill-color': [
	  'interpolate',
	  ['linear'],
	  ['get', 'population'],
	  20000,
	  '#5aca5b',
	  300000,
	  '#ff6a19',
	],
	'text-value':['concat','name: ',['get', 'name']],
	
});


@
`,



  flatStyleExample1() {

    //базовый пример
    vectorLayer.setStyle({
      'stroke-color': 'rgb(255, 153, 51)',
      'stroke-width': 2,
      'fill-color': 'rgba(255, 153, 51, 0.2)',

      //стиль для точек
      'circle-radius': 10,
      'circle-fill-color': 'gray',
      'circle-stroke-color': 'white',
      'circle-stroke-width': 2,

      //добавляем надписи
      'text-value': [
        'concat',
        'id: ',
        ['get', 'name'],
      ],
      'text-font': '20px sans-serif',
      'text-fill-color': 'white',
      'text-stroke-color': 'gray',
      'text-stroke-width': 2,
      'text-align': 'left',
      'text-offset-y': 20,

    });

  },





  flatStyleExample2() {

    vectorLayer.setStyle({

      //стиль для точек
      //треугольник, чей цвет зависит от атрибута population
      'shape-points': 3,
      'shape-radius': 9,
      'shape-fill-color': [
        'interpolate',
        ['linear'],
        ['get', 'population'],
        20000,
        '#5aca5b',
        300000,
        '#ff6a19',
      ],
      'shape-rotate-with-view': true,

    });

  },






  flatStyleExample3() {

    vectorLayer.setStyle({

      //стиль для точек
      //круг, чей радиус зависит от атрибута population
      'circle-radius': [
        'interpolate',
        ['linear'],
        ['get', 'population'],
        40000,
        4,
        2000000,
        24,
      ],

      'circle-fill-color': 'gray',
      'circle-stroke-color': 'white',
      'circle-stroke-width': 2,
    });

  },




  flatStyleExample4() {

    vectorLayer.setStyle({

			//не поддерживается
      //'shape-rotation': ['*', ['time'], 0.13],
      'shape-points': 4,
      'shape-radius': 14,
      'shape-radius2': 4,

      'shape-fill-color': [
        'interpolate',
        ['linear'],
        ['get', 'population'],
        20000,
        '#ffdc00',
        300000,
        '#ff5b19',
      ],
    });

  },

  flatStyleExample111() {

    //не поддерживается
    vectorLayer.setStyle({

      'circle-radius': [
        'interpolate',
        ['exponential', 2],
        ['zoom'],
        5,
        1.5,
        15,
        1.5 * Math.pow(2, 10),
      ],
      'circle-fill-color': 'blue',
      'circle-displacement': [0, 0],
      'circle-opacity': 0.95,


    });

  },






}


class MyOLDemo extends old.OLDemo {


  createView() {
    this.mapView = new ol.View({
      center: [705_191, 5_781_298],
      zoom: 5,
    });
  }

  createVectorLayer() {
    olu.createDemoVectorSource2(this);
    this.vectorLayer = new ol.layer.Vector({
      source: this.vectorSource,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.select.on("select", e => {

      if (e.selected.length) {
        olu.logFeature(e.selected[0]);
      }

    });



  }


}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });

  olDemo.initMap();

}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    selectedOption: "expression1",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}






