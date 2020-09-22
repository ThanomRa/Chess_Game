package com.mushroomboozehound.Chess_Game.pgn;

import com.mushroomboozehound.Chess_Game.engine.classic.board.Board;
import com.mushroomboozehound.Chess_Game.engine.classic.board.Move;
import com.mushroomboozehound.Chess_Game.engine.classic.player.Player;

public interface PGNPersistence {

    void persistGame(Game game);

    Move getNextBestMove(Board board, Player player, String gameText);

}
