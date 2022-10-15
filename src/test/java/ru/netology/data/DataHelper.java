package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLoginAndValidPasswordAuthInfo() {
        return new AuthInfo("petya", "qwerty123");
    }

    public static AuthInfo getValidLoginAndInvalidPasswordAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("invalidUser", "456zxcvb");
    }

    @Value
    public static class VerificationCode {
        public String code;
    }

    public static VerificationCode getValidVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("77777");
    }

    @Value
    public static class CardNumber {
        private String CardNumber1;
        private String CardNumber2;
    }
    public static String getRandomCardNumber() {
        var random = new SecureRandom();
        var list = Arrays.asList("5559 0000 0000 0001", "5559 0000 0000 0002");
        var cardNumber = list.get(random.nextInt(list.size()));
        return cardNumber;
    }

    @Value
    public static class SumforTrnsfer {
        private String Sum;
    }

    public static String getSumForTransfer() {
        Faker faker = new Faker();
        var sumForTransfer = faker.number().toString();
        return sumForTransfer;
    }


}
