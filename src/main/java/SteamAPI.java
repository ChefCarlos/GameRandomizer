import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SteamAPI {

    private String apiKey;
    private String link;
    JSONObject jsonObject;

    SteamAPI(String id){
        Dotenv dotenv = Dotenv.load();
        apiKey = dotenv.get("STEAMAPIKEY");
        link = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+apiKey+"&steamid="+ id+"&format=json";

        try {
            jsonObject = importData();
        } catch (IOException | JSONException e) {
            System.out.println("Invalid ID");
            e.printStackTrace();
        }
        getIDs();
    }

    private String readJson(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int line;
        while ((line = rd.read())!= -1){
            sb.append((char)line);
        }
        return sb.toString();
    }

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

    private ArrayList getIDs(){
        ArrayList idList = new ArrayList<String>();
        try {
            JSONArray gameList = jsonObject.getJSONObject("response").getJSONArray("games");
            System.out.println(gameList.length() + " Games Found");
            for(int i = 0; i<gameList.length();i++)
                idList.add(gameList.getJSONObject(i).get("appid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idList;
    }
}
