package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 功能层次结构，step步数递增
 */
public class IncreaseDisplay extends CountDisplay{
    private int stemp;
    public IncreaseDisplay(DisplayImpl imp,int stemp) {
        super(imp);
        this.stemp = stemp;
    }
    public void increaseDisplay(int level){
        int count = 0;
        for(int i = 0;i<level;i++){
            multiDisplay(count);
            count += stemp;
        }
    }
}
