package com.example.lzl.java.pool;

import android.util.Log;
import android.util.SparseArray;

/**
 * SparseArray：安卓中的稀疏数组，比hashMap更节省内存
 * @param <T>
 */
public abstract class ObjectPool<T> {
    //空闲沲，用户从这个里面拿对象
    private SparseArray<T> freePool;
    //正在使用沲，用户正在使用的对象放在这个沲记录
    private SparseArray<T> lentPool;

    //沲的最大值
    private int maxCapacity;

    public ObjectPool(int initialCapacity, int maxCapacity) {
        //初始化对象沲
        initalize(initialCapacity);
        this.maxCapacity=maxCapacity;
    }

    private void initalize(int initialCapacity) {
        lentPool=new SparseArray<>();
        freePool=new SparseArray<>();
        for(int i=0;i<initialCapacity;i++){
            freePool.put(i,create());
        }
    }

    /**
     * 申请对象
     * @return
     */
    public T acquire() throws Exception {

        Log.e("ObjectPool",(lentPool.size()+"::"+freePool.size())+"");
        T t=null;
        synchronized (freePool){
            int freeSize=freePool.size();
            for(int i=0;i<freeSize;i++){
                int key=freePool.keyAt(i);
                t=freePool.get(key);
                if(t!=null){
                    this.lentPool.put(key,t);
                    this.freePool.remove(key);
                    return t;
                }
            }
            //如果没对象可取了
            if(t==null && lentPool.size()+freeSize<maxCapacity){
                //这里可以自己处理,超过大小
                if(lentPool.size()+freeSize==maxCapacity){
                    Log.e("ObjectPool","超出对象池的最大值，扩容");
                    throw new Exception();
                }
                t=create();
                lentPool.put(lentPool.size()+freeSize,t);
            }
        }
        return t;
    }

    /**
     * 回收对象
     * @return
     */
    public void release(T t){
        if(t==null){
            return;
        }
        int key=lentPool.indexOfValue(t);
        //释放前可以把这个对象交给用户处理
//        restore(t);
        Log.e("ObjectPool","需要释放的编号"+key);
        this.freePool.put(key,t);
        this.lentPool.remove(key);

    }

    protected void restore(T t){

    }


    protected abstract T create();

    public ObjectPool(int maxCapacity) {
        this(maxCapacity/2,maxCapacity);
    }

}
