// CHAPTER:   11. SBT & UNIT TESTING
// PROBLEM:   Testing first, develop a cinema sbt project.
// TIME:       45 m

// PART 1 -- SBT

// Q. Review the Exercise11.Test folder in detail

// Q. in a terminal, cd to cinema
// Q. at the prompt start sbt
// Q. at the sbt prompt, run
// Q. modify the build file, add "logLevel in run := Level.Warn"
// Q. again at the sbt prompt, run
// Q. now at the sbt prompt, test
// Q. modify the describe() to return a different message
// Q. again at the sbt prompt, run

// EXTRA:
// Q. at the sbt prompt try, ~ run
//... and modify describe() -- each modification cause a rerun



// PART 2 -- TDD:  THE BRIEF...
// Q. now modify the sbt project to include a test suit
// and code to the following brief:


// a cinema has a name and some showings
// a showing is a film and a screen room

//a customer has a ticket
//a ticket is either premium or standard
// standard tickets have seat numbers
// premium tickets are for all the VIP space

//a cinema can admit several people to a showing
//a showing admits one person at a time

//when a showing admits a customer
// they are told their seat number if they have a standard ticket
// or they are told to sit in the VIP area otherwise

// Q. devise a specification (TDD) which tests admission
// Q. then code the solution to this specification, so all tests pass


//HINT:  there is a lot of freedom to implement this however you'd like
// the key goal is to write a test first and code second



// HINT: (an example spec might contain: )
// "A film" should "have a name"
//so, film.name should be

// "A showing" should "have a film name and screen"
//so, showing.film.name should be

// "A cinema" should "admit premium and standard ticket holders"
//so, cinema.admit(film, customers) should be List(messages)


// REVIEW: What did you learn from this exercise?