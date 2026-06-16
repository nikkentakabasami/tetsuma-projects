



//компонент с кнопкой, которая поворачивает карту прямо
export class RotateNorthControl extends ol.control.Control {

  constructor(opt_options) {
    const options = opt_options || {};

    const button = document.createElement('button');
    button.innerHTML = 'R';

    const element = document.createElement('div');
    element.className = 'test-rotate-control ol-unselectable ol-control';
    element.appendChild(button);

    super({
      element: element,
      target: options.target,
    });

    button.addEventListener('click', this.handleRotate.bind(this), false);
  }

  handleRotate() {
		//повернуть карту наискосок
		this.getMap().getView().setRotation(Math.PI / 2.6);
  }
}


