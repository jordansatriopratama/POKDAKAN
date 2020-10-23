package jordansatriopratama.uas.com.datakelompok;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Input extends AppCompatActivity implements View.OnClickListener{

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextNama;
    private EditText editTextNamaKelompok;
    private EditText editTextUmur;
    private EditText editTextTelepon;
    private EditText editTextEmail;
    private EditText editTextJabatan;
    private EditText editTextAlamat;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //Inisialisasi dari View
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextNamaKelompok = (EditText) findViewById(R.id.editTextNamaKelompok);
        editTextUmur = (EditText) findViewById(R.id.editTextUmur);
        editTextTelepon = (EditText) findViewById(R.id.editTextTelepon);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextJabatan = (EditText) findViewById(R.id.editTextJabatan);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nama = editTextNama.getText().toString().trim();
        final String namakelompok = editTextNamaKelompok.getText().toString().trim();
        final String umur = editTextUmur.getText().toString().trim();
        final String telepon = editTextTelepon.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String jabatan = editTextJabatan.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Input.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Input.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA,nama);
                params.put(konfigurasi.KEY_EMP_NAMA_KELOMPOK,namakelompok);
                params.put(konfigurasi.KEY_EMP_UMUR,umur);
                params.put(konfigurasi.KEY_EMP_TELEPON,telepon);
                params.put(konfigurasi.KEY_EMP_EMAIL,email);
                params.put(konfigurasi.KEY_EMP_JABATAN,jabatan);
                params.put(konfigurasi.KEY_EMP_ALAMAT,alamat);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,SemuaPeternak.class));
        }
    }
}