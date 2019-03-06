package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.agregarFragment;

import androidx.lifecycle.ViewModel;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.Repository;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public class AgregarViewModel extends ViewModel {
    private Repository repository;
    private Libro nuevoLibro;

    public AgregarViewModel(Repository repository) {
        this.repository = repository;
    }

    public void insertLibro(Libro libro){
        repository.insertLibro(libro);
    }

    public Libro getNuevoLibro() {
        return nuevoLibro;
    }

    public void setNuevoLibro(Libro nuevoLibro) {
        this.nuevoLibro = nuevoLibro;
    }
}
