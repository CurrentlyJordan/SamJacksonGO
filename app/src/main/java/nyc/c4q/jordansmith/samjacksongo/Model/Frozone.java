package nyc.c4q.jordansmith.samjacksongo.Model;

/**
 * Created by jordansmith on 12/17/16.
 */

public class Frozone extends SamJackson {

    private String name = "Frozone";
    private String movie = "The Incredibles";
    private Long _id;
    private String imageUrl = "http://orig14.deviantart.net/e122/f/2011/076/f/d/frozone_by_riddsorensen-d3bvpwg.jpg";
    private String gifUrl = "http://i.giphy.com/Bp82qbZ7CeIiA.gif";

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
