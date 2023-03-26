package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static char[] symbols(int charactersCount) {
        char[] codeSymbols = new char[charactersCount];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The secret is prepared: ")
                .append("*".repeat(charactersCount))
                .append(" (0-")
                .append(charactersCount - 1);

        for (int i = 0; i < charactersCount && i < 10; i++) {
            codeSymbols[i] = (char) ('0' + i);
        }
        if (charactersCount > 10) {
            for (int j = 10; j < charactersCount; j++) {
                codeSymbols[j] = (char) ('a' + j - 10);
            }
            stringBuilder.append(", a");
            if (charactersCount > 11) {
                stringBuilder.append("-")
                        .append((char) ('a' + charactersCount - 11));
            }
        }
        stringBuilder.append(").");
        System.out.println(stringBuilder.toString());
        return codeSymbols;
    }
    static char[] generateSecretCode(int codeLength, int charactersCount) {
        char[] symbols = symbols(charactersCount);
        Random r = new Random();
        for (int i = symbols.length - 1; i > 0; i--)
        {
            int index = r.nextInt(i + 1);
            char a = symbols[index];
            symbols[index] = symbols[i];
            symbols[i] = a;
        }
        char[] secretCode = new char[codeLength];
        System.arraycopy(symbols, 0, secretCode, 0, codeLength);
        return secretCode;
    }
    public static void main(String[] args) {
        int turn = 0;
        String guess;
        boolean gameOver = false;
        Grader grader;

        Scanner scanner = new Scanner(System.in);

        int codeLength = 0;

        String codeLengthStr = null;
        try {
            codeLengthStr = scanner.nextLine();
            codeLength = parseInt(codeLengthStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + codeLengthStr + "\" isn't a valid number.");
            return;
        }

        if (codeLength > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (codeLength <= 0) {
            System.out.println("Error: can't generate a secret number with negative or zero length.");
            return;
        }

        int charactersCount = 0;

        String charactersCountStr = null;
        try {
            charactersCountStr = scanner.nextLine();
            charactersCount = parseInt(charactersCountStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + charactersCountStr + "\" isn't a valid number.");
            return;
        }

        if (charactersCount > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (charactersCount <= 0) {
            System.out.println("Error: can't generate a guess with negative or zero length.");
            return;
        }

        if (charactersCount < codeLength) {
            System.out.println("Error: it's not possible to generate a code with a length of " + codeLength + " with " + charactersCount + " unique symbols.");
            return;
        }

        char[] secretCode = generateSecretCode(codeLength, charactersCount);
        System.out.println("Okay, let's start a game!");

        while (!gameOver) {
            turn++;
            System.out.println("Turn " + turn + ":" /*+ "  secret code" + Arrays.toString(secretCode)*/);
            guess = scanner.next();
            System.out.println(guess);
            grader = new Grader(secretCode, guess);
            grader.grade();
            grader.getGrade();
            if (grader.getBulls() == codeLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                gameOver = true;
            }

        }
    }
}
