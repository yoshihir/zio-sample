# zio-sample


## memo
### どこまで作業したか
- health check routesまで動作確認
- Dependenciesで色々消してみる。zio-streamとmacro
- refined系のgithub(https://github.com/fthomas/refined)
- curl http://localhost:9000/health/alive

### 後で試すもの
- buildEnvとlogEnvで環境変数を根こそぎ撮ってきているがこの処理が必要なのか消してみて動作確認する
- configでReqResConfigは使っているのか。使っていないんだったら消す
- dbは一旦posgreで作り、後でmysqlに置き換えてみる
- configのpackageのimport文で使っていないのを消したらどうなるか確認する
- dbtransactorは後で何をしているか確認する
- client系は後で外だし(package構成を変更)をする
- ClientErrorはErrorResponse or Response Errorに書き換えたい
- import doobie.refined.implicits._っているのか?
