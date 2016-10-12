package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    private PigGameState MasterGameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        MasterGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {

        return playerIdx == MasterGameState.getTurnID();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {

        if (canMove(this.getPlayerIdx(action.getPlayer()))) {

            if (action instanceof PigHoldAction) {
                int valToAdd = MasterGameState.getCurrentRunTotal();
                if (MasterGameState.getTurnID() == 0) {
                    MasterGameState.setPlayer0Score(MasterGameState.getPlayer0Score() + valToAdd);
                    MasterGameState.setTurnID(1);
                } else {
                    MasterGameState.setPlayer1Score(MasterGameState.getPlayer1Score() + valToAdd);
                    MasterGameState.setTurnID(0);
                }
                MasterGameState.setCurrentRunTotal(0);
                return true;
            } else if (action instanceof PigRollAction) {
                Random rand = new Random();
                MasterGameState.setCurrentDieVal(rand.nextInt(6) + 1);
                if (MasterGameState.getCurrentDieVal() == 1) {
                    MasterGameState.setCurrentRunTotal(0);
                    if (MasterGameState.getTurnID() == 0) {
                        MasterGameState.setTurnID(1);
                    } else {
                        MasterGameState.setTurnID(0);
                    }
                    return true;
                } else {
                    MasterGameState.setCurrentRunTotal(MasterGameState.getCurrentDieVal() + MasterGameState.getCurrentRunTotal());
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copy = new PigGameState(MasterGameState);
        p.sendInfo(copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (MasterGameState.getPlayer0Score() >= 50) {
            return "Player 0 Wins!";
        }
        if (MasterGameState.getPlayer1Score() >= 50) {
            return "Player 1 Wins!";
        }
        return null;
    }

}// class PigLocalGame
