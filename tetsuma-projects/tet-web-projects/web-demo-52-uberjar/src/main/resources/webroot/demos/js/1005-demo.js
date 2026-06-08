


import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";


let olDemo;


let selectorsData1 = {


	fit1() {
		const feature = vectorSource.getFeatures()[0];
		const polygon = feature.getGeometry();

		//позиционировать карту так, чтобы показывался заданная геометрия, с заданными опциями
		mapView.fit(polygon, { padding: [170, 50, 30, 150] });
		
	},
	fit2() {
		const feature = vectorSource.getFeatures()[1];
		const point = feature.getGeometry();

		//точка будет показана в центре карты
		mapView.fit(point, { padding: [170, 50, 30, 150], minResolution: 50 });
		
	},
	centerOn() {
		const feature = vectorSource.getFeatures()[1];
		const point = feature.getGeometry();
		const size = map.getSize();
		mapView.centerOn(point.getCoordinates(), size, [570, 500]);
	},

	zoomIn() {
		const zoom = mapView.getZoom();
		mapView.setZoom(zoom + 1);
		
	},
	zoomOut() {
		const zoom = mapView.getZoom();
		mapView.setZoom(zoom - 1);
		
	},

}


window.getBriefDemoOptions = () => {
  return {
    demoType: DT_OPENLAYERS,
    selectorsData: selectorsData1,
    //    selectedOption: "init3",
    autoscrollLog1: true,
    formattedJson: true,

    initFunction: initMap,
    moduleMode: true
  };
}



class MyOLDemo extends old.OLDemo {

	attribution;	
	
  createVectorSource() {
    //olu.createDemoVectorSource1(this);
    olu.createDemoVectorSource2(this);
  }

	/*
  getVectorStyle() {
    return {
      'fill-color': 'rgba(255, 255, 255, 0.6)',
      'stroke-width': 1,
      'stroke-color': '#319FD3',
      'circle-radius': 5,
      'circle-fill-color': 'rgba(255, 255, 255, 0.6)',
      'circle-stroke-width': 1,
      'circle-stroke-color': '#319FD3',
    };
  }
	*/

  createControls() {
    //ol.control.Attribution
    //  Элемент управления для показа инфы по карте в правом нижнем углу.
    this.attribution = new ol.control.Attribution({
      collapsible: false,
    });

    return ol.control.defaults.defaults({ attribution: false }).extend([this.attribution]);
	}

	initMap() {
		super.initMap();
		this.map.on('change:size', checkSize);
		this.checkSize();
	}	
	

	
	
	

}


function checkSize() {
  const small = olDemo.map.getSize()[0] < 600;
  olDemo.attribution.setCollapsible(small);
  olDemo.attribution.setCollapsed(small);
}


function initMap() {

  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });

  olDemo.initMap();

}




