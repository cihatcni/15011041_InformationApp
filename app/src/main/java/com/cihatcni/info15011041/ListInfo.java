package com.cihatcni.info15011041;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ListInfo extends AppCompatActivity {

    String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info);

        Intent intent = getIntent();

        ImageView ppImage;
        TextView nameInf;
        TextView surnameInf;
        TextView emailInf;
        TextView dateInf;
        TextView idNumberInf;
        TextView telNumberInf;
        TextView ageText;

        nameInf = findViewById(R.id.nameInfText);
        surnameInf = findViewById(R.id.surnameInfText);
        emailInf = findViewById(R.id.emailInfText);
        dateInf = findViewById(R.id.dateInfText);
        idNumberInf = findViewById(R.id.idnoInfText);
        telNumberInf = findViewById(R.id.telNoInfText);
        ppImage = findViewById(R.id.ppInfImage);
        ageText = findViewById(R.id.ageText);

        nameInf.setText("İsmi : " + intent.getStringExtra("name"));
        surnameInf.setText("Soyismi : " + intent.getStringExtra("surname"));
        dateInf.setText("Doğum tarihi : "+intent.getStringExtra("date"));
        emailInf.setText("Mail Adresi : "+intent.getStringExtra("email"));
        idNumberInf.setText("Kimlik Numarası : "+intent.getStringExtra("idNumber"));
        telNumberInf.setText("Telefon Numarası : " + intent.getStringExtra("telNumber"));
        ageText.setText("(Yaşı : " + intent.getIntExtra("personAge",0) + ")");

        //Bitmap pic = intent.getParcelableExtra("profilePicture");
        //ppImage.setImageBitmap(pic);

        ppImage.setImageBitmap(MainActivity.myImage);

        phoneNumber = intent.getStringExtra("telNumber");
        System.out.println("YENİDEN OLUŞTURULDU");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("YOK EDİLDİ.");
        System.gc();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("KALDIĞI YERDEN DEVAM ETTİ.");
    }

    public void dialPerson(View view) {

        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);

    }

    public void dersListele(View view) {

        Intent intent = new Intent(this,DersActivity.class);
        startActivity(intent);

    }
}
