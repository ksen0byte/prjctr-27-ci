ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "prjct-27-ci",
    libraryDependencies ++= Seq(
      "org.http4s"    %% "http4s-blaze-server" % "0.23.14",
      "org.http4s"    %% "http4s-dsl"          % "0.23.14",
      "org.typelevel" %% "cats-effect"         % "3.5.0",
      "org.scalatest" %% "scalatest"           % "3.2.15"  % Test,
      "org.http4s"    %% "http4s-blaze-client" % "0.23.14" % Test,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.7"   % Test
    )
  )
