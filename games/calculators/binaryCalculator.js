var rs = require('readline-sync');

var input1 = rs.question('1st Number: ');
var action = rs.question('Enter the action{+,-,*,/,%,<<,>>,&,|,~}: ');
var input2;
if(action != '<<' || action != '>>' || action != '~'){
  input2 = rs.question('2nd Number: ');
}

var decimalResult = '';
var normalOperationFlag = 0;

switch(action){
  case '+':
  case '-':
  case '/':
  case '*':
  case '%':
    var one = parseInt(input1, 2);
    var two = parseInt(input2, 2);
    decimalResult = one + action + two;
    decimalResult = eval(decimalResult);
    normalOperationFlag = 1;
    break;
  //bit shift left 101 << 1010
  case '<<':
    decimalResult = input1;
    decimalResult += '0';
    break;
  //bit shift right 101 >> 10
  case '>>':
    decimalResult = input1.substring(0, input1.length - 1);
    break;
  //And 1010 & 1000 = 1000
  case '&':
    var i;
    for(i = 0; i < input1.length; i++){
      if(input1[i] == input2[i]){
        decimalResult += input1[i];
      }else{
        decimalResult += '0';
      }
    }
    break;
  //Or 1010 | 0101 = 1111
  case '|':
    var j;
    for(j = 0; j < input1.length; j++){
      if(input1[j] == '1' || input2[j] == '1'){
        decimalResult += '1';
      }else {
        decimalResult += '0';
      }
    }
    break;
  //invert each bit
  case '~':
    var i;
    for(i = 0; i < input1.length; i++){
      if(input1[i] == '1'){
        decimalResult += '0';
      }else {
        decimalResult += '1';
      }
    }
    break;
}

var binaryResult = decimalResult.toString(2);
if(normalOperationFlag == 0){
  decimalResult = parseInt(decimalResult, 2);
}
console.log('binary: ' + binaryResult + ', decimal: ' + decimalResult);
