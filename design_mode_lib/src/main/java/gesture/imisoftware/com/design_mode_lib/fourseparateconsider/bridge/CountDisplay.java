package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 类的功能层次结构：循环显示多次
 */
public class CountDisplay extends Display{

    public CountDisplay(DisplayImpl imp) {
        super(imp);
    }
    public void multiDisplay(int times){
        open();
        for(int i = 0;i<times;i++){
            print();
        }
        close();
    }
}
