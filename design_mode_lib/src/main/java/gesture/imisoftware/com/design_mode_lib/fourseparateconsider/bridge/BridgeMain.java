package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 继承是强关联，委托是弱关联
 *
 * 将类的功能层次结构与实现层次结构分离
 */
public class BridgeMain {
    public static void main(String[] args){
        CharDisplayImpl charDisplay = new CharDisplayImpl('-','a','|');
        IncreaseDisplay increaseDisplay = new IncreaseDisplay(charDisplay,2);
        increaseDisplay.increaseDisplay(5);
    }
}