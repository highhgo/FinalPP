package com.example.seok2.freepp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallery_main extends Activity {

    private static int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    static final String TAG = "Gallery_main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gallery);
    }

    //로드버튼 클릭시 실행
    public void load_Image_from_Gallery(View view) {
        Intent gallery_intent = new Intent(Intent.ACTION_GET_CONTENT); //Intent 생성
        gallery_intent.setType("image/*"); //image만 볼수 있게
        startActivityForResult(Intent.createChooser(gallery_intent, "Select Picture"), PICK_IMAGE_REQUEST);//Intent 시작.
    }//load_Image_from_Gallery

    //이미지 선택작업을 후의 결과 처리
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();//data에서 이미지를 가져옴.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int inch = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));//이미지의 크기를 줄여줌.
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, inch, true);

                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(scaled);
            }//if
            else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();//'취소 되었습니다.'메시지 출력.
            }//else
        }//try

        //예외처리
        catch (Exception e) {
            Toast.makeText(this, "로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();//'로딩에 오류가 있습니다.' 메시지 출력.
            e.printStackTrace();
        }//catch
    }//onActivityResult

}
