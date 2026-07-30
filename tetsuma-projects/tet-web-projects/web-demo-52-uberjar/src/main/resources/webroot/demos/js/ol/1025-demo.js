import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

let olDemo;

let geoms = {};

//объявляем глобальные переменные
"geomPoint,geomLine,geomPolygon,geomCircle".split(",").forEach(name => window[name] = null);


let selectorsData1 = {
  basic_geoms() {
    /*
    Основные геометрии:

    ol.geom.Point(coordinates);
      Точка

    new ol.geom.LineString(coordinates)
      Линия

    getCoordinates()
    getFirstCoordinate()
    getLastCoordinate()

    new ol.geom.Circle(center, radius)

    new ol.geom.Polygon(coordinates)
      Задаётся двойной массив координат.

		ol.geom.MultiPoint
		ol.geom.MultiLineString
		ol.geom.MultiPolygon
		  мульти геометрии
    */

    log(addTestGeoms);




  },

  geom_methods1: `
/*
ol.geom.Geometry
  Абстрактный класс для геометрических объектов.

-----События-----

change
	
-----Методы-----	
	
getExtent(opt_extent)

getClosestPoint(point)
  Поиск ближайшей точки геометрии

intersectsCoordinate(coord)
  Включает ли геометрия coord

simplify(tolerance)
  Создаёт упрощённую версию геометрии.
	tolerance - расстояние упрощения.

*/
geomLine;

geomLine.getExtent();

geomLine.getClosestPoint([835_169, 1_578_453]);

geomCircle.intersectsCoordinate([5_044_270, 6_870_886]);


`,
  rotate: `
/*
rotate(angle, centerCoord)
  вращение
*/
geomLine.rotate(Math.PI/10,[0,0]);

geomLine;
	
`,
  scale: `
/*
scale(sx, sy)
  растягивает геометрию ()
*/
//увеличить в 2 раза
geomLine.scale(2);

geomLine;
geomLine.getExtent();

`,
  transform: `
/*
transform(sourceProjection, targetProjection)
  Преобразование в другую координатную систему.
*/
geomLine.transform("EPSG:3857","EPSG:4326");

geomLine;

`,




}

function addTestGeoms() {

	if (!olDemo){
		return;
	}
	
  olDemo.vectorSource.clear();

  geoms.geomPoint = new ol.geom.Point([10, 10]);
  geoms.geomLine = new ol.geom.LineString([[2e6, 2e6], [1e6, 1e6], [1e6, 2e6]]);
  geoms.geomPolygon = new ol.geom.Polygon([[[3e6, 3e6], [4e6, 3e6], [4e6, 4e6]]]);
  geoms.geomCircle = new ol.geom.Circle([5e6, 7e6], 1e6);
	geoms.geomMultiPoint = new ol.geom.MultiPoint([[5e5, 5e5],[6e5, 6e5],[7e5, 7e5]]);
	
  Object.assign(window, geoms);

  let features = Object.values(geoms).map(geom => new ol.Feature(geom));
  olDemo.vectorSource.addFeatures(features);
}



class MyOLDemo extends old.OLDemo {


  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();
    olu.addShowCoordHandler(this.map);

    addTestGeoms();


  }


}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
    withFeatues: false,
    debug: true
  });

  olDemo.initMap();


}



window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //selectedOption: "t3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatOL,
    initFunction: initMap,
    beforeExec: () => {
    },
    afterSelectChange: () => {
      addTestGeoms();
    },
  };
}





