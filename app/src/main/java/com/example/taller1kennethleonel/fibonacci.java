package com.example.taller1kennethleonel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.Arrays;

public class fibonacci extends AppCompatActivity {

    ImageButton fibona ;
    TextView resul;
    private final static String urlFobina="https://es.wikipedia.org/wiki/Leonardo_de_Pisa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        String numeroLlego =getIntent().getStringExtra("fiboSeleccionada");
        fibona = findViewById(R.id.imageButton);
        resul =findViewById(R.id.textView10);
        int num = Integer.parseInt(numeroLlego);
        resul.setText(fibonacci(num));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void atras(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        int recibido = getIntent().getIntExtra("contaFibo",0);
        intent.putExtra("contaFibo",recibido+1);
        LocalDateTime fechaIngreso= LocalDateTime.now();
        String cadena= "Fecha Ingreso:"+fechaIngreso.getDayOfMonth()+"/"+fechaIngreso.getMonthValue()+"/"+fechaIngreso.getYear()+"A las "+fechaIngreso.getHour()+" Horas Con "+fechaIngreso.getMinute()+" Minutos";
        intent.putExtra("fechaFibonacci",cadena);
        startActivity(intent);
    }
    public void irABiografía(View view) {
    Intent  intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFobina));
        startActivity(intent);
    }
    static String fibonacci(int numero)
    {
        String proceso="";
        int fibo1,fibo2,i;
        fibo1=1;
        fibo2=1;
        proceso+=Integer.toString(fibo1)+"\n";

        for(i=2;i<=numero;i++){
            proceso+=Integer.toString(fibo2)+"\n";
            fibo2 = fibo1 + fibo2;
            fibo1 = fibo2 - fibo1;
        }
        return proceso;
    }
}