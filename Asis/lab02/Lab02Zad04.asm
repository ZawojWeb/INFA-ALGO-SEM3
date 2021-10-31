Input
Store X
Input
Store Y

/While (x != y){
WhileOne,Load Y
Subt X
Skipcond 800
Jump rangeNotCorrect

/While (D < X){
WhileSecound, Load X
    Subt D
    Skipcond 800
    Jump CheckNextX
    
/if(X % D != 0){D++}else{breakWhile}
    Jump ModuloOperation
ifForCheckModulo,Load Rest
	Skipcond 400
    Jump CheckNextD
    Jump CheckNextX
    
CheckNextD, Load D
    Add ONE
	Store D
    
    Jump WhileSecound
/}
CheckNextX, Load X
Subt D
Skipcond 400
Jump AddOne
Jump PrintThisX
AddOne,Load X / x++
Add ONE
Store X
/D = 2
Load TWO
Store D
Jump WhileOne

Halt
/}
rangeNotCorrect,Halt
EndSecoundWhile,Halt

/ModuloOperation
ModuloOperation, Load X
Store helpWithRest
TheOperation, Load helpWithRest
Subt D
Store helpWithRest
Skipcond 000 
Jump TheOperation
/ Get the rest
Load helpWithRest
Add D
Store Rest
Jump ifForCheckModulo

/Pirnt
PrintThisX, Load X
Output
Jump AddOne

/Varibles end consts
Y, DEC 0
X, DEC 0
D, DEC 2 / Devided
Rest, DEC 0
ONE, DEC 1
TWO, DEC 2
firstWhile, DEC 0
helpWithRest, DEC 0