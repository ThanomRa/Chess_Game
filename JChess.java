package com.mushroomboozehound.Chess_Game;

import com.mushroomboozehound.Chess_Game.engine.board.Board;
import com.mushroomboozehound.Chess_Game.gui.Table;

public class JChess {
    public static void main(String[] args){
        Board board = Board.createStandardBoard();
        System.out.println(board);
        Table table= new Table();
    }
}
