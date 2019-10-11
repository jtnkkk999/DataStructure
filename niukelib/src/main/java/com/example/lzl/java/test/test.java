package com.example.lzl.java.test;

import com.example.lzl.java.niukelib.prototype.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


public class test {

//    8
//            [4,1,8,4,5]
//            [5,0,1,8,4,5]
//            2
//            3
    public static void main(String[] args){
        ListNode A = new ListNode(4);
        A.next = new ListNode(1);
        A.next.next = new ListNode(8);
        A.next.next.next =new ListNode(4);
        A.next.next.next.next  = new ListNode(5);

        ListNode B = new ListNode(5);
        B.next = new ListNode(0);
        B.next.next = new ListNode(1);
        B.next.next.next =new ListNode(8);
        B.next.next.next.next  = new ListNode(4);
        B.next.next.next.next.next  = new ListNode(5);
//        System.out.print(getIntersectionNode(A,B)+"");

//        ListNode result = reverseBetween(A,1,3);
//        while(result!=null){
//            System.out.println(result.val);
//            result = result.next;
//        }

//        [4,9,5]
//[9,4,9,8,4]
//        int[] a = {4,9,5};
//        int[] b = {9,4,9,8,4};
//        int[] result = intersection(a,b);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
//        ListNode result = insertionSortList(B);
//        while(result!=null){
//            System.out.println(result.val);
//            result = result.next;
//        }
//        sortColors(new int[]{2, 0, 2, 1, 1, 0});
//        int[]cost = {7,1,5,3,6,4};
//        System.out.print(minCostClimbingStairs(cost));
//        System.out.print(uniquePaths(2,2));
//        [5,4,8,11,null,13,4,7,2,null,null,null,1]

//        TreeNode tree = new TreeNode(5);
//        tree.left = new TreeNode(4);
//        tree.right = new TreeNode(8);
//        tree.left.left = new TreeNode(11);
//        tree.left.left.left = new TreeNode(7);
//        tree.left.left.right = new TreeNode(2);
//        tree.right.left = new TreeNode(13);
//        tree.right.right = new TreeNode(4);
//        tree.right.right.right = new TreeNode(1);
//        System.out.print(hasPathSum(tree,22));
        int [] a = {1,5,11,5};
        canPartition(a);
    }

    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0;i<nums.length;i++){
            sum += nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        sum = sum/2;
        boolean[] result = new boolean[sum+1];
        result[0] = true;
        for(int i = 0;i<nums.length;i++){
            result[sum] = result[sum] || result[sum - nums[i]];
        }
        return false;
    }
    public static void getResult(boolean[] result,int sum){

    }



    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public static class Bean{
        public Bean(TreeNode node,int sum){
            this.node = node;
            this.sum = sum;
        }
        TreeNode node;
        int sum;
    }

    /**
     * 将递归版本改成非递归版本，通过手动压栈进行计算
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        //先序遍历，即深度优先遍历，
        Stack<Bean> stack = new Stack<>();
        stack.push(new Bean(root,sum-root.val));
        while(!stack.empty()) {
            Bean p = stack.pop();
            if(p.node.right !=null){
                stack.push(new Bean(p.node.right,p.sum-p.node.right.val));
            }
            if(p.node.left!=null){
                stack.push(new Bean(p.node.left,p.sum-p.node.left.val));
            }
            if(p.node.left == null && p.node.right == null){
                if(p.sum ==0){
                    return true;
                }
            }
        }
        return false;
    }
     public static boolean a(TreeNode root, int sum){
         if(root == null){
             return false;
         }
         if(root.left == null&& root.right == null){
             return sum - root.val == 0;
         }
         return a(root.left,sum - root.val) || a(root.right,sum -root.val);
     }

    /**
     * 动态规划，通过二维数组，写出已知的项得出后续项的逻辑。
     *  1）首先可以写出当m == 1和 n==1的时候，行走的方案是1，将相关数组填充好。
     *  2）然后再写result[2][2]你会发现，result[m][n] = result[m-1][n] + result[m][n-1].
     *  3)把整个数组填满，最后result[m][n]就是最后的结果
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] result = new int[m+1][n+1];
        //1.填写初始的已知结果的数组内容
        for(int i = 1;i<result[1].length;i++){
            result[1][i] = 1;
        }
        for(int i = 1;i<result.length;i++){
            result[i][1] = 1;
        }
        //2.计算整个数据的结果
        for(int i = 2;i<result.length;i++){
            for(int j = 2;j<result[i].length;j++){
                result[i][j] = result[i-1][j]+result[i][j-1];
            }
        }
        //3.result[m][n]就是结果
        return result[m][n];
    }

    /**
     *
     * 动态规划版本：
     * 真实的交易状态就是买了股票，又卖了股票，赚了钱才算一次交易成功，也就是dp[i][0]里的值
     * dp[i][0]：表示今天钱最多的状态是多少钱（真实交易了的收益状态）
     *              :1)不买股票，一直下跌。
     *               2）买股票赚钱了。
     * dp[i][1]: 表示买了一手股票的情况下，做多留下的钱。（保存的最优状态，购买最低股价的状态，还没有进行交易）
     *              ：当股票一直下跌，dp[i][1]表示如果买股票，最低点的位置
     *                当股票上涨，dp[i][1]表示买股票，最低点的位置
     *整逻辑就是，股价下降区间，dp[i][0]一直是钱最多的状态（不曾改变）。dp[i][1]一直在被赋值即股价最低时持有1股和剩余钱的状态（值一直改变）。
     *            股价上涨区间，dp[i][0]一直是钱最多的状态（一直改变，在赚钱）。dp[i][1]一直是股价最低的时候持有1股和剩余钱的状态（值一直不变）。
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if(prices==null||prices.length==0||prices.length == 1){
            return 0;
        }
        //返回结果最多能赚多少钱
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1;i<prices.length;i++){
            //当天钱最多的状态
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //买了一股当前波段最低点的股票和赚的剩余的钱（具体举几个例子好好分析一下，上升波段，下降波段，先下再上波段，
            //你会发现，保存的都是买了一手最底部股价后的价钱总和）
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[prices.length-1][0];
    }

    /**
     * 只要右面大的数减去左面小的数就可以了。时刻记录左面最小的值minindex。遍历一遍的时候一直计算相差的最大值，当出现更小的值的时候，
     * minindex改变成当前最小值
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;//记录获得的最大利益
        int minindex = 0;//最小值的数组标记位
        int i =1;//遍历的下标
        while(i<prices.length){
            //当遍历的值比最小值大，计算获得的利益
            if(prices[i]>prices[minindex]){
                if(max<(prices[i]-prices[minindex])){
                    max = prices[i]-prices[minindex];
                }
                i++;
            }else{
                //当遍历的值比最小值小，重新记录最小值，继续遍历
                minindex = i;
                i++;
            }
        }
        return max;
    }
    static int[] result;
    public static int minCostClimbingStairs(int[] cost) {
        //解题的前提：人是站在-1位置上的(相对于cost来说的),result[i]表示（i>2）踩在第i个台阶上所消耗的最少体力。
        //1.cost.length长度小于等于2的情况特殊处理
        if(cost.length<=1){
            return 0;
        }
        if(cost.length ==2){
            return Math.min(cost[0],cost[1]);
        }
        //2.动态规划初始条件填写。
        result = new int[cost.length];
        result[0] = cost[0];
        result[1] = cost[1];
        //3.计算到第i个台阶消耗最少能体力的数组。
        for(int i = 2;i<cost.length;i++){
            result[i] = Math.min(result[i-1]+cost[i],result[i-2]+cost[i]);
        }
        //4.只需要返回result[cost.length-1],result[cost.length-2]的最小值就好，因为
        //result[cost.length-1]是走到cost.length-1位置所消耗的最少体力，cost走到最后了。
        //result[cost.length-2]是走到cost.length-2位置所消耗的最少体力，可以走两步不消耗能量。
        return Math.min(result[cost.length-1],result[cost.length-2]);
    }
//    int[]cost = {0, 0, 1,1};
    private static int getresult(int[] cost,int count) {
        //当count>2的时候，必然会踩到cost[0]和cost[1]上，这两个返回值是递归的终止条件
        if(count == 0){
            return cost[0];
        }
        if(count == 1){
            return cost[1];
        }
        //记忆化搜索的数组，当没有计算过的值，让其保存在数组里。
        if(result[count] == 0){
            //当计算result[cost.length]的时候因为cost[cost.length]值不存在需要特殊处理cost[cost.length] == 0
            if(count == cost.length){
                result[count] = getresult(cost,count-2)+0>getresult(cost,count-1)+0?
                        getresult(cost,count-1)+cost[count-1]:getresult(cost,count-2)+cost[count-1];
            }else{
                //走两步到达count台阶所消耗的能量
                int a =getresult(cost,count-2)+cost[count];
                //走一步到达count台阶所消耗的能量
                int b = getresult(cost,count-1)+cost[count];
                //选择消耗最少的值保存在数组。
                result[count] = a>b? b:a;
            }
        }
        return result[count];
    }


    public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
            next = null;
          }
    }


        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if(headA == null||headB==null){
                return null;
            }
            //1.获取两个链表的长度
            int lengthA = 0;
            int lengthB = 0;
            ListNode ACopy = headA;
            ListNode BCopy = headB;
            while(ACopy!=null){
                ACopy = ACopy.next;
                lengthA++;
            }
            while(BCopy!=null){
                BCopy = BCopy.next;
                lengthB++;
            }
            //2.找到两个链表长度的差值，并进行对齐。（长的链表多出来的长度去掉）
            int dif = 0;
            if(lengthA >lengthB){
                dif = lengthA - lengthB;
                while(headA!=null&&dif!=0){
                    headA = headA.next;
                    dif--;
                }
            }else{
                dif = lengthB - lengthA;
                while(headB!=null&&dif!=0){
                    headB = headB.next;
                    dif--;
                }
            }
            //3.进行比较，节点相等返回
            while(headA!=null&&headB!=null){
                if(headA.val == headB.val){
                    return headA;
                }else{
                    headA = headA.next;
                    headB = headB.next;
                }
            }
            return null;

        }
    //4 1 8 4 5
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        //统计节点的个数
        int count = 1;
        //保存头部节点，用于返回值
        ListNode first = head;
        //反转链表需要的两个标记位顺序是 pre head next
        ListNode pre = null;
        ListNode next;
        //1.将head标记位移动到m的位置
        while(head!=null&&count<m){
            pre = head;
            head = head.next;
            next = head.next;
            count++;
        }
        //firstNode为需要反转节点的前一个位置。firstNextNode需要反转的节点
        ListNode firstNode = pre;
        ListNode firstNextNode = head;
        //2.把从m位置开始的节点进行链表的反转此时，head在n+1的位置。（我的反转逻辑只有在n+1的位置时，才能使m~n的节点都进行翻转）
        while(head!=null&&count<=n){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count++;
        }
        //secondNode反转的最后一个节点，secondNextNode反转最后一个节点的下一个节点。
        ListNode secondNode = pre;
        ListNode secondNextNode = head;
        //3.情况分割：（1）当m>1的时候，将存储的4个节点进行拼接返回存储好的第一个节点,first
        //（2）当m=1的时候，将存储的4个节点进行拼接，返回进行翻转的最后一个节点secondNode。
        if(m > 1){
            firstNode.next = secondNode;
            firstNextNode.next = secondNextNode;
            return first;
        }else{
            first.next = secondNextNode;
            return secondNode;
        }
    }

    public ListNode reverseList(ListNode head) {
        //定义好辅助标记位，其顺序是pre head next
        ListNode pre = null;
        ListNode next;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        //1.将两个数组排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int count1 = 0;
        int count2 = 0;
        HashSet<Integer> set = new HashSet();
        //2.类似于归并排序中的归并思路，将相同的值存入set集合（set集合不会有重复的值）
        while(count1<nums1.length&&count2<nums2.length){
            if(nums1[count1] == nums2[count2]){
                set.add(nums1[count1]);
                count1++;
                count2++;
            }else if(nums1[count1] > nums2[count2]){
                count2++;
            }else{
                count1++;
            }
        }
        //3.将set里的结果传换成数组返回
        int[] result = new int[set.size()];
        int count = 0;
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            result[count++] = iterator.next();
        }
        return result;
//        TreeSet<Integer> set1 = new TreeSet<>();
//        TreeSet<Integer> set2 = new TreeSet<>();
//        for (int i = 0; i < nums1.length; i++) {
//            set1.add(nums1[i]);
//        }
//        for (int i = 0; i < nums2.length; i++) {
//            if(set1.contains(nums2[i])){
//                set2.add(nums2[i]);
//            }
//        }
//        int[] result = new int[set2.size()];
//        int count = 0;
//        while(!set2.isEmpty()){
//            result[count++] = set2.pollFirst();
//        }
//        return result;
    }

    public static ListNode insertionSortList(ListNode head) {
        //复制头节点用于赋值和返回
        ListNode copy = head;
        //1.计算链表长度
        int count = 0;
        while(head!=null){
            head = head.next;
            count++;
        }
        //2.初始化数组，并将链表数值传给数组，以便后续插入排序。
        int[] array = new int[count];
        count = 0;
        head = copy;
        while(head!=null){
            array[count++] = head.val;
            head = head.next;
        }
        //3.插入排序算法
        for(int i =1;i<count;i++){
            int j;
            int value = array[i];
            for(j = i-1;j>=0;j--){
                if(array[j]>value){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = value;
        }
        //4.给链表重新赋值
        head = copy;
        count = 0;
        while(head!=null){
            head.val = array[count++];
            head = head.next;
        }
        return copy;
        //动态数组不让用vector，用vector实现的方法，思路同上
//        ListNode copy = head;
//        Vector<ListNode> vector = new Vector<>();
//        while(head!=null){
//            vector.add(head);
//            head = head.next;
//        }
//        for(int i = 1;i< vector.size();i++){
//            int j;
//            int value = vector.get(i).val;
//            for(j =i-1;j>=0;j--){
//                if(vector.get(j).val>value){
//                    vector.get(j+1).val =vector.get(j).val;
//                }else{
//                    break;
//                }
//            }
//            vector.get(j+1).val = value;
//        }
//        return copy;
    }

    public int[][] allCellsDistOrder(int R, int C, final int r0, final int c0) {
        //1.将矩阵转换成，需要输出的二维数组格式。
        int[][] result = new int[R*C][2];
        int index = 0;
        for(int i =0;i<R;i++){
            for(int j =0;j<C;j++){
                int[] xy = {i,j};
                result[index++] = xy;
            }
        }
        //2.将result[][]看成result[]的一维数组进行比较。通过Comarator，比较器进行比较方法的规定进行排序。
        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                int cha1 = Math.abs(t1[0] - r0) + Math.abs(t1[1] - c0);
                int cha2 = Math.abs(t2[0] - r0) + Math.abs(t2[1] - c0);
                return cha1- cha2;
            }
        });
        return result;
    }

    public static void sortColors(int[] nums) {
        //很明显的荷兰国旗问题，三路快排的一个步骤
        int l = -1;
        int r = nums.length;
        int i = 0;
        int pre;
        while(i<r){
            if(nums[i]==0){
                pre = nums[i];
                nums[i] =nums[++l];
                nums[l]= pre;
                i++;
            }else if(nums[i]==1){
                i++;
            }else{
                pre = nums[--r];
                nums[r]=nums[i];
                nums[i] = pre;
            }
        }
    }

    public int largestPerimeter(int[] A) {
        if(A.length<3){
            return 0;
        }
        //边从小达大排序
        Arrays.sort(A);
        //从后往前两边之和大于第三边。
        for(int i = A.length-1;i>=2;i--){
            if(A[i]<A[i-1]+A[i-2]){
                return A[i]+A[i-1]+A[i-2];
            }
        }
        return 0;
    }


}
