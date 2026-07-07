//--------------------------------------------------------------------
//-----------различные элементы управления на карте--------------
//--------------------------------------------------------------------

//Панель для вывода отладочных сообщений
export class DebugInfoControl extends ol.control.Control {

  $element;

  constructor(opt_options) {
    const options = opt_options || {};

    let element = document.createElement('div');
    element.className = 'debug-info-control';
    element.innerHTML = '-';

    super({
      element: element,
      target: options.target,
    });

    this.$element = $(element);

  }

  setLines(...lines) {
    let html = lines.join("<br>");
    this.$element.html(html);
  }

	addLine(line){
		this.$element.append(line+"<br>");	
	}
	
	clear() {
	  this.$element.html("");
	}

  addShowBaseDebugInfoHandler() {
    let view = this.getMap().getView();
    this.getMap().on('moveend', () => {

      let zs = view.getZoom().toLocaleString("ru", { maximumFractionDigits: 0 });
      let rs = view.getResolution().toLocaleString("ru", { maximumFractionDigits: 1 });


      this.setLines(`zoom: ${zs}, resolution: ${rs} map units/pixel`);
    });
  }

}


//Аналог Zoom. Но содержит панель для показа текущего зума
export class CurrentZoomControl extends ol.control.Control {

  $element;
  $zoomDiv;
  currentZoom;

  constructor(opt_options) {
    const options = opt_options || {};

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../fragments/olZoomControl.html", false); // false для синхронного вызова
    xhr.send();
    if (xhr.status != 200) {
      console.error("Ошибка загрузки");
      return;
    }

    let $el = $(xhr.responseText);

    super({
      element: $el.get(0),
      target: options.target,
    });

    this.$element = $el;
    this.$zoomDiv = $el.find(".olt-current-zoom");
  }


  init() {
    this.getMap().on('moveend', event => {
      let newZoom = this.getMap().getView().getZoom();
      if (this.currentZoom != newZoom) {
        this.currentZoom = Math.round(newZoom);
        this.$zoomDiv.text(this.currentZoom);
      }
    });
		
		map.dispatchEvent("moveend");
		
    this.$element.find(".olt-zoom-in").click(event => {
      this.zoom(this.currentZoom + 1);
    });
    this.$element.find(".olt-zoom-out").click(event => {
      this.zoom(this.currentZoom - 1);
    });
  }

  zoom(newZoom) {
    //зум с анимацией
    this.getMap().getView().animate({
      zoom: newZoom,
      duration: 200,
      easing: ol.easing.easeOut,
    });
  }


}

