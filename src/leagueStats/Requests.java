package leagueStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.CDL;
import java.lang.StringBuilder;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONException;

public class Requests {

	public JSONObject makeRequest(String request ) {

	  try {

		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		StringBuilder sb = new StringBuilder();
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		try {
			JSONObject jsonobj = new JSONObject(sb.toString());
			return jsonobj;
		}
		catch(JSONException ex) {
		      System.out.println("Error: " + ex);
		    }
		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	  
	}
	public JSONObject getAllLeaguesForSummoner(String summonerId, String key ) {
		String request = "https://euw1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + summonerId + "?api_key=" + key;
		JSONObject result = makeRequest(request);
		return result;
	}
	public JSONObject getLeagueById(String leagueId, String key ) {
		String request = "https://euw1.api.riotgames.com/lol/league/v3/leagues/" + leagueId + "?api_key=" + key;
		JSONObject result = makeRequest(request);
		return result;
	}
}