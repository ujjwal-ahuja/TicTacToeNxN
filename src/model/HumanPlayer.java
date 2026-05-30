package model;

import model.enums.PlayerType;

public class HumanPlayer extends Player{
    private int age;

    public HumanPlayer(int age,
                       int id,
                       String name,
                       Symbol symbol,
                       PlayerType playerType){
        super(id, name, symbol, playerType);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
