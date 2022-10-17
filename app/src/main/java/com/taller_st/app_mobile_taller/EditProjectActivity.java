package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class EditProjectActivity extends AppCompatActivity {

    private TextInputEditText txtNombreEdit, txtDescripcionEdit, txtRepoEdit;
    private Spinner spCatEdit,spLengEdit;
    private Button btnGuardarEdit, btnEliminar;

    public void init_ui(){
        txtNombreEdit = findViewById(R.id.txtNombreEdit);
        txtDescripcionEdit = findViewById(R.id.txtDescripcionEdit);
        txtRepoEdit = findViewById(R.id.txtRepoEdit);
        spCatEdit = findViewById(R.id.spCatEdit);
        spLengEdit = findViewById(R.id.spLengEdit);
        btnGuardarEdit = findViewById(R.id.btnGuardarEdit);
        btnEliminar = findViewById(R.id.btnEliminar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        init_ui();
    }
}