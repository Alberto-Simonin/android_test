package test.itexico.movies.utils;

public class Trakt {

    private static String traktURL = "https://private-18a5d-trakt.apiary-proxy.com";
    private static String clientID = "ee910a122f9dbf4808cb68cd4e935401d6806b51b9cc911f22d1eeefe730ed31";

    public static String getClientID(){
        return clientID;
    }

    public static String getSeasonsURL() {
        return traktURL+"/shows/game-of-thrones/seasons"+"?extended=full";
    }

    public static String getEpisodesURL(String seasonId) {
        return traktURL+"/shows/game-of-thrones/seasons/"+seasonId+"?extended=full";
    }

    public static String getImagesService(String seasonId) {
        return "https://api.themoviedb.org/3/tv/1399/season/"+seasonId+"/images?api_key=eb1d66508c874fa4dd101a8883626046";
    }

    public static String getImagesURL(String path, String size) {
        return "https://image.tmdb.org/t/p/w"+size+path;
    }



}
