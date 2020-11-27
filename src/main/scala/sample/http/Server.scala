package sample.http

import cats.syntax.all._
import org.http4s.HttpApp
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import sample.{AppEnv, UserServiceEnv}
import sample.http.routes.HealthCheckRoutes
import sample.services.healthcheck.HealthCheck
import sample.system.config.Config
import zio.clock.Clock
import zio.interop.catz._
import zio.interop.catz.implicits._
import zio.{blocking, Runtime, Task, URIO, ZIO}

object Server {

  private val appRoutes: URIO[UserServiceEnv with HealthCheck, HttpApp[Task]] =
    for {
      healthCheckRoutes <- HealthCheckRoutes.routes
    } yield (healthCheckRoutes).orNotFound

  val runServer: ZIO[AppEnv, Throwable, Unit] =
    for {
      app                          <- appRoutes
      svConfig                     <- Config.httpServerConfig
      implicit0(r: Runtime[Clock]) <- ZIO.runtime[Clock]
      bec                          <- blocking.blocking(ZIO.descriptor.map(_.executor.asEC))
      _ <- BlazeServerBuilder[Task](bec)
        .bindHttp(svConfig.port.value, svConfig.host.value)
        .withoutBanner
        .withNio2(true)
        .withHttpApp(app)
        .serve
        .compile
        .drain
    } yield ()

}
