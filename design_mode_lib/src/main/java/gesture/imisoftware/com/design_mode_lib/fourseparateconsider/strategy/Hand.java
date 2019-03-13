package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.strategy;

public class Hand {
    public static final int HAND_ONE = 0;
    public static final int HAND_TWO = 1;
    public static final int HAND_THREE = 2;
    public static Hand[] hand = {new Hand(HAND_ONE),new Hand(HAND_TWO),new Hand(HAND_THREE)};
    private int handvalue;
    private Hand(int handvalue){
        this.handvalue = handvalue;
    }
    public static Hand getHand(int handvalue){
        return hand[handvalue];
    }

    public int fight(Hand hand){
        if(this ==hand){
            return 0 ;
        }else if((this.handvalue+1)%3 == hand.handvalue){
            return 1 ;
        }else{
            return -1;
        }
    }


}
