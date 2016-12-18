package nyc.c4q.jordansmith.samjacksongo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity implements SamJacksonAdapter.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();
    static final String DETAIL_ACTIVITY = "go to detail screen";
    private RecyclerView samjacksonRecyclerview;
    private static SQLiteDatabase db;
    private SamJacksonAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get an instance of the DatabaseHelper
        SamJacksonDatabaseHelper dbHelper = SamJacksonDatabaseHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

//
//        if (savedInstanceState == null) {
//            addSamJackson(new SamJackson().randomTransform());
//        }

        samjacksonRecyclerview = (RecyclerView) findViewById(R.id.sam_jackson_recyclerview);

        adapter = new SamJacksonAdapter(selectAllSamJacksons(), this);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        samjacksonRecyclerview.setLayoutManager(manager);
        samjacksonRecyclerview.setAdapter(adapter);
        scheduleAlarm();

    }

    public static void addSamJackson(SamJackson sam) {
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
        Toast.makeText(this, sam.getDeleteMessage(), Toast.LENGTH_SHORT).show();
        cupboard().withDatabase(db).delete(sam);
        refreshSamJacksonList();
    }

    @Override
    public void onSamJacksonShortClicked(SamJackson sam) {
        Intent intent = new Intent(this, DetailScreenActivity.class);
        intent.putExtra(DETAIL_ACTIVITY, sam);
        startActivity(intent);
    }

    public void scheduleAlarm() {

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), MyAlarmReciever.class);

        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, MyAlarmReciever.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        long firstMillis = System.currentTimeMillis(); // alarm is set right away

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                20000 * 10, pendingIntent);
    }
}
