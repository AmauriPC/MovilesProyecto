package mx.aepc.proyectoparcial1moviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterResultados extends RecyclerView.Adapter<AdapterResultados.ViewHolderResultados> {

    ArrayList<ResultadosVo> listaResultados;

    public AdapterResultados(ArrayList<ResultadosVo> listaResultados) {
        this.listaResultados = listaResultados;
    }


    public static class ViewHolderResultados extends RecyclerView.ViewHolder {

        TextView etiNombre, etiEdad;
        ImageView foto;

        public ViewHolderResultados(View view) {
            super(view);
            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiEdad = (TextView) itemView.findViewById(R.id.idEdad);
            foto = (ImageView) itemView.findViewById(R.id.idImagenSP);
        }
    }

    @NonNull
    @Override
    public AdapterResultados.ViewHolderResultados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = (View)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_obj,parent, false);
        AdapterResultados.ViewHolderResultados vh= new ViewHolderResultados(view);
        return vh;
    }






    @Override
    public void onBindViewHolder(final AdapterResultados.ViewHolderResultados  holder, int position) {
        final ResultadosVo resultadosVoItem;
        resultadosVoItem=listaResultados.get(position);
        holder.etiNombre.setText((String)listaResultados.get(position).getNombre());
        holder.etiEdad.setText((String)listaResultados.get(position).getEdad());
        holder.foto.setImageResource(listaResultados.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

}