package dam.isi.frsf.edu.ar.lab01c2016;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import java.lang.*;


public class Actividad extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private EditText correo;
    private EditText cuit;
    private TextView rendimiento;
    private SeekBar seekbar;
    private TextView mensaje;
    private EditText importe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
        Button btnPlazoFijo = (Button) findViewById(R.id.button);
        btnPlazoFijo.setOnClickListener(this);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);
        correo = (EditText) findViewById(R.id.editText);
        cuit = (EditText) findViewById(R.id.editText2);


    }
    private double calcularInteres(int inversion, int progreso ){
         int tasa = calcularTasaInteres(inversion, progreso);
        String tasaS = this.getString(tasa);
        double tasaA = Double.valueOf(tasaS).doubleValue();
        double hola = Math.pow((1 + (tasaA/100.00000000000000)),(progreso/360.0000000000));
         double interes = inversion * (hola - 1);
         return interes;
    }

    private int calcularTasaInteres(int inversion, int progreso){
          if (progreso < 30) {
              if (inversion > 0 && inversion <= 5000) {
                  return R.string.tasa1;
              }
              if (inversion > 5000 && inversion <= 99999) {
                  return R.string.tasa3;
              }
              if (inversion > 99999) {
                  return R.string.tasa5;
              }
          }
          if (progreso >= 30) {
              if (inversion > 0 && inversion <= 5000) {
                return R.string.tasa2;
                }
                if (inversion > 5000 && inversion <= 99999) {
                return R.string.tasa4;
                }
                if (inversion > 99999) {
                return R.string.tasa6;
                }
        }
        return 0;
    }


    @Override
    public void onClick(View aView){
        mensaje = (TextView) findViewById(R.id.textView9);
        if(aView.getId()==R.id.button){
             if( !TextUtils.isEmpty(correo.getText()) && !TextUtils.isEmpty(cuit.getText()) &&
                     !TextUtils.isEmpty(importe.getText()) && !TextUtils.isEmpty(rendimiento.getText()) )
             {
                mensaje.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.correcto));
                mensaje.setText("Plazo Fijo Realizado, recibirá "+rendimiento.getText()+" al vencimiento.");
             }
            else {
                 mensaje.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                 mensaje.setText("No se pudo realizar el plazo fijo");
             }

        }
    }

    @Override
    public void onProgressChanged (SeekBar seekbar, int number, boolean b){
        int progreso =  seekbar.getProgress();
        importe = (EditText) findViewById(R.id.editText3);
        TextView dias = (TextView) findViewById(R.id.textView6);
        dias.setText(".."+progreso);
        if (!importe.getText().toString().isEmpty()){
         rendimiento = (TextView) findViewById(R.id.textView8);
         int inversion = Integer.parseInt(importe.getText().toString());
         double rend = calcularInteres(inversion,progreso);
         String rendim = String.format("%.2f",rend);
         rendimiento.setText("$"+rendim);
            }
    }

    public void onStopTrackingTouch(SeekBar seekbar){

    }

    public void onStartTrackingTouch(SeekBar seekbar){

    }
}
