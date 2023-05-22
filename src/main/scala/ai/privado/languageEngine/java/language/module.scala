package ai.privado.languageEngine.java.language

import io.shiftleft.codepropertygraph.generated.{Cpg, EdgeTypes, NodeTypes}
import io.shiftleft.codepropertygraph.generated.nodes.{File, Module, ModuleDependency}
import io.shiftleft.semanticcpg.language._
import overflowdb.traversal.Traversal

import scala.jdk.CollectionConverters.IteratorHasAsScala

object module {

  implicit class NodeStarters(cpg: Cpg) {

    def module: Traversal[Module] =
      cpg.graph.nodes(NodeTypes.MODULE).cast[Module]
  }

  implicit class StepsForModule(val trav: Traversal[Module])
      extends AnyVal {

    def file: Traversal[File] = trav.out(EdgeTypes.SOURCE_FILE).cast[File]

    def dependencies: Traversal[ModuleDependency] = trav.out(EdgeTypes.DEPENDENCIES).asScala.cast[ModuleDependency]
  }

  implicit class StepsForDependency(
    val traversal: Traversal[ModuleDependency]
  ) extends AnyVal {

    def file: Traversal[File] = traversal.out(EdgeTypes.SOURCE_FILE).cast[File]

  }

}
