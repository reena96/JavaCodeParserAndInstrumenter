name := "algo"

version := "1.0"

scalaVersion := "2.12.3"

//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.eclipse.jdt" % "core" % "3.3.0-v_771"
// https://mvnrepository.com/artifact/org.eclipse.jdt/org.eclipse.jdt.annotation
libraryDependencies += "org.eclipse.jdt" % "org.eclipse.jdt.annotation" % "2.0.0"
// https://mvnrepository.com/artifact/org.eclipse.jdt.core.compiler/ecj
libraryDependencies += "org.eclipse.jdt.core.compiler" % "ecj" % "4.4.2"
// https://mvnrepository.com/artifact/org.eclipse.core/org.eclipse.core.runtime
libraryDependencies += "org.eclipse.core" % "org.eclipse.core.runtime" % "3.7.0"
// https://mvnrepository.com/artifact/org.apache.commons/commons-io
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"
// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.16"
// https://mvnrepository.com/artifact/org.eclipse.osgi/org.eclipse.osgi
libraryDependencies += "org.eclipse.osgi" % "org.eclipse.osgi" % "3.9.1.v20140110-1610"


libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

// for debugging sbt problems
logLevel := Level.Debug

scalacOptions += "-deprecation"
