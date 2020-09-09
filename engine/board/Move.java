package com.mushroomboozehound.Chess_Game.engine.board;

import com.mushroomboozehound.Chess_Game.engine.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate){
        this.board =board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }
    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    public abstract Board execute();

    public static final class MajorMove extends Move{

        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            //sets pieces on board
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                //TODO hashcode and equals for pieces
                if(!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            //sets opponents pieces on board
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            //move the moved piece..
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }
    public static final class AttackMove extends Move{
        final Piece attackedPiece;
        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece=attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }
}
