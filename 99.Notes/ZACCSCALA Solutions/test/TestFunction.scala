import org.scalatest.FunSuite

/**
 * Created by kat on 28/01/15.
 */
class TestFunction extends FunSuite{
/*
4.	Write a function that checks if an email address is valid.
You should check that there is an @ symbol present and at least one period (full stop symbol).
 */
  test("emails should have a @ and a ." ){
    assert(validEmail("kat@email.com"))
    assert(! validEmail("kat"))
    assert(! validEmail("kat@"))
    assert(! validEmail("email.com"))
  }


  def validEmail(email : String): Boolean =  {
    if (email.contains('@') && email.contains('.')) return true
    else return false
  }
}
