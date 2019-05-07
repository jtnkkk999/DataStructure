package com.example.lzl.java.multithread.thread.class4_balking;

public class Data {
    private final String fileName;
    private String content;
    private boolean changed;
    public Data(String fileName,String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }
    //修改数据内容
    public synchronized void change(String newContent){
        content = newContent;
        changed = true;
    }
    //保存数据
    public synchronized void save(){
        if(!changed){
            return;
        }
        doSave();
        changed = false;
    }

    private void doSave() {
        System.out.print("保存数据");
    }
}
