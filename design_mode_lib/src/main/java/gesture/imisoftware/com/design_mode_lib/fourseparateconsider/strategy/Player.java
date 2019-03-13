package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.strategy;

/**
 * 将算法，策略方式使用委托这种弱关联来整体替换算法。
 */
public class Player {
    private String name;
    private Strategy mStrategy;
    private int winCount= 0 ;
    private int loseCount = 0;
    private int gameCount = 0;
    public Player(String name,Strategy strategy){
        this.name = name;
        this.mStrategy = strategy;
    }
    public Hand nextHand(){
        return mStrategy.nextHand();
    }
    public void win(){
        mStrategy.study(true);
        winCount++;
        gameCount++;
    }
    public void lose(){
        mStrategy.study(false);
        loseCount++;
        gameCount++;
    }
    public void even(){
        gameCount++;
    }

}
