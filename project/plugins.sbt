resolvers += Resolver.typesafeRepo("releases")
resolvers += Resolver.url("bintray-sbt-plugin-releases",url("https://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

// resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

resolvers += Resolver.mavenLocal

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.0-SNAPSHOT")
