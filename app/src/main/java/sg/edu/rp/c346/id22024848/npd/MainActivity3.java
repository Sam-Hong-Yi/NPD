package sg.edu.rp.c346.id22024848.npd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity3 extends AppCompatActivity {
    EditText etID, etSong, etSingers, etYear;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intentReceived=getIntent();
        Song data=(Song) intentReceived.getSerializableExtra("data");
        etID=findViewById(R.id.editTextID);

        etSong=findViewById(R.id.editTextSong);
        etSingers=findViewById(R.id.editTextSingers);
        etYear=findViewById(R.id.editTextYear);
        rgStars=findViewById(R.id.radioGroupStars);
        btnUpdate=findViewById(R.id.buttonUpdate);
        btnDelete=findViewById(R.id.buttonDelete);
        btnCancel=findViewById(R.id.buttonCancel);

        SharedPreferences prefs=getSharedPreferences("data", MODE_PRIVATE);
        int id=prefs.getInt("id", 0);
        String title=prefs.getString("title", "errorTitle");
        String singers=prefs.getString("singers", "errorSingers");
        int year=prefs.getInt("year", 0);
        int stars=prefs.getInt("stars", 0);

        etID.setText(Integer.toString(id));
        etSong.setText(title);
        etSingers.setText(singers);
        etYear.setText(Integer.toString(year));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgStars.getCheckedRadioButtonId();
                RadioButton selectedRadioButton;
                selectedRadioButton = (RadioButton)findViewById(selectedId);
                int newStars=Integer.parseInt(selectedRadioButton.getText().toString());

                String newSong=etSong.getText().toString();
                String newSinger=etSingers.getText().toString();
                int newYear=Integer.parseInt(etYear.getText().toString());




                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setSong(newSong, newSinger, newYear, newStars);
                dbh.updateSong(data);
                dbh.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteSong(data.getId());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);

            }
        });

    }

}