import * as old from './ol-demo-base.js';
import * as olu from "./ol-demo-utils.js";

import { selectorsData1 } from "./1001-selectors-data.js";


export let olDemo;


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

function initMap() {
  olDemo = new MyOLDemo({
    withVectorLayer: true,
    withTileLayer: true,
  });
  olDemo.initMap();
}






export class MyOLDemo extends old.OLDemo {

  select;
  dragBox;

  createVectorSource() {
    olu.createDemoVectorSource1(this);
  }

  createView() {
    this.mapView = new ol.View({
      center: [0, 0],
      zoom: 2,
      constrainRotation: 16,
    });
  }

  initMap() {
    super.initMap();

  }


  //-----------------Select---------------------
  createSelect() {
    this.select = new ol.interaction.Select({
      //можно выбирать несколько фич
      multi: true,
      //можно выделять все фичи
      filter: function(feature) {
        return true;
      },
      //стиль с подкраской красным
      style: function(feature) {
        return old.defaultSelectStyle;
      },


    });
    //чтобы удобнее выделять
    this.select.setHitTolerance(5);

    this.select.on("select", e => {
      log("selected:", e.selected.length);
    });
  }


  //-----------------DragBox---------------------
  createDragBox() {
    this.dragBox = new ol.interaction.DragBox({
      //рисовать область только если нажат Ctrl
      condition: ol.events.condition.platformModifierKeyOnly,
    });

    this.dragBox.on('boxend', e => {

      const boxExtent = this.dragBox.getGeometry().getExtent();
      olu.logCoord(boxExtent);

      const boxFeatures = vectorSource.getFeaturesInExtent(boxExtent);
      log("boxFeatures:", boxFeatures.length);

      //выделяем все фичи в области
      boxFeatures.forEach((feature) => {
        this.select.selectFeature(feature);
      });

    });

    this.dragBox.on('boxstart', () => {
      this.select.clearSelection();
    });



  }

  createModify() {

    this.modify = new ol.interaction.Modify({
      features: this.select.getFeatures(),
      deleteCondition: event => {
        //удалять вершины при нажатии shift+click
        return ol.events.condition.shiftKeyOnly(event) && ol.events.condition.singleClick(event);
      },
      insertVertexCondition: event => {
        //не добавлять новые вершины
        return ol.events.condition.never(event);
      },


    });

    //организует прилипание новых точек к существующим
    this.snap = new ol.interaction.Snap({ source: this.vectorSource });

  }




  createInteractions() {
    this.createSelect();
    this.createDragBox();
    this.createModify();

    return ol.interaction.defaults.defaults({
      doubleClickZoom: false
    }).extend([this.select, this.dragBox, this.modify, this.snap]);

  }


}







