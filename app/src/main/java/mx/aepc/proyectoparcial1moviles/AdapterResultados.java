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

    @NonNull
    @Override
    public ViewHolderResultados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_obj, null, false);
        return new ViewHolderResultados(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderResultados holder, int position) {
        holder.etiNombre.setText(listaResultados.get(position).getNombre());
        holder.etiEdad.setText(listaResultados.get(position).getEdad());
        holder.foto.setImageResource(listaResultados.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ViewHolderResultados extends RecyclerView.ViewHolder {

        TextView etiNombre, etiEdad;
        ImageView foto;

        public ViewHolderResultados(@NonNull View itemView) {
            super(itemView);
            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiEdad = (TextView) itemView.findViewById(R.id.idEdad);
            foto = (ImageView) itemView.findViewById(R.id.idImagenSP);
        }
    }
}
