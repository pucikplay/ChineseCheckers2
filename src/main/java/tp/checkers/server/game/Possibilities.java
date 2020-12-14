package tp.checkers.server.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Possibilities {

    public static Coordinates[] getMoves(Board board, int i, int j) {
        ArrayList<Coordinates> possibilitiesList = new ArrayList<>(Arrays.asList(new Coordinates(i,j)));

        possibilitiesList.addAll(simpleMove(board, i, j));
        possibilitiesList.addAll(jumpMove(board, i, j, new ArrayList<Coordinates>()));

        return possibilitiesList.toArray(new Coordinates[0]);
    }

    private static ArrayList<Coordinates> simpleMove(Board board, int i, int j) {
        ArrayList<Coordinates> list = new ArrayList<>();

        for (Field field : board.getFields()[i][j].getNeighbors()) {
            if(field != null && field.getPiece() == null) {
                list.add(new Coordinates(field.getCoordinates().i, field.getCoordinates().j));
            }
        }

        return list;
    }

    private static ArrayList<Coordinates> jumpMove(Board board, int i, int j, ArrayList<Coordinates> checked) {
        ArrayList<Coordinates> list = new ArrayList<>();
        boolean beenThere = false;
        for(Coordinates coordinates : checked) {
            if(coordinates.i == i && coordinates.j == j) {
                beenThere = true;
                break;
            }
        }
        if(!beenThere) {
            list.add(new Coordinates(i, j));
            checked.add(new Coordinates(i, j));

            for (int a = 0; a < 6; a++) {
                if (board.getFields()[i][j].getNeighbors()[a] != null
                        && board.getFields()[i][j].getNeighbors()[a].getPiece() != null
                        && board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a] != null
                        && board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getPiece() == null) {

                    list.addAll(jumpMove(board, board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getCoordinates().i, board.getFields()[i][j].getNeighbors()[a].getNeighbors()[a].getCoordinates().j, checked));
                }
            }
        }
        return list;
    }

}
