package com.mushroomboozehound.Chess_Game.engine;

import com.mushroomboozehound.Chess_Game.engine.board.Board;

public class JChess {
    public static void main(String[] args){
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
