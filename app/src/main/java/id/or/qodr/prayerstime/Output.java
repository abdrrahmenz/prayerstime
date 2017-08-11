package id.or.qodr.prayerstime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class Output extends AppCompatActivity {

    TextView idpel,nama,status,total;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        b = getIntent().getExtras();
        idpel = (TextView) findViewById(R.id.text1);
        nama = (TextView) findViewById(R.id.text2);
        status = (TextView) findViewById(R.id.text3);
        total = (TextView) findViewById(R.id.text4);
        //menset nilai dari widget textview
        idpel.setText("ID : "+b.getCharSequence("ambil_idpel"));
        nama.setText("Nama : "+b.getCharSequence("ambil_nama"));
        status.setText("Status : "+b.getCharSequence("ambil_status"));
        total.setText("Total Belanja : "+b.getCharSequence("ambil_total"));
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "ID : "+b.getCharSequence("ambil_idpel") +"\n"
                + "Nama : "+b.getCharSequence("ambil_nama") +"\n" + "Status : "+b.getCharSequence("ambil_status")+"\n"
                        +"Total Belanja : "+b.getCharSequence("ambil_total"));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

        }
    }
}
