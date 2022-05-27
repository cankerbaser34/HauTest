package com.haufe.uiPages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.haufe.utilities.ConfigurationReader;
import com.haufe.utilities.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class Purchase_Home extends Base {

    public int finalPrice;
    public int totalPriceCart;

    public Purchase_Home() {
        PageFactory.initElements(Driver.get(ConfigurationReader.get("url")), this);
    }

    @FindBy(css = "h4.product-name")
    public List<WebElement> items;

    @FindBy(xpath = "//div[@class='product-action']/button")
    public List<WebElement> singleClick;

    @FindBy(css = "a.increment")
    public List<WebElement> increment_button;

    @FindBy(css = "p.product-price")
    public List<WebElement> price_item;

    @FindBy(css = "p.product-name")
    public List<WebElement> each_cart_item;

    @FindBy(xpath = "//header/div[1]/div[3]/a[4]/img[1]")
    public WebElement cart_image;

    @FindBy(xpath = "//button[contains(text(),'PROCEED TO CHECKOUT')]")
    public WebElement checkout_button;

    @FindBy(xpath = "//input[@placeholder='Enter promo code']")
    public WebElement prome_code_field;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    public WebElement apply_button;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    public WebElement place_order_button;

    @FindBy(xpath = "//select[@style='width: 200px;']")
    public WebElement country_dropdown;

    @FindBy(xpath = "//li[@class='cart-item'][1]/a")
    public WebElement delete_button;


    @FindBy(css = "p.product-name")
    public List<WebElement> cart_items;

    @FindBy(xpath = "//li[@class='cart-item']/a")
    public List<WebElement> delete_x_button;

    @FindBy(css = "input.chkAgree")
    public WebElement agree_check_box;

    @FindBy(xpath = "//tbody/tr[1]/td[3]/strong[1]")
    public WebElement total_item;

    @FindBy(xpath = "//button[contains(text(),'Proceed')]")
    public WebElement agree_button;

    @FindBy(xpath = "//tbody/tr[2]/td[3]/strong[1]")
    public WebElement price_total;

    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/span[1]")
    public WebElement price_total_cart;


    public void addMore(String[] items_increased, int addnumber) {

        int l = 0;
        for (int i = 0; i < items.size(); i++) {

            String[] name = items.get(i).getText().split("-");
            String formattedName = name[0].trim();

            List items_increasedList = Arrays.asList(items_increased);

            if (items_increasedList.contains(formattedName)) {
                l++;
                for (int s = 1; s < addnumber; s++) {
                    increment_button.get(i).click();
                }

                singleClick.get(i).click();

                if (l == items_increased.length) {
                    break;
                }
                Base.waitFor(3);
            }
        }

    }

    public void addItems(String[] itemsNeeded) {
        int j = 0;
        for (int i = 0; i < items.size(); i++) {

            String[] name = items.get(i).getText().split("-");
            String formattedName = name[0].trim();

            List itemsNeededList = Arrays.asList(itemsNeeded);
            if (itemsNeededList.contains(formattedName)) {
                j++;
                singleClick.get(i).click();
                if (j == itemsNeeded.length) {
                    break;
                }
            }
        }
    }

    public void clickCart() {
        cart_image.click();
    }

    public void proceedToCheckout() {
        checkout_button.click();
      // Base.waitFor(2);
    }


    public void promecodeApply(String code) {
        Base.waitForVisibility(prome_code_field, 2);
        prome_code_field.sendKeys(code);
        apply_button.click();

    }

    public void placeOrderClick() {

        Base.waitForClickablility(place_order_button, 2);
        place_order_button.click();

    }

    public void selectCountry() {

        Select country = new Select(country_dropdown);
        country.selectByVisibleText("Spain");
    }

    public void deleteItems2(String[] items_deleted) {

        int p = 0;
        for (int i = 0; i < cart_items.size(); i++) {
            String[] name = cart_items.get(i).getText().split("-");
            String formattedName = name[0].trim();

            List itemsdeletedList = Arrays.asList(items_deleted);
            if (itemsdeletedList.contains(formattedName)) {
                p++;
                delete_x_button.get(i).click();
                if (p == items_deleted.length) {
                    break;
                }
            }
        }

    }

    public void clickAgreeCheckbox() {
        agree_check_box.click();
    }

    public void clickAgreedButton() {
        agree_button.click();
    }

    public void confirmation_message() {

      Base.waitFor(2);
        Driver.get(ConfigurationReader.get("url")).getPageSource().contains("Thank you, your order has been placed successfully");
        System.out.println(Driver.get(ConfigurationReader.get("url")).getCurrentUrl());

    }

    public int finPrices() {

        String stprice = price_total.getText();
        return finalPrice = Integer.parseInt(stprice);

    }

    public int finalCartPrice() {

        String priceTextCart = price_total_cart.getText();
        return totalPriceCart = Integer.parseInt(priceTextCart);
    }

    public void assertPrices() {
        Assert.assertEquals(finalPrice, totalPriceCart);
    }

    public void AssertDeletedItems(String deletedItem) {
        for (int i = 0; i < each_cart_item.size(); i++) {
            Assert.assertFalse(each_cart_item.get(i).getText().contains(deletedItem));

        }

    }


}



