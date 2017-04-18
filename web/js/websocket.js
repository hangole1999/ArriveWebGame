/**
 * Created by Hangole on 2017-03-20.
 */

var webSocket = new WebSocket("ws://localhost:8882/game");

webSocket.onopen = function(message){
    console.log('onopen()');
    console.log(message);
};
webSocket.onclose = function(message){
    console.log('onclose()');
    console.log(message);
};
webSocket.onerror = function(message){
    console.log('onerror()');
    console.log(message);
};
webSocket.onmessage = function(message){
    console.log('onmessage()');
    console.log(message);
};

function sendData(object) {
    webSocket.send(JSON.stringify(object));
}

var createRoom = {
    type : "create_room",
    name : "jinseong's room",
    password : "1234",
    lock : true
};

var enterRoom = {
    type : "enter_room",
    roomNum : 1,
}

var ready = {
    type : "change_ready",
    roomNum : 1
}

var start = {
    type : "game_start",
    roomNum : 1
}
