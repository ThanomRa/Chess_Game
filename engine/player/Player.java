package com.mushroomboozehound.Chess_Game.engine.player;

import com.google.common.collect.ImmutableList;
import com.mushroomboozehound.Chess_Game.engine.Alliance;
import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.engine.board.Move;
import com.mushroomboozehound.Chess_Game.engine.pieces.King;
import com.mushroomboozehound.Chess_Game.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final Boolean isInCheck;

    Player(final Board board,
           final Collection<Move> legalMoves,
           final Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }
    public King getPlayerKing(){
        return this.playerKing;
    }
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }
    private static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if(piecePosition ==move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    private King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Not a valid board! ¯\\_(ツ)_/¯");
    }
    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }
    public boolean isInCheck() {
        return this.isInCheck;
    }
    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }
    protected boolean hasEscapeMoves(){
        for(final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }
    //TODO Implement these methods ...
    public boolean isCastled(){
        return false;
    }
    public MoveTransition makeMove(final Move move){
        if (!isMoveLegal(move)){
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks =
                Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionBoard.currentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()){
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }


    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
}
