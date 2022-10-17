package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.taller_st.app_mobile_taller.adaptadores.ProjectAdapter;
import com.taller_st.app_mobile_taller.modelos.Project;

import org.json.JSONObject;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        Intent i =  getIntent();
        List<Project> lista = (List<Project>) i.getExtras().get("lista");

        Log.i("lista",lista.toString());

        ProjectAdapter adapter = new ProjectAdapter(lista);
        recyclerView.setAdapter(adapter);

    }
}