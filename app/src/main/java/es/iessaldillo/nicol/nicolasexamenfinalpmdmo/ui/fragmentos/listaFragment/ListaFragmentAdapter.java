package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.listaFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.R;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public class ListaFragmentAdapter extends ListAdapter<Libro, ListaFragmentAdapter.ViewHolder> {

    public ListaFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Libro>() {
            @Override
            public boolean areItemsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Libro oldItem, @NonNull Libro newItem) {
                return TextUtils.equals(oldItem.getTitulo(), newItem.getTitulo())
                        && TextUtils.equals(oldItem.getAutor(), newItem.getAutor())
                        && TextUtils.equals(oldItem.getFechaPublicacion(), newItem.getFechaPublicacion());
            }
        });

    }

    @Override
    public Libro getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.lista_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    //VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblTitulo,lblAutor,lblFecha;
        private final ImageView imgMiniPortada;

        ViewHolder(View itemView) {
            super(itemView);
            lblTitulo = ViewCompat.requireViewById(itemView,R.id.lblTituloLibro);
            lblAutor = ViewCompat.requireViewById(itemView,R.id.lblAutor);
            lblFecha = ViewCompat.requireViewById(itemView,R.id.lblFecha);
            imgMiniPortada = ViewCompat.requireViewById(itemView,R.id.imgMiniPortada);



        }

        void bind(Libro libro) {
            lblTitulo.setText(libro.getTitulo());
            lblAutor.setText(libro.getAutor());
            lblFecha.setText(libro.getFechaPublicacion());
            if(!libro.getUrlPortada().isEmpty()){
                Picasso.with(imgMiniPortada.getContext()).load(libro.getUrlPortada()).into(imgMiniPortada);
            }
        }
    }
}
