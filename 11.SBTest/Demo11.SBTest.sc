// CHAPTER 11:  SBT & UNIT TESTING
// PROBLEM:  Testing first, develop a cinema sbt project.
// PROCESS:  Demonstration, Discussion + Group Work
// EXP?      Unit Testing (eg. JUnit). Build tools (eg. Maven)
// USE?      Create tool chains that verify the reliability of your code.

// PART 1 - SBT
{
  // SIMPLE BUILD TOOL
  // www.scala-sbt.org -- installer
  // sbt command downloads, installs and caches *sbt* and *scala* per config. defintion (.sbt -- sbt, .ivy2 -- libs)
  // thef. initial build takes awhile + internet required

  // build files defined with dsl -- domain specific language (literate api)

  // two modes: batch and interactive

  // why a build tool?
  // dependency management
  // standardized project layouts
  // compile, run & test simplified
  // on-demand recompile/rerun/retest simplified
  // repl
  // multiversion scala + libs

  // at an empty directory sbt loads global plugins only

  // $ sbt
  //> about       # project info.
}

{

  /*

  $ sbt
  > help                              # Describe commands.
  > tasks                             # Show the most commonly-used, available tasks.
  > tasks -V > compile > test         # Show ALL the available tasks.
  > clean
  > ~test
  > console
  > run
  > show x
  > show scalaVersion
  > eclipse # Generate Eclipse project files.
  > exit # Quit the REPL (also control-d works).



  # Incrementally compile the code.
  # Incrementally compile the code and run the tests.
  # Delete all build artifacts.
  # Run incr. compiles and tests whenever files are saved. # This works for any command prefixed by "~".
  # Start the Scala REPL.
  # Run one of the "main" routines in the project.
  # Show the definition of variable "x".
  */
}


{
  // BUILD FILES
  // by convention, build.sbt
  // declarative dsl
  // describe what to build

  name := "hello"                 //newline required!
  version := "1.0"
  scalaVersion := "2.11.8"
}


{
  // CREATING A PROJECT
  // defintion of the project is the most important aspect of the build file
  // each project has a Map of settings

  // lazy vals -- recall: initialized on first *use* . sbt uses vals in right order, so definition can be in any order


  lazy val root = (project in file(".")).settings(            // default project, current folder
    name := "hello",
    version := "1.0"
  )

  scalaVersion := "2.11.8"                            // principle (default) version

  crossScalaVersions = Seq("2.9.2", "2.10.1")         //multiple versions


  // sbt> + package      # + means build for all versions
}



{
  // DEPENDENCIES
  // put jars in project lib/ folder (picked up automatically but unmanaged)
  // OR add references to libs in build file (managed automatically -- prefferred)

  // managed = transitive resolution and download of dependencies

  //latest.integration
  //latest.[any status], such as latest.milestone
  // version ranges [1.0,2.0] matches  1 <= v <= 2

  //      libraryDependencies += groupID % artifactID % revision
  //OR,   libraryDependencies += groupID %% artifactID % revision

  //with %% sbt will add your project’s Scala version to the artifact name -- some libs are named to allow this

  val json4sNative = "org.json4s" %% "json4s-native" % "latest.integration"

  lazy val root = (project in file(".")).settings(            // default project, current folder
    name := "hello",
    version := "1.0",
    libraryDependencies += json4sNative
  )

  scalaVersion := "2.11.8"                            // principle (default) version
}

{
  // PROJECT STRUCTURE
  /* build.sbt            <-- project build file
      project/
        build.properties    <-- sbt version
      src/
        main/
          resources/   <-- files to include in main jar
          scala/       <-- scala sources
          java/        <-- java sources
        test/
          resources/   <-- files to include in test jar
          scala/       <-- scala test sources
          java/        <-- java test sources
      target/          <-- holds generated files
  */
}

{
  // COMPILING, RUNNING CODE
  // add source files to src/main/scala
  // though for one-file projects, can just be in same folder

  // $ sbt compile
  // $ sbt run # run = compile (if necessary) + execute
}

{
  // RUNNING TESTS
  // sbt supports many tdd frameworks (juint, scalacheck, scalatest, specs2...)
  // tests are in src/test/scala

  // test - runs all tests
  // test-only - runs specific tests
  // test-quick - only run tests that have previously failed


  // run all
  sbt> test

  // run on change
  sbt> ~ test

  // run tests matching pattern
  sbt> test-only com.tcl.*
}

