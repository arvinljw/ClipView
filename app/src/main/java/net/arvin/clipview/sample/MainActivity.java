package net.arvin.clipview.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.arvin.clipview.RadiusFrameLayout;
import net.arvin.clipview.RadiusLinearLayout;
import net.arvin.clipview.RadiusRelativeLayout;

@SuppressWarnings("FieldCanBeLocal")
@SuppressLint("HandlerLeak")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int MSG_START = 101;
    private final int MSG_PAUSE = 102;
    private final int MSG_CONTINUE = 103;
    private final int MSG_ADD_PROGRESS = 104;

    private final int DEFAULT_PROGRESS_INCREASE_TIME = 50;

    private TextProgress progress;

    private TextView start;
    private TextView pause;

    private RadiusFrameLayout frame;
    private RadiusRelativeLayout relative;
    private RadiusRelativeLayout relative2;

    private RadiusLinearLayout linear;
    private RadiusLinearLayout linear2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (RadiusFrameLayout) findViewById(R.id.frame_1);
        frame.setRadiusLeftTop(0);

        relative = (RadiusRelativeLayout) findViewById(R.id.relative_1);
        relative.setRadiusLeftBottom(dp2px(24));

        relative2 = (RadiusRelativeLayout) findViewById(R.id.relative_2);
        relative2.setRadiusRightBottom(dp2px(24));

        linear = (RadiusLinearLayout) findViewById(R.id.linear_1);
        linear.setRadiusRightTop(dp2px(24));

        linear2 = (RadiusLinearLayout) findViewById(R.id.linear_2);
        linear2.setRadiusLeftTop(dp2px(24));

        progress = (TextProgress) findViewById(R.id.progress);

        start = (TextView) findViewById(R.id.tv_start);
        start.setOnClickListener(this);

        pause = (TextView) findViewById(R.id.tv_pause);
        pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_start) {
            mHandler.sendEmptyMessage(MSG_START);
            return;
        }
        if (!pause.isSelected()) {
            mHandler.sendEmptyMessage(MSG_PAUSE);
        } else {
            mHandler.sendEmptyMessage(MSG_CONTINUE);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADD_PROGRESS:
                    addProgress();
                    break;
                case MSG_START:
                    startProgress();
                    break;
                case MSG_PAUSE:
                    pauseProgress();
                    break;
                case MSG_CONTINUE:
                    continueProgress();
                    break;
            }
        }
    };

    private void addProgress() {
        if (progress.getProgress() == progress.getMax()) {
            return;
        }
        progress.setProgress(progress.getProgress() + 1);
        sendAddProgressMsg();
    }

    private void startProgress() {
        progress.setProgress(0);
        sendAddProgressMsg();
        pause.setText("暂停进度");
        pause.setSelected(false);
    }

    private void pauseProgress() {
        pause.setText("继续进度");
        pause.setSelected(true);
        if (mHandler.hasMessages(MSG_ADD_PROGRESS)) {
            mHandler.removeMessages(MSG_ADD_PROGRESS);
        }
    }

    private void continueProgress() {
        pause.setText("暂停进度");
        pause.setSelected(false);
        sendAddProgressMsg();
    }

    private void sendAddProgressMsg() {
        if (!mHandler.hasMessages(MSG_ADD_PROGRESS)) {
            mHandler.sendEmptyMessageDelayed(MSG_ADD_PROGRESS, DEFAULT_PROGRESS_INCREASE_TIME);
        }
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
