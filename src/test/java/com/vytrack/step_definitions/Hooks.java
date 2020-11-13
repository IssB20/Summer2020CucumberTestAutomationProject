package com.vytrack.step_definitions;

import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.concurrent.TimeUnit;

/**
 * Hooks name is not reserved. You may name this class in any way.
 * For example: SuiteSetupAndTearDown
 * Hooks triggered based on tags not class name or their location.
 * These methods can be a part of any step definition class.
 * Common practice is to store them in the separate class.
 */



public class Hooks {

    // Hook  before = @BeforeMethod in TestNG
    // Hook After = @AfterMethod in TestNG
    //it's not a good idea to mix implicit and explicit waits. It can lead to unexpectedly long waits.
    //usually, we just use explicit and fluent waits.

    @Before
    public void setup(){
        System.out.println("::: Starting Automation :::");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    // this hook will only run before scenarios with tag @db
    /**
     * @db
     * Scenario: I don't know but here I'm connecting to DB
     * Given user runs following query "SELECT * ...."
     */
    @Before("@db")
    public void dbSetup(){
        System.out.println("::: Connecting to the database:::");
    }

    @After("@db")
    public void dbTearDown(){
        System.out.println("::: Disconnecting from the database:::");
    }


    @After
    public void tearDown(){
        // close browser, close DB connection, close tunnel
        // this is a hook after ==> @AfterMethod in TestNG
        // runs automatically after every test
        Driver.closeDriver();
        System.out.println(":::(^_^) End of test execution (*_*) :::");
    }





}
