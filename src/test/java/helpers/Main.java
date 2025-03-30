package helpers;

import pages.CustPage;

import java.io.IOException;

import static helpers.CustomerDataGenerator.generatePostCode;
import static helpers.CustomerDataGenerator.generateFirstNameFromPostCode;


public class Main {
    public static void main(String[] args) {
        System.out.println(PropertyProvider.getInstance().getProperty("browser.name"));
        String postcode = generatePostCode();
        System.out.println(postcode);
        String firstName = generateFirstNameFromPostCode(postcode);
        System.out.println(firstName);

    }
}
