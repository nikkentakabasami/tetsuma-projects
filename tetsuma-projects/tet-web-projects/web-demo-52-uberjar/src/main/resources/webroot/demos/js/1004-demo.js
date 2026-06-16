import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

let olDemo;


let selectorsData1 = {
  t1() {

    /*
    ol.Feature
      Представляет векторный объект.
      Может содержать индивидуальный стиль (иначе используется стиль векторного слоя).
			extend ol.Object, так что содержит properties.

    В конструктор можно передать геометрию или properties	
  	
    */

    let f1 = new ol.Feature(new ol.geom.Point([640_950, 5_567_518]));
    let f2 = new ol.Feature({
      geometry: new ol.geom.Point([692_598, 5_455_023]),
      labelPoint: new ol.geom.Point([693_000, 5_456_000]),
      id: "p2",
      name: 'My Point2',
      population: 4000,
    });

    let f3 = new ol.Feature(
      new ol.geom.LineString([[1249135, 5433577], [1392248, 5958212]]));


    let f4 = new ol.Feature(
      new ol.geom.Polygon([[[900923, 5295031], [1139449, 5292597], [1027488, 5049257]]]));

    //id не задать в конструкторе, приходится задавать явно.
    f1.setId("f1");
    f2.setId("f2");
    f3.setId("f3");
    f4.setId("f4");

    olDemoGlobal.vectorSource.addFeatures([f1, f2, f3, f4]);

  },
  t2() {
    /*
    Методы Feature:
  	
    getId()
    setId(id)

    getGeometry()
    setGeometry(geometry)

    getProperties()
    getKeys()
    get(propName)
    set(key, value, silent)
      доступ к properties

    clone()
      Клонирует фичу, но не задаёт id		
  	
		setStyle(style)
		  можно задать стиль на уровне фичи.
		  Это используется в interaction.Select например.
			
    */

    let f = olDemoGlobal.vectorSource.getFeatures()[1];

    log("keys:", f.getKeys());

    let name = f.get("name")
    log("name:", name);

		//индивидуальный стиль
		var iconStyle = new ol.style.Style({
		  image: new ol.style.Icon( ({
		    src: '../../accord/icons/home.png'
		  }))
		});
		f.setStyle(iconStyle);		

  },
}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    initFunction: initMap,
  };
}

class MyOLDemo extends old.OLDemo {

  createVectorSource() {
    olu.createDemoVectorSource2(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [705_191, 5_781_298],
      zoom: 5,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.select.on("select", e => {
      clearLog();

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








