package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local.LibroDao;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

public class RepositoryImpl implements Repository {

    private final LibroDao libroDao;

    public RepositoryImpl(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    public LiveData<List<Libro>> consultarCadaLibro() {
        return libroDao.consultarCadaLibro();
    }

    @Override
    public LiveData<Libro> consultarLibro(long idLibro) {
        return libroDao.consultarLibro(idLibro);
    }

    @Override
    public LiveData<String> consultarSinopsisLibro(long idLibro) {
        return libroDao.consultarSinopsisLibro(idLibro);
    }

    @Override
    public LiveData<Long> insertLibro(final Libro libro) {
        MutableLiveData<Long> result = new MutableLiveData<>();

        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {

            Long updated = libroDao.insert(libro);
            result.postValue(updated);
        });

        return result;
    }

    @Override
    public LiveData<Integer> deleteLibro(Libro libro) {
        MutableLiveData<Integer> result = new MutableLiveData<>();

        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {

            Integer deleted = libroDao.delete(libro);
            result.postValue(deleted);
        });

        return result;
    }
}
