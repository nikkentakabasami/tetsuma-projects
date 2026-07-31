


let selectorsData1 = {

  blob_stream() {

    /*
    ReadableStream
      поток байтовых данных. 
  	
    blob.stream()
      Возвращает ReadableStream с данными блоба
    */

    arr1 = new Uint8Array([72, 101, 108, 108, 111]);
    blob1 = new Blob(["<html>", arr1, "</html>"], { type: 'text/html' });

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

  async streamToString1() {
    /*
    Простой способ сконвертировать ReadableStream в строку, используя Response.
    */

    const request1 = new Request(testRequestUrl, {
      method: "POST",
      body: JSON.stringify(testObject1),
    });


    //request1.body возвращает ReadableStream

    let b = await streamToString(request1.body);
    log2("request body:", b);


    log(streamToString);


  },
  async streamToString2() {
		/*
		Второй способ
		*/

		const request1 = new Request(testRequestUrl, {
		  method: "POST",
		  body: JSON.stringify(testObject1),
		});


		//request1.body возвращает ReadableStream

		let b = await streamToString2(request1.body);
		log2("request body:", b);

		log(streamToString2);		
		
  },
  stream4() {
  },



}


async function streamToString(stream) {
  const response = new Response(stream);
  const text = await response.text();
  return text;
};

async function streamToString2(stream) {
  const reader = stream.getReader();
  const decoder = new TextDecoder('utf-8');
  let result = '';

  while (true) {
    const { done, value } = await reader.read();
    if (done) break;
    result += decoder.decode(value, { stream: true });
  }
  // Финальный вызов для завершения декодирования
  result += decoder.decode();

  return result;
};








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







