

var data1 = [
{ label: 'Option 1', title: 'Option 1', value: '1', selected: true },
{ label: 'Option 2', title: 'Option 2', value: '2' },
{ label: 'Option 3', title: 'Option 3', value: '3', selected: true },
{ label: 'Option 4', title: 'Option 4', value: '4' },
{ label: 'Option 5', title: 'Option 5', value: '5' },
{ label: 'Option 6', title: 'Option 6', value: '6', disabled: true }
];


let selectorsData1 = {
	
	
	t1(){
		
		/*
		Bootstrap Multiselect
		
		Очень красивые и аккуратные списки со множеством вариантов выбора
		В 6 версии бутстрапа не работает!!!
		
		https://davidstutz.github.io/bootstrap-multiselect		
		*/

		//с единичным выбором
		$sel1.multiselect({
		onChange: function(option, checked) {
		  log('val(): ' + $sel1.val());
		}
		});

		$sel2.multiselect({
			onDropdownShow: event => {
				log("onDropdownShow");
			},
			onDropdownHide: event => {
				log('onDropdownHide: ' + $sel2.val());
			}

		});
		
		//Динамическое задание данных.
		$sel3.multiselect();
		$sel3.multiselect('dataprovider', data1);		
		
	},
	t2(){
		/*
		Для включения множественного выбора select должен иметь атрибут multiple.
		*/
		
		
		$sel1.prop("multiple",true);
		$sel1.multiselect({
			
			//использование кастомной функции для показа текста в селекте
			numberDisplayed: 1,
			buttonText: multiselectButtonText,
			
			
			onDropdownHide: event => {
				log('onDropdownHide: ' + $sel1.val());
			}

		});		

		$sel2.prop("multiple",true);
		$sel2.multiselect({
			
			//ограничение выпадающего списка по высоте
			maxHeight: 300,
			
			onDropdownHide: event => {
				log('onDropdownHide2: ' + $sel2.val());
			}
	
		});
		
		
		$btn1.text("select")
		$btn1.click(e => {
			$sel1.multiselect('select', ['2', '4']);
			$sel1.multiselect('deselect', ['1', '3']);
		});
		
		
		$btn2.text("selectAll")
		$btn2.click(e => {
			$sel1.multiselect('selectAll', false);
			$sel1.multiselect('refresh');
		});
		$btn3.text("deselectAll")
		$btn3.click(e => {
			$sel1.multiselect('deselectAll', false);
			$sel1.multiselect('refresh');
		});
		
		
		
				
	},
	t3(){
		
		//Динамическое задание данных.
		$sel3.prop("multiple",true);
		$sel3.multiselect();
		$sel3.multiselect('dataprovider', data1);		
		
		
		
		$btn1.text("disable")
		$btn1.click(e => {
			$sel3.multiselect('disable');
		});


		$btn2.text("enable")
		$btn2.click(e => {
			$sel3.multiselect('enable');
		});
		$btn3.text("destroy")
		$btn3.click(e => {
			$sel3.multiselect('destroy');
		});		
		
		
		
		
	},

	
	
}


function multiselectButtonText(options) {
  if (options.length == 0) {
	return '-';
  }
  else if (options.length > 1) {
	return 'выбрано ' + options.length;
  }
  else {
	return options[0].label;
  }
}


function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
    workPanelTemplate: "../fragments/select2Sandbox.html",
    selectorsData: selectorsData1,
    reloadSandboxOnChange: true,
//    selectedOption: "test10",
    autoscrollLog1: true,
    initFunction: () => {
    }
  };
}





















