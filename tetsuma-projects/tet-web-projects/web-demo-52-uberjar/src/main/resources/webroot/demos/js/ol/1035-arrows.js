

//точки в углах сетки с шагом 50 пикселей
const gridPointFeatures = [];
function createGridPointFeatues() {

  for (let y = 50;y < 400;y += 50) {
    for (let x = 50;x < 400;x += 50) {
      let f = new ol.Feature(
        new ol.geom.Point([x, y]),
      );
      gridPointFeatures.push(f);
    }
  }

}

let currentFeatureNo = 0;
function getNextPointFeature(restart = false){
	if (restart){
		currentFeatureNo = 0;
	}
	return gridPointFeatures[currentFeatureNo++];
}


//стили для рисования стрелок
let shaft, head, shaftStyle, headStyle;
export function createArrowStyle1() {

  shaft = new ol.style.RegularShape({
    points: 2,
    radius: 5,
    stroke: new ol.style.Stroke({
      width: 2,
      color: 'black',
    })
  });

  head = new ol.style.RegularShape({
    points: 3,
    radius: 5,
    fill: new ol.style.Fill({
      color: 'black',
    })
  });

  shaftStyle = new ol.style.Style({ image: shaft });
  headStyle = new ol.style.Style({ image: head });

}


export function drawArrows(vectorContext) {

  function drawArrow(angle, scale) {

    shaft.setScale([1, scale]);
    shaft.setRotation(angle);
    head.setDisplacement([
      0,
      head.getRadius() / 2 + shaft.getRadius() * scale,
    ]);

    head.setRotation(angle);

    let f = getNextPointFeature();
    vectorContext.drawFeature(f, shaftStyle);
    vectorContext.drawFeature(f, headStyle);
  }


  currentFeatureNo = 0;

  drawArrow(0, 1);
  drawArrow(Math.PI / 4, 2);
  drawArrow(Math.PI / 6, 4);
  drawArrow(Math.PI, 4);


}

export function drawTestFeatures(vectorContext) {

  let starStyle = new ol.style.Style({
    image: new ol.style.RegularShape({
      points: 5,
      radius: 20,
      radius2: 10,
      displacement: [0, 20],
      stroke: new ol.style.Stroke({
        width: 2,
        color: 'black',
      }),
      fill: new ol.style.Fill({
        color: 'blue',
      })
    })
  });

	currentFeatureNo = 0;

	let f = getNextPointFeature(true);
  vectorContext.drawFeature(f, starStyle);

	f = getNextPointFeature();
  starStyle.getImage().setRotation(Math.PI / 4);
  vectorContext.drawFeature(f, starStyle);


	f = getNextPointFeature();
  starStyle.getImage().setRotation(Math.PI * 2 / 10);
  starStyle.getImage().setDisplacement([0, 0]);
  vectorContext.drawFeature(f, starStyle);


}




//----------инициализация-----------

createGridPointFeatues();
createArrowStyle1();

