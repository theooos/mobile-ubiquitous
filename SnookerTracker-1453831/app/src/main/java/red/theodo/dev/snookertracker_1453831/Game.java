package red.theodo.dev.snookertracker_1453831;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

enum Balls {
    Red(1), Yellow(2), Green(3), Brown(4), Blue(5), Pink(6), Black(7);

    private int numVal;

    Balls(int numVal) {
        this.numVal = numVal;
    }

    public int val() {
        return numVal;
    }
}

class Game {
    private Player p1, p2, p3, p4;

    private int currentPlayer = 1;
    private boolean showdown = false;

    private int team1FoulBonus = 0;
    private int team2FoulBonus = 0;

    private int remainingRed = 15;
    private EnumSet<Balls> legalBalls = EnumSet.of(Balls.Red);
    private boolean finished = false;

    Game(String p1, String p2, String p3, String p4) {
        this.p1 = new Player(p1);
        this.p2 = new Player(p2);
        this.p3 = new Player(p3);
        this.p4 = new Player(p4);
    }

    void nextPlayer() {
        switch (currentPlayer) {
            case 1: currentPlayer = 3; break;
            case 2: currentPlayer = 4; break;
            case 3: currentPlayer = 2; break;
            case 4: currentPlayer = 1;
        }

        if (remainingRed < 1 && !showdown) {
            showdown = true;
            legalBalls = EnumSet.of(Balls.Yellow);
        }
        if (!showdown) legalBalls = EnumSet.of(Balls.Red);

        System.out.println("Current player: " + currentPlayer + " | Showdown: " + showdown);
    }

    int getTeam1Score() {
        return p1.getScore() + p2.getScore() + team1FoulBonus;
    }

    int getTeam2Score() {
        return p3.getScore() + p4.getScore() + team2FoulBonus;
    }

    Player getPlayer1() {
        return p1;
    }

    Player getPlayer2() {
        return p2;
    }

    Player getPlayer3() {
        return p3;
    }

    Player getPlayer4() {
        return p4;
    }

    int getCurrentPlayer(){
        return currentPlayer;
    }

    void potBall(Balls ball) {
        if (!legalBalls.contains(ball)) {
//            TODO Better illegal move handling
            System.out.println("Illegal ball played: " + ball.toString());
            return;
        }

        score(ball.val());

        if (!showdown) {
            switch (ball) {
                case Red:
                    remainingRed--;
                    legalBalls = EnumSet.of(Balls.Yellow, Balls.Green, Balls.Brown,
                            Balls.Blue, Balls.Pink, Balls.Black);
                    break;
                default:
                    if (remainingRed == 0) {
                        showdown = true;
                        legalBalls = EnumSet.of(Balls.Yellow);
                    } else {
                        legalBalls = EnumSet.of(Balls.Red);
                    }
            }
        } else {
            switch (ball) {
                case Yellow: legalBalls = EnumSet.of(Balls.Green); break;
                case Green: legalBalls = EnumSet.of(Balls.Brown); break;
                case Brown: legalBalls = EnumSet.of(Balls.Blue); break;
                case Blue: legalBalls = EnumSet.of(Balls.Pink); break;
                case Pink: legalBalls = EnumSet.of(Balls.Black); break;
                case Black:
                    legalBalls = EnumSet.noneOf(Balls.class);
                    finished = true;
            }
        }

        System.out.println("Scored: " + ball.val() + " | Remaining red: " + remainingRed);
    }

    private void score(int value) {
        switch (currentPlayer) {
            case 1: p1.addScore(value); break;
            case 2: p2.addScore(value); break;
            case 3: p3.addScore(value); break;
            case 4: p4.addScore(value);
        }
    }

    void foul() {
        switch (currentPlayer % 2) {
            case 0: team2FoulBonus += 4; break;
            case 1: team1FoulBonus += 4;
        }
        nextPlayer();
    }

    String getWinningTeam() {
        if(getTeam1Score() > getTeam2Score())
            return "Team 1 wins with " + getTeam1Score() + " points!";
        else if (getTeam1Score() < getTeam2Score())
            return "Team 2 wins with " + getTeam2Score() + " points!";
        else
            return "It's a tie with " + getTeam1Score() + " points!";
    }

    String getBestPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1); players.add(p2); players.add(p3); players.add(p4);
        Collections.sort(players);
        return "" + players.get(0).getName() + " was first with " + players.get(0).getScore() + "!";
    }

    boolean isFinished() {
        return finished;
    }
}

class Player implements Comparable {
    private String name;
    private int score;

    Player(String name) {
        this.name = name;
        score = 0;
    }

    String getName() {
        return name;
    }

    int getScore() {
        return score;
    }

    void addScore(int n) {
        score += n;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        int rivalScore = ((Player) o).getScore();
        return rivalScore - score;
    }
}