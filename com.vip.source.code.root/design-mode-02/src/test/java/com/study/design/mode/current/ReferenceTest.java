package com.study.design.mode.current;

import org.junit.Test;

import java.lang.ref.*;

/**
 * 功能描述: 4种级别由高到低依次为：强引用、软引用、弱引用和虚引用。
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/5/6 15:55
 */
public class ReferenceTest {

    //软引用
    @Test
    public void mySoftReferenceTest() throws InterruptedException {

        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        String str = new String("abc");
        SoftReference<String> softReference = new SoftReference<>(str, referenceQueue);

//        str = null;
        // Notify GC
        System.gc();
        System.out.println(softReference.get()); // abc

        Reference<? extends String> reference = referenceQueue.remove();
        System.out.println(reference.get()); //null

    }
    //弱引用
    @Test
    public void myWeakReferenceTest(){

        String str = new String("abc");
        WeakReference<String> weakReference = new WeakReference<>(str);
        str = null;


        str = null;
        // Notify GC
        System.gc();
        System.out.println(weakReference.get()); // abc


    }
    //虚引用
    @Test
    public void myPhantomReferenceTest(){

        String str = new String("abc");
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，要求必须与一个引用队列关联
        PhantomReference pr = new PhantomReference(str, queue);


        str = null;
        // Notify GC
//        System.gc();
        System.out.println(pr.get()); // abc


    }
}
