package com.example.lzl.java.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.lzl.java.myapplication", appContext.getPackageName());
    }
    @Test
    public void readFiles(){
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        que.offer(2);
        que.offer(3);
        que.offer(4);
        Iterator<Integer> iterator = que.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
