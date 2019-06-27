package com.example.lzl.java.baseniuke;

/**
 * 1.时间复杂度：时间复杂度的指标,常数操作的次数O（1），去掉低阶项，去掉高阶项的系数。指标相同再比常数项（最高阶前面的系数）
 * 2.二分查找，是有序的数组。
 * 3.三种排序:冒泡，选择，插入
 * 4.递归的实质，符合分析递归的时间复杂度
 *      分治思想，将大问题化分成小问题。系统帮你压栈。T(N) = aT(n/b) +0(n^d):N是原本的样本量， a是分成几个子过程，b是子过程的样本量。出去调用子过程外的代价。
 *      符合该要求的计算时间复杂度公式Master公式：a,b,d :1)log(b,a)>d -> N^log(b,a)   （子问题规模一样）
 *                                           2)log(b,a)=d -> N^d*log(N)
 *                                           3)log(b,a)<d -> N^d
 * 5.归并排序，以及应用 O(n*logn)  分治思想 归并排序的速度快是因为每个组内是有序的，不会浪费比较。
 * 6.小和问题， 1,2,3,4   1+ 1+2 + 1+2+3  =
 *   逆序列问题  当i<j 时 a[i]>a[j]
 */
public class Class1 {
    public static void main(String[] args){
        //2.二分查找
        int[] a ={1,2};
//        System.out.print(binarySearch(a,3));
        //3.排序
        int[] b = {1,3,4,2,5};
        int[] c = {5,3,7,2,8}; //5+3+5+3+7+2
        //冒泡排序
//        bubblingSort(c);
        //选择排序
//        selectSort(c);
        //插入排序
//        insertSort(b);
        //归并排序
//        mergeSort(c,0,c.length-1);
//        print(c);
        //6小和问题
//        System.out.println(smallSum(b,0,b.length-1));
        //7.逆序对问题
        System.out.println(smallSum(b,0,b.length-1));
    }

    private static int smallSum(int[] a,int l,int r) {
        //1.终止条件。,当只有一个数的时候已经排好了
        if(l>=r){
            return 0;
        }

        int center = l+((r-l)>>1);
        //左面的小和+右面的小和+merge的小和。
        return smallSum(a,l,center)+smallSum(a,center+1,r)+ merge1(a,l,center,r);
    }

    private static int merge1(int[] a, int l, int center, int r) {
        int count = r-l+1;
        int[] copy = new int[count];
        for(int i = 0;i<count;i++){
            copy[i] = a[i+l];
        }
        int ll = l;
        int lf = center+1;
        int w = l;
        int sum = 0;
        while(w<=r){
            if(ll>center){
                a[w++] = copy[lf-l];
                lf++;
            }else if(lf>r){
                a[w++] = copy[ll-l];
                ll++;
            }else if(copy[ll-l]<copy[lf-l]){//只需要处理，左面小，右面大的时候。
                a[w++] = copy[ll-l];
//                sum+= copy[ll-l]*(r-lf+1);小和问题
                ll++;
            }else if(copy[ll-l]>copy[lf-l]){
//                for(int i = ll;i<=center;i++){
//                    System.out.println(copy[ll-l]+"--"+copy[i-l]);
//                }
                System.out.println(copy[ll-l]+"--"+copy[lf-l]);//逆序对问题
                a[w++] = copy[lf-l];
                lf++;
            }
        }
        return sum;
    }

    /**
     * 归并排序是将数组一直拆分成2个数进行比较。
     * @param a
     */
    public static void mergeSort(int[] a,int l,int r){
//        //1.终止条件。,当只有一个数的时候已经排好了
//        if(l>=r){
//            return;
//        }
//        //2.递归逻辑
//        //1)分成两部分
//        int center = l+((r-l)>>1);
//        //2）左部分排序
//        mergeSort(a,l,center);
//        //3）右部分排序
//        mergeSort(a,center+1,r);
//        //4）此时左右部分排好序，将两个排好序的数组重新排序
//        merge(a,l,center,r);
    }

    private static void merge(int[] a, int l, int center, int r) {
        //拷贝数组；
        int count = r-l+1;
        int[] copy = new int[count];
        for(int i = 0;i<count;i++){
            copy[i] = a[i+l];
        }
        int ll = l;
        int lf = center+1;
        int w = l;
        while(w<=r){
            if(ll>center){
                a[w++] = copy[lf-l];
                lf++;
            }else if(lf>r){
                a[w++] = copy[ll-l];
                ll++;
            }else if(copy[ll-l]<copy[lf-l]){
                a[w++] = copy[ll-l];
                ll++;
            }else if(copy[ll-l]>copy[lf-l]){
                a[w++] = copy[lf-l];
                lf++;
            }
        }

    }

    /**
     * 插入排序，维护一个有序的序列
     * 1)为了优化代码，不进行频繁的交换操作，需要用temp变量来保存c[i]的值
     * @param c
     */
    private static void insertSort(int[] c) {
        for(int i = 1;i<c.length;i++){
            int temp = c[i];
            int j;
            for(j =i-1;j>=0;j--){
                if(temp <c[j]){
                    c[j+1] = c[j];
                }else{
                    break;
                }
            }
            c[j+1] = temp;
        }
    }

    /**
     * 选择排序，寻找一轮下来最小的下标
     * @param b
     */
    private static void selectSort(int[] b) {
        for(int i =0;i<b.length;i++){
            int min =i;
            for(int j = i+1;j<b.length;j++){
                if(b[j]<b[min]){
                    min = j;
                }
            }
            swap(b,i,min);
        }
    }

    private static void print(int[] b) {
        for(int i = 0;i<b.length;i++){
            System.out.println(b[i]);
        }
    }



    /**
     * 冒泡排序第一重循环只是代表循环次数
     * @param a
     */
    public static void bubblingSort(int[] a){
        for (int i = 0;i<a.length;i++){
            for(int j = 0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                }
            }
        }
    }

    /**
     * 交换顺序的方法
     * @param a
     * @param l
     * @param r
     */
    public static void swap(int[] a , int l , int r){
        int temp;
        temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }


    /**
     * 二分查找 1 2 3   1 2
     * 1)+的优先级大于>> 所以在>>操作的时候需要加括号。
     * 2）center的值小于find , l = center+1; 否则 r = center-1;
     * 3)while循环中等于来确定偶数数组下，到底是哪面的数大。
     * 4）跳过while循环中的等于号的原因是2）中的+1，-1。
     */
    public static Integer binarySearch(int[] a,int find){
        int l = 0;
        int r = a.length-1;
        while(l<=r){
//            int center = (r+l)>>1;
            int center = l+((r-l)>>1);
            if(a[center]==find){
                return center;
            }else if(a[center]<find){
                l = center+1;
            }else{
                r = center-1;
            }
        }
        return null;
    }
}
