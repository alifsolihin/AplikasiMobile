package com.example.mimpi_kita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AddEventOw2 extends AppCompatActivity {

    Button BTNsaveData;
    TextInputEditText ETbenefit1EV, ETbenefit2EV, EThargaEv, ETtiketEv;
    String nama,namaEv, tglrgEV, lokasiEV, jadwalEV, mulaiEV, akhirEV, ownerEV, deskripsiEV, benefit1Ev, benefit2Ev, hargaEv, tiketEv, jenisEv;
    ProgressDialog progressDialog;
    Spinner SPlvl;

    ImageButton BTNImageEv;
    String pilihan;
    //private static final int PHOTO_REQUEST_GALLERY = 1;//gallery
    static final int REQUEST_TAKE_PHOTO = 1;
    final int CODE_GALLERY_REQUEST = 999;
    String rPilihan[]= {"Take a photo","From Album"};
    public final String APP_TAG = "MyApp";
    Bitmap bitMap = null;
    public String photoFileName = "photo.jpg";
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_ow2);

        BTNsaveData = findViewById(R.id.BTNSdataEV);
        ETbenefit1EV = findViewById(R.id.ETBen1);
        ETbenefit2EV = findViewById(R.id.ETBen2);
        EThargaEv = findViewById(R.id.ETHargaTK);
        ETtiketEv = findViewById(R.id.ETJumlahTK);
        BTNImageEv    = findViewById(R.id.IBGambarEV);
        SPlvl = findViewById(R.id.idSPlv);


        progressDialog = new ProgressDialog(this);

        nama = getIntent().getStringExtra("nama");
        namaEv = getIntent().getStringExtra("nama_ev");
        tglrgEV = getIntent().getStringExtra("tanggal_ev");
        lokasiEV = getIntent().getStringExtra("lokasi_ev");
        jadwalEV = getIntent().getStringExtra("jadwal_ev");
        mulaiEV = getIntent().getStringExtra("mulai_ev");
        akhirEV = getIntent().getStringExtra("akhir_ev");
        ownerEV = getIntent().getStringExtra("owner_ev");
        deskripsiEV = getIntent().getStringExtra("deskripsi_ev");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDE2);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }

        });

        BTNImageEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitMap != null) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddEventOw2.this);
                    alertDialogBuilder.setMessage("Do yo want to take photo again?");

                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(context,"You clicked yes button",Toast.LENGTH_LONG).show();
                            //call fuction of TakePhoto
                            TakePhoto();
                        }
                    });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else {

                    TakePhoto();
                }


            }
        });

        BTNsaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Menambahkan Data...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                namaEv = getIntent().getStringExtra("nama_ev");
                tglrgEV = getIntent().getStringExtra("tanggal_ev");
                lokasiEV = getIntent().getStringExtra("lokasi_ev");
                jadwalEV = getIntent().getStringExtra("jadwal_ev");
                mulaiEV = getIntent().getStringExtra("mulai_ev");
                akhirEV = getIntent().getStringExtra("akhir_ev");
                ownerEV = getIntent().getStringExtra("owner_ev");
                deskripsiEV = getIntent().getStringExtra("deskripsi_ev");
                benefit1Ev     = ETbenefit1EV.getText().toString();
                benefit2Ev     = ETbenefit2EV.getText().toString();
                hargaEv        = EThargaEv.getText().toString();
                tiketEv        = ETtiketEv.getText().toString();
                jenisEv         = SPlvl.getSelectedItem().toString();

                if (bitMap == null){

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddEventOw2.this);
                    builder.setMessage("Please take a photo ");
                    AlertDialog alert1 = builder.create();
                    alert1.show();
                    progressDialog.dismiss();


                }
                else {

                    validasiData();

                }
            }
        });
    }
    void validasiData(){
        if(benefit1Ev.equals("")){
            progressDialog.dismiss();
            Toast.makeText(AddEventOw2.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }


    // taking image
    public  void TakePhoto(){
        // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddEventOw2.this);
        builder.setTitle("Silahkan Pilih ????");
        builder.setItems(rPilihan, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                pilihan = String.valueOf(rPilihan[which]);

                if (pilihan.equals("Take a photo"))
                {
                    // create Intent to take a picture and return control to the calling application
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Create a File reference to access to future access
                    photoFile = getPhotoFileUri(photoFileName);

                    // wrap File object into a content provider
                    // required for API >= 24
                    // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
                    String authorities = getPackageName() + ".fileprovider";
                    Uri fileProvider = FileProvider.getUriForFile(AddEventOw2.this, authorities, photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

                    // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                    // So as long as the result is not null, it's safe to use the intent.
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        // Start the image capture intent to take photo
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);

                    }
                }
                else
                {

                    ActivityCompat.requestPermissions(AddEventOw2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_GALLERY_REQUEST);

                }



            }
        });
        builder.show();


    }

    //permission
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALLERY_REQUEST);
            }else{
                Toast.makeText(getApplicationContext(), "You don't have permission to access gallery!", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        //set photo size
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                // by this point we have the camera photo on disk
                //Bitmap takenImage = BitmapFactory.decodeFile(String.valueOf(photoFile));
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                bitMap = decodeSampledBitmapFromFile(String.valueOf(photoFile), 1000, 700);
                BTNImageEv.setImageBitmap(bitMap);
            } else { // Result was a failure
                Toast.makeText(AddEventOw2.this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (intent != null) {
                Uri photoUri = intent.getData();
                // Do something with the photo based on Uri
                bitMap = null;
                try {
                    //InputStream inputStream = getContentResolver().openInputStream(photoUri);
                    //bitMap = BitmapFactory.decodeStream(inputStream);

                    //btnImage.setVisibility(View.VISIBLE);
                    bitMap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Load the selected image into a preview
                BTNImageEv.setImageBitmap(bitMap);
            }
        }
    }
    //get data photo
    public File getPhotoFileUri(String fileName)  {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use getExternalFilesDir on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }
            File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

            return file;

        }
        return null;
    }

    //set photo
    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }


    // get encode image to minimize image
    public String getStringImage(Bitmap bmp){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    void kirimData(){
        String photo = getStringImage(bitMap);
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/addDatates.php")
                .addBodyParameter("nama_ev",""+namaEv)
                .addBodyParameter("tanggal_ev",""+tglrgEV)
                .addBodyParameter("lokasi_ev",""+lokasiEV)
                .addBodyParameter("jadwal_ev",""+jadwalEV)
                .addBodyParameter("mulai_ev",""+mulaiEV)
                .addBodyParameter("akhir_ev",""+akhirEV)
                .addBodyParameter("owner_ev",""+ownerEV)
                .addBodyParameter("deskripsi_ev",""+deskripsiEV)
                .addBodyParameter("benefit1_ev",""+benefit1Ev)
                .addBodyParameter("benefit2_ev",""+benefit2Ev)
                .addBodyParameter("harga_ev",""+hargaEv)
                .addBodyParameter("tiket_ev",""+tiketEv)
                .addBodyParameter("jenis_ev",""+jenisEv)
                .addBodyParameter("gambar_ev",""+photo)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(AddEventOw2.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(AddEventOw2.this)
                                        .setMessage("Berhasil Menambahkan Data !")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent page;
                                                page = new Intent(AddEventOw2.this,PengolahanEventOwner.class);
                                                startActivity(page);
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(AddEventOw2.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //Intent i = getIntent();
                                                //setResult(RESULT_CANCELED,i);
                                                //add_mahasiswa.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }
}