package gesture.imisoftware.com.design_mode_lib.adapter;

public class Banner {
    private String mString;
    public Banner(String string){
        mString = string;
    }
    public void showWithParen(){
        System.out.println("("+mString+")");
    }
    public void showWithAster(){
        System.out.println("*"+mString+"*");
    }
}
