package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.strategy;

import java.util.Random;

public class EasyStrategy implements Strategy {
    private Random mRandom;
    private boolean won = false;
    private Hand prevHand;
    public EasyStrategy(int seek){
        mRandom = new Random(seek);
    }
    @Override
    public Hand nextHand() {
        if(!won){
            prevHand = Hand.getHand(mRandom.nextInt(3));
        }
        return prevHand;
    }

    @Override
    public void study(boolean win) {
        won = win;
    }
}
