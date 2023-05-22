/*
 * This file is part of Privado OSS.
 *
 * Privado is an open source static code analysis tool to discover data flows in the code.
 * Copyright (C) 2022 Privado, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, contact support@privado.ai
 */

package ai.privado.semantic

import ai.privado.dataflow.Dataflow
import io.shiftleft.codepropertygraph.generated.{Cpg, EdgeTypes, NodeTypes}
import io.shiftleft.codepropertygraph.generated.nodes.{File, SqlColumnNode, SqlQueryNode, SqlTableNode}
import io.shiftleft.semanticcpg.language._
import scala.jdk.CollectionConverters.IteratorHasAsScala

object Language {

  implicit val finder: NodeExtensionFinder         = DefaultNodeExtensionFinder
  implicit def privadoDataflow(cpg: Cpg): Dataflow = new Dataflow(cpg)

  implicit class NodeStarterForSqlQueryNode(cpg: Cpg) {
    def sqlQuery: Iterator[SqlQueryNode] =
      cpg.graph.nodes(NodeTypes.SQL_QUERY_NODE).asScala.cast[SqlQueryNode]

    def sqlTable: Iterator[SqlTableNode] =
      cpg.graph.nodes(NodeTypes.SQL_TABLE_NODE).asScala.cast[SqlTableNode]

    def sqlColumn: Iterator[SqlColumnNode] =
      cpg.graph.nodes(NodeTypes.SQL_COLUMN_NODE).asScala.cast[SqlColumnNode]
  }

  implicit class StepsForPropertyForSqlQueryNode(val trav: Iterator[SqlQueryNode]) extends AnyVal {
    def file: Iterator[File] = trav.out(EdgeTypes.SOURCE_FILE).asScala.cast[File]

  }

  implicit class StepsForPropertyForSqlTableNode(val trav: Iterator[SqlTableNode]) extends AnyVal {
    def file: Iterator[File] = trav.out(EdgeTypes.SOURCE_FILE).asScala.cast[File]

  }

  implicit class StepsForPropertyForSqlColumnNode(val trav: Iterator[SqlColumnNode]) extends AnyVal {
    def file: Iterator[File] = trav.out(EdgeTypes.SOURCE_FILE).asScala.cast[File]

  }
}
