package com.mushroomboozehound.Chess_Game.engine.player;

import com.mushroomboozehound.Chess_Game.engine.Alliance;
import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.engine.board.Move;
import com.mushroomboozehound.Chess_Game.engine.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
