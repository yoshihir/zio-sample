# zio-sample

https://github.com/saraiva132/zio-cats-backend


## memo
### どこまで作業したか
- Dependenciesで色々消してみる。zio-streamとmacro -> 済
- refined系のgithub(https://github.com/fthomas/refined)
- curl http://localhost:9000/health/alive まで動作確認
- ReqResClientHTTPは外部APIを叩いていている(実際は自分たちのサービスだと思うがマイクロサービスを意識しているんだろう)
- createUserのclientを除いてcreateできるようにする -> 済
- routesでUserRoutesを作成 -> 済
- Serverでdocsを作成するように修正 -> 済
- http://localhost:9000/docsでopenapiが作成されることまで確認 -> 済
- testを真面目に書く

### 後で試すもの
- buildEnvとlogEnvで環境変数を根こそぎ撮ってきているがこの処理が必要なのか消してみて動作確認する -> 消して動いた
- configでReqResConfig, HTTPClientは使っているのか。testでいらなかったら消す
- dbは一旦posgreで作り、後でmysqlに置き換えてみる
- configのpackageのimport文で使っていないのを消したらどうなるか確認する
- dbtransactorは後で何をしているか確認する
- client系は後で外だし(package構成を変更)をする
- ClientErrorはErrorResponse or Response Errorに書き換えたい
- import doobie.refined.implicits._っているのか? -> refined系はimplicitで結構使ってた

