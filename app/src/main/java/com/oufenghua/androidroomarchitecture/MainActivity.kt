package com.oufenghua.androidroomarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

/**
 * // File: Song.java
@Entity
public class User {
@PrimaryKey
private int id;
private String name;
@ColumnInfo(name = "release_year")
private int releaseYear;
// getters and setters are ignored for brevity but they are required for Room to work.
}
// File: SongDao.java
@Dao
public interface SongDao {
@Query("SELECT * FROM song")
List<Song> loadAll();
@Query("SELECT * FROM song WHERE id IN (:songIds)")
List<Song> loadAllBySongId(int... songIds);
@Query("SELECT * FROM song WHERE name LIKE :name AND release_year = :year LIMIT 1")
Song loadOneByNameAndReleaseYear(String first, int year);
@Insert
void insertAll(Song... songs);
@Delete
void delete(Song song);
}
// File: MusicDatabase.java
@Database(entities = {Song.java})
public abstract class MusicDatabase extends RoomDatabase {
public abstract SongDao userDao();
}
 */

const val MSG_NOTIFY_ADAPTER = 0

class MainActivity : AppCompatActivity() {

    private val random = Random(System.currentTimeMillis())
    val handler: Handler = Handler{
        when(it.what){
            MSG_NOTIFY_ADAPTER -> {
                presenter.adapter?.notifyDataSetChanged()
            }
        }
        false
    }

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        recyclerview.adapter = presenter.adapter
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        add_btn.setOnClickListener {
            var user = User()
            user.name = "UserName${random.nextInt()}"
            user.age = random.nextInt(0, 100)
            user.id = System.currentTimeMillis()
            presenter.addUser(user)
        }

        remove_btn.setOnClickListener {
            presenter.deleteUser()
        }

    }
}
