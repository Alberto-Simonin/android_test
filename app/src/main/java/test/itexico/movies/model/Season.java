package test.itexico.movies.model;

import org.json.JSONObject;

public class Season {
    private int number;
    private JSONObject ids;
    private String rating;
    private String votes;
    private String episode_count;
    private String aired_episodes;
    private String title;
    private String overview;
    private String first_aired;
    private String network;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public JSONObject getIds() {
        return ids;
    }

    public void setIds(JSONObject ids) {
        this.ids = ids;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(String episode_count) {
        this.episode_count = episode_count;
    }

    public String getAired_episodes() {
        return aired_episodes;
    }

    public void setAired_episodes(String aired_episodes) {
        this.aired_episodes = aired_episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_aired() {
        return first_aired;
    }

    public void setFirst_aired(String first_aired) {
        this.first_aired = first_aired;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Season(int number, JSONObject ids, String rating, String votes, String episode_count, String aired_episodes, String title, String overview, String first_aired, String network) {
        this.number = number;
        this.ids = ids;
        this.rating = rating;
        this.votes = votes;
        this.episode_count = episode_count;
        this.aired_episodes = aired_episodes;
        this.title = title;
        this.overview = overview;
        this.first_aired = first_aired;
        this.network = network;
    }

    public Season() {

    }
}
