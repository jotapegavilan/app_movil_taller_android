package com.taller_st.app_mobile_taller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ShowProfileActivity extends AppCompatActivity {

    private TextInputEditText txtNombresEdit, txtApellidosEdit, txtActualPassword, txtNewPassword;
    private Button btnGuardarUsu;

    public void init_ui(){
        txtNombresEdit = findViewById(R.id.txtNombresEdit);
        txtApellidosEdit = findViewById(R.id.txtApellidosEdit);
        txtActualPassword = findViewById(R.id.txtActualPassword);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        btnGuardarUsu = findViewById(R.id.btnGuardarUsu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        init_ui();

    }


}