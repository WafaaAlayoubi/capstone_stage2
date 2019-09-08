package todoapplication.wafaa.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface noteDao {

    @Query("SELECT * FROM notes ORDER BY id")
    LiveData<List<Note>> loadAllNotes();

    @Insert
    void insertNote(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    void deleteNote(int id);


}

