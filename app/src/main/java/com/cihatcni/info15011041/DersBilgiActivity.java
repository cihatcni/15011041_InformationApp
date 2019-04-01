package com.cihatcni.info15011041;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DersBilgiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_bilgi);

        TextView lessonName = findViewById(R.id.lessonName);
        TextView lessonStuCount = findViewById(R.id.lessonStudentCount);
        TextView lessonGrade = findViewById(R.id.lessonGrade);

        Intent intent = getIntent();
        lessonName.setText(intent.getStringExtra("lessonName"));
        lessonStuCount.setText(intent.getStringExtra("stuCount"));
        lessonGrade.setText(intent.getStringExtra("lessonGrade"));
    }
}
