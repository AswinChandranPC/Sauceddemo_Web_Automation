package com.sauceddemo.qa.pages;

import com.sauceddemo.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends TestBase {
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public ShoppingCartPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        shoppingCart.click();
    }

    public void clickOnCheckoutBtn() {
        checkoutButton.click();
    }
}
