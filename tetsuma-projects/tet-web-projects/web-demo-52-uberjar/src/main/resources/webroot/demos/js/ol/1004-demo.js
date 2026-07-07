import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

/**
 * Фичи и стили
 */

let olDemo;

//объявляем глобальные переменные
"f1,f2,f3,f4,f5".split(",").forEach(name => window[name] = null);


let selectorsData1 = {
  Feature1() {

    /*
    ol.Feature
      Представляет векторный объект.
      Может содержать индивидуальный стиль (иначе используется стиль векторного слоя).
      extend ol.Object, так что содержит properties.

    В конструктор можно передать геометрию или properties	
  	
    */

    //точка
    f1 = new ol.Feature(new ol.geom.Point([640_950, 5_567_518]));

    f2 = new ol.Feature({
      geometry: new ol.geom.Point([692_598, 5_455_023]),
      labelPoint: new ol.geom.Point([693_000, 5_456_000]),
      id: "p2",
      name: 'My Point2',
      population: 4000,
    });

    //ломаная линия
    f3 = new ol.Feature(
      new ol.geom.LineString([[1249135, 5433577], [1392248, 5958212]]));

    //многоугольник
    f4 = new ol.Feature(
      new ol.geom.Polygon([[[900923, 5295031], [1139449, 5292597], [1027488, 5049257]]]));

    //круг
    f5 = new ol.Feature({
      geometry: new ol.geom.Circle([679_663, 5_667_780], 40000),
    });

    //id не задать в конструкторе, приходится задавать явно.
    f1.setId("f1");
    f2.setId("f2");
    f3.setId("f3");
    f4.setId("f4");
    f5.setId("f5");




    olDemoGlobal.vectorSource.addFeatures([f1, f2, f3, f4, f5]);

  },
  Feature2() {
    /*
    Методы Feature:
  	
    getId()
    setId(id)

    getGeometry()
    setGeometry(geometry)

    clone()
      Клонирует фичу, но не задаёт id		
  	
    setStyle(style)
      можно задать стиль на уровне фичи.
      Это используется в interaction.Select например.
    	
    */

    log("keys:", pointFeature.getKeys());

    let name = f1.get("name")
    log("name:", name);


  },


  styleDoc: `
/*

ol.style.Style

---Опции---

geometry 	
  геометрия или функция, возвращающая геометрию, которая будет прорисовывать фичу.
	Эта геометрия заменит оригинальную геометрию!

fill
stroke 	
image 	
text 	

renderer
  кастомная функция для прорисовки фич.

hitDetectionRenderer
Custom renderer for hit detection.

zIndex 	

*/
`,




  customStyle() {
    /*
    Стиль слоя по умолчанию - бледно голубой.
    Можно задать свой стиль:
    */
    vectorLayer.setStyle(old.defaultVectorStyle);
  },



  iconStyle() {
    //задать иконку в качестве стиля для точки 
    let iconStyle = new ol.style.Style({
      image: new ol.style.Icon(({
        src: '../../accord/icons/home.png'
      }))
    });
    pointFeature.setStyle(iconStyle);
  },



  iconStyle2() {
    //задать иконку и её цвет для точки 
    let iconStyle = new ol.style.Style({
      image: new ol.style.Icon(({
        src: '../images/square.svg',
        color: '#BADA55',
      }))
    });
    pointFeature.setStyle(iconStyle);
  },


	iconStyle3() {
	  //задать иконку и её цвет для точки 
	  let iconStyle = new ol.style.Style({
	    image: new ol.style.Icon(({
	      src: '../images/icon.png',
			anchor: [0.5, 46],
			anchorXUnits: 'fraction',
			anchorYUnits: 'pixels',
			
			//Можно растянуть иконку
			//width: 100,
			//height: 100,
			
	    }))
	  });
	  pointFeature.setStyle(iconStyle);
		
		
	},	
	


  circleStyle() {

    //задать кружок в качестве стиля для точки 
    let circleImage = new ol.style.Circle({
      radius: 5,
      fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
      stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
    });

    let vectorStyle = new ol.style.Style({
      image: circleImage,
    });

    pointFeature.setStyle(vectorStyle);

    //стиль для полигона
    vectorStyle = new ol.style.Style({
      stroke: new ol.style.Stroke({
        color: 'rgb(0, 200, 0)',
        width: 3,
      }),
      fill: new ol.style.Fill({
        color: 'rgba(0, 200, 0, 0.2)',
      }),
    });
    polygonFeature.setStyle(vectorStyle);

  },
  style2() {

    //Задаём стиль фичи через renderer
    //Позволяет прорисовать фичу как угодно
    let vectorStyle = new ol.style.Style({
      renderer(coordinates, state) {
        renderGradient(coordinates, state);
      }

    });
    circleFeature.setStyle(vectorStyle);

    log(renderGradient);
  },
  style3() {

    //Прорисовываем текст.
    let vectorStyle = new ol.style.Style({
      renderer(coordinates, state) {
        renderGradient(coordinates, state);
        renderLabelText(coordinates, state, 'rgba(120, 120, 120, 1)');
      },

      hitDetectionRenderer(coordinates, state) {
        const [x, y] = coordinates[0];
        const ctx = state.context;
        renderLabelText(ctx, x, y, 'rgba(255,255,255,1)');
      },

    });
    circleFeature.setStyle(vectorStyle);

    log(renderGradient);
    log(renderLabelText);


  },

  StyleArray() {

    //можно задавать массив стилей
    const styles = [
      new ol.style.Style({
        stroke: new ol.style.Stroke({
          color: 'blue',
          width: 3,
        }),
        fill: new ol.style.Fill({
          color: 'rgba(0, 0, 255, 0.1)',
        }),
      }),
      new ol.style.Style({
        image: new ol.style.Circle({
          radius: 5,
          fill: new ol.style.Fill({
            color: 'orange',
          }),
        }),

        //вершины многоугольника можно прорисовывать своей геометрией
        geometry: function(feature) {
          const coordinates = feature.getGeometry().getCoordinates()[0];
          return new ol.geom.MultiPoint(coordinates);
        },
      }),
    ];

    polygonFeature.setStyle(styles);


  },


  StyleText() {
    /*
    ol.style.Text
      позволяет добавить текст к векторным фичам.
    */


    let circleImage = new ol.style.Circle({
      radius: 5,
      fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
      stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
    });

    //Задаём текст для точки
    let vectorStyle = new ol.style.Style({
      image: circleImage,

      text: new ol.style.Text({
        text: 'My Label',
        font: '14px Calibri,sans-serif',
        fill: new ol.style.Fill({ color: '#000000' }),
        stroke: new ol.style.Stroke({ color: '#FFFFFF', width: 3 }), // Creates a halo effect for legibility
        offsetX: 0,
        offsetY: 15,
        placement: 'point' // or 'line'
      })

    });
    pointFeature.setStyle(vectorStyle);

  },






  styleCss() {

    //задаём стиль через css
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
      'text-offset-y': 20,

    });






  },

	styleWind() {
		
		//https://openlayers.org/en/latest/examples/wind-arrows.html
		
		},






  styleDoc2: `
/*
*/
`,
  styleDoc3: `
/*
*/
`,




}




