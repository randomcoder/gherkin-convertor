/*
 * Copyright (C) 2017. RandomCoder <randomcoder@randomcoding.co.uk>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package uk.co.randomcoding.cucumber.generator

import java.io.File
import java.nio.file.{Files, Path}

import org.scalatest.Assertion

import scala.io.Source

import scala.language.implicitConversions

/**
 * Helper functions for tests with Feature Files
 *
 * @author RandomCoder
 */
trait FeatureTestHelpers {
  implicit def sourceToLines(s: Source): List[String] = s.getLines().toList

  implicit def pathToLines(p: String): List[String] = Source.fromInputStream(getClass.getResourceAsStream(p)).getLines().toList
}

  trait FileTestHelpers {
  def withTempDir(test: File => Assertion): Assertion = {
    val tempDir = Files.createTempDirectory("")

    try test(tempDir)
    finally deleteAll(tempDir)
  }

  private[this] def deleteAll(path: File): Unit = {
    if (path.isDirectory) Option(path.listFiles).map(_.toList).getOrElse(Nil).foreach(deleteAll)
    path.delete()
  }

  def createFile(directory: File, name: String): Path = {
    Files.createFile(directory.resolve(name))
  }

  implicit def fileToPath(f: File): Path = f.toPath
  implicit def pathToFile(p: Path): File = p.toFile
}
