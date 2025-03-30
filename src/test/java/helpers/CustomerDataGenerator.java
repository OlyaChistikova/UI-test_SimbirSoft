package helpers;

import java.util.Random;

public class CustomerDataGenerator {

    public static String generatePostCode(){
        Random random = new Random();
        StringBuilder postCode = new StringBuilder();

        for (int i = 0; i < 10; i++){
            postCode.append(random.nextInt(10));
        }
        return postCode.toString();
    }

    public static String generateFirstNameFromPostCode(String postcode){
        StringBuilder firstName = new StringBuilder();

        for (int i = 0; i < postcode.length(); i += 2) {
            String pair = postcode.substring(i, Math.min(i + 2, postcode.length()));
            int number = Integer.parseInt(pair);
            char letter = (char) ('a' + (number % 26));
            firstName.append(letter);
        }

        return firstName.toString();
    }
}
