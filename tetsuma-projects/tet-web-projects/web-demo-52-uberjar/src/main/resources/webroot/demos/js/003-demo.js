

//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

    test1() {
        //меняем dom
        log2("remove first text field")
        $inp1.remove();
    },

    test2() {
        $inp2.addClass("blue-border");
    },


    test3() {
        $inp2.addClass("bg-blue");
    },

}







function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
    workPanelTemplate: TEMPLATE_FORM1,
    selectorsData: selectorsData1,
    lfMode: false,
	reloadSandboxOnChange: true,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
    initFunction: () => {
    }
  };
}

