package dev.theodo.red.tinybreak;

/**
 * Created by theo on 05/02/2018.
 */

class Game {
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;

    private int current_player;

    public Game(String p1, String p2, String p3, String p4){
        this.p1 = new Player(p1);
        this.p2 = new Player(p2);
        this.p3 = new Player(p3);
        this.p4 = new Player(p4);

        current_player = 1;
    }

    public void nextPlayer(){
        current_player = (current_player + 2 + (current_player - 1)/2) % 4;
    }

    public int getTeam1Score(){
        return p1.getScore() + p2.getScore();
    }

    public int getTeam2Score(){
        return p3.getScore() + p4.getScore();
    }

    public int getPlayer1Score(){
        return p1.getScore();
    }

    public int getPlayer2Score(){
        return p2.getScore();
    }

    public int getPlayer3Score() {
        return p3.getScore();
    }

    public int getPlayer4Score(){
        return p4.getScore();
    }
}

class Player {
    private String name;
    private int score;

    public Player(String name){
        this.name = name;
        score = 0;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }
}