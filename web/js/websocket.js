/**
 * Created by Hangole on 2017-03-20.
 */

var webSocket = new WebSocket("ws://localhost:8882/game");

var Network = {
    roomNumber: -1,
    createRoom: function(name, lock, password) {
        this.type = 'create_room';
        this.name = name;
        this.lock = lock;
        this.password = password;
    }, enterRoom: function(roomNum) {
        this.type = 'enter_room';
        this.roomNum = roomNum;
    }, ready: function() {
        this.type = 'ready';
        this.roomNum = this.roomNumber;
    }, start: function() {
        this.type = 'start';
        this.roomNum = this.roomNumber;
    }, position: function(x, y, rotation) {
        this.type = 'position';
        this.roomNum = this.roomNumber;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }, send: function(object) {
        webSocket.send(JSON.stringify(object));
    }, sendCreateRoom: function(name, lock, password) {
        this.send(new this.createRoom(name, lock, password));
    }, sendJoinAndReady: function(roomNum) {
        this.send(new this.enterRoom(roomNum));
        this.send(new this.ready());
    }, sendStartAndPosition: function(x, y, rotation) {
        this.send(new this.start());
        this.send(new this.position(x, y, rotation));
    }, sendPosition: function(x, y, rotation) {
        this.send(new this.position(x, y, rotation));
    }
};

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
