package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCalenderEventPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import sun.lwawt.macosx.CDropTarget;

import java.util.Map;

public class CreateCalenderEventStepDefinitions {


    CreateCalenderEventPage createCalenderEventPage = new CreateCalenderEventPage();



    @Given("user clicks on create calendar event button")
    public void user_clicks_on_create_calendar_event_button() {
        createCalenderEventPage.clickOnCreateCalenderEvent();

    }

    /*
      | Title       | B20 Graduation Party                        |
      | Description | All B20 friends are invited for this party! |
     */
    @When("user adds new calendar event information")
    public void user_adds_new_calendar_event_information(Map<String, String > data) {
        String title = data.get("Title");
        String description = data.get("Description");


        createCalenderEventPage.enterDescription(description);
        createCalenderEventPage.enterTitle(title);

    }


    @Then("user verifies that new calendar event is displayed:")
    public void user_verifies_that_new_calendar_event_is_displayed(Map<String , String> data) {
        String title = data.get("Title");
        String description = data.get("Description");

        Assert.assertEquals(title, createCalenderEventPage.getDataFromGeneralInfo("Title"));
        Assert.assertEquals(description, createCalenderEventPage.getDataFromGeneralInfo("Description"));




    }







}
