package sample

import zio.{Has, RIO, Task}

package object persistence {

  type UserPersistence = Has[UserPersistence.Service]

  object UserPersistence {

    trait Service {
      def isHealthy: Task[Boolean]
    }

    def isHealthy: RIO[UserPersistence, Boolean] = RIO.accessM(_.get.isHealthy)
  }

}
