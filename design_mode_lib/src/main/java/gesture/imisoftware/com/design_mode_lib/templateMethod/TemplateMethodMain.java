package gesture.imisoftware.com.design_mode_lib.templateMethod;


/**
 * 模板方法设计模式：
 */
public class TemplateMethodMain {
    public static void main(String[] args){
        AbstractDisplay ch = new CharDisplay('l');
        AbstractDisplay string = new StringDisplay("雪胜");
        ch.display();
        string.display();
    }

}
