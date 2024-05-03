package com.sauceddemo.qa.pages;

import com.sauceddemo.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends TestBase {

    @FindBy(id = "first-name")
    private WebElement firstNameTextBox;

    @FindBy(id = "last-name")
    private WebElement lastNameTextBox;

    @FindBy(id = "postal-code")
    private WebElement postalCodeTextBox;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(xpath = "//div[2]/div[8]")
    private WebElement totalPrice;

    @FindBy(className = "complete-header")
    private WebElement finalMessageText;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        firstNameTextBox.sendKeys(firstName);
        lastNameTextBox.sendKeys(lastName);
        postalCodeTextBox.sendKeys(postalCode);
        continueButton.click();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public String getFinalMessage() {
        return finalMessageText.getText();
    }

    public void finishCheckout() {
        finishButton.click();
    }

    public List<String> getProductNamesInCart() {
        //Store the xpath of products in Cart to get the products added in Cart
        List<WebElement> products = driver.findElements(By.xpath("//div[*]/div[2]/a[1]/div[1]"));

        //Store the names of products added in Cart
        List<String> productNamesInCart = new ArrayList<>();

        //iterate through products list and add the products name to productNamesInCart list
        for (WebElement productname : products) {
            productNamesInCart.add(productname.getText());
        }
        return productNamesInCart;
    }
}
