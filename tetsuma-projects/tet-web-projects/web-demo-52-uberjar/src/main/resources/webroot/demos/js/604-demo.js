

let selectorsData1 = {


  t1() {
	
	let defaultOptions = {
	    locale: dateRangeLocale,
	};
	$inp1.daterangepicker(defaultOptions);
	

	let options = $.extend(
	    {},
	    defaultOptions,
	    {
	        singleDatePicker: true,
	        showDropdowns: true,
	        minYear: 1951,
	        maxYear: parseInt(moment().format('YYYY'), 10),
	        startDate: "20.04.1982",
	    });


	$inp2.daterangepicker(options, function(start, end, label) {
	    var years = moment().diff(start, 'years');
	    alert("You are " + years + " years old!");
	});
	
	
	
	
	
	
  },
  t2() {

	initDateEditor($inp1);

	initTableDateRange($inp2);
	
	
  }

}






function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
    workPanelTemplate: TEMPLATE_FORM1,
    selectorsData: selectorsData1,
    reloadSandboxOnChange: true,
    //    selectedOption: "test10",
    autoscrollLog1: true,
    initFunction: () => {
    }
  };
}





