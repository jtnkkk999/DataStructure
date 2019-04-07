package com.example.lzl.java.baseniuke;

import javax.sound.sampled.Line;

/**
 * 1.猫，狗，动物 实现stack返回，用到装饰模式，对猫狗类进行再次包装。
 * 2.打印算法。1）转圈打印，思想是（左上角的点和右下角）的点，进行打印缩进
 *             2)顺时针旋转一圈，暂定只能是正方形
 *             3）之字打印，a,b两个点的指针移动
 * 3.排好序的矩阵找数，
 * 4.链表问题，往往是优化在空间复杂度上。时间上往往是O(N)面试需要空间低，笔试先过为主，不考虑空间
 *      1）链表是不是回文链表：使用stack，进行比对，
 *                            快慢指针压栈对比
 *      2)链表荷兰国旗问题：使用链表数组，处理完再穿成链表
 *                         三个引用，小于的，等于的，大于的（头和尾的指针），常数的空间处理，串起来，还能保证稳定性
 *      3）带有random的链表拷贝：hashmap保存key,value（原节点，拷贝节点）
 *                              源节点，拷贝节点，串起来。每次拿两个。
 *      4）一个链表有环还是无环：有环，返回成环的那个节点，无环返回空----快慢指针，第一次相遇后，快指针回到头节点，变为一次走一步，当再次相遇便是成环节点
 *
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
//        System.out.println("之子打印");
//        int[][] b = {{1, 2,  3,  4, 20},
//                    {5,  6,  7,  8, 20},
//                    {9,  10, 11, 12,20},
//                    {13, 14, 15, 16,20}};
//        printZ
        //4.链表回文判断
//        Bean bean = new Bean(1,new Bean(2,new Bean(3,new Bean(5,new Bean(1,null)))));
//        Bean slow = bean;
//        Bean fast = bean;
//        while(fast.next!=null&& fast.next.next!=null){
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        fast = slow.next;
//        fast = reverseList(fast);
//        Bean temp = bean;
//        Boolean istrue = true;
//        Bean fastCopy = fast;
//        while(fast!=null){
//            if(temp.value == fast.value){
//                temp = temp.next;
//                fast = fast.next;
//            }else{
//                istrue = false;
//                break;
//            }
//        }
//        fast = reverseList(fastCopy);
//        slow.next = fast;
//        System.out.println(istrue);
//        Bean b = bean;
//        while(b!=null){
//            System.out.println(b.value);
//            b = b.next;
//        }
        //5.判断两个链表是否相交
        //1.首先判断两个链表是否有环，有环返回成环的点，无环返回null：快慢指针，当快指针为null，返回null，当快指针和慢指针相交，让快指针回到起始点，走一步
        //下一次的相交便是成环的点。（结论，技巧）
        //2.分情况处理：1）当返回都为null，则比较两个链表最后的值是否相等，不相等则没共同节点，相等则从长链表的链表长度差值开始比对，找到相同的节点。
        //              2）一个null。一个有值，则不存在交点。
        //              3）两个都有值:
        //                  先判断两个交点是否相等，相等则是跟1）情况一样，
        //                  再判断交点是否在环上，通过一个节点走一遍知道回到原点是否碰到另一个交点，有则返回相交点。否则美交点。
    }

    private static boolean LinkedListJudgeHuiWenShu(Bean bean) {
        Bean fast  = bean;
        Bean slow = bean;
        while(fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        reverseList(slow);
        return true;
    }

    private static Bean reverseList(Bean slow) {
        Bean now = slow;
        Bean pre = null;
        Bean next;
        while (now!=null){
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
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
    static class Bean{
        private int value;
        private Bean next;
        public Bean(int value,Bean bean){
            this.value = value;
            next = bean;
        }
    }

}
