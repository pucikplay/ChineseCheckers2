package tp.checkers.server.game;

import java.lang.reflect.Array;

public class Board {

    private Pieces[][] fields = null;
    private Pieces[][] bases = null;
    private static int baseSide;
    private static int playerNumber;
    private static int end;

    public Board(int baseSide, int playerNumber) {
        this.baseSide = baseSide;
        this.playerNumber = playerNumber;
        this.end = 4*baseSide + 2;

        createBoard();
        placePlayers();
    }

    public Pieces[][] getPieces(){
        return this.fields;
    }

    private void createBoard() {
        fields = new Pieces[end + 1][end + 1];
        bases = new Pieces[end + 1][end + 1];


        for(int i = baseSide + 1; i < end - baseSide; i++) {
            for(int j = baseSide + 1; j < end - baseSide; j++) {
                fields[i][j] = Pieces.EMPTY;
            }
        }

        for(int i = end - baseSide; i < end; i++) {
            for(int j = baseSide + 1; j <= 2*baseSide; j++) {
                if(i + j < baseSide + 1 + end){
                    fields[i][j] = Pieces.EMPTY;
                    fields[j][i] = Pieces.EMPTY;

                    bases[i][j] = Pieces.GREEN;
                    fields[j][i] = Pieces.BLACK;
                }
            }
        }

        for(int i = end - 2*baseSide; i < end - baseSide; i++) {
            for(int j = 1; j <= baseSide; j++) {
                if(i + j >= end - baseSide){
                    fields[i][j] = Pieces.EMPTY;
                    fields[j][i] = Pieces.EMPTY;

                    bases[i][j] = Pieces.BLUE;
                    bases[j][i] = Pieces.RED;
                }
            }
        }

        for(int i = baseSide + 1; i <= 2*baseSide; i++) {
            for(int j = baseSide + 1; j <= 2*baseSide; j++) {
                if(i + j < 3*baseSide + 2){
                    fields[i][j] = Pieces.EMPTY;
                    bases[i][j] = Pieces.YELLOW;
                }
            }
        }

        for(int i = end - 2*baseSide; i < end - baseSide; i++) {
            for(int j = end - 2*baseSide; j < end - baseSide; j++) {
                if(i + j >= 2*end - 3*baseSide - 1){
                    fields[i][j] = Pieces.EMPTY;
                    bases[i][j] = Pieces.WHITE;
                }
            }
        }
    }

    private void placePlayers() {

    }

}
