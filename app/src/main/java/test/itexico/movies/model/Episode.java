package test.itexico.movies.model;

import org.json.JSONObject;

public class Episode {
    private int season;
    private int number;
    private String title;
    private JSONObject ids;
    private String number_abs;
    private String overview;
    private String rating;
    private String votes;
    private String comment_count;
    private String first_aired;
    private String updated_at;
    private transient String available_translations;
    private String runtime;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject getIds() {
        return ids;
    }

    public void setIds(JSONObject ids) {
        this.ids = ids;
    }

    public String getNumber_abs() {
        return number_abs;
    }

    public void setNumber_abs(String number_abs) {
        this.number_abs = number_abs;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getFirst_aired() {
        return first_aired;
    }

    public void setFirst_aired(String first_aired) {
        this.first_aired = first_aired;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAvailable_translations() {
        return available_translations;
    }

    public void setAvailable_translations(String available_translations) {
        this.available_translations = available_translations;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public Episode(int season, int number, String title, JSONObject ids, String number_abs, String overview, String rating, String votes, String comment_count, String first_aired, String updated_at, String available_translations, String runtime) {
        this.season = season;
        this.number = number;
        this.title = title;
        this.ids = ids;
        this.number_abs = number_abs;
        this.overview = overview;
        this.rating = rating;
        this.votes = votes;
        this.comment_count = comment_count;
        this.first_aired = first_aired;
        this.updated_at = updated_at;
        this.available_translations = available_translations;
        this.runtime = runtime;
    }
}
