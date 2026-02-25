package com.buildrun.gameoflife;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner enter = new Scanner(System.in);

        System.out.print("w: ");
        int sizeW = enter.nextInt();

        System.out.print("h: ");
        int sizeH = enter.nextInt();

        System.out.print("g: ");
        int sizeG = enter.nextInt();

        System.out.print("n (1=dir,2=baixo,3=esq,4=cima): ");
        int n = enter.nextInt();

        enter.nextLine();

        GameOfLife jogo = new GameOfLife(sizeW, sizeH);

        String mapa;
        boolean ok;

        do {
            System.out.println("Digite o mapa (use # para separar linhas):");
            mapa = enter.nextLine();
            ok = jogo.naColuna(mapa);
        } while (!ok);

        IO.println("Geração 1 (inicial):");
        jogo.printGrid();

        for (int gen = 1; gen <= sizeG; gen++) {
            jogo.step(n); // <- usa o passo completo (regras + movimento em geração par)
            IO.println("Depois do step (geração " + (gen + 1) + "):");
            jogo.printGrid();

        }
        IO.println("obs: projeto não está pronto, falta a inicialização dos metódos post e get. Funcionamento do servidor, Documentação Swagger e integração com a interace");
    }
}