{
  //TASKS
  // sbt = task runner
  // tasks  -- common tasks
  // inspect -- show task dependencies
  // show -- run a task & show result

  // tasks can depended on one another, in which case are executed serially

  /*
  > tasks
  This is a list of tasks defined for the current project.
  It does not list the scopes the tasks are defined in; use the 'inspect' command for that.
  Tasks produce values. Use the 'show' command to run the task and print the resulting value.

  clean            Deletes files produced by the build,
                   such as generated sources, compiled
                   classes, and task caches.
  compile          Compiles sources.
  console          Starts the Scala interpreter with the
                   project classes on the classpath.
  doc              Generates API documentation.
  package          Produces the main artifact, such as a
                   binary jar.
  */

  // inspect
  // inspect tree
  // show sources
}


{

  // MULTIPLE PROJECTS
  // build files may contain more than one project
  // here there is a root project & util & core projects

  /*
  build.sbt            <--  build file
  core/                <-- 'core' project
  project/
  target/
  util/                <-- 'util' project
  */

  lazy val root = project in file(".")

  lazy val util = project
  lazy val core = project


  //> projects                # show current projects
  //> project core
  //> projects

  //> core/compile        # run compile task on core

  // building root will also build util and core

  // projects can depend each other:
  //1. as aggregates: building the root project will parallel build dependent projects
  //2. classpath sharing: one project on the classpath of another, so serial building (dependsOn)

  lazy val util = project
  lazy val core = project

  lazy val core = project.dependsOn(util)                         // serial, cp share
  lazy val root = (project in file(".")).aggregate(util, core)    // parallel
}

// PART 2 - TDD
{
  //TYPE SYSTEMS AS TESTS
  //functional programmers prefer to let the compiler check the correctness of the program
  //oo programming langs often have very limited type systems so have to rely on dynamic checking
  //ie., unit tests

  //there is still an advantage for using both approaches,
  // but esp. the first!

  // sketch the types
  case class Ingredient(name: String, amount: Double)
  case class Food(name: String, ingredients: List[Ingredient])

  class Oven {
    def bake(mix: List[Ingredient]): Food = ???
  }

  // put it together
  val myOven = new Oven
  val mix = List(Ingredient("Flour", 100), Ingredient("Sugar", 100))
  myOven.bake(mix)        // Q. does it type check? A. yes

  // TEST DRIVEN DEVELOPMENT
  // tdd means "you write the tests first" -- why?
  // same as writing the types first (which are a form of test): specification first, then implementation
  // refining the specification (sans implementation) will often imply an obvious implementation

  class Person {
    def eat(f: Food) = ???
  }

  val me = new Person
  me.eat(myOven.bake(mix))        // obvious?
}

{

  // ASSERTIONS
  type Person = (String, String)
  val person = ("Michael", "UK")

  def getName(p: Person) = p._1
  def getLocation(p: Person) = p._2

  assert(getName(person) == "Michael")
  assert(getLocation(person) == "UK")

  /*ERROR:
      assert(getName(person) == "John")
      assert(getLocation(person) == "US") //java.lang.AssertionError
  */
}

{
  // we can create our own TDD system
  type PersonTest = Person => Unit            // A person test takes a Person and returns nothing

  import scala.util.{Success, Failure, Try}   //use Try to handle assertion exceptions

  def testPerson(spec:  PersonTest) {
    val person = ("Michael", "UK")

    println("Running spec...")

    Try { spec(person) } match {
      case Success(_) => println("all tests passed!")
      case Failure(e) => println("tests failed!")
    }

    println("...end")
  }

  testPerson { (p: Person) =>
    assert(getName(p) == "Michael")
    assert(getLocation(p) == "UK")
  }

  testPerson { (p: Person) =>
    assert(getName(p) == "John")
    assert(getLocation(p) == "US")
  }


  /*

  */
}

{
  // ASIDE: VARIATIONS

  // assume - same as assert, intended for use by static analysis tools
  // require - used as a precondition of a method to check parameters. Throws IllegalArgumentException if it fails.
  // ensuring - used to check the return from a function

  //eg. from docs
  def addNaturals(nats: List[Int]): Int = {
    require(nats forall (_ >= 0), "List contains negative numbers")

    nats.foldLeft(0)(_ + _)

  } ensuring (_ >= 0)
}




