import cats.effect.{IO, IOApp}
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io.*

object SimpleTestServer extends IOApp.Simple {

  private val service = HttpRoutes
    .of[IO] { case GET -> Root / "user" / id =>
      Ok(s"""{"id": "$id", "name": "Test User"}""")
    }
    .orNotFound

  def runServer(port: Int = 8080, host: String = "localhost"): IO[Unit] =
    BlazeServerBuilder[IO]
      .bindHttp(port, host)
      .withHttpApp(service)
      .serve
      .compile
      .drain

  override def run: IO[Unit] = runServer()
}
