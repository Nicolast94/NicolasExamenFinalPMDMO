package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.agregarFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.Repository;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.listaFragment.ListaViewModel;

public class AgregarViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public AgregarViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AgregarViewModel(repository);
    }
}
