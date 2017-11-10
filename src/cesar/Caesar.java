package cesar;

import java.util.Scanner;

public class Caesar {

    final static int KEY = 2;

    public static void main(String... s) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message: ");
        String message = sc.nextLine();
        String encryptedMessage = encrypt(message);
        System.out.println(encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println(decryptedMessage);
    }

    private static String encrypt(String message) {
        char ch;
        String encryptedMessage = "";
        for (int i = 0; i < message.length(); ++i) {
            ch = message.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch + KEY);
                if (ch > 'z') {
                    ch = (char) (ch - 'z' + 'a' - 1);
                }
                encryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + KEY);
                if (ch > 'Z') {
                    ch = (char) (ch - 'Z' + 'A' - 1);
                }
                encryptedMessage += ch;
            } else {
                encryptedMessage += ch;
            }
        }
        return encryptedMessage;
    }

    private static String decrypt(String encryptedMessage) {
        char ch;
        String decryptedMessage = "";
        for (int i = 0; i < encryptedMessage.length(); ++i) {
            ch = encryptedMessage.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - KEY);
                if (ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }
                decryptedMessage += ch;
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - KEY);
                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }
                decryptedMessage += ch;
            } else {
                decryptedMessage += ch;
            }
        }

        return decryptedMessage;
    }
}
