
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

// move the sprite to the center of the screen
player.x = app.renderer.width / 2;
player.y = app.renderer.height / 2;

app.stage.addChild(player);

// Listen for animate update
app.ticker.add(function(delta) {
    if(keyDowns[37]) {
        player.rotation = 3.16;
        player.position.x -= player.speed;
        if (keyDowns[38]) {
            player.rotation = 3.955;
            player.position.y -= player.speed;
        } else if (keyDowns[40]) {
            player.rotation = 2.375;
            player.position.y += player.speed;
        }
    } else if (keyDowns[39]) {
        player.rotation = 0;
        player.position.x += player.speed;
        if (keyDowns[38]) {
            player.rotation = 5.52;
            player.position.y -= player.speed;
        }
        if (keyDowns[40]) {
            player.rotation = 0.795;
            player.position.y += player.speed;
        }
    } else if (keyDowns[38]) {
        player.rotation = 4.75;
        player.position.y -= player.speed;
    } else if (keyDowns[40]) {
        player.rotation = 1.59;
        player.position.y += player.speed;
    }
});
