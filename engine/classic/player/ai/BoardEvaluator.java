package com.mushroomboozehound.Chess_Game.engine.classic.player.ai;

import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;

public interface BoardEvaluator {

    int evaluate(Board board, int depth);

}
