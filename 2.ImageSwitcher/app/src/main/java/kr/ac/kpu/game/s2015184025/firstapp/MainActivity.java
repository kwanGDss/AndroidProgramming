package kr.ac.kpu.game.s2015184025.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView mainImageView;

    private int pageIndex;
    private static int[] resIds = {
            R.mipmap.cat1,
            R.mipmap.cat2,
            R.mipmap.cat3,
            R.mipmap.cat4,
            R.mipmap.cat5,
    };

    private ImageButton prevButton;
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageIndex = 0;

        mainTextView = findViewById(R.id.mainTextView);
        mainImageView = findViewById(R.id.mainImageView);

        prevButton = findViewById(R.id.prevButtonView);
        nextButton = findViewById(R.id.nextButtonView);

        showImage();
    }

    public void OnBtnPrevious(View view) {
        if (pageIndex == 0) {
            return;
        }

        pageIndex--;
        showImage();
    }

    public void OnBtnNext(View view) {
        if (pageIndex == 4) {
            return;
        }

        pageIndex++;
        showImage();
    }


    private void showImage() {
        mainTextView.setText((pageIndex + 1) + " / " + resIds.length);
        int resId = resIds[pageIndex];
        mainImageView.setImageResource(resId);

        prevButton.setEnabled(pageIndex != 0);
        nextButton.setEnabled(pageIndex != resIds.length - 1);
    }
}