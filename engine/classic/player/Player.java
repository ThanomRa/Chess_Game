package com.mushroomboozehound.Chess_Game.engine.classic.player;

import com.mushroomboozehound.Chess_Game.engine.classic.Alliance;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move;
import com.mushroomboozehound.Chess_Game.engine.classic.board.MoveTransition;
import com.mushroomboozehound.Chess_Game.engine.classic.pieces.King;
import com.mushroomboozehound.Chess_Game.engine.classic.pieces.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.mushroomboozehound.Chess_Game.engine.classic.pieces.Piece.PieceType.KING;
import static java.util.stream.Collectors.collectingAndThen;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final Boolean isInCheck;

    Player(final Board board,
           final Collection<Move> playerLegals,
           final Collection<Move> opponentLegals){
        this.board = board;
        this.playerKing = establishKing();
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentLegals).isEmpty();
        playerLegals.addAll(calculateKingCastles(playerLegals, opponentLegals));
        this.legalMoves = Collections.unmodifiableCollection(playerLegals);
    }
    public boolean isInCheck() { return this.isInCheck; }
    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }
    public boolean isCastled() { return this.playerKing.isCastled(); }
    public boolean isKingSideCastleCapable() { return this.playerKing.isKingSideCastleCapable();}
    public boolean isQueenSideCastleCapable() { return this.playerKing.isQueenSideCastleCapable();}
    public King getPlayerKing(){
        return this.playerKing;
    }

    private King establishKing(){
        return (King) getActivePieces().stream()
                                        .filter(piece -> piece.getPieceType() == KING)
                                        .findAny()
                                        .orElseThrow(RuntimeException::new);
    }
    private boolean hasEscapeMoves() {
        return  this.legalMoves.stream()
                                .anyMatch(move -> makeMove(move)
                                .getMoveStatus().isDone());
    }
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }
    static Collection<Move> calculateAttacksOnTile(final int tile,
                                                   final Collection<Move> moves) {
        return moves.stream()
                    .filter(move -> move.getDestinationCoordinate() == tile)
                    .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
    public MoveTransition makeMove(final Move move){
        if (!this.legalMoves.contains(move)){
            return new MoveTransition(this.board, this.board, move, Move.MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();
        return transitionBoard.currentPlayer().getOpponent().isInCheck() ?
                new MoveTransition(this.board, this.board, move, Move.MoveStatus.LEAVES_PLAYER_IN_CHECK) :
                new MoveTransition(this.board, transitionBoard, move, Move.MoveStatus.DONE);
    }
    public MoveTransition unMakeMove(final Move move) {
        return new MoveTransition(this.board, move.undo(), move, Move.MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected  abstract Collection<Move> calculateKingCastles (Collection<Move> playerLegals,
                                                               Collection<Move> opponentsLegals);
    protected boolean hasCastleOpportunities(){
        return !this.isInCheck && !this.playerKing.isCastled() &&
                (this.playerKing.isKingSideCastleCapable() || this.playerKing.isQueenSideCastleCapable());
    }
}
