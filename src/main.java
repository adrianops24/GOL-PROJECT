import java.util.Scanner;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class main {



    public void main() {
        //chamada de metodo
        Scanner enter = new Scanner(System.in);
        int sizeW = enter.nextInt(); //columns
        int sizeH = enter.nextInt(); //lines
        int sizeG = enter.nextInt(); //generates

        gameOfLife jogo = new gameOfLife(sizeW, sizeH);

        jogo.Grid();
    }




}
