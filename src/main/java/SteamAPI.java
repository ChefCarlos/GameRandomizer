import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

class SteamAPI {

    private String link;
    private JSONObject jsonObject;

    //starts the process of getting the link and starting to collect the ids to be placed into the database
    HashMap<String,String> initialiseReader(String id){
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("STEAMAPIKEY");
        link = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+ apiKey +"&steamid="+ id+"&include_appinfo=true&format=json";

        try {
            jsonObject = importData();
        } catch (IOException | JSONException e) {
            System.out.println("Invalid ID");
            e.printStackTrace();
        }
        return getGames();
    }

    //used to read each line of json, to be placed into a stringbuilder that is returned as string
    private String readJson(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int line;
        while ((line = rd.read())!= -1){
            sb.append((char)line);
        }
        return sb.toString();
    }

    //gets the json from the url provided and places it into a jsonobject
    private JSONObject importData() throws IOException, JSONException {
        InputStream is = new URL(link).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readJson(rd);
            return new JSONObject(jsonText);
        }
        finally {
            is.close();
        }
    }

    //goes through the json object and gets the id and names of all the games placed into a hashmap
    private HashMap<String,String> getGames(){
        HashMap<String, String> gameMap = new HashMap<>();
        try {
            JSONArray gameList = jsonObject.getJSONObject("response").getJSONArray("games");
            System.out.println(gameList.length() + " Games Found");
            for(int i = 0; i<gameList.length();i++)
                gameMap.put(gameList.getJSONObject(i).get("appid").toString(),gameList.getJSONObject(i).get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gameMap;
    }
}
