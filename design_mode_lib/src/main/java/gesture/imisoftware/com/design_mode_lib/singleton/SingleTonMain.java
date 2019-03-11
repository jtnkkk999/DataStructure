package gesture.imisoftware.com.design_mode_lib.singleton;

/**
 * 确保任何情况下都只有一个实例使用单例模式
 */
public class SingleTonMain {
    public static void main(String[] args){
//        single1 instance = single1.getInstance();
        single2 instance = single2.getInstance();
    }

    /**
     * 懒汉模式
     */
    static class single1{
        private static single1 instance;
        public static synchronized single1 getInstance(){
            if(instance ==null){
                instance = new single1();
            }
            return instance;
        }
    }

    /**
     * 恶汉模式
     */
    static class single2{
        private static single2 instance = new single2();
        public static single2 getInstance(){
            return instance;
        }
    }

    /**
     * 静态内部类
     */
    static class single3{
        public static single3 getInstance(){
            return holder.instance;
        }
        static class holder{
            private static final single3 instance = new single3();
        }
    }

}
