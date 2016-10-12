package edu.up.cs301.pig;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof PigGameState){
            PigGameState myGameState = (PigGameState) info;
            if(this.playerNum != myGameState.getTurnID()){
                return;
            }
            else{
                Log.i("Computer", "took turn");
                Random rand = new Random();
                int val = rand.nextInt(50);
                if(val > 25){
                    Log.i("Computer", "held");
                    PigHoldAction action = new PigHoldAction(this);
                    game.sendAction(action);
                }
                else{
                    Log.i("Computer", "rolled");
                    PigRollAction action = new PigRollAction(this);
                    game.sendAction(action);
                }
            }
        }
    }//receiveInfo

}
