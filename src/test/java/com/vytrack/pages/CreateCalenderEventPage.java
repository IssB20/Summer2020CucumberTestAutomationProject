package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCalenderEventPage extends BasePage{



    @FindBy(xpath = "//a[@title='Create Calendar event']")
    private WebElement createCalenderEventBtn;

    @FindBy(name = "oro_calendar_event_form[title]")
    private WebElement titleInputBox;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement descriptionInputBox;

    public void clickOnCreateCalenderEvent(){
        BrowserUtils.clickOnElement(createCalenderEventBtn);
    }

    public void enterTitle(String text){
        BrowserUtils.enterText(titleInputBox, text);
    }

    public void enterDescription(String text){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        //exit from all frames
        Driver.getDriver().switchTo().defaultContent();
        //wait for frame and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        //Enter text
       // BrowserUtils.enterText(descriptionBox, text);
        descriptionInputBox.sendKeys(text);
        // exit from the frame

        Driver.getDriver().switchTo().defaultContent();
    }

    public String getDataFromGeneralInfo(String parameterName){
      WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
      String xpath = "//label[text()='"+parameterName+"']/../div/div";
      WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

      return element.getText().trim();
    }
}
