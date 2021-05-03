package kr.ac.kpu.game.s2015184025.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap bitmap;

    private float x;
    private float y;
    private long lastFrame;
    private float frameTime;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
        //update();
        x += 1 * frameTime;
        y += 2 * frameTime;

        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                frameTime = (float) (time - lastFrame) / 1_000_000_000;
                if(lastFrame == 0) {
                    lastFrame = time;
                }
                doGameFrame();
                lastFrame = time;
            }
        });

        //draw();
    }

    private void initResources() {
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        x = 100;
        y = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
        Log.d(TAG, "Drawing at: " + x + ", " + y + " ft=" + frameTime);
    }
}
