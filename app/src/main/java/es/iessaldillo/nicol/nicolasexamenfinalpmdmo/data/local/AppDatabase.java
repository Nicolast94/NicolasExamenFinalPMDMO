package es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.iessaldillo.nicol.nicolasexamenfinalpmdmo.data.model.Libro;

@Database(entities = {Libro.class},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "libro.db";

    public abstract LibroDao libroDao();


    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance =
                            Room.databaseBuilder(
                                    context.getApplicationContext(), AppDatabase.class,
                                    DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
