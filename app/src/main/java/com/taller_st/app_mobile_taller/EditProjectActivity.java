package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.taller_st.app_mobile_taller.modelos.Category;
import com.taller_st.app_mobile_taller.modelos.Languaje;
import com.taller_st.app_mobile_taller.modelos.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditProjectActivity extends AppCompatActivity {

    private TextInputEditText txtNombreEdit, txtDescripcionEdit, txtRepoEdit;
    private Spinner spCatEdit,spLengEdit;
    private Button btnGuardarEdit, btnEliminar;
    private Project pro;

    private int lenguaje, categoria;


    private List<Languaje> lenguajes;

    public void obtenerLenguajes(){
        lenguajes = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MainActivity.baseUrl+"api/lenguajes";
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for( int i=0; i < response.length(); i++ ){
                    try {
                        Languaje lang = new Gson().fromJson(String.valueOf(response.getJSONObject(i)),Languaje.class);


                        lenguajes.add(lang);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                cargarSpinnerLen(lenguajes);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR:", error.toString());
            }
        });

        queue.add(jaRequest);

    }

    public void cargarSpinnerLen(List<Languaje> languajes){
        spLengEdit.setAdapter(new ArrayAdapter<Languaje>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,languajes));
        spLengEdit.setSelection(lenguaje - 1);
    }

    public void init_ui(){
        txtNombreEdit = findViewById(R.id.txtNombreEdit);
        txtDescripcionEdit = findViewById(R.id.txtDescripcionEdit);
        txtRepoEdit = findViewById(R.id.txtRepoEdit);
        spCatEdit = findViewById(R.id.spCatEdit);
        spLengEdit = findViewById(R.id.spLengEdit);
        btnGuardarEdit = findViewById(R.id.btnGuardarEdit);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProyecto();
            }
        });
    }

    public void eliminarProyecto(){

        String url = MainActivity.baseUrl+"api/proyectos/"+pro.getId();

        Log.i("url", url);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if( response.getString("msg").equals("ok") ){
                        Toast.makeText(getApplicationContext(), "Proyecto eliminado correctamente",Toast.LENGTH_LONG).show();
                        //Thread.sleep(1000);
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error",error.toString());
            }
        });
        queue.add(req);

    }

    private List<Category> categories;

    public void obtenerCategorias(){
        categories = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MainActivity.baseUrl+"api/categorias";
        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for( int i=0; i < response.length(); i++ ){
                    try {
                        Category cat = new Gson().fromJson(String.valueOf(response.getJSONObject(i)),Category.class);


                        categories.add(cat);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                cargarSpinnerCat(categories);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR:", error.toString());
            }
        });

        queue.add(jaRequest);

    }

    public void cargarSpinnerCat(List<Category> categories){
        spCatEdit.setAdapter(new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,categories));
        spCatEdit.setSelection(categoria - 1);
    }

    public void cargarDatos(){
        pro = (Project) getIntent().getExtras().get("proyecto");

        txtNombreEdit.setText(pro.getNombre());
        txtDescripcionEdit.setText(pro.getDescripcion());
        txtRepoEdit.setText(pro.getRepositorio());

        lenguaje = pro.getLenguaje_id();
        categoria = pro.getCategoria_id();


        obtenerLenguajes();
        obtenerCategorias();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        init_ui();
        cargarDatos();


    }
}