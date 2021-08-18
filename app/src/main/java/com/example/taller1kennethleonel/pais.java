package com.example.taller1kennethleonel;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class pais extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);


       TextView nombre = findViewById(R.id.textView6);
       TextView codigo = findViewById(R.id.textView7);
       TextView nombreNativo = findViewById(R.id.textView8);
       TextView reg = findViewById(R.id.textView9);
       TextView idi = findViewById(R.id.textidioma);
       TextView recurr = findViewById(R.id.textnombreRecurrente);
       ImageView ban = findViewById(R.id.imageView);



        Bundle bundle2 = getIntent().getBundleExtra("bundle");
        nombre.setText(bundle2.getString("nombre"));
        codigo.setText( "El Codigo es: "+ bundle2.getString("codigo"));
        nombreNativo.setText("El nombre nativos es: "+ bundle2.getString("nombreNativo"));
        reg.setText("El continente al que perteneces es: "+bundle2.getString("continente"));
        idi.setText("El idioma de´"+bundle2.getString("nombre") + " es: "+ bundle2.getString("idioma"));
        recurr.setText("Se le llama a "+bundle2.getString("nombre") + " recurrentemente: "+ bundle2.getString("nombreRecurrente"));
        Picasso. get ()
                .load (bundle2.getString("bandera"))
                .into(ban);
        //ban.setImageDrawable(Drawable.createFromPath(bundle2.getString("bandera")));

    }
}