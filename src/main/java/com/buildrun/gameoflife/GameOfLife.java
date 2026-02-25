package com.buildrun.gameoflife;

public class GameOfLife {

    private int[][] grid;
    private int geracao = 1;

    public GameOfLife(int sizeW, int sizeH) {
        grid = new int[sizeH][sizeW];
    }

    private int contadorVizin(int row, int col, int target) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i == row && j == col) continue;

                if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
                    if (grid[i][j] == target) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
//metodo para aceitar por coluna os inputs
    public boolean naColuna(String input) {
        String[] rows = input.split("#");

        if (rows.length != grid.length) {
            System.out.println("Erro: número de linhas inválido.");
            return false;
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i].length() != grid[i].length) {
                System.out.println("Erro: número de colunas inválido na linha " + i);
                return false;
            }

            for (int j = 0; j < rows[i].length(); j++) {
                char c = rows[i].charAt(j);
                if (c < '0' || c > '3') {
                    System.out.println("Erro: valor inválido '" + c + "' em (" + i + "," + j + "). Use 0..3.");
                    return false;
                }

                grid[i][j] = c - '0';
            }
        }

        return true;
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                String symbol = switch (grid[i][j]) {
                    case 0 -> ".";
                    case 1 -> "🌳";
                    case 2 -> "🐾";
                    case 3 -> "💧";
                    default -> "?";
                };

                System.out.printf("%-3s", symbol);
            }
            System.out.println();
        }
    }

    public void testGrid() {
        int h = grid.length;
        int w = grid[0].length;
        int[][] next = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int current = grid[i][j];
                next[i][j] = (current == 0) ? 1 : 0;
            }
        }

        for (int i = 0; i < h; i++) {
            System.arraycopy(next[i], 0, grid[i], 0, w);
        }
    }

    public void nextStep() {
        int[][] next = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                int current = grid[i][j];

                if (current == 1) {
                    int water = contadorVizin(i, j, 3);
                    next[i][j] = (water == 0) ? 0 : 1;

                } else if (current == 2) {
                    int water = contadorVizin(i, j, 3);
                    int trees = contadorVizin(i, j, 1);
                    next[i][j] = (water == 0 || trees == 0) ? 0 : 2;

                } else if (current == 0) {
                    int trees = contadorVizin(i, j, 1);
                    next[i][j] = (trees >= 2) ? 1 : 0;

                } else if (current == 3) {
                    next[i][j] = 3;

                } else {
                    next[i][j] = current;
                }
            }
        }

        grid = next;
    }

    public void step(int n) {
        nextStep();
        if (geracao % 2 == 0) {
            moveAnimal(n);
        }
        geracao++;
    }

    private void moveAnimal(int n) {
        int h = grid.length;
        int w = grid[0].length;

        int dx = 0;
        int dy = 0;

        if (n == 1) dx = 1;
        else if (n == 2) dy = 1;
        else if (n == 3) dx = -1;
        else if (n == 4) dy = -1;
        else return;

        int[][] moved = new int[h][w];

        for (int i = 0; i < h; i++) {
            System.arraycopy(grid[i], 0, moved[i], 0, w);
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                if (grid[i][j] != 2) continue;

                int ni = i + dy;
                int nj = j + dx;

                if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if (grid[ni][nj] == 3) continue;
                if (moved[ni][nj] == 2) continue;

                moved[i][j] = 0;
                moved[ni][nj] = 2;
            }
        }

        grid = moved;
    }

    public int[][] getGrid() {
        int[][] snap = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, snap[i], 0, grid[i].length);
        }
        return snap;
    }

    public int getGeracao() {
        return geracao;
    }
}