// PART 3 -- SCALA TEST
{
  //SCALA TEST
  //http://www.scalatest.org

  //ScalaTest unit testing library (TDD, BDD, test*)
  //supports >8 styles of test + custom styles
  //overrides assert() for non-terminating behaviour

  //styles
  //most common: emphasis on programmer
  //FlatSpec     - typical unit tests       (programmer audience)
  //FeatureSpec  - literate, more bdd style (programmer and non-programmer audience)

  //and: emphasis on non-programmer
  //FunSpec      - behavioural
  //WordSpec     - literate

  // others...
  //PropSpec - property checks
  //FreeSpec - custom dsl
  //Spec - defines tests as methods, faster for v. large projects
  //+ more
}

{
  //FLATSPEC
  //a test comprises is a sentence + codeblock that describes behaviour

  import collection.mutable.Stack
  import org.scalatest._

  class StackFlatSpec extends FlatSpec {
    //subject verb   adjective/phrase
    "A Stack" should "pop values in LIFO order" in {
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      assert(stack.pop() == 2)
      assert(stack.pop() == 1)
    }
  }
}



{


  // FEATURE SPEC
  // features and scenarios
  // given -- when -- then --
  // style common to agile-style acceptance tests

  class Television(var isPowerOn: Boolean = false) {
    def powerButton() { isPowerOn = !isPowerOn }
  }

  class TelevisionFeatureSpec extends FeatureSpec {
    feature("Power Button") {
      scenario("user presses power button when tv is off") {

        Given("a powered-off tv set")
        val tv = new Television
        assert(!tv.isPowerOn)

        When("the power button is pressed")
        tv.powerButton()

        Then("the tv should power-on")
        assert(tv.isOn)
      }
    }
  }
}

{

  // MATCHERS
  // assert() doesnt read very well
  // scalatest also provides a DSL for specifying assertions

  // Using assert
  assert(stack.pop() == 2)

  // Using matchers
  stack.pop() should be (2)

  // maybe mixed into any scalatest
  class ExampleSpec extends FlatSpec with Matchers {}
  class ExampleSpec extends FlatSpec {}

  //examples
  result should be (2)
  result should be < 7
  result should equal (6.9 +- 0.2)

  str should have length 10   // for strings, arrays
  str should startWith ("hello")
  str should startWith regex "hel+o"

  myObject shouldBe a [Thing]

  result shouldBe empty  // for things that can be
  // empty, eg. lists


  Set(1, 2, 3) should contain (2)

  "123" should contain ('1')

  List(1, 2, 3, 4, 5) should contain oneOf (5, 7, 9)
  List(1, 2, 3, 4, 5) should contain allOf (2, 4)


  // including logical combinations
  map should (contain key ("two") and not contain value (7))

  string should (
    equal ("fee") or
      equal ("fie") or
      equal ("fo")  or
      equal ("fum")
    )
}

{
  // stack example with matchers
  class ExampleSpec extends FlatSpec with Matchers {

    "A Stack" should "pop values in last-in-first-out order" in {
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      stack.pop() should be (2)
      stack.pop() should be (1)
    }

    it should "throw if an empty stack is popped" in {
      val emptyStack = new Stack[Int]
      a [NoSuchElementException] should be thrownBy {           // matchers for exceptions
        emptyStack.pop()
      }
    }
  }
}



{
  // ASIDE: WordSpec
  class SetSpec extends WordSpec {

    // Describe a scope for a subject, in this case: "A Set"
    // All tests within these curly braces are about "A Set"

    "A Set" can {

      // All tests within these curly braces are about "A Set (when empty)"
      "empty" should {
        "have size 0" in {
          assert(Set.empty.size == 0) // of this test is: "A Set (when empty) should have size 0"
        }

        "produce NoSuchElementException when head is invoked" in {
          intercept[NoSuchElementException] {
            Set.empty.head
          }
        }

        "should be empty" ignore { // To ignore a test, change 'it' to 'ignore'...
          assert(Set.empty.isEmpty)
        }
      }

      "non-empty" should {
        "have the correct size" in {
          assert(Set(1, 2, 3).size == 3)     // name is: "A Set (when non-empty) should have the correct size"
        }
      }
    }
  }
}