package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.strategy;

/**
 * 策略模式，算法
 * 传入策略。
 */
public class StrategyMain {
    public static void main(String[] args){
        Player player = new Player("雪胜",new EasyStrategy(2));
    }
}
