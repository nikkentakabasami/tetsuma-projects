
let ext1, ext2, projection1, coord1, coord2, func1;

let selectorsData1 = {


  Projection: `
	/*
ol.proj.Projection
  как правило этот объект не надо создавать явно.
	Обычно он задаётся/получается строковым кодом.
	
опции:

code 	
units 	
extent

Методы:

getCode()
getExtent()
  валидный экстент

getMetersPerUnit()
getUnits()
getWorldExtent()
isGlobal()
  покрывает весь мир
*/
	
//Web Mercator (Spherical Mercator, WGS 84 Web Mercator ) (EPSG:3857, EPSG:3785)
//  Система координат, основанная на проецировании земли как идеального шара на цилиндр.
//  Единицы имерения - псевдометры (на экваторе равен реальному метру)
//Самая распространённая система, используется по умолчанию
projection1 = ol.proj.get("EPSG:3857"); !


projection1.getCode();
projection1.getExtent();

projection1.getMetersPerUnit();
projection1.getUnits();
projection1.getWorldExtent();
projection1.isGlobal();

//World Geodetic System (WGS 84, EPSG:4326)
//  Единицы имерения - градусы
projection1 = ol.proj.get("EPSG:4326"); !

projection1.getCode();
projection1.getExtent();

projection1.getMetersPerUnit();
projection1.getUnits();
projection1.getWorldExtent();
projection1.isGlobal();
	
	
	`,

  Projection2: `
/*
Создание собственной проекции для просмотра изображений.
*/
@
projection1 = new ol.proj.Projection({
  code: 'my-image-projection',
  units: 'pixels',
  extent: [0, 0, 1024, 968],
});
@ !

projection1.getCode();
projection1.getExtent();

projection1.getMetersPerUnit();
projection1.getUnits();
projection1.getWorldExtent();
projection1.isGlobal();
`,

  units: `
/*
ol.proj.Units
  коды единиц измерения координат.
*/

ol.proj.Units.METERS_PER_UNIT
`,


  proj: `
/*
ol.proj

ol.proj.transform(coordinate, source, destination)
  Преобразование координат из одной проекции в другую

*/	
//Конвертируем WGS в web mercator
ol.proj.transform([-1.81185, 52.44314], 'EPSG:4326', 'EPSG:3857');

/*
ol.proj.fromLonLat(coordinate, projection)
  Преобразует долготу-широту (WGS) в координаты заданной проекции (default Web Mercator).
*/	
ol.proj.fromLonLat([37.41, 8.82])	

//60 морских миль на восток
ol.proj.fromLonLat([1, 0])

/*
ol.proj.toLonLat(coordinate, projection)
  конвертация координаты в WGS
*/	
//получаем wgs координаты
ol.proj.toLonLat([1000_000, 1000])	


/*
ol.proj.get(projectionLike)
  Получение проекции по коду
*/	
projection1 = ol.proj.get("EPSG:3857"); !
projection1.getExtent();


/*
ol.proj.addProjection(projection)
  Добавляет объект Projection в список поддерживаемых проекций, которые можно найти по коду.

ol.proj.equivalent(projection1, projection2)
  Сравнение проекций.

ol.proj.getTransform(source, destination)  {ol.TransformFunction}
  Возвращает функцию для преобразования координат одной проекции в координаты другой.
*/	
	
/*


ol.proj.setProj4(proj4)
ol.proj.getPointResolution(projection, resolution, point){number}
ol.proj.addCoordinateTransforms(source, destination, forward, inverse)
ol.proj.addEquivalentProjections(projections)
ol.proj.transformExtent(extent, source, destination){ol.Extent}
	*/
`,


  Extent1: `
/*
ol.extent
  функции для работы с экстентами.

Extent
  Прямоугольный регион на карте.
  Массив из 4-х чисел: 2 склеенные координаты.
	[minx, miny, maxx, maxy]. Ось y направлена вверх!


--Создание экстентов

createEmpty()
  Пустой экстент (содержащий в ячейках Infinity)

boundingExtent(coordArray)
  экстент, включающий в себя все заданные координаты.

getBottomLeft(extent)
getBottomRight(extent)
getTopLeft(extent)
getTopRight(extent)
getCenter(extent)
  координаты

getSize(ext1)
  Возвращает [width, height]

getHeight(extent)
getWidth(extent)	
  Размеры
	
ol.extent.getArea(ext1)
  площадь	
*/

//экстент
ext1 = [100,100, 200, 500]; !

//пустой экстент
ext2 = ol.extent.isEmpty([Infinity,Infinity,-Infinity,-Infinity])

//или его можно создать так
ext3 = ol.extent.createEmpty();

//экстент, включающий в себя все заданные координаты.
ol.extent.boundingExtent([[10,20],[100,5],[30,30]])

//координаты
ol.extent.getTopRight(ext1)
ol.extent.getBottomRight(ext1)
ol.extent.getCenter(ext1)

//размеры
ol.extent.getSize(ext1)
ol.extent.getHeight(ext1)
ol.extent.getWidth(ext1)
ol.extent.getArea(ext1)
	`,
  Extent2: `
/*
ol.extent

extend(extent1, extent2, opt_extent)
  объединение

getIntersection(extent1, extent2, opt_extent)
  пересечение	
	
containsCoordinate(extent, coordinate)
containsExtent(extent1, extent2)
containsXY(extent, x, y)
intersects(extent1, extent2)
equals(extent1, extent2)
isEmpty(extent)	
  Проверки
	
extend(extent1, extent2)
  записывает в extent1 объединение двух экстентов 
*/

ext1 = [100,100, 200, 200]; !
ext2 = [10,150, 300, 160]; !

//объединение двух экстентов
ol.extent.extend(ext1, ext2);

//пересечение
ol.extent.getIntersection(ext1, ext2);

//пустое пересечение
ext3 = ol.extent.getIntersection(ext1, [1,1,10,10]);
ol.extent.isEmpty(ext3);

//проверки
ol.extent.intersects(ext1, ext2);
ol.extent.isEmpty([Infinity,Infinity,-Infinity,-Infinity]);
ol.extent.containsCoordinate(ext1, [120,120]);
`,

  coord1: `
/*
ol.coordinate
  утилиты для работы с координатами.


ol.coordinate.add(coordinate, delta)
  добавляет к координате дельту
*/

coord1 = [7.85, 47.9]; !
ol.coordinate.add(coord1, [-2, 4]);


/*
rotate(coordinate, angle)
  вращает координату относительно 0,0
*/
ol.coordinate.rotate(coord1, Math.PI / 2);

`,
  coord2: `

coord1 = [7.8532, 47.9123]; !

/*
toStringXY(coordinate, fractionDigits = 0)
  Форматирует координату как 2 числа через запятую.
*/
ol.coordinate.toStringXY(coord1);
ol.coordinate.toStringXY(coord1,2);


/*
format(coordinate, template, fractionDigits = 0)
  Форматирует координату, используя заданный шаблон
*/

ol.coordinate.format(coord1, 'Coordinate is ({x}|{y}).');

ol.coordinate.format(coord1, 'Coordinate is ({x}|{y}).',2);


/*
toStringHDMS(coordinate, fractionDigits)
  Форматирует координату, выводя градусы, минуты, секунды...
*/

ol.coordinate.toStringHDMS(coord1);


/*
createStringXY(fractionDigits = 0)
  Возвращает функцию (CoordinateFormat) для форматирования координаты в строку.

*/


func1 = ol.coordinate.createStringXY(); !
func1(coord1);
func1([123_567, 455_888]);

func1 = ol.coordinate.createStringXY(2); !
func1(coord1);


`,

  coord3() {

		//кастомная функция для форматирования координат
    function fcp(d, mfd = 0) {
      let r = d.toLocaleString("ru", { maximumFractionDigits: mfd });
      return r.replace(/\s/g, '_');
    }

		
		function formatCoord(coord){
			return "[ "+fcp(coord[0])+", "+ fcp(coord[1])+" ]";
		}

		log2(formatCoord([745654.85, 4785569.23]));
		
  },

  proj4() {

    /*
  	
    proj4
     библиотека, которая позволяет работать с системой координат и преобразовывать их между разными проекциями (картографическими системами координат). 
     Она помогает определить новые системы координат или использовать уже существующие, чтобы точно отображать карты и геоданные.

     Пример: объявление новой системы координат
     Указываются параметры трансформации, такие как сдвиги (+x_0, +y_0) и параметры для выравнивания с другими системами (+towgs84), 
     что позволяет точно преобразовывать координаты.
    */

    proj4.defs(
      'EPSG:21781',
      '+proj=somerc +lat_0=46.95240555555556 +lon_0=7.439583333333333 +k_0=1 ' +
      '+x_0=600000 +y_0=200000 +ellps=bessel ' +
      '+towgs84=660.077,13.551,369.344,2.484,1.783,2.939,5.66 +units=m +no_defs',
    );
    ol.proj.proj4.register(proj4);

    projection1 = new ol.proj.Projection({
      code: 'EPSG:21781',
      extent: [485869.5728, 76443.1884, 837076.5648, 299941.7864],
    });



  },



}


function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    //selectedOption: "coord1",
    debugMode: false,
    logObjectsAsJson: true,
    customFormatter: formatCoord,

    initFunction: () => {
    }

  };
}
