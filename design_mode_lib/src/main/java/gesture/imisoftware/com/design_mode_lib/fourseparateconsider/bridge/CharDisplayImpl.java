package gesture.imisoftware.com.design_mode_lib.fourseparateconsider.bridge;

/**
 * 一行的开头，中间，结尾的字符
 */
public class CharDisplayImpl extends DisplayImpl{
    char head,body,foot;
    public CharDisplayImpl(char head,char body,char foot) {
        this.head = head;
        this.body = body;
        this.foot = foot;
    }

    @Override
    public void rawOpen() {
        System.out.print(head);
    }

    @Override
    public void rawPrint() {
        System.out.print(body);
    }

    @Override
    public void rawClose() {
        System.out.println(foot);
    }
}
