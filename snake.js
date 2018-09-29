var startButton = document.getElementById("start");
var x = 10;
var y = 10;
var context = document.getElementById("game").getContext("2d");
var width = document.getElementById("game").width;
var height = document.getElementById("game").height;
startButton.onclick = function(){
	//X = 600, y = 400

	setInterval(drawHead, 100);

}

function drawHead(){
	x += 10;
	y += 10;
	if(x < width && y < height){
		context.fillStyle = "#FF0000";
		context.rect(x, y, 10, 10);
		context.fill();
	}
	
}
