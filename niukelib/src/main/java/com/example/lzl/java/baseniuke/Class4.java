package com.example.lzl.java.baseniuke;

/**
 * 1.猫，狗，动物 实现stack返回，用到装饰模式，对猫狗类进行再次包装。
 * 2.打印算法。1）转圈打印，思想是（左上角的点和右下角）的点，进行打印缩进
 *             2)顺时针旋转一圈，暂定只能是正方形
 *             3）之字打印
 */
public class Class4 {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}};
        //1)旋转打印
//        System.out.println("旋转打印");
//        printRotate(a);
        //2)旋转矩阵
//        System.out.println("矩阵顺时针旋转一圈");
//        Rotate(a);
//        for(int i = 0;i<4;i++){
//            for(int j = 0;j<4;j++){
//                System.out.print(a[i][j]+" ");
//            }
//            System.out.println();
//        }
        //3）之子打印
        System.out.println("之子打印");
        int[][] b = {{1, 2,  3,  4, 20},
                    {5,  6,  7,  8, 20},
                    {9,  10, 11, 12,20},
                    {13, 14, 15, 16,20}};
        printZhi(b);
    }

    private static void printZhi(int[][] array) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        boolean istrue = true;
        while(a!=array.length){//只要判定一个边界就可以
//            System.out.println(a+":"+b+"   "+c+":"+d);
            printOneZhi(array,a,b,c,d,istrue);
            //此处逻辑先增长b，b到了增长点，再增长a
            a = b==array[0].length-1?++a:a;
            b = b==array[0].length-1?b:++b;
            //此处逻辑先增长c,c到了增长点，再增长d
            d = c==array.length-1?++d:d;
            c = c==array.length-1?c:++c;

            istrue = !istrue;

        }

    }

    private static void printOneZhi(int[][] array, int a, int b, int c, int d, boolean istrue) {
        if(istrue){
            while(c>=a&&d<=b){
                System.out.print(array[c--][d++]+" ");
            }
        }else{
            while(c>=a&&d<=b){
                System.out.print(array[a++][b--]+" ");
            }
        }
    }

    private static void Rotate(int[][] a) {
        int tl = 0;
        int tr = 0;
        int bl = a.length-1;
        int br = a.length-1;
        while(tl<=bl&&tr<=br){
            rotateOne(a,tl++,tr++,bl--,br--);
        }

    }

    /**
     * 要带上之前的坐标。即便是0
     * @param a
     * @param tl
     * @param tr
     * @param bl
     * @param br
     */
    private static void rotateOne(int[][] a, int tl, int tr, int bl, int br) {
        int c = bl-tl;//需要旋转的第一个点
        for(int i = 0;i<c;i++){
            int temp = a[tl][tr+i];//每轮第一行的值
            a[tl][tr+i] = a[bl-i][tr];
            a[bl-i][tr] =  a[bl][br-i];
            a[bl][br-i] = a[tl+i][br];
            a[tl+i][br] = temp;
        }
    }

    private static void printRotate(int[][] a) {
        int tl = 0;
        int tr = 0;
        int bl = a.length-1;
        int br = a.length-1;
        while(tl<=bl&&tr<=br){
            printOne(a,tl++,tr++,bl--,br--);
        }
    }

    private static void printOne(int[][] array,int a, int b, int c, int d) {
        int i = a;
        int j = b;
        while(j!=d){
            System.out.print(array[i][j++]+" ");
        }
        System.out.println();
        while(i!=c){
            System.out.print(array[i++][j]+" ");
        }
        i = c;
        j = d;
        System.out.println();
        while(b!=j){
            System.out.print(array[i][j--]+" ");
        }
        System.out.println();
        while(a!=i){
            System.out.print(array[i--][j]+" ");
        }
        System.out.println();
    }

}
