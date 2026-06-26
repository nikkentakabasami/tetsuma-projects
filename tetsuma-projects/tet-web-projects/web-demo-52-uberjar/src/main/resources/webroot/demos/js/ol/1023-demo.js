
import * as demoData from './ol-demo-data.js';



let r, coord1, coord2, geom1;

let selectorsData1 = {

  sp1: `
/*
ol.sphere
  содержит функции, позволяющие вычислять расстояния и площади на сфере.

По умолчанию считает что радиус сферы=6371008м.
Использует проекцию EPSG:3857.	

ol.sphere.getDistance(c1, c2, radius)
  расстояние в метрах между координатами (в градусах)
*/
coord1 = ol.proj.toLonLat([0,0]);  
coord2 = ol.proj.toLonLat([10_000,10_000]);
r = ol.sphere.getDistance(coord1,coord2)
	
`,
  sp2: `
/*	
getArea(geometry, options)
  получение площади геометрии
 
options: projection, radius
*/

geom1 = new ol.geom.Polygon([[ [0, 0], [1000, 1000],[1000,0] ]]); !
ol.sphere.getArea(geom1);
		
`,
  sp3: `
/*
getLength(geometry, options)
  Общая длина геометрии

options: projection, radius

*/
geom1 = new ol.geom.LineString([[0, 0], [1000, 1000], [1000, 2000]]); !
ol.sphere.getLength(geom1);
`,


}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: "coord1",
    debugMode: false,
    logObjectsAsJson: true,
    customFormatter: formatCoord,

    initFunction: () => {
    }

  };
}

