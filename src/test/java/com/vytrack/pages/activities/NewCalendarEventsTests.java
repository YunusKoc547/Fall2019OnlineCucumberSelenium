package com.vytrack.pages.activities;

import com.vytrack.pages.AbstractPageBase;
import com.vytrack.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

class CalendarEventsPage extends AbstractPageBase {

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;
    @FindBy(className = "select2-chosen")
    private WebElement owner;
    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;
    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    private WebElement startTime;
    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    private WebElement endTime;
    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;
    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;
    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;
    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;
    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;
    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;
    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//p[1]")
    private WebElement generalInfoDescription;
    public WebElement viewPerPageToggle;
    public List<String> getColumnNames(){
        BrowserUtilities.waitForPageToLoad(20);
        return BrowserUtilities.getTextFromWebElements(columnNames);
    }
    public String getStartTime() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }
    public String getEndTime() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }
    public String getOwnerName() {
        BrowserUtilities.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }
    public void clickToCreateCalendarEvent() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtilities.waitForPageToLoad(20);
    }
    public String getStartDate() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtilities.scrollTo(startDate);
        return startDate.getAttribute("value");
    }


}

