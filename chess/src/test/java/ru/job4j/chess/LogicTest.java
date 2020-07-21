package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Test(expected = FigureNotFoundException.class)
    public void whenNotHaveException() throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Logic logic = new Logic();
        logic.add(bishop);
        logic.move(Cell.A1, Cell.B2);
        assertThat(bishop.position(), is(Cell.B2));
    }

    @Test(expected = IllegalStateException.class)
    public void whenWayIsNotDiagonal() throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Logic logic = new Logic();
        logic.add(bishop);
        logic.move(Cell.A1, Cell.D3);
    }

    @Test(expected = OccupiedCellException.class)
    public void whenOnTheWayStayOtherUnit() throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        BishopBlack bishop2 = new BishopBlack(Cell.B2);
        Logic logic = new Logic();
        logic.add(bishop);
        logic.add(bishop2);
        logic.move(Cell.A1, Cell.B2);
    }
}