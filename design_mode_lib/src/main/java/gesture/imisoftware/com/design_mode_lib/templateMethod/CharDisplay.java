package gesture.imisoftware.com.design_mode_lib.templateMethod;

public class CharDisplay extends AbstractDisplay{

    private char ch;
    public CharDisplay(char ch){
        this.ch = ch;
    }
    @Override
    public void open() {
        System.out.println("<<");
    }

    @Override
    public void print() {
        System.out.println(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
