var rs = require('readline-sync');

var input1 = rs.question('1st Number: ');
var input2 = rs.question('2nd Number: ');
var action = rs.question('Enter the action{+,-,*,/,%}');
var result;

switch(action){
  case '+':
  case '-':
  case '/':
  case '*':
  case '%':
    var one = parseInt(input1, 2);
    var two = parseInt(input2, 2);
    result = one + action + two;
    result = eval(result);
    break;
  //bit shift left 101 << 1010
  case '<<':
    break;
  //bit shift right 101 >> 10
  case '>>':
    break;
  //And 1010 & 1000 = 1000
  case '&':
    break;
  //Or 1010 | 0101 = 1111
  case '|':
    break;
  //invert each bit
  case '~':
    break;
}

console.log(result);
