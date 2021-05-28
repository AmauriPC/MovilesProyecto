package mx.aepc.proyectoparcial1moviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AdapterResultados extends FirestoreRecyclerAdapter<ResultadosVo, AdapterResultados.ViewHolderResultados> {

    //ArrayList<ResultadosVo> listaResultados;

    public AdapterResultados(@NonNull FirestoreRecyclerOptions<ResultadosVo> listaResultados) {
       super(listaResultados);
    }

    @Override
    public ViewHolderResultados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_obj, null, false);
        return new ViewHolderResultados(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolderResultados holder, int position, @NonNull ResultadosVo resultado) {
        holder.etiNombre.setText(resultado.getNombre());
        holder.etiEdad.setText(resultado.getEdad());
        holder.foto.setImageResource(resultado.getFoto());
    }

    /*@Override
    public int getItemCount() {
        return listaResultados.size();
    }*/

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
