package com.mushroomboozehound.Chess_Game.engine.player;

import com.mushroomboozehound.Chess_Game.engine.Alliance;
import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.engine.board.Move;
import com.mushroomboozehound.Chess_Game.engine.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
