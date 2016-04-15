package net.archeryc.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadActivity extends AppCompatActivity implements Callable<String>{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView = (TextView) findViewById(R.id.text_future);
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
