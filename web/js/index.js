
var keyDowns = [];

function onKeyDown(event) {
    if (event.keyCode == 32 && keyDowns[event.keyCode] != true) {
        shot(player);
    }

    keyDowns[event.keyCode] = true;
}

function onKeyUp(event) {
    keyDowns[event.keyCode] = false;
}

document.addEventListener('keydown', onKeyDown);
document.addEventListener('keyup', onKeyUp);

var app = new PIXI.Application(800, 600, {backgroundColor : 0x1099bb});
document.body.appendChild(app.view);

// Lists
var bullets = [];

// Create a new Sprite from an image path
var player = PIXI.Sprite.fromImage('assets/player.png');

// Center the sprite's anchor point
player.anchor.set(0.5);
player.pivot.x = -50;
player.scale.x = 0.3;
player.scale.y = 0.3;
player.speed = 4;
player.bulletSpeed = 50;
player.rotation = 0;
player.rotationDegrees = 0;
player.destination = {position: {x: 1, y: 0}, rotation: 0};
player.velocity = {x: 0, y: 0};
player.lerpValue = 0.2;
player.slerpValue = 0.2;
player.rebound = 0.2;

// move the sprite to the center of the screen
player.x = app.renderer.width / 2;
player.y = app.renderer.height / 2;

player.getGunPoint = function () {
    var gunPoint = {x: (Math.cos(player.rotation + Math.PI/2) * 15) + (Math.cos(player.rotation) * 60), y: (Math.sin(player.rotation + Math.PI/2) * 15) + (Math.sin(player.rotation) * 60)};

    return gunPoint;
}

app.stage.addChild(player);

// Listen for animate update
app.ticker.add(function(delta) {

    // Processing Velocity
    if (keyDowns[37]) {
        player.destination.position.x = -1;
        player.velocity.x = -1;
    } else if (keyDowns[39]) {
        player.destination.position.x = 1;
        player.velocity.x = 1;
    } else if (keyDowns[38] || keyDowns[40]) {
        player.destination.position.x = 0;
        player.velocity.x = lerp(player.velocity.x, 0, player.lerpValue);
    } else {
        player.velocity.x = lerp(player.velocity.x, 0, player.lerpValue);
    }
    if (keyDowns[38]) {
        player.destination.position.y = -1;
        player.velocity.y = -1;
    } else if (keyDowns[40]) {
        player.destination.position.y = 1;
        player.velocity.y = 1;
    } else if (keyDowns[37] || keyDowns[39]) {
        player.destination.position.y = 0;
        player.velocity.y = lerp(player.velocity.y, 0, player.lerpValue);
    } else {
        player.velocity.y = lerp(player.velocity.y, 0, player.lerpValue);
    }

    // Moving
    player.position.x += Math.cos(player.rotation) * Math.abs(player.velocity.x) * player.speed;
    player.position.y += Math.sin(player.rotation) * Math.abs(player.velocity.y) * player.speed;

    // Rotating
    player.destination.rotation = Math.atan2(player.destination.position.y, player.destination.position.x);
    player.rotation = slerp(player.rotation, player.destination.rotation, player.slerpValue);

    // Processing Bullets
    for(var i in bullets) {
        bullets[i].position.x += Math.cos(bullets[i].rotation) * bullets[i].speed;
        bullets[i].position.y += Math.sin(bullets[i].rotation) * bullets[i].speed;

        if (bullets[i].position.x < 0 || bullets[i].position.y < 0 || bullets[i].position.x > app.renderer.width || bullets[i].position.y > app.renderer.height) {
            app.stage.removeChild(bullets[i]);
            bullets.splice(i,1);
        }
    }
});

function shot(player) {
    player.position.x -= Math.cos(player.rotation) * 1 + Math.cos(player.rotation) * player.bulletSpeed * player.rebound * Math.abs(player.velocity.x);
    player.position.y -= Math.sin(player.rotation) * 1 + Math.sin(player.rotation) * player.bulletSpeed * player.rebound * Math.abs(player.velocity.y);

    var bullet = PIXI.Sprite.fromImage('assets/bullet.png');
    bullet.anchor.set(0.5);
    bullet.position = player.getGunPoint();
    bullet.position.x += player.position.x;
    bullet.position.y += player.position.y;
    bullet.rotation = player.rotation;
    bullet.speed = player.bulletSpeed;

    app.stage.addChild(bullet);
    bullets.push(bullet);
}

function slerp(from, to, percent) {
    var fromVector = {x: Math.cos(from), y: Math.sin(from)};
    var toVector = {x: Math.cos(to), y: Math.sin(to)};

    var equation = (toVector.y - from.y) / (toVector.x - fromVector.x);

    var resultVector = {x: lerp(fromVector.x, toVector.x, percent), y: lerp(fromVector.y, toVector.y, percent)};

    return Math.atan2(resultVector.y, resultVector.x);;
}

function lerp(from, to, percent) {
    return from + percent * (to - from);
}
