


let selectorsData1 = {

	blob_stream(){
		  
		/*
		blob.stream()
		  Возвращает ReadableStream с данными блоба
		*/
		
		arr1 = new Uint8Array([72, 101, 108, 108, 111]);
		blob1 = new Blob(["<html>",arr1,"</html>"], {type: 'text/html'});
		
		let stream = blob1.stream();
		
		const reader = stream.getReader();
		let chunks = [];

		reader.read().then(function processData({ done, value }) {
		  if (done) {
			
//			let arr = chunks.reduce((acc, chunk) => new Uint8Array([...acc, ...chunk]), new Uint8Array());
	
			//объединяем все данные в один Uint8Array
			let totalLength = chunks.reduce((sum, arr) => sum + arr.length, 0);
			let arr = new Uint8Array(totalLength);
			let offset = 0;
			for (let chunk of chunks) {
			  arr.set(chunk, offset);
			  offset += chunk.length;
			}

			//декодируем			
			let str = new TextDecoder().decode(arr);
		    log2('Поток завершён:', str);
		    return;
		  }
		  
		  //value - Uint8Array
		  chunks.push(value);
		  reader.read().then(processData);
		});
		
		
	},	
	
	stream2(){
		/*
		ReadableStream
		  поток байтовых данных. 
		*/
	},
	stream3(){
	},
	stream4(){
	},
	
	
	
}





function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
    initFunction: () => {

    }
  };
}







