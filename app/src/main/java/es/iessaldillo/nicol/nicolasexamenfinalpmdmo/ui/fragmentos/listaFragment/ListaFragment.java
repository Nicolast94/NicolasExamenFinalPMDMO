package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.listaFragment;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.R;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.Repository;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.RepositoryImpl;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local.AppDatabase;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local.LibroDao;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public class ListaFragment extends Fragment {

    private ListaViewModel vm;
    private Repository repository;
    private TextView lblEmptyView;
    private RecyclerView rcvLibros;
    private ListaFragmentAdapter listaAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LibroDao estudianteDao = AppDatabase.getInstance(requireContext()).libroDao();
        repository = new RepositoryImpl(estudianteDao);
        vm = ViewModelProviders.of(this, new ListaViewModelFactory(repository)).get(ListaViewModel.class);
        setupViews();
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuToSettings) {
            NavHostFragment.findNavController(this).navigate(R.id.actionListaToSettings);
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupViews() {
        FloatingActionButton fab = ActivityCompat.requireViewById(requireActivity(), R.id.fabCrearLibro);
        lblEmptyView = ActivityCompat.requireViewById(requireActivity(),R.id.lblEmptyView);
        rcvLibros = ActivityCompat.requireViewById(requireActivity(),R.id.lstLibros);

        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.actionListaToAgregar));
        setupRecyclerView();

        vm.getLibroList().observe(this, this::actualizarListaEmpresas);

    }

    private void actualizarListaEmpresas(List<Libro> libros) {
        listaAdapter.submitList(libros);

        if(libros.size() == 0){
            lblEmptyView.setVisibility(View.VISIBLE);
        }else{
            lblEmptyView.setVisibility(View.INVISIBLE);
        }

    }

    private void setupRecyclerView() {
        ItemTouchHelper itemTouchHelper;
        listaAdapter = new ListaFragmentAdapter();

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                vm.setLibroBorrado(listaAdapter.getItem(viewHolder.getAdapterPosition()));
                vm.deleteLibro(listaAdapter.getItem(viewHolder.getAdapterPosition()));
                Snackbar.make(lblEmptyView, vm.getLibroBorrado().getTitulo() + " ha sido borrado", Snackbar.LENGTH_LONG)
                        .setAction("Deshacer", view -> {
                            vm.insertLibro(vm.getLibroBorrado());
                            vm.consultarCadaLibro();
                        }).show();
            }
        });

        rcvLibros.setHasFixedSize(true);
        rcvLibros.setLayoutManager(new GridLayoutManager(requireContext(),1));
        rcvLibros.setItemAnimator(new DefaultItemAnimator());
        rcvLibros.setAdapter(listaAdapter);
        itemTouchHelper.attachToRecyclerView(rcvLibros);
    }

}
