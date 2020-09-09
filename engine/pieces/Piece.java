package com.mushroomboozehound.Chess_Game.engine.pieces;
import com.mushroomboozehound.Chess_Game.engine.Alliance;
import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.engine.board.Move;

import java.util.Collection;


public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType,
          final int piecePosition,
          final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO TO DO LATER  ...
        this.isFirstMove = false;
    }
    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }

    public boolean isFirstMove() { return this.isFirstMove; }

    public PieceType getPieceType(){
        return this.pieceType;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);
    public abstract Piece movePiece(Move move);
    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };
        //TODO final?????
        private final String pieceName;
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }
        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();
    }
}
