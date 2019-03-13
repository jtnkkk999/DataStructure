package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.strategy;

/**
 * 策略或算法的通用接口：1.获取下一个招式。2.统计出招，为下一次出招做准备。
 * 接口就是public static
 */
public interface Strategy {
    public abstract Hand nextHand();
    void study(boolean win);
}
