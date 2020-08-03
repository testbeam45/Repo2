package beam3bProgram;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue={"beam3bProgram.steps"},
        tags = {"@Scenario3"},
        plugin = {"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
        monochrome = true
)
public class RunCukesTest {
}
