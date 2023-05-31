import java.util.Scanner;

public class MowLawn {
    private int height;
    private int width;
    private int start_x = -1;
    private int start_y = -1;
    private String[][] unit;
    private boolean[][] mowed;
    private static final String BLANK = "BLANK";
    private static final String MOWED = "MOWED";
    private static final String TREE = "TREE";
    private boolean displayInitialPosition = true;

    private int count = 1;

    public void initialize(Scanner input) {
        System.out.print("Enter lawn height(X): ");
        this.height = input.nextInt();
        System.out.println();

        System.out.print("Enter lawn width(Y): ");
        this.width = input.nextInt();
        System.out.println();

        this.unit = new String[this.height][this.width];
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                this.unit[row][col] = BLANK;
            }
        }

        int count = 0;
        int numTrees = ((this.height - 1) * (this.width - 1)) / 12;
        int min = 1;

        while (count <= numTrees) {
            int randomX = (int) (Math.random() * ((this.height - 1) - min + 1) + min);
            int randomY = (int) (Math.random() * ((this.width - 1) - min + 1) + min);

            this.unit[randomX][randomY] = TREE;
            count++;
        }

        printLawn();

        while(start_x <= 0 || start_x >= this.height -1) {
            System.out.print("Enter the starting X (row) position of lawn mower( 0 based index): ");
            start_x = input.nextInt();
        }

        while(start_y <= 0 || start_y >= this.width -1) {
            System.out.print("Enter the starting Y (column) position of lawn mower( 0 based index): ");
            start_y = input.nextInt();
        }
    }

    public void printLawn() {
        System.out.println();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (row == start_x && col == start_y) {
                    if (displayInitialPosition == false) {
                        System.out.print("X");
                        System.out.print(" ");
                        displayInitialPosition = true;
                    }
                }
                if (this.unit[row][col] == BLANK)
                    System.out.print(".");
                else if (this.unit[row][col] == MOWED)
                    System.out.print("C");
                else if (this.unit[row][col] == TREE)
                    System.out.print("T");

                if (col < this.width) {
                    System.out.print(" ");
                }
            }

            if (row < this.height - 1) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }

    private boolean canMowSquare(int x, int y) {

        if (x == 0 || y == 0 || x == this.height - 1 || y == this.width - 1) {
            return false;
        }

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (this.unit[x + i][y + j] == TREE) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTreeInStartingPath(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (this.unit[x + i][y + j] == TREE) {
                    System.out.println("Cannot mow Lawn. Tree located in Lawn mower starting path.");
                    return true;
                }
            }
        }
        return false;
    }

    private void mowSquare(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                this.unit[x + i][y + j] = MOWED;
            }
        }
    }

    private void moveLawnMower(int x, int y) {
        if (!canMowSquare(x, y) || this.mowed[x][y]) {
            return;
        }
        this.mowed[x][y] = true;
        mowSquare(x, y);

        moveLawnMower(x + 1, y);
        moveLawnMower(x - 1, y);
        moveLawnMower(x, y + 1);
        moveLawnMower(x, y - 1);
    }


    public boolean mowLawn() {
        this.mowed = new boolean[this.height][this.width];
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                this.mowed[row][col] = false;
            }
        }
        System.out.println(" X  " + start_x + "  Y " + start_y);

        boolean treeInStartingPath = isTreeInStartingPath(start_x, start_y);
        if(treeInStartingPath == false)
        {
            moveLawnMower(start_x, start_y);
        }
        return true;
    }
}