package gesture.imisoftware.com.design_mode_lib.templateMethod;

public class StringDisplay extends AbstractDisplay{
    String string;
    public StringDisplay(String string){
        this.string = string;
    }

    @Override
    public void open() {
        System.out.println("<<");
    }

    @Override
    public void print() {
        System.out.println(string);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
