Input
Store X
Input
Store Y

Skipcond 000 / negative so do not jump for y
Jump checkIsNegaitveX
Load Help
Add ONE
Store Help
checkIsNegaitveX, Load X
Skipcond 000 / negative so do not jump for x
Jump checkHowManyNegativeIs
Load Help
Add ONE
Store Help
checkHowManyNegativeIs,Load Help
Subt ONE
Store Help
Skipcond 000
Jump checkHowManyNegative
Jump OpertionForPositivs
checkHowManyNegative,Skipcond 400
Jump OpertionForTwoNegatives
Jump OpertionForOneNegative

OpertionForPositivs,Load X
Subt Y
Skipcond 800
Jump Else
TheOperation, Load X
Subt Y
Store X
Skipcond 000 
Load Total
Skipcond 000 
ADD ONE
Skipcond 000 
Store Total

Load Xeee
Skipcond 000 
Jump TheOperation
/ Get the rest
Add Y
Store X
/ Print stuff
Load ONE_NEGATIVE
Skipcond 400
Jump changeIntoNegativeTotal
isNegative,Load Total
Output
Load X
Output
Halt

changeIntoNegativeTotal, Load ZERO
Subt Total
Store Total
Jump isNegative

changeIntoNegativeRest, Load ZERO
Subt X
Store X
Jump isNegativeRest

/if x < y
Else, Load ONE_NEGATIVE
Skipcond 400
Jump changeIntoNegativeRest
isNegativeRest, Load Total
Subt Total
Output
Load X
Output
Halt

/ if y<0 and x<0
OpertionForTwoNegatives, Load ZERO
Subt Y
Store Y
Load ZERO
Subt X
Store X
Jump OpertionForPositivs

/if y<0 or x<0
OpertionForOneNegative, Load ONE_NEGATIVE
Add ONE
Store ONE_NEGATIVE
Load X
Skipcond 000
Jump checkY
Load ZERO
Subt X
Store X
checkY, Load Y
Skipcond 000
Jump OpertionForPositivs
Load ZERO
Subt Y
Store Y
Jump OpertionForPositivs

/Variable decleration
Y, DEC 0
X, DEC 0
Help, DEC 0
Total, DEC 0
ZERO, DEC 0
ONE, DEC 1
TWO, DEC 2
ONE_NEGATIVE, DEC 0
