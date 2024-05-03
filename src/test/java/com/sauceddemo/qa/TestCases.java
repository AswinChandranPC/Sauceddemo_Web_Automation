package com.sauceddemo.qa;

import com.sauceddemo.qa.base.TestBase;
import com.sauceddemo.qa.pages.CheckoutPage;
import com.sauceddemo.qa.pages.LoginPage;
import com.sauceddemo.qa.pages.ProductsPage;
import com.sauceddemo.qa.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.List;

public class TestCases extends TestBase {
    private LoginPage loginPage;
    private ProductsPage productPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;

    public TestCases() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        initialization();
        loginPage = new LoginPage();
        productPage = new ProductsPage();
        shoppingCartPage = new ShoppingCartPage();
        checkoutPage = new CheckoutPage();
    }

    @Test
    public void testAddProductsAndCheckout() {
        try {
            //Login to Saucedemo website
            loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

            Thread.sleep(3000);

            //Sort products by price - Low to High
            productPage.sortProductsByPriceLowToHigh();
            //Add products to Cart
            productPage.addToCart(4);

            Thread.sleep(3000);

            //Go to shopping cart
            shoppingCartPage.goToCart();
            //Click on checkout button
            shoppingCartPage.clickOnCheckoutBtn();

            Thread.sleep(3000);

            //Fill checkout form
            checkoutPage.fillCheckoutForm("John", "Doe", "560037");
            // Get total price in USD
            String totalPrice = checkoutPage.getTotalPrice().substring(8);
            //Store the names of products added in Cart
            List<String> ProductNamesInCart = checkoutPage.getProductNamesInCart();
            //Finish checkout
            checkoutPage.finishCheckout();
            // Assert success message
            String successMessage = checkoutPage.getFinalMessage();
            Assert.assertTrue(successMessage.contains("Thank you for your order!"));

            Thread.sleep(3000);

            System.out.println("===============================================");
            System.out.println("Success message : " + successMessage);
            System.out.println("===============================================");
            System.out.println("========Products Added in Cart=================");
            for (String productName : ProductNamesInCart) {
                System.out.println(productName);
            }
            System.out.println("===============================================");
            //Convert total from US Dollar to Indian Rupee
            // 1 US Dollar = INR 83.43
            double conversionRate = 83.43;
            double totalInIndianRupee = new Double(totalPrice) * conversionRate;
            // Create DecimalFormat object with desired pattern
            DecimalFormat df = new DecimalFormat("#.##");
            //Format the total to show last 2 decimal points only
            System.out.println("Total in Indian Rupee : Rs." + df.format(totalInIndianRupee));
            System.out.println("===============================================");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
