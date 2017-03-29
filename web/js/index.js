
var keyDowns = [];

function onKeyDown(event) {
    keyDowns[event.keyCode] = true;
}

function onKeyUp(event) {
    keyDowns[event.keyCode] = false;
}

document.addEventListener('keydown', onKeyDown);
document.addEventListener('keyup', onKeyUp);

var app = new PIXI.Application(800, 600, {backgroundColor : 0x1099bb});
document.body.appendChild(app.view);

// create a new Sprite from an image path
var player = PIXI.Sprite.fromImage('assets/player.png')

// center the sprite's anchor point
player.anchor.set(0.5);
player.pivot.x = -50;
player.scale.x = 0.3;
player.scale.y = 0.3;
player.speed = 2;
player.destination = {position: {x: 0, y: 0}, rotation: 0};

// move the sprite to the center of the screen
player.x = app.renderer.width / 2;
player.y = app.renderer.height / 2;

app.stage.addChild(player);

// Listen for animate update
app.ticker.add(function(delta) {
    if (keyDowns[37]) {
        player.destination.position.x = -1;
    } else if (keyDowns[39]) {
        player.destination.position.x = 1;
    } else if (keyDowns[38] || keyDowns[40]) {
        player.destination.position.x = 0;
    }
    if (keyDowns[38]) {
        player.destination.position.y = -1;
    } else if (keyDowns[40]) {
        player.destination.position.y = 1;
    } else if (keyDowns[37] || keyDowns[39]) {
        player.destination.position.y = 0;
    }

    if ((player.destination.position.x != 0 && keyDowns[38+player.destination.position.x]) || (player.destination.position.y != 0 && keyDowns[39+player.destination.position.y])) {
        player.position.x += player.destination.position.x * player.speed;
        player.position.y += player.destination.position.y * player.speed;
    }

    player.destination.rotation = Math.atan2(player.destination.position.y, player.destination.position.x);

    // player.rotation = lerpAngle(player.rotation, player.destination.rotation, 0.3);
    player.rotation = player.destination.rotation;
});

function lerpAngle(from, to, t) {
    return lerp(from, to, t);
}

function lerp(from, to, t) {
    return from + t * (to - from);
}
