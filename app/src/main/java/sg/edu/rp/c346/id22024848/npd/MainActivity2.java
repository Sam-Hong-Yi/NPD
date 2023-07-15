package sg.edu.rp.c346.id22024848.npd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.rp.c346.id22024848.npd.R;

public class MainActivity2 extends AppCompatActivity {
    ListView lw;
    Button btn;
    ArrayAdapter aaSong;
    ArrayList<Song> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





    }
    @Override
    protected void onResume() {
        super.onResume();
        lw=findViewById(R.id.listView);
        btn=findViewById(R.id.buttonFive);

        Intent intentReceived=getIntent();
        DBHelper db = new DBHelper(MainActivity2.this);
        data=db.getSong();
        db.close();
        aaSong=new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, data);
        lw.setAdapter(aaSong);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
                Song clicked=data.get(position);
                SharedPreferences prefs=getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("id", clicked.getId());
                prefEdit.putString("title", clicked.getTitle());
                prefEdit.putString("singers", clicked.getSingers());
                prefEdit.putInt("year", clicked.getYear());
                prefEdit.putInt("stars", clicked.getStars());
                prefEdit.commit();
                Intent intent= new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("data", clicked);

                startActivity(intent);





            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                data.clear();

                int filterText = 5;

                data.addAll(dbh.getSong(filterText));



                aaSong.notifyDataSetChanged();
            }
        });


    }


}