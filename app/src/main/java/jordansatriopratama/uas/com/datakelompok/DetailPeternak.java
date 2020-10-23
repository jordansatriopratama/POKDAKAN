package jordansatriopratama.uas.com.datakelompok;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailPeternak extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextNama;
    private EditText editTextNamaKelompok;
    private EditText editTextUmur;
    private EditText editTextTelepon;
    private EditText editTextEmail;
    private EditText editTextJabatan;
    private EditText editTextAlamat;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peternak);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextNamaKelompok = (EditText) findViewById(R.id.editTextNamaKelompok);
        editTextUmur = (EditText) findViewById(R.id.editTextUmur);
        editTextTelepon = (EditText) findViewById(R.id.editTextTelepon);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextJabatan = (EditText) findViewById(R.id.editTextJabatan);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPeternak.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String nama_kelompok = c.getString(konfigurasi.TAG_NAMA_KELOMPOK);
            String umur = c.getString(konfigurasi.TAG_UMUR);
            String telepon = c.getString(konfigurasi.TAG_TELEPON);
            String email = c.getString(konfigurasi.TAG_EMAIL);
            String jabatan = c.getString(konfigurasi.TAG_JABATAN);
            String alamat = c.getString(konfigurasi.TAG_ALAMAT);

            editTextNama.setText(nama);
            editTextNamaKelompok.setText(nama_kelompok);
            editTextUmur.setText(umur);
            editTextTelepon.setText(telepon);
            editTextEmail.setText(email);
            editTextJabatan.setText(jabatan);
            editTextAlamat.setText(alamat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String nama = editTextNama.getText().toString().trim();
        final String nama_kelompok = editTextNamaKelompok.getText().toString().trim();
        final String umur = editTextUmur.getText().toString().trim();
        final String telepon = editTextTelepon.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String jabatan = editTextJabatan.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPeternak.this,"Updating...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailPeternak.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(konfigurasi.KEY_EMP_NAMA_KELOMPOK,nama_kelompok);
                hashMap.put(konfigurasi.KEY_EMP_UMUR,umur);
                hashMap.put(konfigurasi.KEY_EMP_TELEPON,telepon);
                hashMap.put(konfigurasi.KEY_EMP_EMAIL,email);
                hashMap.put(konfigurasi.KEY_EMP_JABATAN,jabatan);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT,alamat);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPeternak.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailPeternak.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Peternak ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(DetailPeternak.this,SemuaPeternak.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}

