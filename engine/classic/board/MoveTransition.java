package com.mushroomboozehound.Chess_Game.engine.classic.board;

import static com.mushroomboozehound.Chess_Game.engine.classic.board.Move.*;

public final class MoveTransition {

    private final Board fromBoard;
    private final Board toBoard;
    private final Move transitionMove;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board fromBoard,
                          final Board toBoard,
                          final Move transitionMove,
                          final MoveStatus moveStatus) {
        this.fromBoard = fromBoard;
        this.toBoard = toBoard;
        this.transitionMove = transitionMove;
        this.moveStatus = moveStatus;
    }
    public Board getFromBoard(){ return this.fromBoard; }
    public Board getToBoard(){ return this.toBoard; }
    public Move getTransitionMove(){ return this.transitionMove; }
    public MoveStatus getMoveStatus() { return this.moveStatus; }
}
