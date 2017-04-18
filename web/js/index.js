
// Key Downs
var keyDowns = [];

// On Key Down
function onKeyDown(event) {
    if (event.keyCode == 32 && keyDowns[event.keyCode] != true) {
        player.shot();
    }

    keyDowns[event.keyCode] = true;
}

// On Key Down
function onKeyUp(event) {
    keyDowns[event.keyCode] = false;
}

// Add Key Listener
document.addEventListener('keydown', onKeyDown);
document.addEventListener('keyup', onKeyUp);

// Create PIXI.js Application
var app = new PIXI.Application(800, 600, {backgroundColor : 0x1099bb});
document.body.appendChild(app.view);

// Character Prototype
function character(position) {

    // Construction Sprite
    this.sprite = PIXI.Sprite.fromImage('assets/player.png');

    // Initializing Sprite
    this.sprite.anchor.set(0.5);
    this.sprite.pivot.x = -50;
    this.sprite.scale.x = 0.3;
    this.sprite.scale.y = 0.3;
    this.sprite.rotation = 0;
    this.sprite.rotationDegrees = 0;
    this.sprite.destination = {position: {x: 1, y: 0}, rotation: 0};
    this.sprite.velocity = {x: 0, y: 0};

    // Initializing
    this.speed = 4;
    this.bulletSpeed = 50;
    this.lerpValue = 0.2;
    this.slerpValue = 0.2;
    this.rebound = 0.2;
    this.collision = {radius: 40};

    // Initializing Start Position
    this.sprite.position.x = position.x;
    this.sprite.position.y = position.y;

    // Get Start of Bullet Position Function
    this.getGunPoint = function () {
        var gunPoint = {x: (Math.cos(this.sprite.rotation + Math.PI/2) * 15) + (Math.cos(this.sprite.rotation) * 60), y: (Math.sin(this.sprite.rotation + Math.PI/2) * 15) + (Math.sin(this.sprite.rotation) * 60)};
        return gunPoint;
    };

    // Rebound Return Position Function
    this.returnPosition = function(additionalPosition) {
        this.sprite.position.x += additionalPosition.x * 0.7;
        this.sprite.position.y += additionalPosition.y * 0.7;
    };

    // Shot Function
    this.shot = function() {
        var additionalPosition = {
            x: Math.cos(this.sprite.rotation) * 1 + Math.cos(this.sprite.rotation) * this.bulletSpeed * this.rebound * Math.abs(this.sprite.velocity.x),
            y: Math.sin(this.sprite.rotation) * 1 + Math.sin(this.sprite.rotation) * this.bulletSpeed * this.rebound * Math.abs(this.sprite.velocity.y)
        }

        this.sprite.position.x -= additionalPosition.x;
        this.sprite.position.y -= additionalPosition.y;

        var _this = this;

        setTimeout(function(){_this.returnPosition(additionalPosition);}, 50);

        var bullet = PIXI.Sprite.fromImage('assets/bullet.png');
        bullet.anchor.set(0.5);
        bullet.position = this.getGunPoint();
        bullet.position.x += this.sprite.position.x;
        bullet.position.y += this.sprite.position.y;
        bullet.rotation = this.sprite.rotation;
        bullet.speed = this.bulletSpeed;

        app.stage.addChild(bullet);
        bullets.push(bullet);
    };

    // Moving
    this.moving = function() {
        this.sprite.position.x += Math.cos(this.sprite.rotation) * Math.abs(this.sprite.velocity.x) * this.speed;
        this.sprite.position.y += Math.sin(this.sprite.rotation) * Math.abs(this.sprite.velocity.y) * this.speed;
    }

    // Rotating
    this.rotating = function() {
        this.sprite.destination.rotation = Math.atan2(this.sprite.destination.position.y, this.sprite.destination.position.x);
        this.sprite.rotation = slerp(this.sprite.rotation, this.sprite.destination.rotation, this.slerpValue);
    }
}

var player = new character({x: app.renderer.width / 2, y: app.renderer.height / 2});

// Lists
var bullets = [];
var characters = [player];

// Add Player to Stage
app.stage.addChild(player.sprite);

// Listen for animate update
app.ticker.add(function(delta) {

    // Processing Velocity
    if (keyDowns[37]) {
        player.sprite.destination.position.x = -1;
        player.sprite.velocity.x = -1;
    } else if (keyDowns[39]) {
        player.sprite.destination.position.x = 1;
        player.sprite.velocity.x = 1;
    } else if (keyDowns[38] || keyDowns[40]) {
        player.sprite.destination.position.x = 0;
        player.sprite.velocity.x = lerp(player.sprite.velocity.x, 0, player.lerpValue);
    } else {
        player.sprite.velocity.x = lerp(player.sprite.velocity.x, 0, player.lerpValue);
    }
    if (keyDowns[38]) {
        player.sprite.destination.position.y = -1;
        player.sprite.velocity.y = -1;
    } else if (keyDowns[40]) {
        player.sprite.destination.position.y = 1;
        player.sprite.velocity.y = 1;
    } else if (keyDowns[37] || keyDowns[39]) {
        player.sprite.destination.position.y = 0;
        player.sprite.velocity.y = lerp(player.sprite.velocity.y, 0, player.lerpValue);
    } else {
        player.sprite.velocity.y = lerp(player.sprite.velocity.y, 0, player.lerpValue);
    }

    // Processing Characters
    for(var i in characters) {
        characters[i].moving();
        characters[i].rotating();
    }

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

// Spherical Linear Interpolation
function slerp(from, to, percent) {
    var fromVector = {x: Math.cos(from), y: Math.sin(from)};
    var toVector = {x: Math.cos(to), y: Math.sin(to)};

    var equation = (toVector.y - from.y) / (toVector.x - fromVector.x);

    var resultVector = {x: lerp(fromVector.x, toVector.x, percent), y: lerp(fromVector.y, toVector.y, percent)};

    return Math.atan2(resultVector.y, resultVector.x);
}

// Linear Interpolation
function lerp(from, to, percent) {
    return from + percent * (to - from);
}
