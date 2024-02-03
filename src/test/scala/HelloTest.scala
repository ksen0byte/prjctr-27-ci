import org.scalatest.funsuite.AnyFunSuite

class HelloTest extends AnyFunSuite {
  test("say hello") {
    assert(Hello.greet == "Hello, Scala project!")
  }
}
