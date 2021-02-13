package controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nono.concesionariocoches.R;

import java.util.ArrayList;

import model.Vehiculo;

public class AdaptadorCoche extends RecyclerView.Adapter<AdaptadorCoche.HolderCoche>{

    private final ArrayList<Vehiculo> vehiculos;
    public static Context context;

    public AdaptadorCoche(ArrayList<Vehiculo> vehiculos, Context context) {

        this.vehiculos = vehiculos;
        this.context =  context;
    }

    @NonNull
    @Override
    public AdaptadorCoche.HolderCoche onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementcoche, parent, false );
        return new HolderCoche(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCoche.HolderCoche holder, int position) {


        holder.txtPrecio.setText( String.valueOf(vehiculos.get(position).getModelo().getPrecio()) + " €");
        holder.txtMarcayModelo.setText(String.valueOf(vehiculos.get(position).getMarca().getNombre_marca()
                + " - " + String.valueOf(vehiculos.get(position).getModelo().getNombre_modelo())));

        holder.txtPotencia.setText(String.valueOf(vehiculos.get(position).getModelo().getPotencia() + "kW"));
        holder.txtMatricula.setText(String.valueOf(vehiculos.get(position).getMatricula()));
        holder.txtColor.setText(String.valueOf(vehiculos.get(position).getColor().getNombre_color()));
        holder.btnAddCesta.setOnClickListener(v ->{
           
        });

    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }


    public void refrescar() {

        notifyDataSetChanged();
    }

    public class HolderCoche extends RecyclerView.ViewHolder {

        CardView cardCoche;
        TextView txtPrecio;
        TextView txtMarcayModelo;
        TextView txtMatricula;
        TextView txtColor;
        TextView txtPotencia;
        Button btnAddCesta;
        ImageView imgCoche;

        public HolderCoche(@NonNull View itemView) {
            super(itemView);

            cardCoche = itemView.findViewById(R.id.cardCoche);
            txtPrecio = itemView.findViewById(R.id.txtPrecioC);
            txtMarcayModelo = itemView.findViewById(R.id.txtMarcayModelo);
            txtMatricula = itemView.findViewById(R.id.txtMatricula);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtPotencia = itemView.findViewById(R.id.txtPotenciaC);
            imgCoche = itemView.findViewById(R.id.imgCoche);
            btnAddCesta = itemView.findViewById(R.id.btnAddCesta);

        }
    }


}
