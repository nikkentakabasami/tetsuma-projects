/**
 * Вспомогательные данные, строки и объекты.
 * 
 */

let testUrl = "http://api.github.com/zen";
let testUrl2 = "http://api.github.com/zen?p1=777&p2=888#4";

let testObject = {
    id: 123,
    name: "bob"
}

function heavyTask() {
    let result = 0;
    for (let i = 0;i < 1000000;i++) {
        result += Math.sqrt(i);
    }
    return result;
}
