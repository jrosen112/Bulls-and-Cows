import java.io.*;
import java.util.*;

public class BullsAndCows {

    public static void main(String[] args) {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> array = new ArrayList<Integer>();

        System.out.print("Pick a number from 3-10: ");
        num = scanner.nextInt();
        for(int i = 0; i < num; i++) {
            int randNum = randomInt(0, 9);
            array.add(randNum);
        }
        mastermind(array);
    }

    public static void mastermind(ArrayList<Integer> array) {
        boolean completed = false;
        int realNum = toDigits(array); System.out.println(realNum);
        ArrayList<Integer> digits = new ArrayList<Integer>();
        int bulls = 0; // correct number, correct spot
        int cows = 0; // correct number, wrong spot
        int turns = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Pick a maximum amount amount of turns (greater than 10): ");
        int maxTurns = scanner.nextInt();

        while(!completed || turns <= maxTurns) {
            System.out.print("Guess a " + array.size() + "-digit number: ");
            int guess = scanner.nextInt();
            turns++;
            digits = getDigits(guess);
            if(digits.equals(array)) {
                completed = true;
                break;
            }
            if(turns == maxTurns) {
                break;
            }
            for(int i = 0; i < digits.size(); i++) {
                if(digits.get(i) == array.get(i)) {
                    bulls++;
                } else if(array.contains(digits.get(i)) && digits.get(i) != array.get(i)) {
                    cows++;
                }
            }
            System.out.println("Bulls: " + bulls + "; Cows: " + cows);
            bulls = 0; cows = 0;
        }

        if(completed) {
            System.out.println("Congratulations! You guessed the number in " + turns + " turns.");
        } else {
            System.out.println("Sorry, you have hit the maximum amount of turns (" + maxTurns + "). The number was " + realNum + ".");
        }
    }

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max-min) + 1) + min;
        return randomNum;
    }

    public static ArrayList<Integer> getDigits(int num) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        String number = String.valueOf(num);
        for(int i = 0; i < number.length(); i++) {
            int j = Character.digit(number.charAt(i), 10);
            array.add(j);
        }
        return array;
    }

    public static int toDigits(ArrayList<Integer> array) {
        String s = "";
        for(int i = 0; i < array.size(); i++) {
            s += Integer.toString(array.get(i));
        }
        return Integer.parseInt(s);
    }

}
