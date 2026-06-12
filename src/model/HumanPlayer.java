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
    public Move makeMove(Board board) {
        int row = readInt("Enter row num");
        int column = readInt("Enter column num");
        return new Move(new Cell(row, column), this);
    }

    private int readInt(String message) {
        while (true) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }
}
