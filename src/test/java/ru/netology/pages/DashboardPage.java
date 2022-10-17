package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private ElementsCollection cards = $$(".list__item  div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getCardBalance(String id) {

        var text = cards.findBy(attribute("data-test-id", id)).getText(); //
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
    private SelenideElement actionTransferButton = $("[data-test-id='action-transfer']");

    public DashboardPage transferFromCard1ToCard2(Integer sumTransfer) {
        cards.findBy(attribute("data-test-id", DataHelper.getIdCard2())).find("[data-test-id='action-deposit']").click();
        ammount.setValue(sumTransfer.toString());
        from.setValue(DataHelper.getNumberCard1());
        actionTransferButton.click();
        return new DashboardPage();
    }

    public DashboardPage transferFromCard2ToCard1(Integer sumTransfer) {
        cards.findBy(attribute("data-test-id", DataHelper.getIdCard1())).find("[data-test-id='action-deposit']").click();
        ammount.setValue(sumTransfer.toString());
        from.setValue(DataHelper.getNumberCard2());
        actionTransferButton.click();
        return new DashboardPage();
    }

}
