package com.taller_st.app_mobile_taller.adaptadores;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.taller_st.app_mobile_taller.EditProjectActivity;
import com.taller_st.app_mobile_taller.R;
import com.taller_st.app_mobile_taller.modelos.Category;
import com.taller_st.app_mobile_taller.modelos.Project;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.viewHolder> {

    private List<Project> projectList;

    public  ProjectAdapter( List<Project> list ){
        this.projectList = list;
    }

    @NonNull
    @Override
    public ProjectAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View project_element = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_element,parent,false);
        return new ProjectAdapter.viewHolder(
                project_element
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.viewHolder holder, int position) {
        try {
            Project pro = projectList.get(position);
            holder.cargarDatos(pro);
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),
                            EditProjectActivity.class).putExtra("proyecto",pro));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.projectList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView txt_nombre, txt_descripcion, txt_desarrollador;
        ImageView img_lenguaje;
        View card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView;
            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion);
            txt_desarrollador = itemView.findViewById(R.id.txt_desarrollador);
            img_lenguaje = itemView.findViewById(R.id.img_lenguaje);
        }

        public void cargarDatos(Project json) throws JSONException {
            int color = 0;
            int colorText = 0;

            switch (json.getLenguaje().getNombre()){
                case "Java":
                    img_lenguaje.setImageResource(R.drawable.java);
                    break;
                case "JS":
                    img_lenguaje.setImageResource(R.drawable.js);
                    break;
                case "PHP":
                    img_lenguaje.setImageResource(R.drawable.php);
                    break;
                case "Python":
                    img_lenguaje.setImageResource(R.drawable.python);
                    break;
                case "C++":
                    img_lenguaje.setImageResource(R.drawable.cpp);
                    break;
                case "C#":
                    img_lenguaje.setImageResource(R.drawable.cs);
                    break;
            }

            if(json.getCategoria().getColor().equalsIgnoreCase("rojo")){
                color = Color.RED;
                colorText = Color.WHITE;

            }
            if(json.getCategoria().getColor().equalsIgnoreCase("verde")){
                color = Color.GREEN;
                colorText = Color.BLACK;
            }
            if(json.getCategoria().getColor().equalsIgnoreCase("azul")){
                color = Color.BLUE;
                colorText = Color.WHITE;
            }
            if(json.getCategoria().getColor().equalsIgnoreCase("naranja")){
                color = Color.YELLOW;
                colorText = Color.BLACK;
            }
            if(json.getCategoria().getColor().equalsIgnoreCase("morado")){
                color = Color.CYAN;
                colorText = Color.BLACK;
            }
            CardView card = this.card.findViewById(R.id.cardView);
            card.setCardBackgroundColor(color);
            txt_nombre.setTextColor(colorText);
            txt_descripcion.setTextColor(colorText);
            txt_desarrollador.setTextColor(colorText);
            this.txt_nombre.setText(json.getNombre().toUpperCase());
            this.txt_descripcion.setText(json.getDescripcion());
            this.txt_desarrollador.setText(json.getUsuario().getNombres()+ " " +json.getUsuario().getApellidos() );
        }
    }
}
