package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
               result = false;
               break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = true;
        for (int row = 0; row < table.length; row++) {
            if (table[row][0] != 1) {
                for (int column = 0; column < table.length; column++) {
                    if (table[row][column] == 1) {
                        for (int rowWin = 0; rowWin < table.length; rowWin++) {
                            if (table[rowWin][column] != 1) {
                                result = false;
                                break;
                            }
                        }
                    }
                }
            } else  {
                int winRowFixed = row;
                for (int column = 1; column < table.length; column++) {
                    if (table[winRowFixed][column] != 1){
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

//    public static boolean monoHorizontal(char[][] board) {
//        boolean result = true;
//        for (int column = 0; column < board.length; column++) {
//            if (board[row][column] != 1) {
//                result = false;
//                break;
//            }
//        }
//        return result;
//    }
//
//    public static boolean monoVertical(char[][] board) {
//        boolean result = true;
//        for (int row = 0; row < board.length; row++) {
//            for (int column = 0; column < board.length; column++) {
//                if (board[row][column] != 1) {
//                    result = false;
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//
//    public static boolean isWin(char[][] board) {
//        boolean result = false;
//        for (int index = 0; index < board.length; index++) {
//            if (monoHorizontal(board, index) || monoVertical(board, index)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }

    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.convert());
    }
}
