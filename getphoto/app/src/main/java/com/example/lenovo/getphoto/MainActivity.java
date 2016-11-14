package com.example.lenovo.getphoto;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyImageView photo1;
    private MyImageView photo2;
    private ImageButton next;
    private int SELECT_PICTURE = 0; // 从图库中选择图片
    private Bitmap bmp;
    private boolean flag1 = false;
    private boolean flag2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        photo1 = (MyImageView) findViewById(R.id.imageView1);
        photo1.setOnClickListener(this);
        photo2 = (MyImageView) findViewById(R.id.imageView2);
        photo2.setOnClickListener(this);
        next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(this);

    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                if (bmp != null) {
                    bmp.recycle();
                    bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            switch (requestCode){
                case 1:
                    photo1.setImageURI(uri);
                    flag1 = true;
                    break;
                case 2:
                    photo2.setImageURI(uri);
                    flag2 = true;
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(MainActivity.this, "选择图片失败,请重新选择", Toast.LENGTH_SHORT).show();

        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.imageView1:
                showChoosePhotoDialog(1);
                break;
            case R.id.imageView2:
                showChoosePhotoDialog(2);
                break;
            case R.id.next:
                if(flag1==true&&flag2==true){
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }else if(flag1==false&&flag2==false) {
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("你需要选择两张图片")
                            .setPositiveButton("确定", null)
                            .show();
                }else if(flag2==false) {
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("请选择需加密的图片")
                            .setPositiveButton("确定", null)
                            .show();
                }else{
                    new AlertDialog.Builder(this)
                            .setTitle("ERROR")
                            .setMessage("请选择需展示的图片")
                            .setPositiveButton("确定", null)
                            .show();
                }
                break;
            default:
                break;
        }
    }

    private void showChoosePhotoDialog(final int photoid) {
        CharSequence[] items = { "相册", "相机" };
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("选择图片来源")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if (which == SELECT_PICTURE) {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("image/*");
                            startActivityForResult(intent, photoid);
                        }
                        else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, photoid);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                }).create();
        dialog.show();
    }


}

