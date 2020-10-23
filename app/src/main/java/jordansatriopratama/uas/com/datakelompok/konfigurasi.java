package jordansatriopratama.uas.com.datakelompok;

public class konfigurasi {

    //JANGAN LUPA SESUAIKAN IP KOMPUTER DIMANA DATA PHP HTDOCS ANDROIDS BERADA
    public static final String URL_ADD = "http://192.168.43.72/androids/tambah.php";
    public static final String URL_GET_ALL = "http://192.168.43.72/androids/tampilSemua.php";
    public static final String URL_GET_EMP = "http://192.168.43.72/androids/tampil.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.72/androids/update.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.72/androids/hapus.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Scrip PHP
    public static final String KEY_EMP_ID = "id"; //id itu variabel untuk id
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_NAMA_KELOMPOK = "nama_kelompok";
    public static final String KEY_EMP_UMUR = "umur";
    public static final String KEY_EMP_TELEPON = "telepon";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_JABATAN = "jabatan";
    public static final String KEY_EMP_ALAMAT = "alamat";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_NAMA_KELOMPOK = "nama_kelompok";
    public static final String TAG_UMUR = "umur";
    public static final String TAG_TELEPON = "telepom";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_JABATAN = "jabatan";
    public static final String TAG_ALAMAT = "alamat";

    //ID karyawan
    public static final String EMP_ID = "emp_id"; //emp itu singkatan dari Employee
}