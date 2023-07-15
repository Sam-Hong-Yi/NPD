package sg.edu.rp.c346.id22024848.npd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import sg.edu.rp.c346.id22024848.npd.R;


public class MainActivity extends AppCompatActivity {
    EditText etSong, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShow;
    String newSong, newSingers;
    int newYear, newStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSong=findViewById(R.id.editTextSong);
        etSingers=findViewById(R.id.editTextSingers);
        etYear=findViewById(R.id.editTextYear);
        rgStars=findViewById(R.id.radioGroupStars);
        btnInsert=findViewById(R.id.buttonInsert);
        btnShow=findViewById(R.id.buttonShow);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                RadioButton selectedRadioButton;

                int selectedId = rgStars.getCheckedRadioButtonId();
                selectedRadioButton = (RadioButton)findViewById(selectedId);



                newSong=etSong.getText().toString();
                newSingers=etSingers.getText().toString();
                newYear=Integer.parseInt(etYear.getText().toString());
                newStar=Integer.parseInt(selectedRadioButton.getText().toString());


                // Insert a task
                db.insertSong(newSong, newSingers, newYear, newStar);

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(MainActivity.this, MainActivity2.class);

                startActivity(intent);

            }
        });
    }
}