/*
 * Copyright (C) 2013 RandomCoder <randomcoder@randomcoding.co.uk>
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
 * Contributors:
 * RandomCoder - initial API and implementation and/or initial documentation
 */
package uk.co.randomcoding.cucumber.generator.gherkin

/**
 * A generic component that represents a Gherkin Syntax component
 *
 * @author RandomCoder
 */
sealed abstract class GherkinComponent(val identifier: String, tags: Seq[String])

case class Feature(description: String, inOrderTo: String, asA: String, iWantTo: String, tags: Seq[String] = Seq.empty) extends GherkinComponent("Feature", tags)