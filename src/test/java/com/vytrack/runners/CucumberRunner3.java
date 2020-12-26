package com.vytrack.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "html:target/cucumber.html",
                "rerun:target/rerun.txt",
                "json:target/cucumber3.json",
                "timeline:target/timeline-report"
        },
        features = {
                "src/test/resources/features/Login.feature"
        },
        glue = "com/vytrack/step_definitions",
        tags = "@smoke",
        publish = true
)
public class CucumberRunner3 {

}
