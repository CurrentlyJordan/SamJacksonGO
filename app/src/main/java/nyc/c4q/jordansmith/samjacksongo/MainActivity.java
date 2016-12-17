package nyc.c4q.jordansmith.samjacksongo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity implements SamJacksonAdapter.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView samjacksonRecyclerview;
    private SQLiteDatabase db;
    private SamJacksonAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get an instance of the DatabaseHelper
        SamJacksonDatabaseHelper dbHelper = SamJacksonDatabaseHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

//        samJacksonList = new ArrayList<>();
//
//        samJacksonList.add(new SamJackson("Pulp Fiction"));
//        samJacksonList.add(new SamJackson("Black Snake Moan"));
//        samJacksonList.add(new SamJackson("The Spirit"));
//        samJacksonList.add(new SamJackson("That Scottish Movie"));
        if (savedInstanceState == null) {

            addSamJackson(new SamJackson("brushy", "Pulp Fiction"));
        }

        samjacksonRecyclerview = (RecyclerView) findViewById(R.id.sam_jackson_recyclerview);

        adapter = new SamJacksonAdapter(selectAllSamJacksons(), this);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        samjacksonRecyclerview.setLayoutManager(manager);
        samjacksonRecyclerview.setAdapter(adapter);

    }

    private void addSamJackson(SamJackson sam) {
        cupboard().withDatabase(db).put(sam);
    }

    private List<SamJackson> selectAllSamJacksons() {
        List<SamJackson> SamJacksonsList = new ArrayList<>();

        try {
            // Iterate sam jacksons
            QueryResultIterable<SamJackson> itr = cupboard().withDatabase(db).query(SamJackson.class).query();
            for (SamJackson sam : itr) {
                SamJacksonsList.add(sam);
            }
            itr.close();
        } catch (Exception e) {
            Log.e(TAG, "selectAllCats: ", e);
        }

        return SamJacksonsList;
    }

    private void refreshSamJacksonList() {
        adapter.setData(selectAllSamJacksons());

    }

    @Override
    public void onSamJacksonLongClicked(SamJackson sam) {
        Toast.makeText(this, "I've had it!", Toast.LENGTH_SHORT).show();
        cupboard().withDatabase(db).delete(sam);
        refreshSamJacksonList();
    }

    @Override
    public void onSamJacksonShortClicked(SamJackson sam) {

    }
}
