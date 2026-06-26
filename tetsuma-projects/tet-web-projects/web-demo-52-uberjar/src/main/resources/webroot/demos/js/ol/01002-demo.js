import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demoData from './ol-demo-data.js';


//объявляем глобальные переменные
"r, coord1, coord2, geom1".split(",").forEach(name => window[name] = null);

let selectorsData1 = {

  sp1: `
/*
ol.sphere
  содержит функции, позволяющие вычислять расстояния и площади на сфере.
*/
coord1 = ol.proj.toLonLat([0,0]);  
coord2 = ol.proj.toLonLat([10_000,10_000]);
r = ol.sphere.getDistance(coord1,coord2)
	
`,
  sp2: `
`,
  sp3: `
`,


}

window.getBriefDemoOptions = () => {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    selectedOption: "sp1",
    debugMode: false,
    logObjectsAsJson: true,
    customFormatter: olu.formatCoord,
    beforeExec: () => {
    },

    initFunction: () => {
    }

  };
}

