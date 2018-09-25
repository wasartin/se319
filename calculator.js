// CALCULATOR.JS
var Calc = {

Model : {
},


View : {
	textRow : {id: "textRow", type: "text", value: "", onclick:""},
	button0 : {id: "button0", type: "button", value: 0, onclick:""},
	button1 : {id: "button1", type: "button", value: 1, onclick:""},
	button2 : {id: "button2", type: "button", value: 2, onclick:""},
	button3 : {id: "button3", type: "button", value: 3, onclick:""},
	button4 : {id: "button4", type: "button", value: 4, onclick:""},
	button5 : {id: "button5", type: "button", value: 5, onclick:""},
	button6 : {id: "button6", type: "button", value: 6, onclick:""},
	button7 : {id: "button7", type: "button", value: 7, onclick:""},
	button8 : {id: "button8", type: "button", value: 8, onclick:""},
	button9 : {id: "button9", type: "button", value: 9, onclick:""},
	button10 : {id: "buttonAdd", type: "button", value: "+", onclick:""},
	button11 : {id: "buttonSub", type: "button", value: "-", onclick:""},
	button12 : {id: "buttonMultiply", type: "button", value: "*", onClick:""},
	button13 : {id: "buttonDivide", type: "button", value: "/", onClick:""},
	button14 : {id: "buttonDot", type: "button", value: ".", onClick: ""},
	button15: {id: "buttonEquals", type: "button", value: "=", onClick:""},
	button16 : {id: "buttonC", type: "button", value: "C", onClick: ""},
	button17 : {id: "buttonMC", type: "button", value: "MC", onClick:""},
	button18 : {id: "buttonMS", type: "button", value: "M-", onClick:""},
	button19 : {id: "buttonMP", type: "button", value: "M+", onClick:""},
	button20 : {id: "buttonMR", type: "button", value: "MR", onClick:""}
},

Controller : {

},

run : function() {
  Calc.attachHandlers();
  console.log(Calc.display());
  return Calc.display();
},


displayElement : function (element) {
  var s = "<input ";
  s += " id=\"" + element.id + "\"";
  s += " type=\"" + element.type + "\"";
  s += " value= \"" + element.value + "\"";
  s += " onclick= \"" + element.onclick + "\"";
  s += ">";
  return s;

},

display : function() {
	var s;
	s = "<table cellpadding=5 id=\"myTable\" border=2>"
	s += "<tr><td>" + Calc.displayElement(Calc.View.textRow) + "</td></tr>";
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button7);
	s += Calc.displayElement(Calc.View.button8);
	s += Calc.displayElement(Calc.View.button9);
	s += Calc.displayElement(Calc.View.button10);
	s += "</tr></td>";
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button4);
	s += Calc.displayElement(Calc.View.button5);
	s += Calc.displayElement(Calc.View.button6);	
	s += Calc.displayElement(Calc.View.button11);
	s += "</tr></td>"
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button1);
	s += Calc.displayElement(Calc.View.button2);
	s += Calc.displayElement(Calc.View.button3);
	s += Calc.displayElement(Calc.View.button12);
	s += "</tr></td>";
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button0);
	s += Calc.displayElement(Calc.View.button14);
	s += Calc.displayElement(Calc.View.button15);
	s += Calc.displayElement(Calc.View.button13);
	s += "</tr></td>";
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button16);
	s += Calc.displayElement(Calc.View.button20);
	s += Calc.displayElement(Calc.View.button18);
	s += Calc.displayElement(Calc.View.button19);
	s += "</tr></td>";
	s += "<tr><td>";
	s += Calc.displayElement(Calc.View.button17);
	s += "</tr></td></table>";
  return s;
},

attachHandlers : function() {
	for( var i = 0; i < 21; i++){
		Calc.View["button" + i].onclick = "Calc.numberHandler(this)";
	}
},

numberHandler : function(element){
	if(!isNaN(element.value)){
		document.getElementById("textRow").value += element.value;
	}
	else if(isNaN(element.value)){
		switch(element.value){
			case "C":
				document.getElementById("textRow").value = "";
				break;
			//M+/- add or subtract to memory
			case "M+":
				break;
			case "M-":
				break;
			//MR recall current memory value
			case "MR":
				break;
			//MC set memory register to zero
			case "MC":
				break;
			case "+":
				document.getElementById("textRow").value += "+";
				break;
			case "=":
				
		}
	}
}

} // end of Calc;
