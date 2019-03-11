package gesture.imisoftware.com.design_mode_lib.prototype;

public class LineProduct implements Product {
    char ch;
    public LineProduct(char ch){
        this.ch = ch;
    }
    @Override
    public void use(String s) {
        System.out.println(s);
        for(int i = 0;i<s.length()+2;i++){
            System.out.print(ch);
        }
    }

    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
