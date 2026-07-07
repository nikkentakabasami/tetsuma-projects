import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";
import * as demodata from "./ol-demo-data.js";

let olDemo;

//объявляем глобальные переменные
"testLayer".split(",").forEach(name => window[name] = null);

let selectorsData1 = {
	
	
	
		doc1:`
	/*
	ol.layer.WebGLTile  (extend ol.layer.Tile)
	
	ol.source.DataTile
	Источник, предоставляющий данные в виде типизированного массива.
	
	
	loader
	Загрузчик данных
	 	

	Data loader. Called with z, x, and y tile coordinates. 
	Returns data for a tile or a promise for the same. 
	For loaders that generate images, the promise should not resolve until the image is loaded.
	
	
	*/
	`,
		doc2:`
	/*
	*/
	`,
	
	
	
	
	
  t1() {
    /*
		Генерация тайлов для слоя через canvas.
    */

    const size = 256;

    const canvas = document.createElement('canvas');
    canvas.width = size;
    canvas.height = size;

    const context = canvas.getContext('2d', { willReadFrequently: true });
    context.strokeStyle = 'white';
    context.textAlign = 'center';
    context.font = '24px sans-serif';




    testLayer = new ol.layer.WebGLTile({
      source: new ol.source.DataTile({
        loader: function(z, x, y) {
          const half = size / 2;

          const lineHeight = 30;

          context.clearRect(0, 0, size, size);
          context.fillStyle = 'rgba(100, 100, 100, 0.5)';
          context.fillRect(0, 0, size, size);
          context.fillStyle = 'black';
          context.fillText(`z: ${z}`, half, half - lineHeight);
          context.fillText(`x: ${x}`, half, half);
          context.fillText(`y: ${y}`, half, half + lineHeight);
          context.strokeRect(0, 0, size, size);
					let data = context.getImageData(0, 0, size, size).data; 
          return data;
        },
        // disable opacity transition to avoid overlapping labels during tile loading
        transition: 0,
      }),
    });

    map.addLayer(testLayer);

  },
  t2() {
    /*
    */
  },
}


class MyOLDemo extends old.OLDemo {

  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 1,
    });
  }

  initMap() {
    super.initMap();

    olu.addSelectInteractions(this);
    olu.addShowCoordHandler(this.map);

    this.vectorSource.addFeature(demodata.testFeatures.pointFeature);
    this.vectorSource.addFeature(demodata.testFeatures.lineFeature);

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
    debug: true
  });

  olDemo.initMap();


}



window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,
    moduleMode: true,
    customFormatter: olu.formatCoord,
    initFunction: initMap,
    beforeExec: () => {
    },
    afterSelectChange: () => {
      if (testLayer) {
        map.removeLayer(testLayer);
        testLayer = null;
      }

    }
  };
}





