package net.archeryc.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.archeryc.demo.base.BaseActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadActivity extends BaseActivity implements Callable<String>{

    TextView textView;
    int a1=0;
    int a2=0;
    int a3=0;
    int a4=0;
    int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView = (TextView) findViewById(R.id.text_future);
        MyThread1 thread1=new MyThread1();
        MyThread2 thread2=new MyThread2();
        MyThread3 thread3=new MyThread3();
        MyThread4 thread4=new MyThread4();

        long start=System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int total=a1 + a2 + a3 + a4;
        Log.d("total", "user 4 thread total:" + total+"time:"+(System.currentTimeMillis()-start));

        MyThread myThread=new MyThread();
        long startTime=System.currentTimeMillis();
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("total", "thread total:" + sum+"time:"+(System.currentTimeMillis()-startTime));

    }

    @Override
    public String call() throws Exception {
        return "这里是Callable的call()方法的返回值";
    }


    /**
     * 开启一个FutureTask
     * @param view
     */
    public void openTask(View view){
        //FutureTask实现了Future<V>和Runnable接口,在他的run方法中对callable进行了处理。
        //Thread是只接受runnable接口的。
        FutureTask<String> futureTask = new FutureTask<String>(this);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            textView.setText(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0;i<=4000;i++) {
                sum+=i;
            }
        }
    }
    class MyThread1 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0;i<1000;i++) {
                a1+=i;
            }
        }
    }

    class MyThread2 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=1000;i<2000;i++) {
                a2+=i;
            }
        }
    }

    class MyThread3 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=2000;i<3000;i++) {
                a3+=i;
            }
        }
    }

    class MyThread4 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=3000;i<=4000;i++) {
                a4+=i;
            }
        }
    }


    /**
     * 开启线程池
     * @param view
     */
    public void openExcutor(View view){
        //Executors是一个创建ExecutorService的工厂，有一些预设好的，也可以自定义。
        //创建一个线程池
        ExecutorService executorService=Executors.newCachedThreadPool();
        Future<String> future=executorService.submit(this);
        try {
            Toast.makeText(ThreadActivity.this, future.get(), Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
