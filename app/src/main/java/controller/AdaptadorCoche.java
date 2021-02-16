package controller;


import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nono.concesionariocoches.R;

import java.io.IOException;
import java.util.ArrayList;
import logic.PeticionHTTP;
import logic.VariablesGlobales;
import model.Vehiculo;

public class AdaptadorCoche extends RecyclerView.Adapter<AdaptadorCoche.HolderCoche>{

    private static ArrayList<Vehiculo> vehiculos;
    public static Context context;
    public static String nameClass;



    public AdaptadorCoche(ArrayList<Vehiculo> vehiculos, Context context,String nameClass) {

        this.vehiculos = vehiculos;
        this.context =  context;
        this.nameClass = nameClass;
    }


    @NonNull
    @Override
    public AdaptadorCoche.HolderCoche onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementcoche, parent, false );
        return new HolderCoche(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCoche.HolderCoche holder, int position) {
        // Imagen
        String url= VariablesGlobales.url+"/images/"+vehiculos.get(position).getFileData()+".jpg";
        Glide
                .with(AdaptadorCoche.context)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imgCoche);

        holder.txtPrecio.setText( String.valueOf(vehiculos.get(position).getModelo().getPrecio()) + " €");
        holder.txtMarcayModelo.setText(String.valueOf(vehiculos.get(position).getMarca().getNombre_marca()
                + " - " + String.valueOf(vehiculos.get(position).getModelo().getNombre_modelo())));

        holder.txtPotencia.setText(String.valueOf(vehiculos.get(position).getModelo().getPotencia() + "kW"));
        holder.txtMatricula.setText(String.valueOf(vehiculos.get(position).getMatricula()));
        holder.txtColor.setText(String.valueOf(vehiculos.get(position).getColor().getNombre_color()));
        holder.btnAddCesta.setOnClickListener(v ->{

            if(this.nameClass.equals("Catalogo")){

                añadirCarrito(position,String.valueOf(vehiculos.get(position).getId_vehiculo()));

            }else{

                borrarCarrito(position,String.valueOf(vehiculos.get(position).getId_vehiculo()),holder);


            }
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

            if(AdaptadorCoche.nameClass.equals("Catalogo")){


            }else{

                btnAddCesta.setBackgroundColor(context.getColor(R.color.btnBorrar));
                btnAddCesta.setText(R.string.btnBorrardeCesta);

            }

        }
    }



    // Añadir carrito

    public static void añadirCarrito(int position,String id_vehiculo){
        String id_user = logic.MainLogic.leerPreferenciasUsuario(AdaptadorCoche.context);
        new addCarrito_AsyncTask(position).execute(VariablesGlobales.url + "/api/newCompraVehiculo/"+id_vehiculo+"/"+id_user);
    }
    private static class addCarrito_AsyncTask extends AsyncTask<String, Void, String> {
        private int position;
        public addCarrito_AsyncTask(int position) {
            this.position = position;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTPGET(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){
            if(result.equals("OK")){

                AdaptadorCoche.vehiculos.remove(position);

                if(AdaptadorCoche.vehiculos.size() == 0){
                    CatalogoVehiculosActivity.txtmsgError.setVisibility(View.VISIBLE);

                }else{
                    CatalogoVehiculosActivity.txtmsgError.setVisibility(View.INVISIBLE);
                }

                AdaptadorCoche adaptador = new AdaptadorCoche((ArrayList<Vehiculo>)  AdaptadorCoche.vehiculos,context,"Catalogo");
                CatalogoVehiculosActivity.listCoches.setAdapter(adaptador);
                adaptador.refrescar();



            }else{
                Toast.makeText(LoginActivity.context, result, Toast.LENGTH_LONG).show();
            }

        }

    }



    // GET VEHICULO
    public static void borrarCarrito(int position,String id_vehiculo,AdaptadorCoche.HolderCoche holder){

        new deteleCarritoByIdVehiculo_AsyncTask(position,holder).execute(VariablesGlobales.url + "/api/compraVehiculo/deleteByIdVehiculo/"+ id_vehiculo);
    }
    private static class deteleCarritoByIdVehiculo_AsyncTask extends AsyncTask<String, Void, String> {

        private AdaptadorCoche.HolderCoche holder;
        private int position;

        public deteleCarritoByIdVehiculo_AsyncTask(int position, HolderCoche holder) {

            this.holder = holder;
            this.position = position;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // CARGANDO...

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTPGET(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){

            if(result.equals("OK")){

                AdaptadorCoche.vehiculos.remove(position);
                if(AdaptadorCoche.vehiculos.size() == 0){
                    cestaActivity.txtmsgCesta.setVisibility(View.VISIBLE);
                }else{
                    cestaActivity.txtmsgCesta.setVisibility(View.INVISIBLE);
                }

                AdaptadorCoche adaptador = new AdaptadorCoche((ArrayList<Vehiculo>)  AdaptadorCoche.vehiculos,context,"Cesta");
                cestaActivity.listCesta.setAdapter(adaptador);
                adaptador.refrescar();


            }else{
                Toast.makeText(LoginActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }

        }

    }

}
