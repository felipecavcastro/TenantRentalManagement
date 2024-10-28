package com.felipe.castro.TenantRentalManagement.utils;

public class DocumentValidator {
    public static boolean isCpfValid(String cpf) {
        return validateDigit(cpf, 1) && validateDigit(cpf, 2);
    }

    private static boolean validateDigit(String cpf, int digit) {
        String[] digits = cpf.split("");
        int numberOfDigits = digits.length - digit;

        int sum = calculateSum(digits, numberOfDigits);

        int informedFirstDigit = Integer.parseInt(digits[numberOfDigits]);
        int calculatedFirstDigit = calculateDigit(sum);

        return informedFirstDigit == calculatedFirstDigit;
    }

    private static int calculateSum(String[] digits, int numberOfDigits) {
        int sum = 0;
        int multiplier = numberOfDigits + 1;
        for (int i = 0; i < numberOfDigits; i++) {
            sum += Integer.parseInt(digits[i]) * multiplier--;
        }
        return sum;
    }

    public static int calculateDigit(int sum) {
        int rest = sum % 11;
        return rest < 2 ? 0 : 11 - rest;
    }
}
