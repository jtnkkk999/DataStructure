package gesture.imisoftware.com.design_mode_lib.build;

/**
 * 组装复杂的实例
 * build模式我常见的不同：将复杂的对象创建和表示相分离。
 * 本书的build模式则是，build类创建所有需要使用的抽象方法，而在director类中的construct中使用这
 * 些抽象方法，通过build的不同子类来创建不同的construct操作的对象。
 *
 * 模板方法设计模式~则是通过父类控制顺序，builder则是通过类似装饰模式控制对build类里的抽象方法进
 * 行逻辑顺序控制
 */
public class BuildMain {
    public static void main(String[] args){

    }
}
