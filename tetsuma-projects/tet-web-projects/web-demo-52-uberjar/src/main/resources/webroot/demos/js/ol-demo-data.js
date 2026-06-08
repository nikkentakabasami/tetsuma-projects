/*
Тестовые данные
*/


//массив объектов, на основе которых будут созданы фичи
export const demoObjects2 = [
  {
    id: "line1",
    name: "линия 1",
    coords: [
      [4e6, -2e6],
      [8e6, 2e6],
      [9e6, 2e6],
    ]
  }



];


export const demoGeojsonObject1 = {
  'type': 'FeatureCollection',
  'crs': {
    'type': 'name',
    'properties': {
      'name': 'EPSG:3857',
    },
  },
  'features': [

    {
      'type': 'Feature',
			'id' : 'f1',
      'geometry': {
        'type': 'Point',
        'coordinates': [0, 0],
      },
    },

    {
      'type': 'Feature',
			'id' : 'f2',
      'geometry': {
        'type': 'LineString',
        'coordinates': [
          [4e6, -2e6],
          [8e6, 2e6],
          [9e6, 2e6],
        ],
      },
    },

    //двойной треугольник
    {
      'type': 'Feature',
			'id' : 'f3',
      'geometry': {
        'type': 'Polygon',
        'coordinates': [
          [
            [-5e6, -1e6],
            [-4e6, 1e6],
            [-3e6, -1e6],
            [-5e6, -1e6],
          ],
          [
            [-4.5e6, -0.5e6],
            [-3.5e6, -0.5e6],
            [-4e6, 0.5e6],
            [-4.5e6, -0.5e6],
          ],
        ],
      },
    },


    {
      'type': 'Feature',
      'geometry': {
        'type': 'MultiLineString',
				'id' : 'f4',
        'coordinates': [

          //вертикальная слева
          [
            [-1e6, -7.5e5],
            [-1e6, 7.5e5],
          ],

          //ломаная справа
          [
            [1e6, -7.5e5],
            [15e5, 0],
            [1e6, 7.5e5],
          ],

          //горизонтальная снизу
          [
            [-7.5e5, -1e6],
            [7.5e5, -1e6],
          ],
          //горизонтальная сверху
          [
            [-7.5e5, 1e6],
            [7.5e5, 1e6],
          ],
        ],
      },
    },


    //3 четырёхугольника
    {
      'type': 'Feature',
			'id' : 'f5',
      'geometry': {
        'type': 'MultiPolygon',
        'coordinates': [
          [
            [
              [-5e6, 6e6],
              [-5e6, 8e6],
              [-3e6, 8e6],
              [-3e6, 6e6],
              [-5e6, 6e6],
            ],
          ],
          [
            [
              [-3e6, 6e6],
              [-2e6, 8e6],
              [0, 8e6],
              [0, 6e6],
              [-3e6, 6e6],
            ],
          ],
          [
            [
              [1e6, 6e6],
              [1e6, 8e6],
              [3e6, 8e6],
              [3e6, 6e6],
              [1e6, 6e6],
            ],
          ],
        ],
      },
    },



  ],
};
