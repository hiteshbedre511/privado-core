name := "standalone-schema"

libraryDependencies += "io.shiftleft" %% "overflowdb-codegen" % "2.88"
libraryDependencies += "io.shiftleft" %% "codepropertygraph-schema" % Versions.cpg

Compile / generateDomainClasses / classWithSchema := "CpgExtSchema$"
Compile / generateDomainClasses / fieldName := "instance"
