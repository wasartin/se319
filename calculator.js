// CALCULATOR.JS
var Calc = {

Model : {
	onScreen : {id: "onScreen", type: "array", value: ""},
	operator : {id: "operator", type: "text", value: ""},
	operands : {id: "operands", type: "float", value1: "", value2: ""},
	memory : {id: "memory", type: "array", value:""}
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
	button12 : {id: "buttonMultiply", type: "button", value: "*", onclick:""},
	button13 : {id: "buttonDivide", type: "button", value: "/", onclick:""},
	button14 : {id: "buttonDot", type: "button", value: ".", onclick: ""},
	button15 : {id: "buttonEquals", type: "button", value: "=", onclick:""},
	button16 : {id: "buttonC", type: "button", value: "C", onclick: ""},
	button17 : {id: "buttonMC", type: "button", value: "MC", onclick:""},
	button18 : {id: "buttonMS", type: "button", value: "M-", onclick:""},
	button19 : {id: "buttonMP", type: "button", value: "M+", onclick:""},
	button20 : {id: "buttonMR", type: "button", value: "MR", onclick:""}
},

Controller : {

	handler : function(element){
		if(!isNaN(element.value)){
			document.getElementById("textRow").value += element.value;
			//if operator is empty, then its value 1, else value 2
			if(document.getElementById("operator") == null){
				document.getElementById("operands").value1 = element.value;
			}else{
				document.getElementById("operands").value2 += element.value;
			}
			document.getElementById("onScreen").value += element.value;
		}
		else if(isNaN(element.value)){
			switch(element.value){
				case "C":
					document.getElementById("textRow").value = "";
					break;
				case ".":
					document.getElementById("textRow").value += element.value;
					
					//change curr element to a decimal
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
					document.getElementById("textRow").value += element.value;
					document.getElementById("operator").value = element.value;
					document.getElementById("onScreen").value += element.value
					break;
				case "=":
					var operator, elt1, elt2;
					if(Calc.Controller.isFull == true){
						operator = document.getElementById("operator").value;
						elt1 = document.getElementById("operands").value1;
						elt2 = document.getElementById("operands").value2;
						switch (operator){
							case "+":
								var result = parseInt(elt1) + parseInt(elt2);
								break;
						}
						document.getElementById("textRow").value = result;
					}
					break;
				
				}
		}
	},

	isFull : function(){
		if(document.getElementById("operator").value != "" &&
			document.getElementById("operand").value1 != "" &&
			document.getElementById("operand").value2 != ""){
				return true;
			}
		return false;
	}
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
		Calc.View["button" + i].onclick = "Calc.Controller.handler(this)";
	}
}

} // end of Calc;
