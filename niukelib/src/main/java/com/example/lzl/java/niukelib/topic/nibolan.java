package com.example.lzl.java.niukelib.topic;

import java.util.LinkedList;

public class nibolan {
    public static void main(String[] args){
//        -1764 420 1 -12 7 -1
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));
        exp = "(-5)+1*4";
        System.out.println(getValue(exp));

        exp = "2*3*4*5/2";
        System.out.println(getValue(exp));

        exp = "9+(3-1)*3+10/2";
        System.out.println(getValue(exp));
    }
    public static int getValue(String string){
        return value(string.toCharArray(),0)[0];
    }

    private static int[] value(char[] chars, int i) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        int temp = 0;
        while(i<chars.length&&chars[i]!=')'){
            if(chars[i]>='0'&&chars[i]<='9'){
                temp = temp*10+chars[i++]-'0';
            }else if(chars[i]!='('){
                addNumber(temp,list,list2);
                temp = 0;
                list.addLast(chars[i++]+"");
            }else{
                int[] p = value(chars,i+1);
                temp = p[0];
                i = p[1]+1;
            }
        }
        addNumber(temp,list,list2);
        return new int[]{getSum(list,list2),i};
    }

    private static int getSum(LinkedList<String> list, LinkedList<Integer> list2) {
        while(!list.isEmpty()&&!list2.isEmpty()){
            String st = list.pollFirst();
            if("+".equals(st)){
                list2.addFirst(list2.pollFirst()+list2.pollFirst());
            }else{
                list2.addFirst(list2.pollFirst()-list2.pollFirst());
            }
        }
        return list2.getFirst();
    }

    private static void addNumber(int temp, LinkedList<String> list, LinkedList<Integer> list2) {
        if(!list.isEmpty()&&!list2.isEmpty()){
            String st = list.pollLast();
            if(st.equals("*")||st.equals("/")){
                if(st.equals("*")){
                    list2.addLast(temp*list2.pollLast());
                }else{
                    list2.addLast(list2.pollLast()/temp);
                }
            }else{
                list.addLast(st);
                list2.addLast(temp);
            }
            return;
        }
        list2.addLast(temp);
    }
}
