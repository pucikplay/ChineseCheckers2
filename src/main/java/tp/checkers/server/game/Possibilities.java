package tp.checkers.server.game;

import java.util.ArrayList;
import java.util.List;

public class Possibilities {

    public static Coordinates[] getMoves(Board board, int i, int j) {
        List<Coordinates> possibilitiesList = new ArrayList<>();
        Coordinates[] possibilities = null;
        possibilitiesList.addAll(simpleMove(board, i, j));
        possibilitiesList.addAll(jumpMove(board, i, j));

        //TO REMOVE; TESTING
        for (Coordinates coordinates : possibilitiesList) {
            System.out.println(coordinates.i + " " + coordinates.j);
        }
        //

        return possibilitiesList.toArray(new Coordinates[0]);
    }

    private static List<Coordinates> simpleMove(Board board, int i, int j) {
        List<Coordinates> list = new ArrayList<>();
        for (Field field : board.getFields()[i][j].getNeighbors()) {
            if(field != null && field.getPiece() == null) {
                list.add(new Coordinates(i, j));
            }
        }

        // TO REMOVE; TESTING
        for (Coordinates coordinates : list) {
            System.out.println("simple move:" + coordinates.i + " " + coordinates.j);
        }
        //

        return list;
    }

    private static List<Coordinates> jumpMove(Board board, int i, int j) {
        List<Coordinates> list = new ArrayList<>();
        for (int a = 0; a < 6; a++) {
            if (board.getFields()[i][j].getNeighbors()[a] != null
                    && board.getFields()[i][j].getNeighbors()[a].getPiece() != null
                    && board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a] != null
                    && board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getPiece() == null) {

                list.addAll(jumpMove(board, board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getCoordinates().i, board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getCoordinates().j));
            }
        }
        return list;
    }

}
