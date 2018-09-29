var startButton = document.getElementById("start");
var xDirection = 10;
var yDirection = 10;
var xIncrease = 10;
var yIncrease = 0;

var context = document.getElementById("game").getContext("2d");
var width = document.getElementById("game").width;
var height = document.getElementById("game").height;
var leftButton = document.getElementById("left");
var rightButton = document.getElementById("right");

var goingLeft, goingRight, goingUp, goingDown;
goingLeft = goingUp, goingDown = false;
goingRight = true;
	
startButton.onclick = function(){
	//X = 600, y = 400
	setInterval(drawHead, 100);
}

leftButton.onclick = function(){
	if(goingUp){
		xIncrease = -10; 
		goingUp = false;
		goingLeft = true;
		yIncrease = 0;
	}
	else if(goingDown){
		xIncrease = 10;
		goingDown = false;
		goingRight = true;
		yIncrease = 0;
	}
	else if(goingLeft){
		yIncrease = 10;
		goingLeft = false;
		goingDown = true;
		xIncrease = 0;
	}
	else if(goingRight){
		yIncrease = -10;
		goingRight = false;
		goingUp = true;
		xIncrease = 0;
	}
}

rightButton.onclick = function(){
		if(goingUp){
			xIncrease = 10;
			goingUp = false;
			goingRight = true;
			yIncrease = 0;
		} 
		else if(goingDown){
			xIncrease = -10;
			goingDown = false;
			goingLeft = true;
			yIncrease = 0;
		}
		else if(goingLeft){
			yIncrease = -10;
			xIncrease = 0;
			goingLeft = false;
			goingUp = true;
		}
		else{ //going right
			yIncrease = 10;
			xIncrease = 0;
			goingRight = false;
			goingDown = true;
		}
}

function drawHead(){

	if(xDirection + xIncrease < width && yDirection + yIncrease < height){
		xDirection += xIncrease;
		yDirection += yIncrease;
		context.fillStyle = "#FF0000";
		context.rect(xDirection, yDirection, 10, 10);
		context.fill();
	}
	
}
