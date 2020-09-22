package com.mushroomboozehound.Chess_Game.engine.classic.pieces;

import com.mushroomboozehound.Chess_Game.engine.classic.Alliance;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;
import com.mushroomboozehound.Chess_Game.engine.classic.board.BoardUtils;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9,-8,-7,-1,1,7,8,9};

    public Queen(final Alliance alliance, final int piecePosition) {
        super (PieceType.QUEEN, alliance, piecePosition, true);
    }
    public Queen (final Alliance alliance,
                  final int piecePosition,
                  final boolean isFirstMove) {
        super(PieceType.QUEEN, alliance, piecePosition, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            while(true){
                if (isFirstColumnExclusion(currentCandidateOffset, candidateDestinationCoordinate) ||
                    isEighthColumnExclusion(currentCandidateOffset, candidateDestinationCoordinate)) {
                    break;
                }
                candidateDestinationCoordinate += currentCandidateOffset;
                if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    break;
                    } else {
                        final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
                        if (pieceAtDestination == null) {
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                        } else {
                            final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAllegiance();
                            if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                                legalMoves.add(new Move.MajorAttackMove
                                        (board,this, candidateDestinationCoordinate,pieceAtDestination));
                            }

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
    public int locationBonus() {return this.pieceAlliance.queenBonus(this.piecePosition); }

    @Override
    public Queen movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedQueen(move.getMovedPiece().getPieceAllegiance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidatePosition) {
        return BoardUtils.INSTANCE.FIRST_COLUMN.get(candidatePosition) &&
                (currentPosition == -9 || currentPosition == -1 || currentPosition == 7);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidatePosition) {
        return BoardUtils.INSTANCE.EIGHTH_COLUMN.get(candidatePosition) &&
                (currentPosition == -7 || currentPosition == 1 || currentPosition == 9);
    }
}
