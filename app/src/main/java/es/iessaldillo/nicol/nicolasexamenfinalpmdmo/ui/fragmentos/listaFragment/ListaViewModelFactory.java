package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.listaFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.Repository;

public class ListaViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public ListaViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListaViewModel(repository);
    }
}
