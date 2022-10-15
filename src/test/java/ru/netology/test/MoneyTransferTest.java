package ru.netology.test;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPageV1;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @BeforeEach
    void SetUp() { //регистрация, дойти до лк
        var authInfo = DataHelper.getValidAuthInfo();
        open("http://localhost:9999/", LoginPageV1.class)
                .validLogin(authInfo)
                .validVerify(DataHelper.getValidVerificationCodeFor(authInfo));
    }


    @Test
    void showBalance() {
        DashboardPage dashboardPage = new DashboardPage();
        int sss = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");

        System.out.println(sss);
    }

    @Test
    void actionRandomDeposit() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.actionDeposit(DataHelper.getSumForTransfer(), DataHelper.getRandomCardNumber());
        System.out.println(dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0"));
    }
}
