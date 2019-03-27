package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 分开考虑的设计模式：1.bridge模式，是将类的功能和类的实现相分离的模式。
 *                    2.strategy策略模式，策略方式抽出来，
 *
 *
 * 继承是强关联，委托是弱关联
 *
 * 将类的功能层次结构与实现层次结构分离
 *
 * 类的功能类实现，
 * 类的实现类 ，接口
 */
public class BridgeMain {
    public static void main(String[] args){
        //类的实现
        CharDisplayImpl charDisplay = new CharDisplayImpl('-','a','|');
        //类的功能
        IncreaseDisplay increaseDisplay = new IncreaseDisplay(charDisplay,2);
        increaseDisplay.increaseDisplay(5);
    }
}