package fr.m2iformation.neumi.tagdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements EditTag.OnOutListener {

    EditTag etTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTag = findViewById(R.id.etTag);
        etTag.setOnOutListener(this);

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

    }
}
