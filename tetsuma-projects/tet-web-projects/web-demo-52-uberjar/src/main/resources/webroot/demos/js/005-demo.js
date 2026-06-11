
let selectorsData1 = {
	
	String_declaration() {
		
		a.s1 = "моя строка 1";
		a.s4 = String ("моя строка 3");
		a.s11 = String(false);
		a.s22 = `результат: ${1 + 2}`;
		
	},
	
	String_encoding_func: `
/*
String.fromCharCode()
  создание строк из кодов UTF-16.
  Работает только с кодами в диапазоне от 0 до 65535.
  Не поддерживает эмодзи или исторические символы, у которых кодовые точки выше 0xFFFF.
*/
  
String.fromCharCode(189, 43, 190, 61);

testString1.toUpperCase();

`,

  test1(){
	},
	test2(){
	},
	test3(){
	},

}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
//    selectedOption: "",
    debugMode: false,
//	logObjectsAsJson: true,
    initFunction: () => {
    }
	
  };
}