function renderGradient(coordinates, state) {
  const [[x, y], [x1, y1]] = coordinates;
  const ctx = state.context;
  const dx = x1 - x;
  const dy = y1 - y;
  const radius = Math.sqrt(dx * dx + dy * dy);

  const gradient = ctx.createRadialGradient(
    x,
    y,
    0,  //innerRadius
    x,
    y,
    radius * 1.4,  //outerRadius
  );
  gradient.addColorStop(0, 'rgba(255,0,0,0)');
  gradient.addColorStop(0.6, 'rgba(255,0,0,0.2)');
  gradient.addColorStop(1, 'rgba(255,0,0,0.8)');
  ctx.beginPath();
  ctx.arc(x, y, radius, 0, 2 * Math.PI, true);
  ctx.fillStyle = gradient;
  ctx.fill();

  ctx.arc(x, y, radius, 0, 2 * Math.PI, true);
  ctx.strokeStyle = 'rgba(255,0,0,1)';
  ctx.stroke();


}

function renderLabelText(coordinates, state, stroke) {
  const [[x, y]] = coordinates;
  const ctx = state.context;
  const labelText = 'Columbus Circle';

  ctx.fillStyle = 'rgba(255,0,0,1)';
  ctx.strokeStyle = stroke;
  ctx.lineWidth = 1;
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  ctx.font = `bold 30px verdana`;
  ctx.filter = 'drop-shadow(7px 7px 2px #e81)';
  ctx.fillText(labelText, x, y);
  ctx.strokeText(labelText, x, y);
};



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
    selectedOption: "iconStyle3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}






