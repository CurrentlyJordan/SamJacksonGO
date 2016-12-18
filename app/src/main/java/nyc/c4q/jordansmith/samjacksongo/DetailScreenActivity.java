package nyc.c4q.jordansmith.samjacksongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

public class DetailScreenActivity extends AppCompatActivity implements Serializable, View.OnClickListener {

    TextView nameView;
    TextView movieView;
    ImageView imageView;
    TextView quoteView;
    Button editButton;
    SamJackson samJackson;
    final static String EDIT_TAG = "edit activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        imageView = (ImageView) findViewById(R.id.detail_screen_image_view);
        nameView = (TextView) findViewById(R.id.detail_screen_name_textview);
        movieView = (TextView) findViewById(R.id.detail_screen_movie_textview);
        quoteView = (TextView) findViewById(R.id.detail_screen_quote_textview);
        editButton = (Button) findViewById(R.id.edit_button);

        Intent intent = getIntent();
        samJackson = (SamJackson) intent.getSerializableExtra(MainActivity.DETAIL_ACTIVITY);

        setViews(samJackson);

        editButton.setOnClickListener(this);


    }

    private void setViews(SamJackson samJackson) {
        Glide.with(this).load(samJackson.getImageUrl()).into(imageView);
        nameView.setText(samJackson.getName());
        movieView.setText(samJackson.getMovie());
        quoteView.setText(samJackson.getQuote());
}

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), EditScreenActivity.class);
        intent.putExtra(EDIT_TAG, samJackson);
        startActivity(intent);

   }
}
