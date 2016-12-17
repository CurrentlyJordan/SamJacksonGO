package nyc.c4q.jordansmith.samjacksongo.Model;

import nyc.c4q.jordansmith.samjacksongo.R;

/**
 * Created by jordansmith on 12/14/16.
 */

public class SamJackson {

    private String name;
    private String movie;
    private Long _id;
    int image = R.drawable.frozone_image;




    public SamJackson(String name, String movie){
        this.name = name;
        this.movie = movie;
    }

    public SamJackson() {
        this.name = "Unknown";
        this.movie = "unkown";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        movie = movie;
    }

    public Long getId() {
        return _id;
    }

    public int getImage(){
        return image;
    }
}

