package model;

import model.enums.PlayerType;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private int age;

    public HumanPlayer(int id,
                       String name,
                       Symbol symbol,
                       int age){
        super(id, name, symbol, PlayerType.FREE_HUMAN);
        this.age = age;
    }

    Scanner scanner = new Scanner(System.in);

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Move makeMove() {
        System.out.println("Enter row num");
        int row = scanner.nextInt();
        System.out.println("Enter column num");
        int column = scanner.nextInt();
        return new Move(new Cell(row, column), this);
    }
}
