package com.example.demo.controller;

import com.example.demo.service.ThreadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    /**
     * CPU占用过高
     */
    @RequestMapping("/cpu")
    public void test_cpu() {
        int num = 0;
        long start = System.currentTimeMillis() / 1000;
        while (true) {
            num = num + 1;

            if (num == Integer.MAX_VALUE){
                System.out.println("reset");
                num = 0;
            }
            if ((System.currentTimeMillis() / 1000) - start > 1000) {
                return;
            }
        }
    }

    /**
     * 堆溢出
     */
    @RequestMapping("/head")
    public void test_head() {
        List<byte[]> list = new ArrayList<>();
        int i=0;
        while(true){
            list.add(new byte[5*1024*1024]);
            System.out.println("分配次数："+(++i));
        }

    }

    class StackSOFTest{
        int depth = 0;

        public void sofMethod(){
            depth ++ ;
            sofMethod();
        }

        public void oomMethod(){
            while(true){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loopMethod();
                    }
                }).start();;
            }
        }

        private void loopMethod(){
            while(true){

            }
        }
    }


    /**
     * 栈空间不足StackOverflowError
     */
    @RequestMapping("/stack/overflow")
    public void test_stack() {
        StackSOFTest test = null;
        try {
            test = new StackSOFTest();
            test.sofMethod();
        } finally {
            System.out.println("递归次数："+test.depth);
        }

    }



    /**
     * 栈空间不足OutOfMemberError
     */
    @RequestMapping("/stack/outofmember")
    public void test_outofmember() {
        StackSOFTest test = new StackSOFTest();
        test.oomMethod();

    }

    /**
     * 持久代溢出
     * -XX:PermSize=10m -XX:MaxPermSize=10m
     */
    @RequestMapping("/permgenspace")
    public void test_permgenspace() {
        List<String> list = new ArrayList<>();
        int i=1;
        try {
            while(true){
                list.add(UUID.randomUUID().toString().intern());
                i++;
            }
        } finally {
            System.out.println("运行次数："+i);
        }

    }

    /**
     * 线程死锁
     */
    @RequestMapping("/thread/dead")
    public void test_dead() {
        ThreadService dt1 = new ThreadService();
        dt1.setFlag("a");
        Thread t1 = new Thread(dt1);
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dt1.setFlag("b");
        Thread t2 = new Thread(dt1);

        t2.start();

    }

    /**
     * 线程阻塞
     */


    /**
     * 线程等待资源
     */

    /**
     * 线程获取监视器
     */



}
