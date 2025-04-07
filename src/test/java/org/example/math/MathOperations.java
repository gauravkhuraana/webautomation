package org.example.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathOperations {
    private static final Logger logger = LoggerFactory.getLogger(MathOperations.class);

    public void checkEvenOdd(int number) {
        logger.info("Checking if number {} is even or odd", number);
        String result = (number % 2 == 0) ? "even" : "odd";
        logger.info("Number {} is {}", number, result);
    }

    public void checkArmstrongNumber(int number) {
        logger.info("Checking if {} is an Armstrong number", number);
        int originalNumber = number;
        int sum = 0;
        
        while (number > 0) {
            int digit = number % 10;
            sum += (digit * digit * digit);
            number /= 10;
        }
        
        boolean isArmstrong = (originalNumber == sum);
        logger.info("Number {} is{} an Armstrong number", originalNumber, isArmstrong ? "" : " not");
    }

    public void calculateFactorial(int number) {
        logger.info("Calculating factorial for number: {}", number);
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        logger.info("Factorial of {} is {}", number, result);
    }

    public void checkPrimeNumber(int number) {
        logger.info("Checking if {} is prime", number);
        boolean isPrime = true;
        
        for (int i = 2; i <= number/2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        
        logger.info("Number {} is{} prime", number, isPrime ? "" : " not");
    }
}