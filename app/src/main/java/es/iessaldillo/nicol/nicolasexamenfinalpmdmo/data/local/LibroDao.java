package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.base.BaseDao;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

@Dao
public interface LibroDao extends BaseDao<Libro> {

    @Query("SELECT * FROM libro")
    LiveData<List<Libro>> consultarCadaLibro();


    @Query("SELECT * FROM libro WHERE id = :idLibro")
    LiveData<Libro> consultarLibro(long idLibro);

    @Query("SELECT sinopsis FROM libro WHERE id = :idLibro")
    LiveData<String> consultarSinopsisLibro(long idLibro);


}
