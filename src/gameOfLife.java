import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class gameOfLife {

    int[][] grid;

    public gameOfLife(int sizeW, int sizeH) {
        grid = new int[sizeW][sizeH];
    }

    public void Grid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}


