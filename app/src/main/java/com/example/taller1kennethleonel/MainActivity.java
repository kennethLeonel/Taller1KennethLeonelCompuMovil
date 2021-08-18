package com.example.taller1kennethleonel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static  final  String ARCHIVO_PAISES ="countries.json";
    Spinner factoriales;
    Spinner listaPaisesSpinner;
    EditText fibo;
    int contadorFibo = 0;
    int contadorFact = 0;
    String fechaFact ;
    String fechaFibo ;
    JSONArray countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fibo = findViewById(R.id.editTextNumber);
        factoriales = findViewById(R.id.spinner);
        contadorFibo = getIntent().getIntExtra("contaFibo",0);
        contadorFact = getIntent().getIntExtra("contaFact",0);
        listaPaisesSpinner = findViewById(R.id.spinner2);
        fechaFibo= getIntent().getStringExtra("fechaFibonacci");
       if(contadorFibo > 0){
           Toast.makeText(getBaseContext(),
                   String.format("Contador Fibonacci es: %s \n \n Fecha de la última vez %s", contadorFibo,fechaFibo),
                   Toast.LENGTH_LONG).show();
       }
       fechaFact = getIntent().getStringExtra("fechaFactorial");
        if(contadorFact > 0){
            Toast.makeText(getBaseContext(),
                    String.format("Contador Factorial es: %s \n \n Fecha de la última vez %s", contadorFact,fechaFact),
                    Toast.LENGTH_LONG).show();
        }
        List<String> spinnerArray =  new ArrayList<String>();
        try {
            JSONObject jsonFile = loadCountriesByJson();
            countries = jsonFile.getJSONArray("Countries");
            for(int i=0; i< countries.length(); i++){
                spinnerArray.add(countries.getJSONObject(i).getString("Name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_gallery_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaPaisesSpinner.setAdapter(adapter);


    }


    public void botonFactorial(View view) {
        String numeroFac = (String) factoriales.getSelectedItem();
        Toast.makeText(getBaseContext(),
                String.format("Número factorial seleccionado es: %s", numeroFac),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,factorial.class);
        intent.putExtra("factorialSeleccionada",numeroFac);
        intent.putExtra("contaFact", contadorFact);
        startActivity(intent);
    }

    public void botonFibonacci(View view) {
        try {
            String numeroFibo = fibo.getText().toString();
            int num = Integer.parseInt(numeroFibo);
            if(num>0){
                Toast.makeText(getBaseContext(),
                        String.format("Número Fibonacci escrito es: %s", numeroFibo),
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,fibonacci.class);
                intent.putExtra("fiboSeleccionada",numeroFibo);
                intent.putExtra("contaFibo", contadorFibo);
                startActivity(intent);
            }else{
                Toast.makeText(getBaseContext(),
                        String.format("Ingrese un Número Valído"),
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(),
                    String.format("Ingrese un Número"),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void botonPaises(View view) {

        long pais =  listaPaisesSpinner.getSelectedItemId();
        Toast.makeText(getBaseContext(),
                String.format("El país seleccionado : %s", listaPaisesSpinner.getSelectedItem()),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(view.getContext(),pais.class);
        try {
            Bundle bundle = new Bundle();

            bundle.putString("codigo", countries.getJSONObject((int) pais).getString("Alpha3Code"));
            bundle.putString("nombreNativo", countries.getJSONObject((int) pais).getString("NativeName"));
            bundle.putString("continente", countries.getJSONObject((int) pais).getString("Region"));
            bundle.putString("bandera", countries.getJSONObject((int) pais).getString("FlagPng"));
            bundle.putString("idioma", countries.getJSONObject((int) pais).getString("NativeLanguage"));
            bundle.putString("nombreRecurrente", countries.getJSONObject((int) pais).getString("CurrencyName"));
            bundle.putString("nombre", String.valueOf(countries.getJSONObject(listaPaisesSpinner.getSelectedItemPosition()).getString("Name")));
            intent.putExtra("bundle",bundle);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }






        //intent.putExtra("paisSeleccionada",pais);

        /*
        listaPaisesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),
                        String.format("Posicion en la lista: %s", position),
                        Toast.LENGTH_LONG).show();
                Object item = parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), pais.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre", countries.getJSONObject(position).getString("Name"));
                    bundle.putString("codigo", countries.getJSONObject(position).getString("Alpha3Code"));
                    bundle.putString("nombreNativo", countries.getJSONObject(position).getString("NativeName"));
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

         */
    }

    public String loadJSONFromAsset(String assetName) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(assetName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public JSONObject loadCountriesByJson() throws JSONException {
        return new JSONObject(loadJSONFromAsset(ARCHIVO_PAISES));
    }




}