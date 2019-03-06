package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.ui.fragmentos.listaFragment;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.Repository;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public class ListaViewModel extends ViewModel {

    private Repository repository;
    private LiveData<List<Libro>> libroList = new MutableLiveData<>();
    private LiveData<Libro> selectedLibro = new MutableLiveData<>();
    private LiveData<String> libroSinopsis = new MutableLiveData<>();
    private Libro libroBorrado;

    public ListaViewModel(Repository repository) {
        this.repository = repository;
        consultarCadaLibro();
    }

    public void consultarCadaLibro(){
        libroList = repository.consultarCadaLibro();
    }

    public void consultarLibro(long idLibro){
        selectedLibro = repository.consultarLibro(idLibro);
    }

    public void consultarSinopsis(long idLibro){
        libroSinopsis = repository.consultarSinopsisLibro(idLibro);
    }

    public void deleteLibro(Libro libro){
        repository.deleteLibro(libro);
    }

    public LiveData<List<Libro>> getLibroList() {
        return libroList;
    }

    public void setLibroList(LiveData<List<Libro>> libroList) {
        this.libroList = libroList;
    }

    public LiveData<Libro> getSelectedLibro() {
        return selectedLibro;
    }

    public void setSelectedLibro(LiveData<Libro> selectedLibro) {
        this.selectedLibro = selectedLibro;
    }

    public LiveData<String> getLibroSinopsis() {
        return libroSinopsis;
    }

    public void setLibroSinopsis(LiveData<String> libroSinopsis) {
        this.libroSinopsis = libroSinopsis;
    }
}
