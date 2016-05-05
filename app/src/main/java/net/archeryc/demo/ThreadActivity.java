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

public class ThreadActivity extends BaseActivity implements Callable<String> {

    TextView textView;
    private volatile int a = 0;
    private volatile int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView = (TextView) findViewById(R.id.text_future);
        countFrom1To(100, 1);
        countFrom1To(100, 4);
    }

    @Override
    public String call() throws Exception {
        return "这里是Callable的call()方法的返回值";
    }


    /**
     * Callable返回计算1段的计算结果
     */
    class CountCallable implements Callable<Long> {
        private long from;
        private long to;
        private long sum = 0;

        public CountCallable(long from, long to) {
            this.to = to;
            this.from = from;
        }

        @Override
        public Long call() throws Exception {
            for (long i = from; i <= to; i++) {
                sum += i;
            }
            return sum;
        }
    }

    /**
     * 从1加到1个数
     *
     * @param target
     * @param threadNum
     * @return
     */
    public long countFrom1To(long target, int threadNum) {
        long beginTime = System.currentTimeMillis();
        long total = 0;
        FutureTask<Long>[] futureTasks = new FutureTask[threadNum];
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            futureTasks[i] = new FutureTask<>(new CountCallable(i * target / threadNum + 1, (i + 1) * target / threadNum));
            threads[i] = new Thread(futureTasks[i]);
            threads[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                threads[i].join();
                total += futureTasks[i].get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Log.d("total", "  threadNum:" + threadNum + "  thread total:" + total + "  time:" + (System.currentTimeMillis() - beginTime));

        return total;
    }

    /**
     * 开启线程池
     *
     * @param view
     */
    public void openExcutor(View view) {
        //Executors是一个创建ExecutorService的工厂，有一些预设好的，也可以自定义。
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(this);
        try {
            Toast.makeText(ThreadActivity.this, future.get(), Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启一个FutureTask
     *
     * @param view
     */
    public void openTask(View view) {
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

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            add();
        }

        private void add() {
            while (a < 11000000) {
                a++;
            }
        }
    }


    class MyAddThreadSynchronized extends Thread {
        @Override
        public void run() {
            super.run();
            add();
        }

        private synchronized void add() {
            while (b < 10000000) {
                b++;
            }
        }
    }

    /**
     * 线程不同步方法
     *
     * @param view
     */
    public void unSynchronizedMethod(View view) {
        MyRunnable runnable=new MyRunnable();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("result", "不同步方法result:" + a);
    }

    /**
     * 线程同步方法
     *
     * @param vIew
     */
    public void synchronizedMethod(View vIew) {
        MyAddThreadSynchronized thread1 = new MyAddThreadSynchronized();
        thread1.start();
        MyAddThreadSynchronized thread2 = new MyAddThreadSynchronized();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("result", "同步方法result:" + b);
    }
}
