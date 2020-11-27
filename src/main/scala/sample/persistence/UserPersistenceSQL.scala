package sample.persistence

import doobie.Transactor
import doobie.implicits._
import doobie.refined.implicits._
import doobie.util.query.Query0
import sample.persistence.UserPersistenceSQL.Queries
import sample.system.dbtransactor.DBTransactor
import zio.interop.catz._
import zio.{Task, ZLayer}

final class UserPersistenceSQL(trx: Transactor[Task]) extends UserPersistence.Service {

  def isHealthy: Task[Boolean] =
    Queries.health.option
      .transact(trx)
      .isSuccess

}

object UserPersistenceSQL {

  object Queries {

    val health: Query0[Unit] = sql"""SELECT 1 as one;""".query[Unit]
  }

  val live: ZLayer[DBTransactor, Throwable, UserPersistence] =
    ZLayer.fromEffect(
      DBTransactor.transactor.map(new UserPersistenceSQL(_))
    )

}
