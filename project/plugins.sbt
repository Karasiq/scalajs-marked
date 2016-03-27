logLevel := Level.Warn

resolvers += Resolver.sonatypeRepo("snapshots")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.8")

addSbtPlugin("com.github.karasiq" % "sbt-scalajs-bundler" % "1.0.7")

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "scalatags" % "0.5.4",
  "org.webjars.bower" % "marked" % "0.3.5",
  "org.webjars" % "highlightjs" % "9.2.0",
  "org.webjars" % "bootstrap" % "3.3.6"
)