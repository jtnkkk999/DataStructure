package gesture.imisoftware.com.design_mode_lib.adapter;

/**
 * 适配器类：类适配器
 */
public class PrintBanner1 extends Banner implements Print{
    public PrintBanner1(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
