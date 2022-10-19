package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.taller_st.app_mobile_taller.adaptadores.ProjectAdapter;
import com.taller_st.app_mobile_taller.modelos.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private FloatingActionButton fbtAgregar;
    private TextView txtPresentacion;
    private Button btnPerfil;
    private RecyclerView recyclerView;
    private List<Project> proyectos;

    public void cargarRecycler(int user){
        proyectos = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = MainActivity.baseUrl+"api/proyectos/"+user;

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for( int i=0; i < response.length(); i++ ){
                    try {
                        Project pro = new Gson().fromJson(String.valueOf(response.getJSONObject(i)),Project.class);
                        proyectos.add(pro);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ProjectAdapter adapter = new ProjectAdapter(proyectos);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR:", error.toString());
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer "+MainActivity.usuario.getToken());
                return params;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        queue.add(jaRequest);
    }

    public void init_ui(){
        recyclerView = findViewById(R.id.recyclerView);
        txtPresentacion = findViewById(R.id.txtPresentacion);
        btnPerfil = findViewById(R.id.btnPerfil);
        fbtAgregar = findViewById(R.id.fbtnAgregar);
        txtPresentacion.setText(MainActivity.usuario.getNombres() + " " +MainActivity.usuario.getApellidos() +"\n"+MainActivity.usuario.getEmail()+" - "+MainActivity.usuario.getRol());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init_ui();

        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        cargarRecycler(MainActivity.usuario.getId());

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShowProfileActivity.class));
            }
        });

        fbtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NewProjectActivity.class));
            }
        });

    }
}