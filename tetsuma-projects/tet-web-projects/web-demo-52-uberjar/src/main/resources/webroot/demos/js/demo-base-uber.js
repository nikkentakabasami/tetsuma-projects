
let demoAllPromise;

import { accordUtils } from '../../accord/js/accord-bundle.js';

let defaultSelectorsData = {

  console1() {

    /*
    console.log
      Удобный способ вывода отладочной информации.
      Можно задавать несколько параметров - они будут выведены через пробел
    */
    console.log("C+pgUp", "changes selected tabs.", 123, "+++");

    //# Вывод с форматированием
    //# %s — строка
    //# %d, %i — целое число
    //# %f — дробное число
    //# %o — объект в форматированном виде
    console.log(
      "%s theory is %d concept. numbers: %i, %d, %f, %f",
      "string", 1,
      48, 2.87, 3.14, Math.PI
    );




  },
  console2() {

    //# Замер времени
    //# console.time(timerId)
    //# console.timeEnd(timerId)
    //# 
    console.time();
    heavyTask();
    console.timeEnd();

    console.time("timer1");
    heavyTask();
    console.timeEnd("timer1");


  },

}

//переопределяется в конкретных демках
function makeDefaultOptions() {

  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: defaultSelectorsData,
    lfMode: true,
    selectedOption: null,
  }

}




$(document).ready(function() {

	//чтобы загрузить константы, которые будут использоваться в getBriefDemoOptions()
  accordUtils.addJSToPagePromise("../../accord/js/demo-base.js").then(r => {
		initUberDemo();
  });


});


function initUberDemo(){
	
	let options;
	if (typeof getBriefDemoOptions != 'undefined') {
	  options = getBriefDemoOptions();
	} else {
	  options = makeDefaultOptions();
	}


	let promises = [
	  accordUtils.addJSToPagePromise("../../accord/js/accord-publish.js", "module"),
	  accordUtils.addJSToPagePromise("../../accord/js/demo-base-classes.js"),
	  accordUtils.addJSToPagePromise("../../accord/js/demo-base-log.js"),
	  accordUtils.addJSToPagePromise("../../accord/js/demo-aux-data.js"),
	  accordUtils.addJSToPagePromise("../../accord/js/demo-base-parse.js"),
	  accordUtils.addCssToPagePromise("../../accord/css/accord.css"),
	  accordUtils.addCssToPagePromise("../../accord/css/tabbed-panel.css"),
	  accordUtils.addCssToPagePromise("../../accord/css/demo-base.css"),
	  accordUtils.addCssToPagePromise("../../accord/css/acc-form-elements.css"),
	];

	if (options.demoType == DT_OPENLAYERS) {

	  promises.push(
	    accordUtils.addJSToPagePromise("../../openlayers10/ol.js"),
			accordUtils.addJSToPagePromise("../../openlayers10/proj4-src.js"),
	    accordUtils.addCssToPagePromise("../../openlayers10/ol.css"),
	    accordUtils.addCssToPagePromise("../css/openlayers10-demo.css"),
	  );
		
		if (!options.moduleMode) {
			promises.push(
				accordUtils.addJSToPagePromise("../js/ol/ol-demo-base.js")
			);
		}
		
		
	}

	//загружаем все недостающие js и css
	demoAllPromise = Promise.all(promises);

	demoAllPromise.then(r => {
	  initBriefDemo(options);

	});

	window.demoAllPromise = demoAllPromise;
	
	
}


