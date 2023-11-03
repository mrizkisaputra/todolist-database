package mrizkisaputra.utils;

import java.util.Scanner;

public class TextInput {
    private static final Scanner scanner = new Scanner(System.in);
    public static String getTextInput() {
        return scanner.nextLine();
    }
}
