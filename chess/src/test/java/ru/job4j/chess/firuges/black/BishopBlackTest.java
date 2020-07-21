package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {

    private static final String Z = System.lineSeparator();

    @Test
    public void testWayYes() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell[] way = bishop.way(Cell.A1, Cell.D4);
        Cell[] expected = new Cell[]{Cell.B2, Cell.C3, Cell.D4};
        assertThat(way, is(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void testWayNot() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell[] way = bishop.way(Cell.A1, Cell.D5);
        assertThat(way, is(new Cell[]{}));
    }

    @Test
    public void testIsDiagonal() {
        boolean result = new BishopBlack(Cell.A1).isDiagonal(Cell.A1, Cell.H8);
        assertTrue(result);
    }

    @Test
    public void testIsNotDiagonal() {
        boolean result = new BishopBlack(Cell.A1).isDiagonal(Cell.A1, Cell.A5);
        assertFalse(result);
    }
}