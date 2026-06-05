

export {defaultVectorStyle,defaultSelectStyle,defaultStyleFunction,defaultSelectStyleFunction};

const circleImage = new ol.style.Circle({
  radius: 5,
  fill: null,
  stroke: new ol.style.Stroke({ color: 'rgb(64, 64, 64)', width: 2 }),
});


const circleImageSelected = new ol.style.Circle({
  radius: 5,
  fill: null,
  stroke: new ol.style.Stroke({ color: 'red', width: 2 }),
});


//серый
const defaultVectorStyle = new ol.style.Style({
  stroke: new ol.style.Stroke({
    color: 'rgb(64, 64, 64)',
    width: 3,
  }),
  fill: new ol.style.Fill({
    color: 'rgba(179, 179, 179, 0.2)',
  }),
  image: circleImage,
});

//красный
const defaultSelectStyle = new ol.style.Style({
  stroke: new ol.style.Stroke({
    color: 'red',
    width: 3,
  }),
  fill: new ol.style.Fill({
    color: 'rgba(255, 0, 0, 0.2)',
  }),
  image: circleImageSelected,
});

//оранжевый
const selectStyle2 = new ol.style.Style({
  stroke: new ol.style.Stroke({
    color: 'rgb(255, 153, 51)',
    width: 3,
  }),
  fill: new ol.style.Fill({
    color: 'rgba(255, 153, 51, 0.2)',
  }),
  image: circleImageSelected,
});



function defaultStyleFunction(){
	return defaultVectorStyle;
} 

function defaultSelectStyleFunction(){
	return defaultSelectStyle;
} 







