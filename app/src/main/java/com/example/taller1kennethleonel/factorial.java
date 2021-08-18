package com.example.taller1kennethleonel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Arrays;

public class factorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        String numeroLlego =getIntent().getStringExtra("factorialSeleccionada");
        int num = Integer.parseInt(numeroLlego);

        TextView textoOperacion = findViewById(R.id.textView4);
        TextView textoResultado  = findViewById(R.id.textView5);
        int facto = 1;
        String[] proceso = new String[num];
        for( int i =1; i<=num;i++){
            facto = facto *i;

            if ( i == num){
                proceso[i-1] = String.valueOf(i);
            }
            else
            {
                proceso[i-1] = String.valueOf(i)+ "* ";
            }

        }
        textoResultado.setText("El resultado es: "+ facto);

        textoOperacion.setText("La operación: "+ Arrays.toString(proceso));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void atras(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        int recibido = getIntent().getIntExtra("contaFact",0);
        intent.putExtra("contaFact",recibido+1);
        LocalDateTime fechaIngreso= LocalDateTime.now();
        String cadena= "Fecha Ingreso:"+fechaIngreso.getDayOfMonth()+"/"+fechaIngreso.getMonthValue()+"/"+fechaIngreso.getYear()+"A las "+fechaIngreso.getHour()+" Horas Con "+fechaIngreso.getMinute()+" Minutos";
        intent.putExtra("fechaFactorial",cadena);
        startActivity(intent);

    }
}