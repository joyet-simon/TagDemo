package fr.m2iformation.neumi.tagdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EditTag etTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTag = findViewById(R.id.etTag);
    }

    public void clear(View view) {
        etTag.clear();
    }
}
