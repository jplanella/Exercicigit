package cesar;

import java.util.Scanner;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CaesarAES {

    final static int KEY = 2;
    static String IV = "AAAAAAAAAAAAAAAA";
    static String encryptionKey = "0123456789abcdef";

    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8"))); //ULL !!! CBC. Per tant, el tercer paràmetre és l'array d'inicialització
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8"))); //ULL !!! CBC. Per tant, el tercer paràmetre és l'array d'inicialització
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }

    public static void main(String... s) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message: ");
        String message = sc.nextLine();
        String encryptedMessage = encrypt(message);
        System.out.println(encryptedMessage);

        byte[] cipher = encrypt(encryptedMessage, encryptionKey);
        for (int i = 0; i < cipher.length; i++) {
            System.out.print(new Integer(cipher[i]) + " ");
        }
        System.out.println("");

        String decrypted = decrypt(cipher, encryptionKey);
        System.out.println(decrypted);

        String decryptedMessage = decrypt(decrypted);
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
