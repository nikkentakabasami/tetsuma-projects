/*
Тестовые данные
*/


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
      'geometry': {
        'type': 'Point',
        'coordinates': [0, 0],
      },
    },

    {
      'type': 'Feature',
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
