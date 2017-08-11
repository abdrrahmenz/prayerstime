package id.or.qodr.prayerstime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import id.or.qodr.prayerstime.api.ApiClient;
import id.or.qodr.prayerstime.api.ApiInterface;
import id.or.qodr.prayerstime.data.Datum;
import id.or.qodr.prayerstime.data.PrayersModel;
import id.or.qodr.prayerstime.helper.PrayersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "b470df72d2ba5e34a75d8e98728fb129";
    private EditText text;
    RadioGroup status, pilihan;
    EditText idpel, nama;
    Button date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        date = (Button) findViewById(R.id.button2);
        time = (Button) findViewById(R.id.button3);
        idpel = (EditText) findViewById(R.id.id);
        nama = (EditText) findViewById(R.id.nama);
        pilihan=(RadioGroup) findViewById(R.id.brang);
        status=(RadioGroup)findViewById(R.id.radiogroup2);
//        if (API_KEY.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//
//        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_list);
//        recyclerView.setLayoutManager(layoutManager);
//
//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<PrayersModel> call = apiService.getDataPrayers(API_KEY, 259,6,2017);
//        call.enqueue(new Callback<PrayersModel>() {
//            @Override
//            public void onResponse(Call<PrayersModel> call, Response<PrayersModel> response) {
//                List<Datum> prayers = response.body().getData();
//                recyclerView.setAdapter(new PrayersAdapter(prayers, R.layout.items_list, getApplicationContext()));
//            }
//
//            @Override
//            public void onFailure(Call<PrayersModel> call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//            }
//        });

        text = (EditText) findViewById(R.id.inputValue);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button1:
                RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
                RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());
                if (celsiusButton.isChecked()) {
                    text.setText(String
                            .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);
                } else {
                    text.setText(String
                            .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                }
                break;
            case R.id.button2:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.button3:
                // Get Current Time
                final Calendar ctime = Calendar.getInstance();
                int mHour = ctime.get(Calendar.HOUR_OF_DAY);
                int mMinute = ctime.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
            case R.id.button4:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.view_change_password, null);
                final EditText input_fulname = (EditText) dialogView.findViewById(R.id.edt_fulname_form);
                final EditText input_name = (EditText) dialogView.findViewById(R.id.edt_user_form);
                final EditText input_pass = (EditText) dialogView.findViewById(R.id.edt_pass_form);
                final EditText input_cfrpass = (EditText) dialogView.findViewById(R.id.edt_cfrpass_form);

                builder.setTitle("Change Data Account");

                builder.setView(dialogView);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fulnamUpdate = input_fulname.getText().toString();
                        String namUpdate = input_name.getText().toString();
                        String passUpdate = input_pass.getText().toString();
                        String cfrpassUpdate = input_cfrpass.getText().toString();


                    }
                });
                builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setCancelable(false);
                builder.show();
            case R.id.hitung:
                String id = idpel.getText().toString();
                String nm = nama.getText().toString();
                if(TextUtils.isEmpty(id)) {
                    idpel.setError("ID Pelanggan tidak boleh kosong !");
                    return;
                }

                if(TextUtils.isEmpty(nm)) {
                    nama.setError("Nama Pelanggan tidak boleh kosong !");
                    return;
                }

                int selectedId = pilihan.getCheckedRadioButtonId();
                int  harga=0;
                if (selectedId==R.id.laptop){
                    harga=5000000;
                }
                else if (selectedId==R.id.pc){
                    harga=10000000;
                }
                else if (selectedId==R.id.ssd) {
                    harga=950000;
                }

                int selectedId2 = status.getCheckedRadioButtonId();
                RadioButton statusnya = (RadioButton) findViewById(selectedId2);
                int diskon=0;
                if (selectedId2==R.id.status1){
                    diskon=50*harga/100;
                }
                else if (selectedId2==R.id.status2){
                    diskon=200000;
                }

                int total = harga-diskon;


                Intent i = null;
                i = new Intent(this, Output.class);
                Bundle b = new Bundle();
                b.putString("ambil_idpel", idpel.getText().toString());
                b.putString("ambil_nama", nama.getText().toString());
                b.putString("ambil_status", statusnya.getText().toString());
                b.putString("ambil_total", String.valueOf(total));
                i.putExtras(b);
                startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
