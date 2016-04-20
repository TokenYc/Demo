package net.archeryc.demo.imageLoader;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.archeryc.demo.R;
import net.archeryc.demo.base.BaseDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 24706 on 2016/4/20.
 */
public class LongClickDialog extends BaseDialog {

    private TextView textView;
    private Context mContext;

    private String dir=Environment.getExternalStorageDirectory()+"/demo/";
    private Bitmap mBitmap;

    public LongClickDialog(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    protected int getConvetViewId() {
        return R.layout.long_click_dialog;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.tv_save);
    }

    public void setBitmap(Bitmap bitmap){
        this.mBitmap=bitmap;
    }

    @Override
    protected void initListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveToLocal();
            }
        });
    }

    /**
     * 将图片保存到本地
     */
    private void saveToLocal() {
        File dirFile = new File(dir);
        //设置文件名
        File file=new File(dir+ System.currentTimeMillis()+".png");

        if (!dirFile.exists()){
            dirFile.mkdir();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fs = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 90, fs);
            fs.flush();//不要缓冲，立刻保存到文件
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dismiss();
    }

    @Override
    protected void setDialogAttr(Dialog dialog) {

    }
}
