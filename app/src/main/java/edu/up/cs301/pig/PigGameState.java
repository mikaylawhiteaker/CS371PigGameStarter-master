package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by whiteake19 on 10/12/2016.
 */
public class PigGameState extends GameState {
    private int turnID;
    private int player0Score;
    private int player1Score;
    private int currentRunTotal;
    private int currentDieVal;

    public PigGameState(){
        super();
        turnID =0;
        player0Score=0;
        player1Score=0;
        currentDieVal=0;
        currentRunTotal=0;
    }

    public PigGameState(PigGameState p){
        super();
        turnID = p.getTurnID();
        player1Score = p.getPlayer1Score();
        player0Score = p.getPlayer0Score();
        currentDieVal = p.getCurrentDieVal();
        currentRunTotal = p.getCurrentRunTotal();
    }

    public int getTurnID(){
        return turnID;
    }
    public int getPlayer0Score(){
        return player0Score;
    }
    public int getPlayer1Score(){
        return player1Score;
    }
    public int getCurrentRunTotal(){
        return currentRunTotal;
    }
    public int getCurrentDieVal(){
        return currentDieVal;
    }


    public void setTurnID(int ID){
        turnID = ID;
    }
    public void setPlayer0Score(int score){
        player0Score = score;
    }
    public void setPlayer1Score(int score){
        player1Score = score;
    }
    public void setCurrentRunTotal(int val){
        currentRunTotal = val;
    }
    public void setCurrentDieVal(int val){
        currentDieVal = val;
    }
}
