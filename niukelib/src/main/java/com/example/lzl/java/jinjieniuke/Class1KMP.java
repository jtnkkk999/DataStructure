package com.example.lzl.java.jinjieniuke;

/**
 * KMP：String1（N） 中是否包含String2(M),并且返回在String1中的下标。O(N)
 *      子序列：都可以
 *      字串，子数组：必须连续。
 *      最差情况O(N*M)
 *      1.求解String2的next数组，前缀不可以包含最后一个字符，后缀不可以包含第一个字符。所以next数组next[0] = -1,next[1] = 0;（人为规定）
 *      2.通过next数组加速求得位置。
 * 题目：1.abcabc 如何添加最少的项，获得两个起始位置不同的最短序列。（abcabcabc）
 *      2.如何查找一个二叉树中，是否有一个子树，跟另一个二叉树相同。（将二叉树扁平化，再KMP）
 *      3.如何判断一个字符串是否是通过某个字符串反复多次组合而成。
 *
 * Manacher:一个字符串中找到最长回文子串。(回文问题都需要通过添加额外字符来减少奇偶分题的区分)
 * 题目：给一个字符串添加最少的字符，使得该串成为回文串。
 *
 *
 * BFPRT:无序数组中找第K小的数或者第K大的数。O(n)的时间复杂度严格的。就是选取划分值的方式不一样。（通过5个分成一份排序计算中位数，然后再通过一半的partition进行查找）
 *      如果用排序则是O(nlogn)的时间复杂度。（快排取一般就是O(N)的复杂度，长期期望）
 * 题目：1.在一个数组中找出最大的K个数，和最小的K个数。（堆排序O(nlogn)）
 */
public class Class1KMP {
        public static void main(String args[]){
            //kmp
            System.out.println(KMP("ababaaaa","aaaaa"));
            //manacher
//            System.out.print(Manacher("babcbaa"));
            //BFPRT
            int[] a = {6,5,4,8,9,1};
            System.out.print(BFPRT(a,4));
//            getMedia(a,0,a.length-1);
//            int[] b = partition(a,0,a.length-1,6);
//            System.out.print(b[0]+":"+b[1]);
        }
        public static int BFPRT(int[] a,int k){
            return bfprt(a,0,a.length-1,k-1);
            //1.把数组进行分组。

        }

    /**
     * 计算数组第K小的数。
     * @param a 求结果的数组
     * @param begin 开始位置
     * @param end 结束位置
     * @param k 第K小的数
     * @return 返回第K小的结果
     */
        public static int bfprt(int[] a,int begin,int end,int k){
            if(begin == end){
                return a[begin];
            }
            //获取中位数。
            int center = mediaOfMedias(a,begin,end);
            //partition操作，大于的放左面，小于的放右面。
            int[] pivot = partition(a,begin,end,center);
            //
            if(k<pivot[0]){
                return bfprt(a,begin,pivot[0]-1,k);
            }else if(k>pivot[1]){
                return bfprt(a,pivot[1]+1,end,k);
            }else{
                return a[k];
            }
        }

        private static int[] partition(int[] a, int begin, int end, int center) {
            int l = begin-1;
            int r = end+1;
            int i = begin;
            int[] result = new int[2];
            while(i<r){
                if(a[i]<center){
                    int temp = a[++l];
                    a[l] = a[i];
                    a[i++] = temp;
                }else if(a[i]>center){
                    int temp  = a[--r];
                    a[r] = a[i];
                    a[i] = temp;
                }else{
                    i++;
                }
            }
            result[0] = l+1;
            result[1] = r;
            return result;
        }

    /**
     * 5个一组求中位数
     * @param a
     * @param begin
     * @param end
     * @return
     */
    public static int mediaOfMedias(int[] a,int begin,int end){
        int count = end-begin+1;
        int[] array = new int[count/5+count%5==0?0:1];
        for(int i = 0;i<array.length;i++){
            int st = begin+i*5;
            int en = st+4;
            array[i] = getMedia(a,st,Math.min(en,end));
        }
        return bfprt(array,0,array.length-1,array.length/2);
    }

    private static int getMedia(int[] a, int st, int min) {
        //排序
        for(int i =st+1;i<=min;i++){
            int j;
            int temp = a[i];
            for(j = i-1;j>=st;j--){
                if(a[j]>temp){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = temp;
        }
        //返回中间值
        return a[(st+min)/2+(st+min)%2];
    }

    //================================================manacher===============
        public static int Manacher(String str){
            //1.给字符串变成字符数组,并拼接上间隔符号
            char[] arr = str.toCharArray();
            char[] res = new char[2*arr.length+1];
            for(int i = 0;i<res.length;i++){
                res[i] = i%2 == 0?'#':arr[i/2];
            }
            //2.
            int C = 0;
            int R = -1;//不是回文的边界。
            int max = Integer.MIN_VALUE;
            int[] ring = new int[res.length];//回文半径，是包含中间那个数的。（扩展后的数组都是基数个）
            for(int i =0;i<ring.length;i++){
                if(R>i){
                    //1)i在R里面的情况(有三种情况，i'半径在里面，i'半径在外面，i'半径在边上)，i'=2*c-i;
                    //前两种情况暴力扩一次就退出循环了，最后一种就会进行暴力扩。
                    ring[i] = Math.min(ring[2*C-i],R-i);
                }else{
                    //暴力扩首先赋值1；
                    ring[i] = 1;
                }
                //暴力扩先,检测i的暴力扩两边是否越界。
                while(i+ring[i]<ring.length&&i-ring[i]>=0){
                    if(res[i+ring[i]]==res[i-ring[i]]){
                        ring[i]++;
                    }else{
                        break;
                    }
                }
                if(i+ring[i]>R){
                    R = i+ring[i];
                    C = i;
                }
                max = Math.max(max,ring[i]);
            }
            //因为添加了二外元素，所以结果为回文半径-1
            return max - 1;
        }
        // ===============================================KMP====================
        public static int KMP(String st1,String st2){
            int[] next = getNext(st2);
            for(int i = 0;i<st2.length();i++){
                System.out.println(next[i]+"");
            }
            char[] arr1 = st1.toCharArray();
            char[] arr2 = st2.toCharArray();
            int count1 = 0;
            int count2 = 0;
            while(count1<arr1.length&&count2!=arr2.length){
                if(arr1[count1] == arr2[count2]){
                    count1++;
                    count2++;
                }else{
                    if(next[count2]!=-1){
                        count2 = next[count2];
                    }else{
                        count1++;
                    }
                }
            }
            if(count2 == arr2.length){
                return count1-count2;
            }else{
                return -1;
            }

        }
        public static int[] getNext(String str){
            int[] next = new int[str.length()];
            next[0] = -1;
            next[1] = 0;
            char[] array = str.toCharArray();
            int count = 2;
            int index = next[count-1];
            while(count<array.length){
                if(array[count-1] == array[index]){
                    next[count] = ++index;
                    count++;
                }else{
                    if(next[index] ==-1){
                        next[count] = 0;
                        count++;
                    }else{
                        index = next[index];
                    }
                }
            }
            return next;
        }
}
