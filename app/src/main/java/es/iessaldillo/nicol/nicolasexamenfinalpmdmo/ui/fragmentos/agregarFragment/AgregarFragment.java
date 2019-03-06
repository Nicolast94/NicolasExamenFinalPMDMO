package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.agregarFragment;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.R;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.RepositoryImpl;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local.AppDatabase;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local.LibroDao;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.dialogs.ConfirmDialog;

public class AgregarFragment extends Fragment implements ConfirmDialog.Listener {
    private static final String TAG_DIALOG_FRAGMENT = "TAG_DIALOG_FRAGMENT";
    private static final int RC_DIALOG_FRAGMENT = 1;

    private AgregarViewModel vm;
    private TextView txtTitulo,txtAutor,txtFecha,txtURL,txtSinopsis;
    private ImageView imgPortada;
    private TextInputLayout tilTitulo,tilAutor,tilFecha,tilURL;
    private RepositoryImpl repository;
    ConfirmDialog dialogFragment;


    public static AgregarFragment newInstance() {
        return new AgregarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agregar_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LibroDao estudianteDao = AppDatabase.getInstance(requireContext()).libroDao();
        repository = new RepositoryImpl(estudianteDao);
        vm = ViewModelProviders.of(this,new AgregarViewModelFactory(repository)).get(AgregarViewModel.class);
        setupViews();
    }

    private void setupViews() {
        txtTitulo = ViewCompat.requireViewById(getView(),R.id.txtTitulo);
        txtAutor = ViewCompat.requireViewById(getView(),R.id.txtAutor);
        txtFecha = ViewCompat.requireViewById(getView(),R.id.txtFecha);
        txtURL = ViewCompat.requireViewById(getView(),R.id.txtURL);
        txtSinopsis = ViewCompat.requireViewById(getView(),R.id.txtSinopsis);

        tilTitulo = ViewCompat.requireViewById(getView(),R.id.tilTitulo);
        tilAutor = ViewCompat.requireViewById(getView(),R.id.tilAutor);
        tilFecha = ViewCompat.requireViewById(getView(),R.id.tilFecha);
        tilURL = ViewCompat.requireViewById(getView(),R.id.tilURL);

        imgPortada = ViewCompat.requireViewById(getView(),R.id.imgPortada);
        FloatingActionButton fabCrearLibro = ViewCompat.requireViewById(getView(), R.id.fabSaveLibro);

        fabCrearLibro.setOnClickListener(v -> saveLibro());
        Picasso.with(imgPortada.getContext()).load("https://via.placeholder.com/300.png").into(imgPortada);
    }

    private void saveLibro() {
        String titulo,autor,fecha,url,sinopsis;

        titulo = txtTitulo.getText().toString();
        autor = txtAutor.getText().toString();
        fecha = txtFecha.getText().toString();
        url = txtURL.getText().toString();
        sinopsis = txtSinopsis.getText().toString();



        if(!checkInputErrors()){
            if(url.isEmpty()){
                url = "https://via.placeholder.com/300.png";
            }
            vm.setNuevoLibro(new Libro(0,titulo,autor,fecha,url,sinopsis));
            showConfirmationDialog();
        }

    }

    private boolean checkInputErrors() {
        boolean error = false;

        if(txtTitulo.getText().toString().isEmpty()){
            error = true;
            tilAutor.setError("¡Error!.Campo vacío");
        }else{
            tilAutor.setErrorEnabled(false);
        }

        if(txtFecha.getText().toString().isEmpty()){
            error = true;
            tilFecha.setError("¡Error!.Campo vacío");
        }else{
            tilFecha.setErrorEnabled(false);
        }

        if(txtTitulo.getText().toString().isEmpty()){
            error = true;
            tilTitulo.setError("¡Error!.Campo vacío");
        }else{
            tilTitulo.setErrorEnabled(false);
        }
        if (!txtFecha.getText().toString().matches("(19|20)[0-2][0-9]")){
            error = true;
        }

        return error;
    }

    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {
        vm.insertLibro(vm.getNuevoLibro());
        NavController navCtrl = Navigation.findNavController(getView());
        navCtrl.navigate(R.id.destListaFragment);
    }

    @Override
    public void onNegativeButtonClick(DialogInterface dialog) {
        dialogFragment.dismiss();
    }


    // ...

    private void showConfirmationDialog() {
        dialogFragment = ConfirmDialog.newInstance(
                "Crear libro",
                "¿Seguro que quiere crear este libro?",
                "Si",
                "No");
        dialogFragment.setTargetFragment(this, RC_DIALOG_FRAGMENT);
        dialogFragment.show(getFragmentManager(),
                TAG_DIALOG_FRAGMENT);
    }
}
