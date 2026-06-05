/**
 * Шаблон для демок.
 * 
 * Вспомогательные методы: ol-demo-base.js
 * 
 */


let selectorsData1 = {

  t1() {
  },
	t2() {
	},
	t3() {
	},



}


function getBriefDemoOptions() {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
  };
}


function initMap() {

	initMapBasic();


}




