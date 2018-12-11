package com.example.lzl.java.niukelib.sort;

public class AllSort {
    public static void main(String[] args){
        int[] st = {2,5,9,1,4,3,14};
        //1.冒泡排序
//        bubblingSort(st);
        //2.插入排序
//        insertSort(st);
        //3.选择排序
//        selectSort(st);
        //4.归并排序
//        mergeSort(st,0,st.length-1);
        //5.快速排序
//        quickSort(st,0,st.length-1);
        //6.堆排序
        heapSort(st);
        print(st);
    }

    private static void heapSort(int[] st) {
        //1.将数组一个个加入堆
        for(int i = 0;i<st.length;i++){
            heapInsert(st,i);
        }
        int size = st.length;//大根堆里的数据个数
        swap(st,0,--size);//此时最大值已经到了数组的最后。
        while(size>0){
            heapFy(st,0,size);//将数组再次变成大根堆
            swap(st,0,--size);
        }

    }

    private static void heapFy(int[] st, int i, int size) {
        while(i<size){
            int left = 2*i+1;
            int max = i;//记录最大值的下标
            if(left<size){
                max = st[left]>st[max]?left:max;
                if(left+1<size){
                    max = st[max]>st[left+1]?max:left+1;
                }

            }
            if(max == i){
                //说明i节点就是以该节点为根节点的最大值
                break;
            }
            //否则交换位置
            swap(st,i,max);
            i = max;
        }
    }

    /**
     *    0
     *  1   2
     *3  4 5 6
     * i = ,左孩子为2*i+1,右孩子为2*i+2
     * 父亲为 (i-1)/2
     *
     * 该方法是从加入的数从底部往上面走，其父亲比他小就交换
     *
     * 生成的堆是个大根堆。
     * @param st
     * @param i
     */
    private static void heapInsert(int[] st, int i) {
        while (st[(i-1)/2]<st[i]){
            swap(st,(i-1)/2,i);
            i = (i-1)/2;
        }
    }


    /**
     * 三路快排，没优化版
     * @param st
     * @param l
     * @param r
     */
    private static void quickSort(int[] st, int l, int r) {
        if(l>=r){
            return;
        }
        int[] p = partition(st,l,r);
        quickSort(st,l,p[0]-1);
        quickSort(st,p[1]+1,r);
    }

    /**
     * 从最右面取数，最后交换简单些
     * @param st
     * @param l
     * @param r
     * @return
     */
    private static int[] partition(int[] st, int l, int r) {
        int temp = st[r];
        int i = l;
        int start = l-1;//{)
        int end = r;//(}

        while(i<end){
            if(st[i]>temp){
                swap(st,i,--end);
            }else if(st[i]<temp){
                swap(st,i,++start);
                i++;
            }else{
                i++;
            }
        }
        //此时i在大于temp处，所以开始选择最后一个数进行对比
        swap(st,i,r);
        return new int[]{start+1,end};
    }


    /**
     * 归并排序
     * @param st
     */
    private static void mergeSort(int[] st, int i, int length) {
        if(i >= length){
            return;
        }
        //注意加号的优先级高于>>符号
        int center = i+((length-i)>>1);
        mergeSort(st,i,center);
        mergeSort(st,i+1,length);
        merge(st,i,center,length);
    }

    private static void merge(int[] st, int l, int center, int r) {
        int count = r-l+1;
        int[] array = new int[count];
        for(int i=l;i<=r;i++){
            array[i-l] = st[i];
        }
        int left = l;
        int right = center+1;
        int j = l;
        while(j<=r){
            if(left>center){
                st[j++] = array[right-l];
                right++;
            }else if(right>r){
                st[j++] = array[left-l];
                left++;
            }else if(array[left-l]>array[right-l]){
                st[j++] = array[right-l];
                right++;
            }else{
                st[j++] = array[left-l];
                left++;
            }
        }

    }


    /**
     * 选择排序
     * @param st
     */
    private static void selectSort(int[] st) {
        for(int i =0;i<st.length;i++){
            int min = i;
            for(int j = i;j<st.length;j++){
                if(st[min]>st[j]){
                    min = st[j];
                }
            }
            swap(st,i,min);
        }
    }

    /**
     * 插入排序
     * @param st
     */
    private static void insertSort(int[] st) {
        for(int i =1;i<st.length;i++){
            int temp = st[i];
            int j = i;
            for(j = i-1;j>=0;j--){
                if(st[j]<temp){
                    break;
                }
                st[j+1] =st[j];
            }
            st[j+1] = temp;
        }
    }

    /**
     * 冒泡排序
     * @param st
     */
    private static void bubblingSort(int[] st) {
        for(int i = 0;i<st.length;i++){
            for(int j = 0;j<st.length-i-1;j++){
                if(st[j]>st[j+1]){
                    swap(st,j,j+1);
                }
            }
        }
    }

    /**
     * 通过异或进行交换  不同为真
     * a = a^b;  00 11 -> 11
     * b = b^a;  11 11 -> 00
     * a = b^a;  00 11 -> 11
     *
     * *警告：异或交换位置不适用于相同内存空间的数进行交换。只能使用不同内存空间的两个数进行交换。
     * @param st
     * @param j
     * @param i
     */
    private static void swap(int[] st, int j, int i) {
//        st[i] = st[i]^st[j];
//        st[j] = st[j]^st[i];
//        st[i] = st[j]^st[i];
        int temp = st[j];
        st[j] = st[i];
        st[i] = temp;
    }
    private static void print(int[] st){
        for(int i = 0;i<st.length;i++){
            System.out.println(st[i]);
        }
    }


}
