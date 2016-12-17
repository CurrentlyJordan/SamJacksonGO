package nyc.c4q.jordansmith.samjacksongo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jordansmith on 12/15/16.
 */

public class SamJacksonDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "samJackson.db";
    private static final int DATABASE_VERSION = 1;
    private static SamJacksonDatabaseHelper instance;


    public static synchronized SamJacksonDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new SamJacksonDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private SamJacksonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(SamJackson.class);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

    }
}
