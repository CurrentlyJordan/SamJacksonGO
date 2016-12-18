package nyc.c4q.jordansmith.samjacksongo.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jordansmith on 12/14/16.
 */

public class SamJackson implements Serializable {

    private String name;
    private String movie;
    private Long _id;
    private String imageUrl;
    private String gifUrl;
    private String deleteMessage;
    private String quote;
    private String uniqueId;
    private static Map<String, String> idMap = new HashMap<>();


    public SamJackson() {
        this.name = "Unknown";
        this.movie = "unkown";
        uniqueId = checkidMap(generateUniqueId());
    }

    private String generateUniqueId() {
        String output = "";
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            int randDigit = rand.nextInt(10);
            output = "" + randDigit;
        }
        return output;
    }

    private String checkidMap(String generatedId) {
        boolean randomAchieved = false;
        while (!randomAchieved) {
            if (!idMap.containsKey(generatedId)) {
                randomAchieved = true;
            } else {
                generatedId = generateUniqueId();
            }
        }
        return generatedId;
    }

    public String getuniqueId(){
        return uniqueId;
    }


    public SamJackson(String name, String movie) {
        this.name = name;
        this.movie = movie;
    }

    public SamJackson(String name, String movie, String imageUrl, String gifUrl) {
        this.name = name;
        this.movie = movie;
        this.imageUrl = imageUrl;
        this.gifUrl = gifUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public String getQuote() {
        return quote;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Long getId() {
        return _id;
    }

    public SamJackson randomTransform() {
        Random random = new Random();
        int randNumber = random.nextInt(4) + 1;
        switch (randNumber) {
            case 1:
                return frozone();
            case 2:
                return jules();
            case 3:
                return mrGlass();
            case 4:
                return nickFury();
            default:
                return frozone();
        }
    }

    public SamJackson frozone() {
        SamJackson samJackson = new SamJackson();
        samJackson.name = "Frozone";
        samJackson.movie = "The Incredibles";
        samJackson.imageUrl = "http://orig14.deviantart.net/e122/f/2011/076/f/d/frozone_by_riddsorensen-d3bvpwg.jpg";
        samJackson.gifUrl = "http://i.giphy.com/Bp82qbZ7CeIiA.gif";
        samJackson.deleteMessage = "HONEY, WHERE'S MY SUPER SUIT";
        samJackson.quote = "Now Freeze";
        return samJackson;
    }

    public SamJackson jules() {
        SamJackson samJackson = new SamJackson();
        samJackson.name = "Jules";
        samJackson.movie = "Pulp Fiction";
        samJackson.imageUrl = "http://img10.deviantart.net/44e2/i/2014/356/6/8/pulp_fiction_by_wahyuajisadewa-d8asbg1.jpg";
        samJackson.gifUrl = "http://giphy.com/gifs/quentin-tarantino-pulp-fiction-samuel-l-jackson-3otPoz2V9lPlSH9pde";
        samJackson.deleteMessage = "Time to get a Royale with cheese";
        samJackson.quote = "DO I LOOK LIKE A BITCH!";
        return samJackson;
    }

    public SamJackson nickFury() {
        SamJackson samJackson = new SamJackson();
        samJackson.name = "Nick Fury";
        samJackson.movie = "The Avengers";
        samJackson.imageUrl = "http://www.hallereyecenter.com/wp-content/uploads/2014/07/trauma.jpg";
        samJackson.gifUrl = "http://i.giphy.com/9D7rSsb3V16Jq.gif";
        samJackson.deleteMessage = "AVENGERS DISASSEMBLE!";
        samJackson.quote = "Welcome to the Avengers Anitiative";
        return samJackson;
    }

    public SamJackson mrGlass() {
        SamJackson samJackson = new SamJackson();
        samJackson.name = "Mr. Glass";
        samJackson.movie = "Unbreakable";
        samJackson.imageUrl = "http://vignette2.wikia.nocookie.net/villains/images/f/f3/Elijah_Price.jpg/revision/latest?cb=20120511213535";
        samJackson.gifUrl = "http://i.giphy.com/146SJ7jy4BFXHy.gif";
        samJackson.deleteMessage = "You just broke all my bones...";
        samJackson.quote = "Do you know what the scariest thing is? To not know your place in this world, to not know why you're here. That's - that's just an awful feeling.";
        return samJackson;
    }


}

