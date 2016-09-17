package dam.isi.frsf.edu.ar.lab01c2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import java.lang.Thread;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(),Actividad.class));
    }
}
