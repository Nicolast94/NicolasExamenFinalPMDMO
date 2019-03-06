package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public interface Repository {
    LiveData<List<Libro>> consultarCadaLibro();

    LiveData<Libro> consultarLibro(long idLibro);

    LiveData<String> consultarSinopsisLibro(long idLibro);

    LiveData<Long> insertLibro(Libro libro);

    LiveData<Integer> deleteLibro(Libro libro);

}
