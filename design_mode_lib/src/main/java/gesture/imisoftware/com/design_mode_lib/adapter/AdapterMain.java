package gesture.imisoftware.com.design_mode_lib.adapter;



/**
 * 适配器模式也叫做包装模式：类适配器模式、对象配器模式
 * 类适配器模式：通过继承实现的。
 * 对象适配器模式：通过传入对象实例实现的。
 */
public class AdapterMain {
    public static void main(String[] args){
        //1.类适配模式
        Print print = new PrintBanner1("雪胜");
        print.printStrong();
        print.printWeak();
        //2.对象适配
        Print print1 = new PrintBanner2("星星");
        print1.printWeak();
        print1.printStrong();
    }
}
