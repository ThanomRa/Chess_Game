package com.mushroomboozehound.Chess_Game.engine.classic.pieces;

import com.mushroomboozehound.Chess_Game.engine.classic.Alliance;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;
import com.mushroomboozehound.Chess_Game.engine.classic.board.BoardUtils;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.mushroomboozehound.Chess_Game.engine.classic.board.BoardUtils.isValidTileCoordinate;

public final class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9,-7,7,9};

    public Bishop (final Alliance alliance,
                   final int piecePosition) {
        super(PieceType.BISHOP, alliance, piecePosition,true);
    }
    public Bishop (final Alliance alliance,
                   final int piecePosition,
                   final boolean isFirstMove) {
        super(PieceType.BISHOP, alliance, piecePosition,isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset: CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            while(isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstColumnExclusion(candidateCoordinateOffset, candidateDestinationCoordinate) ||
                        isEighthColumnExclusion(candidateCoordinateOffset, candidateDestinationCoordinate)){
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if(isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
                    if (pieceAtDestination == null){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAllegiance();
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.MajorAttackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }
    @Override
    public int locationBonus() {return this.pieceAlliance.bishopBonus(this.piecePosition); }

    @Override
    public Bishop movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedBishop(move.getMovedPiece().getPieceAllegiance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentCandidate,
                                                  final int candidateDestinationCoordinate) {
        return (BoardUtils.INSTANCE.FIRST_COLUMN.get(candidateDestinationCoordinate) &&
                ((currentCandidate == -9) || (currentCandidate == 7)));
    }

    private static boolean isEighthColumnExclusion(final int currentCandidate,
                                                  final int candidateDestinationCoordinate) {
        return (BoardUtils.INSTANCE.EIGHTH_COLUMN.get(candidateDestinationCoordinate) &&
                ((currentCandidate == -7) || (currentCandidate == 9)));
    }
}
