import * as old from './ol-demo-base.js';
import * as olds from './ol-demo-styles.js';
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


  Style() {
    /*
    ol.style.Style
  
    geometry 	
      геометрия или функция, возвращающая геометрию, которая будет прорисовывать фичу.
      Эта геометрия заменит оригинальную геометрию!
  
    fill
    stroke 	
    image 	
    text 	
  
    renderer
    кастомная функция для прорисовки фич.
  
  
    ------------
    ol.style.Fill
  
    color
  
    ------------
    ol.style.Stroke
  
    color
    width
  
  
    fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
    stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
  
    */

		vectorStyle = new ol.style.Style({
		  stroke: new ol.style.Stroke({
				color: 'rgb(0, 200, 0)',
		    width: 3,
		  }),
		  fill: new ol.style.Fill({
				color: 'rgba(0, 200, 0, 0.2)',
		  }),
		});		
		
		vectorLayer.setStyle(vectorStyle);

  },

  customStyle() {
    /*
    Стиль слоя по умолчанию - бледно голубой.
    Можно задать свой стиль:
    */

    vectorLayer.setStyle(olds.defaultVectorStyle);
    log(olds.createDefaultVectorStyle)
  },


  iconStyle() {
    /*
    ol.style.Icon

    src

    anchor
    Как привязать иконку к точке
    (defaults to [0.5, 0.5] - по центру)

    anchorXUnitss
    anchorYUnits
    (defaults to 'fraction')

    'fraction'
    'pixels'

    anchorOrigin
    (defaults to 'top-left')

    color

    offset
    (defaults to [0, 0])
    с какого пикселя брать изображение

    offsetOrigin
    (defaults to 'top-left')


    displacement
    (defaults to [0,0])
    смещение иконки на карте

    width 	
    height
    можно задать размеры в пикселях, растянув иконку
  	
    scale
    можно растянуть через скейлинк
  	
    rotation
    поворот по часовой стрелке в радианах
    */

    //задать иконку в качестве стиля для точки 
    vectorStyle = new ol.style.Style({
      image: new ol.style.Icon(({
        src: '../../accord/icons/home.png'
      }))
    });
		vectorLayer.setStyle(vectorStyle);
  },
  iconStyle2() {
    //задать иконку и её цвет для точки.
    //Иконка будет касаться точки левым верхним углом
    vectorStyle = new ol.style.Style({
      image: new ol.style.Icon(({
        src: '../images/square.svg',
        anchor: [0, 0],
        color: '#BADA55',
      }))
    });
		vectorLayer.setStyle(vectorStyle);
  },

  iconStyle3() {
    //сожмём иконку в 2 раза и повернём.
    //Иконка будет касаться точки нижней центральной частью
    vectorStyle = new ol.style.Style({
      image: new ol.style.Icon(({
        src: '../images/icon.png',
        anchor: [0.5, 1],
        scale: 0.5,
        rotation: Math.PI / 4,

      }))
    });
		vectorLayer.setStyle(vectorStyle);


  },
  circleStyle() {

		/*
		ol.style.Circle
		Стиль для точек - рисование кружков.
		
		fill
		radius 	
		stroke 	
		
		displacement
		scale 	
		rotation 	
		
		*/
		
    //задать кружок в качестве стиля для точки 
    let circleImage = new ol.style.Circle({
      radius: 8,
      fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
      stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
    });
    vectorStyle = new ol.style.Style({
      image: circleImage,
    });

		vectorLayer.setStyle(vectorStyle);

  },
	
	RegularShape() {
		/*
		ol.style.RegularShape
		Стиль для точек - рисование полигонов и звёзд.
		
		points
		radius 	
		radius2 	
		angle 	

		displacement
		scale 	
		rotation
		
		stroke 	
		fill
		
		*/
		
		vectorStyle = new ol.style.Style({
		    image: new ol.style.RegularShape({
		      points: 5,
		      radius: 20,
		      radius2: 10,
		      displacement: [0, 20],
		      stroke: new ol.style.Stroke({
		        width: 2,
		        color: 'black',
		      }),
		      fill: new ol.style.Fill({
		        color: 'rgba(0, 120, 0, 0.2)',
		      }),
					angle: Math.PI/5
					
		    })
		  });		
			vectorLayer.setStyle(vectorStyle);


	},	
	
	
	StyleText() {
	  /*
	  ol.style.Text
	  позволяет добавить текст к векторным фичам.
		
		text 	
		
		font
		(defaults to '10px sans-serif')

		fill 	
		stroke 	
				
		offsetX 	
		offsetY 	

		scale 	
		rotation 	
		
		textAlign
		Possible values: 'left', 'right', 'center', 'end' or 'start'.
		Default is 'center'
		
		textBaseline
		(defaults to 'middle')
		Possible values: 'bottom', 'top', 'middle', 'alphabetic', 'hanging', 'ideographic'.

		padding
		(defaults to [0, 0, 0, 0])
		окружает текст белым гало
		
	  */

	  let circleImage = new ol.style.Circle({
	    radius: 5,
	    fill: new ol.style.Fill({ color: "rgba(0, 120, 0, 0.2)" }),
	    stroke: new ol.style.Stroke({ color: 'rgb(0, 120, 0)', width: 2 }),
	  });

	  //Задаём текст для точки
	  vectorStyle = new ol.style.Style({
	    image: circleImage,
	    text: new ol.style.Text({
	      text: '---',
	      font: '14px Calibri,sans-serif',
	      fill: new ol.style.Fill({ color: '#000000' }),
	      stroke: new ol.style.Stroke({ color: '#FFFFFF', width: 3 }),
				textBaseline: 'top',
				textAlign: 'left',
				padding: [5,5,5,5],
	      offsetX: 5,
	      offsetY: 5,
				
	    })

	  });
		vectorLayer.setStyle(f=>{
			vectorStyle.getText().setText(f.getId())
			return vectorStyle;
		});
		
	},	
	
	
  style_renderer() {

    //Задаём стиль фичи через renderer
    //Позволяет прорисовать фичу на уровне canvas
    let vectorStyle = new ol.style.Style({
      renderer: renderGradient
    });

    circleFeature.setStyle(vectorStyle);

    log(renderGradient);
  },
  style_renderer2() {

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

  style_geometry() {

    //можно задавать массив стилей - фича будет прорисовываться дважды
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
  ctx.font = `bold 14px verdana`;
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
    selectedOption: "StyleText",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}






