package nyc.c4q.jordansmith.samjacksongo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class EditScreenActivity extends AppCompatActivity implements View.OnClickListener,Serializable {

    EditText nameEditText;
    Button saveChangesButton;
    SamJackson samJackson;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        Intent intent = getIntent();
        samJackson = (SamJackson) intent.getSerializableExtra(DetailScreenActivity.EDIT_TAG);

        nameEditText = (EditText) findViewById(R.id.edit_screen_name_edit_text);
        saveChangesButton = (Button) findViewById(R.id.edit_screen_edit_button);

        saveChangesButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(nameEditText.getText().toString().equals("")){
            Toast.makeText(this, "please write something", Toast.LENGTH_SHORT).show();
        }
        else{
            SamJacksonDatabaseHelper dbHelper = SamJacksonDatabaseHelper.getInstance(this);
            db = dbHelper.getWritableDatabase();
            String newName = nameEditText.getText().toString();
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("name", newName);
            long whatever = samJackson.getId();
            cupboard().withDatabase(db).update(SamJackson.class, contentValues);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
    }
}
