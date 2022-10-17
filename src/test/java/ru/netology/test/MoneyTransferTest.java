package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPageV1;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @BeforeEach
    void SetUp() {
        var authInfo = DataHelper.getValidAuthInfo();
        open("http://localhost:9999/", LoginPageV1.class)
                .validLogin(authInfo)
                .validVerify(DataHelper.getValidVerificationCodeFor(authInfo));
    }
    @Test
    void checkBalanceAfterTransfer() {
        DashboardPage dashboardPage = new DashboardPage();
        var balanceCard1Before = dashboardPage.getCardBalance(DataHelper.getIdCard1());
        var balanceCard2Before = dashboardPage.getCardBalance(DataHelper.getIdCard2());
        var transferSum = 2_000;
        dashboardPage.transferFromCard2ToCard1((transferSum));
        var balanceCard1After = dashboardPage.getCardBalance(DataHelper.getIdCard1());
        var balanceCard2After = dashboardPage.getCardBalance(DataHelper.getIdCard2());
        assert balanceCard1After == (balanceCard1Before + transferSum);
        assert balanceCard2After == (balanceCard2Before - transferSum);
    }
    @Test
    void transferCannotMoreThanBalance() {
        DashboardPage dashboardPage = new DashboardPage();
        var balanceCard1Before = dashboardPage.getCardBalance(DataHelper.getIdCard1());
        dashboardPage.transferFromCard1ToCard2((balanceCard1Before * 2));
        var balanceCard1After = dashboardPage.getCardBalance(DataHelper.getIdCard1());
        assert balanceCard1After >= 0;
    }

    @Test
    void balanceCannotBeNegative() {
        DashboardPage dashboardPage = new DashboardPage();
        var balanceCard2Before = dashboardPage.getCardBalance(DataHelper.getIdCard2());
        dashboardPage.transferFromCard2ToCard1((balanceCard2Before * 2));
        var balanceCard2After = dashboardPage.getCardBalance(DataHelper.getIdCard2());
        assert balanceCard2After >= 0;
    }
}
