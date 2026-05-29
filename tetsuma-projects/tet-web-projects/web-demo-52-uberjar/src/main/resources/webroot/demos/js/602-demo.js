

/*
Multi Select Dropdown JS
минималистичный и удобный multiselect с открытым кодом.
*/

let select1, select2, select3;


let select3Data = [
  {
    value: 'opt1',
    text: 'Option 1'
  }, {
    value: 'opt2',
    html: '<strong>Option 2 with HTML!</strong>'
  }, {
    value: 'opt3',
    text: 'Option 3',
    selected: true
  }, {
    value: 'opt4',
    text: 'Option 4'
  }, {
    value: 'opt5',
    text: 'Option 5'
  }, {
    value: 'opt6',
    text: 'Option 6'
  }, {
    value: 'opt7',
    text: 'Option 7',
    disabled: true
  }
];

let data2 = [
  {
    value: 'opt1',
    text: 'Option 1',
    group: 'Basic Settings'
  },
  {
    value: 'opt2',
    html: '<strong style="color: red;">Option 2 with HTML!</strong>',
    group: 'Basic Settings'
  },
  {
    value: 'opt3',
    text: 'Option 3',
    selected: true,
    group: 'Advanced Settings'
  },
  {
    value: 'opt4',
    text: 'Locked Option',
    disabled: true // Prevents user interaction
  }
];




//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {


  basics() {

    /*
    Multi Select Dropdown JS
      минималистичный и удобный multiselect с открытым кодом.
 
    https://github.com/codeshackio/multi-select-dropdown-js
 
    Использовать просто: вызываешь конструктор и передаёшь опции.
    */

    //вид по умолчанию
    select1 = new MultiSelect("#select1", {
    });




  },

  options() {

    //основные опции
    select1 = new MultiSelect("#select1", {
      //Сообщение для показа когда ничего не выбрано
      placeholder: 'Select fruits',
      //сколько записей разрешено выбрать
      min: 2,
      max: 6,
      //default: false
      disabled: false,
      //Показывать раздел "Search" (default: true)
      search: false,
      //Показывать раздел "Select all" (default: true)
      selectAll: true,
      //показывать выбранные значения в select (default: true)
      listAll: true,
    });

    select2 = new MultiSelect("#select2", {

      //задание размеров
      width: "200px",
      height: "100px",

      search: true,
      selectAll: false,
      listAll: false,

    });

    select3 = new MultiSelect("#select3", {
      disabled: true,

    });

  },


  handlers() {


    //обработчики
    select1 = new MultiSelect("#select1", {
      max: 4,

      onChange: function(value, text, element) {
        log('onChange:', value, "selected:", select1.selectedValues);
        loghr()
      },
      onSelect: function(value, text, element) {
        log('onSelect:', value);
        loghr()
      },
      onUnselect: function(value, text, element) {
        log('onUnselect:', value);
        loghr()
      },

      //при попытке выбрать больше позволенного
      onMaxReached: function(max) {
        log('onMaxReached:', max);
        loghr()
      }

    });



  },
  dynamic_data() {

    /*
    Динамическое задание данных.
	
    Данные задаются массивом объектов вида:
    {value: 'opt3',text: 'Option 3',selected: true}	

    Есть ещё поля:	
    disabled: true,
    group: 'Settings',
    Но они поддерживаются только в новой версии.
    */

    select3 = new MultiSelect('#select3', {
      data: select3Data,
    });

    select1 = new MultiSelect('#select1', {
      data: data2,
    });



  },

  
  //select1.options
  fields() {
    /*
    Поля:

    ms.selectedValues
      массив с выбранными значениями.

	ms.options
	  текущие опции.
	  
	ms.data
	  текущие данные
	  
    */

    select1 = new MultiSelect("#select1", {});

    $btn1.text("selectedValues")
    $btn1.click(e => {
      let vals = select1.selectedValues;
      log("selectedValues:", vals);
	  loghr();
    });

	
	$btn2.text("options, data")
	$btn2.click(e => {
	  
	  let options = select1.options;
	  log("options:", options)
	  loghr();
	  
	  log("data:", select1.data)
	  loghr();
	  
	});	
	

  },  
  
  
  
  
  methods1() {
    /*
    Методы:
	
    ms.selectedValues
      массив с выбранными значениями.
	
    ms.setValues(values)
      задание выбранных значений
      
    ms.destroy();
      уничтожение объекта, возврат к исходному состояние
    */

    select1 = new MultiSelect("#select1", {});

    $btn1.text("select1.selectedValues")
    $btn1.click(e => {
      let vals = select1.selectedValues;
      log("selectedValues:", vals)
    });

    $btn2.text("setValues")
    $btn2.click(e => {
      select1.setValues(["2", "3"]);
    });


    $btn3.text("select1.destroy()")
    $btn3.click(e => {
      select1.destroy();
    });
  },

  methods2() {
    /*
    Методы:

    ms.clear()
      удаление всех записей

    removeItem(value)
      удаление записей

    addItem(item)
    addItems(items)
      добавление записей
    */

    select1 = new MultiSelect("#select1", {});

	//удаляем банан
    $btn1.text('select1.removeItem("2");')
    $btn1.click(e => {
      select1.removeItem("2");
    });

	//удаление всех записей
    $btn2.text("select1.clear();")
    $btn2.click(e => {
      select1.clear();
    });

	//добавление записи
    $btn3.text("select1.addItem()")
    $btn3.click(e => {
      select1.addItem({value: '77', text: 'new row77'});
	  select1.addItems([{value: '11', text: 'new row11'},{value: '22', text: 'new row22'}]);
    });
  },



  methods3() {
    /*
	ms.refresh();
	  обновляет опции в соответствии с options.data

	//задаём совершенно другие данные
	select3.options.data = select3Data2;
	select3.refresh();
	
	disable()
	enable()
	
    */

    select1 = new MultiSelect("#select1", {});

  //задаём другие данные
    $btn1.text('select1.refresh();')
    $btn1.click(e => {
		select1.options.data = select3Data;
		select1.refresh();
    });

    $btn2.text("select1.disable();")
    $btn2.click(e => {
      select1.disable();
    });

    $btn3.text("select1.enable()")
    $btn3.click(e => {
		select1.enable();
    });
  },  
  
  
  
  

  
  
  
  
  
}




function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
    workPanelTemplate: "../fragments/select2Sandbox.html",
    selectorsData: selectorsData1,
    lfMode: false,
    reloadSandboxOnChange: true,
    afterSandboxReload: null,
//    selectedOption: "test10",
    debugMode: false,
    autoscrollLog1: true,
    initFunction: () => {
    }
  };
}


