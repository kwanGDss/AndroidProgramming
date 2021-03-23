package kr.ac.kpu.game.s2015184025.pairgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] buttonIds = {
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03, R.id.card_04,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13, R.id.card_14,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23, R.id.card_24,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33, R.id.card_34
    };

    private int[] cards = {
            R.mipmap.card_2c, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_3d, R.mipmap.card_8c,
            R.mipmap.card_4h, R.mipmap.card_4h, R.mipmap.card_5s, R.mipmap.card_5s, R.mipmap.card_8c,
            R.mipmap.card_as, R.mipmap.card_as, R.mipmap.card_jc, R.mipmap.card_jc, R.mipmap.card_6h,
            R.mipmap.card_qh, R.mipmap.card_qh, R.mipmap.card_kd, R.mipmap.card_kd, R.mipmap.card_6h
    };

    private ImageButton prevButton;
    private int visibleCardCount;
    private TextView scoreTextView;
    private int score;

    public void setScore(int score) {
        this.score = score;
        scoreTextView.setText("Score: " + this.score);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);

        startGame();
    }

    public void onBtnCard(View view) {
        if (view == prevButton) {
            return;
        }

        int prevCard = 0;
        if (prevButton != null) {
            prevButton.setImageResource(R.mipmap.card_blue_back);
            prevCard = (Integer)prevButton.getTag();
        }

        int buttonIndex = getButtonIndex(view.getId());
        Log.d(TAG, "onBtnCard() has been called. ID=" +view.getId() + "buttonIndex=" + buttonIndex);

        int card = cards[buttonIndex];
        ImageButton imageButton = (ImageButton)view;
        imageButton.setImageResource(card);

        if (card == prevCard) {
            imageButton.setVisibility(view.INVISIBLE);
            prevButton.setVisibility(view.INVISIBLE);
            prevButton = null;
            visibleCardCount -= 2;
            score++;
            scoreTextView.setText("Score: " + this.score);
            if (visibleCardCount == 0) {
                askRestart();
            }
            return;
        }
        if (prevButton != null) {

        }
        prevButton = imageButton;
    }

    private void askRestart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart");
        builder.setMessage("Do you really want to restart the game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });

        builder.setNegativeButton("No", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private int getButtonIndex(int resId) {
        for(int i = 0; i < buttonIds.length; i++) {
            if(buttonIds[i] == resId) {
                return i;
            }
        }
        return -1;
    }

    public void onBtnRestart(View view) {
        askRestart();
    }

    private void startGame() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int ri = random.nextInt(cards.length);
            int t = cards[i];
            cards[i] = cards[ri];
            cards[ri] = t;
        }

        for(int i = 0; i < buttonIds.length; i++){
            ImageButton b = findViewById(buttonIds[i]);
            b.setTag(cards[i]);
            b.setVisibility(View.VISIBLE);
            b.setImageResource(R.mipmap.card_blue_back);
        }

        prevButton = null;
        visibleCardCount = cards.length;

        setScore(0);
    }
}