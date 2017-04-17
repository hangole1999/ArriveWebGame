/**
 * Created by Hangole on 2017-03-20.
 */

var webSocket = new WebSocket("ws://localhost:8882/game");

webSocket.onopen = function(message){
    console.log('onopen('+message+')');
    console.log(message);

    var createRoom = {
        type : "create_room",
        name : "jinseong's room",
        password : "1234",
        lock : true
    };
    webSocket.send(JSON.stringify(createRoom));
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
