package com.sauceddemo.qa.pages;

import com.sauceddemo.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends TestBase {

    @FindBy(xpath = "//option[contains(text(),'Price (low to high)')]")
    private WebElement priceLowToHigh;

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void sortProductsByPriceLowToHigh() {
        priceLowToHigh.click();
    }

    public void addToCart(int numberOfProductsToAdd) {
        //Store the xpath of Add to cart button
        List<WebElement> AddToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

        //Inorder to add 4 products to Cart,iterate though AddToCartButtons and Click one by one
        for (int i = 0; i <= numberOfProductsToAdd - 1; i++) {
            AddToCartButtons.get(i).click();
        }
    }
}
