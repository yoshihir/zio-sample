//package sample.data
//
//import io.circe.Codec
//import io.circe.generic.semiauto.deriveCodec
//
//final case class PostUser(user_id: UserId, email: Email)
//
//object PostUser {
//  implicit val codec: Codec[PostUser] = deriveCodec
//
//  val Test = PostUser(UserId.Test, Email.Test)
//}
