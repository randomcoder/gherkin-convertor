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

package uk.co.randomcoding.cucumber.generator.reader

import uk.co.randomcoding.cucumber.generator.gherkin.{Examples, Scenario, ScenarioOutline}
import uk.co.randomcoding.cucumber.generator.{FeatureTestHelpers, FlatSpecTest}

import scala.language.implicitConversions

class CucumberScenarioOutlineReaderSpec extends FlatSpecTest with FeatureTestHelpers {

  behaviour of "A Feature Reader"

  it should "Read a Scenario Outline from a Feature that has a single Scenario" in {
    val feature = FeatureReader.read("/single-scenario-outline.feature")
    feature.scenarios should be(Seq(simpleScenarioOutline))
  }

  it should "Read a Scenario Outline from a Feature that has two simple Scenarios only one of which has tags" in {
    val feature = FeatureReader.read("/basic-feature-outline.feature")
    feature.scenarios should be(Seq(basicScenarioOutline1, basicScenarioOutline2))
  }

  it should "Read a Scenario Outline from a Feature that has a single Scenario with each step having 'Ands'" in {
    val feature = FeatureReader.read("/single-scenario-outline-with-ands.feature")
    feature.scenarios should be(Seq(simpleScenarioOutlineWithAnds))
  }

  it should "Read a Scenario Outline from a Feature that has a single Scenario with each step having 'Buts'" in {
    val feature = FeatureReader.read("/single-scenario-outline-with-buts.feature")
    feature.scenarios should be(Seq(simpleScenarioOutlineWithButs))
  }

  it should "Read a Scenario Outline from a Feature file that has tags on the scenario and examples" in {
    val feature = FeatureReader.read("/single-scenario-outline-with-tags-on-examples.feature")
    feature.scenarios should be(Seq(simpleScenarioOutlineWithExampleTags))
  }

  it should "Read both Scenarios and Scenario Outlines from a feature file with both in" in {
    val feature = FeatureReader.read("/basic-feature-mixed-scenario-and-outline.feature")
    feature.scenarios should be(Seq(basicScenario1, basicScenarioOutline1))
  }

  it should "Read all tagged sets of examples frrom a Scenario Outline with multiple tagged examples sections" in {
    val feature = FeatureReader.read("/single-scenario-outline-with-tags-on-multiple-examples.feature")
    feature.scenarios should be(Seq(simpleScenarioOutlineWithMultipleTaggedExampleSections))
  }

  private[this] val simpleScenarioOutline = ScenarioOutline("A simple scenario outline that has single line steps", Seq("@scenario-outline-tag-1"),
    Seq("Given a simple precondition <condition>"), Seq("When I do something easy"), Seq("Then I get the result I expected of <result>"),
    Seq(Examples(Seq("condition", "result"), Seq(Seq("it's running", "it works")), Nil)))

  private[this] val simpleScenarioOutlineWithExampleTags = ScenarioOutline("A simple scenario outline that has tags on the examples",
    Seq("@outline-tag-1"),
    Seq("Given a simple precondition <condition>"), Seq("When I do something easy"), Seq("Then I get the result I expected of <result>"),
    Seq(Examples(Seq("condition", "result"), Seq(Seq("it's running", "it works")), Seq("@example-tag-1",  "@example-tag-2"))))

  private[this] val simpleScenarioOutlineWithMultipleTaggedExampleSections = ScenarioOutline("A simple scenario outline that has tags on multiple examples",
    Seq("@outline-tag-1"),
    Seq("Given a simple precondition <condition>"), Seq("When I do something easy"), Seq("Then I get the result I expected of <result>"),
    Seq(Examples(Seq("condition", "result"), Seq(Seq("it's running", "it works")), Seq("@example-tag-1",  "@example-tag-2")),
      Examples(Seq("condition", "result"), Seq(Seq("it's not running", "it still works")), Seq("@example-tag-3",  "@example-tag-4"))))

  private[this] val simpleScenarioOutlineWithAnds = ScenarioOutline("A simple scenario outline where all steps have an 'and'",
    Seq("@scenario-outline-and-tag-1"),
    Seq("Given a simple precondition <condition 1>", "And another simple precondition <condition 2>"),
    Seq("When I do something easy", "And do something tricky"),
    Seq("Then I get the result I expected of <result>", "And nothing else happens"),
    Seq(Examples(Seq("condition 1", "condition 2", "result"), Seq(Seq("test 1", "test 2", "hooray!")), Nil)))

  private[this] val simpleScenarioOutlineWithButs = ScenarioOutline("A simple scenario outline where all steps have a 'but'",
    Seq("@scenario-outline-and-tag-2"),
    Seq("Given a simple precondition <condition 1>", "But another simple precondition <condition 2>"),
    Seq("When I do something easy", "But do something tricky"),
    Seq("Then I get the result I expected of <result>", "But nothing else happens"),
    Seq(Examples(Seq("condition 1", "condition 2", "result"), Seq(Seq("test 1", "test 2", "hooray!")), Nil)))

  private[this] val basicScenarioOutline1 = ScenarioOutline("A simple scenario outline that has single line steps", Seq("@scenario-outline-tag-1"),
    Seq("Given a precondition <condition>"), Seq("When I do something"), Seq("Then I get the result I expected of <result>"),
    Seq(Examples(Seq("condition", "result"), Seq(Seq("test 1", "result 1")), Nil)))

  private[this] val basicScenarioOutline2 = ScenarioOutline("Another simple scenario outline that has single line steps", Nil,
    Seq("Given a second precondition <condition 2>"), Seq("When I do something else"), Seq("Then I also get the result I expected of <result 2>"),
    Seq(Examples(Seq("condition 2", "result 2"), Seq(Seq("test 2", "result 2")), Nil)))

  private[this] val basicScenario1 = Scenario("A simple scenario that has single line steps", Seq("@scenario-tag-1"),
    Seq("Given a precondition"), Seq("When I do something"), Seq("Then I get the result I expected"))
}
