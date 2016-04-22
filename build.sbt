import sbt.Keys._

// Settings
val scalaJsLibraryName: String = "marked"

lazy val commonSettings = Seq(
  organization := "com.github.karasiq",
  version := "1.0.1",
  isSnapshot := version.value.endsWith("SNAPSHOT"),
  scalaVersion := "2.11.8"
)

lazy val librarySettings = Seq(
  name := s"scalajs-$scalaJsLibraryName",
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  ),
  jsDependencies += RuntimeDOM,
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ ⇒ false },
  licenses := Seq("The MIT License" → url("http://opensource.org/licenses/MIT")),
  homepage := Some(url(s"https://github.com/Karasiq/${name.value}")),
  pomExtra := <scm>
    <url>git@github.com:Karasiq/{name.value}.git</url>
    <connection>scm:git:git@github.com:Karasiq/{name.value}.git</connection>
  </scm>
    <developers>
      <developer>
        <id>karasiq</id>
        <name>Piston Karasiq</name>
        <url>https://github.com/Karasiq</url>
      </developer>
    </developers>
)

lazy val testBackendSettings = Seq(
  name := s"scalajs-$scalaJsLibraryName-test",
  resolvers += Resolver.sonatypeRepo("snapshots"),
  libraryDependencies ++= {
    val sprayV = "1.3.3"
    val akkaV = "2.4.0"
    Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaV,
      "io.spray" %% "spray-can" % sprayV,
      "io.spray" %% "spray-routing-shapeless2" % sprayV,
      "io.spray" %% "spray-json" % "1.3.2"
    )
  },
  mainClass in Compile := Some("com.karasiq.scalajstest.backend.TestApp"),
  scalaJsBundlerInline in Compile := true,
  scalaJsBundlerCompile in Compile <<= (scalaJsBundlerCompile in Compile).dependsOn(fullOptJS in Compile in libraryTestFrontend),
  scalaJsBundlerAssets in Compile += {
    import com.karasiq.scalajsbundler.dsl.{Script, _}
    
    val markedJs = "org.webjars.bower" % "marked" % "0.3.5"
    val highlightJs = "org.webjars" % "highlightjs" % "9.2.0"
    val tabOverrideJs = github("wjbryant", "taboverride", "4.0.3") / "build" / "output"
    
    val jsDeps = Seq(
      // jQuery
      Script from url("https://code.jquery.com/jquery-2.1.4.min.js"),

      // Bootstrap
      Style from "org.webjars" % "bootstrap" % "3.3.6" / "css/bootstrap.css",
      
      // Marked
      Script from markedJs / "marked.min.js",

      // Highlight.js
      Script from highlightJs / "highlight.min.js",
      Style from highlightJs / s"styles/${TestPageAssets.highlightJsStyle}.css",

      // Tab Override
      Script from tabOverrideJs / "taboverride.min.js"
    )
    
    val highlightJsLanguages = for (lang ← TestPageAssets.highlightJsLanguages)
      yield Script from highlightJs / s"languages/$lang.min.js"

    Bundle("index", Html from TestPageAssets.index, jsDeps, highlightJsLanguages, scalaJsApplication(libraryTestFrontend).value)
  }
)

lazy val testFrontendSettings = Seq(
  persistLauncher in Compile := true,
  name := s"scalajs-$scalaJsLibraryName-test-frontend",
  libraryDependencies ++= Seq(
    "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
    "com.lihaoyi" %%% "scalatags" % "0.5.4"
  )
)

// Projects
lazy val library = Project("scalajs-library", file("."))
  .settings(commonSettings, librarySettings)
  .enablePlugins(ScalaJSPlugin)

lazy val libraryTest = Project(s"scalajs-$scalaJsLibraryName-test", file("test"))
  .settings(commonSettings, testBackendSettings)
  .enablePlugins(ScalaJSBundlerPlugin)

lazy val libraryTestFrontend = Project(s"scalajs-$scalaJsLibraryName-test-frontend", file("test") / "frontend")
  .settings(commonSettings, testFrontendSettings)
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(library)