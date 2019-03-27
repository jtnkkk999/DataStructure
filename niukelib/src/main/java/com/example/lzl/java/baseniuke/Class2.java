package com.example.lzl.java.baseniuke;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 1.荷兰国旗问题，引入的快速排序,经典的快速排序跟数据样本有关系，于是出现了随机快排即打乱数据样本的造成的算法影响。
 *  **打乱数据样本对算法的影响方法：1）随机取一个值2）Hash
 *  快排的优点：实现简单，代码简洁，所以常数项小。随机快排的额外空间复杂的度时O(log^n),记录点的空间复杂度
 *  2.堆排序 heapfy heapInsert  求不断插入数据中的中位数，大小根堆。
 *      堆结构插入，删除的操作只需要log（n）的时间复杂度。贪心等问题常使用。
 *  3.堆练习题：中位数：基数是的时候是中间值，偶数的时候是中间两个数的和除以2
 *      1）comparable 和comparator的区别：
 *          comparable：内比较器，compareTo，自己比较。可使用Collection.Sort的方式排序
 *          comparator：外比较器，compare,没实现comparable的类进行一些排序，譬如说优先队列的使用。java默认小根堆，通过该类转换成大根堆。
 *
 */
public class Class2 {
    public static void main(String[] args){
        //快速排序
 //       int[] array = {3,5,1,2,9,7,8,4,5,6,7};
//        quickSort(array,0,array.length-1);
//        for(int i = 0;i<array.length;i++){
//            System.out.println(array[i]);
//        }

        //堆排序
//        int[] array = {3,5,1,2,9,7,8,4};
//        heapSort(array);
//        for(int i = 0;i<array.length;i++){
//            System.out.println(array[i]);
//        }
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        getZhongWeiNumber(array);

    }

    /**
     * 时时获取中位数，通过大小根堆。
     * @param array
     */
    public static void getZhongWeiNumber(int[] array){
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(comparator);//大根堆
        Queue<Integer> minHeap = new PriorityQueue<Integer>();//小根堆
        int maxSie = 0;
        int minSize = 0;
        for(int i =0;i<array.length;i++){
            if(maxSie == 0){
                maxHeap.add(array[i]);
                maxSie++;
                continue;
            }

            if(array[i]<maxHeap.peek()){
                maxHeap.add(array[i]);
                maxSie++;
                if(maxSie-minSize>1){
                    int max = maxHeap.poll();
                    minHeap.add(max);
                }else if(maxSie-minSize<-1){
                    int min = minHeap.poll();
                    maxHeap.add(min);
                }
            }else{
                minHeap.add(array[i]);
                minSize++;
                if(maxSie-minSize>1){
                    int max = maxHeap.poll();
                    minHeap.add(max);
                }else if(maxSie-minSize<-1){
                    int min = minHeap.poll();
                    maxHeap.add(min);
                }
            }
            if((maxSie+minSize)%2==0){
                //偶数
                System.out.println(maxHeap.peek()+":"+minHeap.peek()+":"+(maxHeap.peek()+minHeap.peek())/2.0);
            }else{
                if(maxSie>minSize){
                    System.out.println(maxHeap.peek());
                }else{
                    System.out.println(minHeap.peek());
                }
            }

        }
    }
    static Comparator<Integer>  comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer integer, Integer t1) {
            return t1-integer;
        }
    };

    /**
     * 0，1，2，3，4  parent = (now-1)/2  leftchild = 2*i+1  right = 2*i+2
     * @param array
     */
    private static void heapSort(int[] array) {
        //1.生成一个大根堆
        for(int i = 0;i<array.length;i++){
            heapInsert(array,i);
        }
        //2.
        int count = array.length;
        swap(array,0,--count);
        while(count>0){
            heapFy(array,0,count);
            swap(array,0,--count);
        }

    }

    private static void heapFy(int[] array, int i, int count) {
        while(i<count){
            int left = i*2+1;
            int max = i;
            if(left <count){
                max = array[left]>array[max]?left:max;
                if(left+1<count){
                    max = array[left+1]>array[max]?left+1:max;
                }
            }
            if(max == i){
                break;
            }
            swap(array,max,i);
            i = max;
        }
    }

    /**
     * 将一个数组变成大根堆的过程
     * @param array
     * @param i
     */
    private static void heapInsert(int[] array, int i) {
        while(array[i]>array[(i-1)/2]){
            swap(array,i,(i-1)/2);
            i = (i-1)/2;
        }
    }

    //快速排序
    private static void quickSort(int[] array, int l, int r) {
        if(l >= r){
            return;
        }
        Random ran = new Random();
        swap(array,l+ran.nextInt(r-l+1),r);
        int[] p = partition(array,l,r);
        quickSort(array,l,p[0]-1);
        quickSort(array,p[1]+1,r);
    }

    /**
     * 我的partition操作是()闭区间操作，注意循环后的交换操作
     * @param array
     * @param l
     * @param r
     * @return
     */
    private static int[] partition(int[] array, int l, int r) {
        int ll = l-1;
        int rr = r;
        int i = l;
        while(i<rr){
            if(array[r]<array[i]){
                swap(array,i,--rr);
            }else if(array[r] == array[i]){
                i++;
            }else{
                swap(array,++ll,i++);
            }
        }
        swap(array,i,r);
        return new int[]{ll+1,i};
    }

    private static void swap(int[] array,int ran,int r){
        int temp = array[ran];
        array[ran] = array[r];
        array[r] = temp;
    }

}
