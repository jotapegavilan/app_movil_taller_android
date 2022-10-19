package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.taller_st.app_mobile_taller.modelos.Project;
import com.taller_st.app_mobile_taller.modelos.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private TextInputEditText txtEmailLogin, txtPasswordLogin;
    public static User usuario = null;
    public static final String baseUrl = "http://192.168.38.65:4000/";
    public static final String baseUrl1 = "http://192.168.1.119:4000/";

    public void init_ui(){
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);


    }

    public void next_activity(View view){
        //startActivity(new Intent(this,HomeActivity.class).putExtra("lista", (Serializable) list));
        startActivity(new Intent(this,NewProjectActivity.class));

    }

    public void next_activity2(View view){
        startActivity(new Intent(this,HomeActivity.class).putExtra("lista", (Serializable) list));

    }

    public void logIn(View view){
        pDialog = ProgressDialog.show(MainActivity.this,"Inicio de sesión","Iniciando sesión ..",true);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = baseUrl+"api/sign_in";


        JsonObjectRequest joRequest = null;
        try {
            joRequest = new JsonObjectRequest(Request.Method.POST, url,
                    new JSONObject().put("email",txtEmailLogin.getText().toString()).put("clave", txtPasswordLogin.getText().toString())
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if(response.getString("msg").equals("ok")){
                            pDialog.dismiss();
                            Thread.sleep(1000);
                            usuario = new Gson().fromJson(String.valueOf(response.getJSONObject("user")),User.class);
                            Log.i("user",usuario.toString());
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                        }else
                        {
                            Log.e("msg",response.toString());
                            Toast.makeText(getApplicationContext(),response.getString("msg").toString(),Toast.LENGTH_LONG).show();
                            pDialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR:", error.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(joRequest);
    }

    private List<Project> list = new ArrayList<>();

    public boolean insertProject(Project project) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.119:4000/api/proyectos";

        String jsonInString = new Gson().toJson(project);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonInString),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Exito",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR",error.toString());
            }
        });
        queue.add(request);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_ui();

        Project p = new Project();
        p.setNombre("TEST 2");
        p.setDescripcion("TEST DE INSERCIÓN 2");
        p.setUsuario_id(1);
        p.setCategoria_id(2);
        // insertProject(p);

        usuario = new User();
        usuario.setId(2);




    }
}