/**
 * Created by Hangole on 2017-03-20.
 */

var webSocket = new WebSocket("ws://localhost:8181/game");

webSocket.onopen = function(message){
    console.log('onopen('+message+')');
    console.log(message);
};
webSocket.onclose = function(message){
    console.log('onclose('+message+')');
    console.log(message);
};
webSocket.onerror = function(message){
    console.log('onerror('+message+')');
    console.log(message);
};
webSocket.onmessage = function(message){
    console.log('onmessage('+message.data+')');
    console.log(message);
};

console.log('entry()');
console.log(webSocket);
