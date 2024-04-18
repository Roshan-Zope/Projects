package com.expensetracker.utilities;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP() {
        Random random = new Random();
        int otpValue = 1000 + random.nextInt(9000);
        return String.valueOf(otpValue);
    }
}
