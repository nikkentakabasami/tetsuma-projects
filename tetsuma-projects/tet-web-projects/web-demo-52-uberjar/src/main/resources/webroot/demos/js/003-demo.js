$(function() {
	
	let counter = 1;
	$("#b1").click(e => {
		log("test message "+counter);
		counter++;
	});
	
	
	
});




//для тестов можно задать массив с селекторами:
let selectorsData1 = [
	"#inp1",
	".workPanel *:input",
];


$(() => {
  initDemoCodeSelect("#selectors1", selectorsData1);

  reloadSandbox();

});

