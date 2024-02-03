import cats.effect.{IO, Resource}
import munit.CatsEffectSuite
import org.http4s.blaze.client.BlazeClientBuilder

class SimpleTestServerIntegrationTest extends CatsEffectSuite {

  private val port = 8081

  private val serverResource = Resource.make {
    SimpleTestServer.runServer(port).start
  } { serverFiber =>
    serverFiber.cancel
  }

  private val clientResource = BlazeClientBuilder[IO].resource

  private val testResource = for {
    serverFiber <- serverResource
    client      <- clientResource
  } yield (serverFiber, client)

  test("Integration test for GET /user/:id") {
    val responseIO = testResource.use { case (_, client) =>
      client.expect[String](s"http://localhost:$port/user/123")
    }
    assertIO(responseIO, """{"id": "123", "name": "Test User"}""")
  }

}
