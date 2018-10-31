package fr.m2iformation.neumi.tagdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements EditTag.OnOutListener {

    EditTag etTag;
    long lastTime = 0l;
    ConstraintLayout screen;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTag = findViewById(R.id.etTag);
        etTag.setOnOutListener(this);
        screen = findViewById(R.id.screen);

    }

    public void clear(View view) {
        etTag.clear();
    }

    public void bleu(View view) {
        etTag.setTagColor(Color.BLUE);
    }

    public void rouge(View view) {
        etTag.setTagColor(Color.RED);
    }

    public void vert(View view) {
        etTag.setTagColor(Color.GREEN);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveJpeg(View view) {
        String filePath = this.getFilesDir().getPath() + "fichier";
        etTag.saveJpg(filePath);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void savePng(View view) {
        String filePath = this.getFilesDir().getPath() + "fichier2";
        etTag.savePng(filePath);
    }

    @Override
    public void onOut(View view) {
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vib.hasVibrator()) {
            vib.vibrate(500);
        }
        screen.setBackgroundColor(Color.RED);
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 750);
    }

    private class RemindTask extends TimerTask {
        @Override
        public void run() {
            screen.setBackgroundColor(0xd7d6d6);
            timer.cancel();
        }
    }

}
