package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;

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

public class NewProjectActivity extends AppCompatActivity {

    private Button btnGuardar;
    private TextInputEditText txtNombre, txtDescripcion, txtRepo;

    public void enviarProyecto(Project proyecto) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String jsonPro = new Gson().toJson(proyecto);
        String url = "http://192.168.1.119:4000/api/proyectos";
        JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonPro), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),new Gson().fromJson(String.valueOf(response),Project.class).getNombre() +" se registro correctamente", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
            }
        });
        queue.add(json);
    }

    private Spinner spCategegorias;
    private List<Category> categories;

    public void obtenerCategorias(){
        categories = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.119:4000/api/categorias";
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
        spCategegorias.setAdapter(new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,categories));
    }

    private Spinner spLenguajes;
    private List<Languaje> lenguajes;

    public void obtenerLenguajes(){
        lenguajes = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.119:4000/api/lenguajes";
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
        spLenguajes.setAdapter(new ArrayAdapter<Languaje>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,languajes));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        txtRepo = findViewById(R.id.txtRepoEdit);
        txtDescripcion = findViewById(R.id.txtDescripcionEdit);
        txtNombre = findViewById(R.id.txtNombreEdit);
        btnGuardar = findViewById(R.id.btnGuardarUsu);
        spCategegorias = findViewById(R.id.spCatEdit);
        spLenguajes = findViewById(R.id.spLengEdit);
        obtenerCategorias();
        obtenerLenguajes();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project = new Project();
                project.setNombre(txtNombre.getText().toString());
                project.setDescripcion(txtDescripcion.getText().toString());
                project.setCategoria_id( ((Category) spCategegorias.getSelectedItem()).getId());
                project.setLenguaje_id(( (Languaje) spLenguajes.getSelectedItem() ).getId());
                project.setUsuario_id(MainActivity.usuario.getId());
                try {
                    enviarProyecto(project);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}