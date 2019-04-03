package com.cihatcni.info15011041;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView picture;
    EditText name;
    EditText surname;
    EditText email;
    EditText date;
    EditText idNumber;
    EditText telNumber;
    Drawable placeholder;
    int personAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameText);
        surname = findViewById(R.id.surnameText);
        email = findViewById(R.id.emailText);
        idNumber = findViewById(R.id.idnoText);
        date = findViewById(R.id.dateText);
        telNumber = findViewById(R.id.telText);
        picture  = findViewById(R.id.profilePic);
        placeholder = picture.getDrawable();

        if(savedInstanceState != null) {
            picture.setImageBitmap(null);
            name.setText(savedInstanceState.getString("name"));
            surname.setText(savedInstanceState.getString("surname"));
            email.setText(savedInstanceState.getString("email"));
            date.setText(savedInstanceState.getString("date"));
            idNumber.setText(savedInstanceState.getString("idNumber"));
            telNumber.setText(savedInstanceState.getString("idNumber"));
            Bitmap bitmap = savedInstanceState.getParcelable("image");
            picture.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("PROGRAM DEVAM EDİYOR.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("DURDURULDU.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("YOK EDİLDİ:");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("name",name.getText().toString());
        outState.putString("surname",surname.getText().toString());
        outState.putString("email",email.getText().toString());
        outState.putString("date",date.getText().toString());
        outState.putString("idNumber",idNumber.getText().toString());
        outState.putString("telNumber",telNumber.getText().toString());
        BitmapDrawable drawable = (BitmapDrawable) picture.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        outState.putParcelable("image", bitmap);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        picture.setImageBitmap(null);
        name.setText(savedInstanceState.getString("name"));
        surname.setText(savedInstanceState.getString("surname"));
        email.setText(savedInstanceState.getString("email"));
        date.setText(savedInstanceState.getString("date"));
        idNumber.setText(savedInstanceState.getString("idNumber"));
        telNumber.setText(savedInstanceState.getString("idNumber"));
        Bitmap bitmap = savedInstanceState.getParcelable("image");
        picture.setImageBitmap(bitmap);
    }

    public void imageSelect(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fotoğraf Nereden Alınsın?");
        builder.setItems(new CharSequence[]{"Kamera", "Galeri"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Kameradan
                if(which == 0) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 2);
                    }
                    else
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 11);
                }

                //Galeriden
                else if(which == 1) {
                    //İzin erişimi
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent picker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picker, 1);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12);
                    }
                }

                else
                    Toast.makeText(MainActivity.this, "Seçim Yapılmadı.", Toast.LENGTH_SHORT).show();
            }
        }).show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 12 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent picker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(picker,1);
        }

        if(requestCode == 11 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) { //Galeri
            final Uri imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                selectedImage = getResizedBitmap(selectedImage,134,134);
                picture.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if(requestCode == 2 && resultCode == RESULT_OK && data != null) { //Kamera
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            picture.setImageBitmap(bitmap);
        }
        else
            Toast.makeText(this, "Fotoğraf Alınamadı.", Toast.LENGTH_SHORT).show();

    }

    public void listInfoAct(View view) {
        Intent intent = new Intent(MainActivity.this,ListInfo.class);

        intent.putExtra("name",name.getText().toString());
        intent.putExtra("surname",surname.getText().toString());
        intent.putExtra("email",email.getText().toString());
        intent.putExtra("date",date.getText().toString());
        intent.putExtra("idNumber",idNumber.getText().toString());
        intent.putExtra("telNumber",telNumber.getText().toString());
        intent.putExtra("personAge",personAge);

        //Resim aktarma
        picture.setDrawingCacheEnabled(true);
        picture.buildDrawingCache();
        Bitmap image= picture.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        intent.putExtra("picture",byteArrayOutputStream.toByteArray());
        startActivity(intent);

    }

    public void clearInf(View view) {

        name.setText("");
        surname.setText("");
        email.setText("");
        date.setText("");
        idNumber.setText("");
        telNumber.setText("");
        picture.setImageDrawable(placeholder);

    }

    public void chooseDate(View view) {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month ++; //Ocak=0...Aralık=11 olduğu için
                date.setText(dayOfMonth + "/" + month + "/" + year);
                personAge = getAge(year,month,dayOfMonth);

            }
        }, year, month, day);

        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();

    }

    int getAge(int year, int month, int day){
        Calendar birthday = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        birthday.set(year, month, day);

        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH))
            age--;

        else if (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
                today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
