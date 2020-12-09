package sample.http

import cats.syntax.all._
import org.http4s.HttpApp
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import sample.{AppEnv, UserServiceEnv}
import sample.http.routes.{HealthCheckRoutes, UserRoutes}
import sample.services.healthcheck.HealthCheck
import sample.system.config.Config
import sttp.tapir.openapi.circe.yaml.RichOpenAPI
import sttp.tapir.swagger.http4s.SwaggerHttp4s
import zio.clock.Clock
import zio.interop.catz._
import zio.interop.catz.implicits._
import zio.{Runtime, Task, URIO, ZIO, blocking}

object Server {

  private val userDocs = UserRoutes.docs.toYaml

  private val appRoutes: URIO[UserServiceEnv with HealthCheck, HttpApp[Task]] =
    for {
      userRoutes        <- UserRoutes.routes
      healthCheckRoutes <- HealthCheckRoutes.routes
      docsRoutes         = new SwaggerHttp4s(userDocs).routes[Task]
    } yield (userRoutes <+> healthCheckRoutes <+> docsRoutes).orNotFound

  val runServer: ZIO[AppEnv, Throwable, Unit] =
    for {
      app                          <- appRoutes
      svConfig                     <- Config.httpServerConfig
      implicit0(r: Runtime[Clock]) <- ZIO.runtime[Clock]
      bec                          <- blocking.blocking(ZIO.descriptor.map(_.executor.asEC))
      _ <-
        BlazeServerBuilder[Task](bec)
          .bindHttp(svConfig.port.value, svConfig.host.value)
          .withoutBanner
          .withNio2(true)
          .withHttpApp(app)
          .serve
          .compile
          .drain
    } yield ()

}
