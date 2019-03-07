package gesture.imisoftware.com.design_mode_lib.adapter;

public class PrintBanner2 implements Print{

    private final Banner mBanner;

    public PrintBanner2(String string){
        mBanner = new Banner(string);
    }
    @Override
    public void printWeak() {
        mBanner.showWithParen();
    }

    @Override
    public void printStrong() {
        mBanner.showWithAster();
    }
}
