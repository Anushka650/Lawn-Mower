import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mowingDetails =
                "\nAssumption:\nThe lawn mower is a 3x3 square. It will not be able to mow the lawn under the following conditions. \n" +
                        "1. The lawn mower is placed at the edge of the lawn. \n" +
                        "2. A tree is present in the initial square that the lawn mower is placed.";

        System.out.println(mowingDetails);

        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter number of lawns you would like to mow: ");
        int numLawns = input.nextInt();
        System.out.println();

        for (int i = 0; i < numLawns; i++) {
            MowLawn lawn = new MowLawn();
            System.out.println("");
            System.out.print("Enter lawn details for Lawn number:  " + (i +1));
            System.out.println();
            lawn.initialize(input);
            boolean isLawnMowed = lawn.mowLawn();
            if(isLawnMowed)
                lawn.printLawn();
        }
    }
}