package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 类的功能层次结构：负责显示一些东西
 */
public class Display {
    private DisplayImpl imp;
    public Display(DisplayImpl imp){
        this.imp = imp;
    }
    public void open(){
        imp.rawOpen();
    }
    public void print(){
        imp.rawPrint();
    }
    public void close(){
        imp.rawClose();
    }
    public void display(){
        open();
        print();
        close();
    }


}
