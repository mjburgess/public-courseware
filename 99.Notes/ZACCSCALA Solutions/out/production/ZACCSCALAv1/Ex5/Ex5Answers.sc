//1
def timesTwo = (x: Int) =>  x * 2
//2
timesTwo(2)
timesTwo(4)

//3
def add = (x: Int, y: Int) => x + y
add(1,2)

//4
def checkLength = (x: Int, word: String) => word.length() == x
checkLength(3, "word")
checkLength(4, "word")

//5
def isEven = (x: Int) => x % 2 == 0
//6
def negate = (f : Int => Boolean, x : Int) => ! f(x)
//7
def isOdd = (x : Int) => negate(isEven, x)

isEven(2)
isEven(3)
negate(isEven, 2)
negate(isEven, 3)
isOdd(2)
isOdd(3)

//8
def values(f : Int => Int, min: Int, max:Int) =
  for (i <- min to max) yield f(i)

values(x => x * 2, -2, 2)

//9
def sumThree = (x: Int, y: Int, z : Int) => x + y + z
def curry = sumThree.curried

def step1 = curry(2)
def step2 = step1(3)
def step3 = step2(4)
step3

step2(5)

//10
def addOne = (x: Int) => x + 1

def together = addOne compose timesTwo
def together2 = timesTwo andThen addOne
