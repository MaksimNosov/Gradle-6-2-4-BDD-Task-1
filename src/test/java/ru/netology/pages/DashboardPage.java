package ru.netology.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getCardBalance(String id) {
//        var text = cards.filterBy(text(id)).toString();
//        String text ;
//        for (SelenideElement card : cards) {
             var text = cards.find(Condition.cssClass("[data-test-id='" + id + "']")).getText();
//        var text = cards.findBy(cssClass(id)).getText();


        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    private SelenideElement actionDepositButton = $("[data-test-id='action-deposit']");
    private SelenideElement ammount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement to = $("[data-test-id='to'] input");
    private SelenideElement actionTransferButton = $("[data-test-id='to']");

    public DashboardPage actionDeposit(String sumAction, String idCardFrom) {
        actionDepositButton.click();
        ammount.setValue(sumAction);
        from.setValue(idCardFrom);
//        to.setValue(idCardForUp);
        actionTransferButton.click();
        return new DashboardPage();
    }
}
