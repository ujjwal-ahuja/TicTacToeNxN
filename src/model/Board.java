package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private List<List<Cell>> boardStr;

    public Board(int size){
        this.size = size;
        this.boardStr = new ArrayList<>();
        for(int i=0; i<size; i++){
            this.boardStr.add(new ArrayList<>());
            for(int j=0; j<size; j++){
                this.boardStr.get(i).add(new Cell(i,j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoardStr() {
        return boardStr;
    }

    public void setBoardStr(List<List<Cell>> boardStr) {
        this.boardStr = boardStr;
    }

//    public void display(){
//    }
}
