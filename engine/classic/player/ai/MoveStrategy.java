package com.mushroomboozehound.Chess_Game.engine.classic.player.ai;

import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move;

public interface MoveStrategy {

    long getNumBoardsEvaluated();

    Move execute(Board board);

}
