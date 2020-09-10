package com.mushroomboozehound.Chess_Game.engine.player;

import com.mushroomboozehound.Chess_Game.engine.Alliance;
import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.engine.board.Move;
import com.mushroomboozehound.Chess_Game.engine.board.Tile;
import com.mushroomboozehound.Chess_Game.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Whites king side castle
           if   (!this.board.getTile(61).isTileOccupied() &&
                 !this.board.getTile(62).isTileOccupied()) {

               final Tile rookTile = this.board.getTile(63);
               if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                   if (Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                       Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                       rookTile.getPiece().getPieceType().isRook()) {

                       //TODO ADD A CASTLE MOVE!
                       kingCastles.add(null);
                   }
               }
           }
           if (!this.board.getTile(59).isTileOccupied() &&
               !this.board.getTile(58).isTileOccupied() &&
               !this.board.getTile(57).isTileOccupied()) {

               final Tile rookTile =this.board.getTile(56);
               if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                   //TODO add castle move
                   kingCastles.add(null);
               }
           }
        }


        return null;
    }
}
