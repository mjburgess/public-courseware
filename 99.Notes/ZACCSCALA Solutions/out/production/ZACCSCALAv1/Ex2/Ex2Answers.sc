//1
val n = 5
var m = 5.0

//2
//n = 10
m = 20

//3
val name = "Alice"
val c = 'a'
val f = 3.0f
val b = true

//4
name + c
c + n
name + n
name + b
b & false
b | false
f + m
f + m + n
n + f

//5
val thisIsADouble : Double = 10.0f;
val thisIsAFloat : Float = 10;

val result = {val a = 20; val b = 5; a/b}

//6
val y = {
  val m = 1.0
  val x = 10
  val c = 15
  m * x + c
}

val total = {
  val input = 10
  input + (input * 0.20)
}

val fullname = {
  val firstname = "alice"
  val surname = "smith"
  firstname + " " + surname
}

val something = {
  val a = 5;
  val b = 10;
  println(a + b);
}

//7

def leapYear(year : Int) =
  if (year % 4 == 0 &&
      (year % 100 != 0 || year % 400 == 0)
      ) true else false

leapYear(2000) //2000 should be a leap year
leapYear(2015) //2015 is not
leapYear(2016) //2016 will be!

//8
for (year <- 1960 to 2015) {
  if (leapYear(year)) println(year + " is a leap year");
  else println(year + " is not a leap year")
}

//9
for (c <- "Hello World"){
  print("*" + c)
}
print("*")

//10
for (i <- 1 to 100 if i % 2 == 0) yield i;

//11
def fact(n: Int, acc: Int): Int = {
  if (n == 1) acc
  else fact(n - 1, acc * n)
}

println(fact (5, 1))


//12
def intPow(x: Double, n: Int): Double = {
  // n is >0 and even
  if (n > 0 & n % 2 == 0)  { val y = intPow(x, n/2); y*y }
  // n is odd...
  else if (n > 0) x * intPow(x, n - 1)
  else if (n == 0) 1
  else /*(n < 0)*/ 1 / intPow(x, -n)
}

intPow(5, 2);

