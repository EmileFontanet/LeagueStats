package leagueStats;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Array;
import java.util.ArrayList;
public class main {
	public static void main(String [] args ) {
		//JSONObject json = new JSONObject();
		String key = "RGAPI-424c6995-9201-4e7c-9047-1b5ac512fb36";
		JSONObject json = Requests.getGameByIdRaw(3473272153L, key);
		ArrayList<Integer> winningTeam = DataExtracting.getWinningTeam(json);
		ArrayList<Integer> losingTeam =  DataExtracting.getLosingTeam(json);
		System.out.println("game brut : " + json);
		System.out.println(winningTeam);
		System.out.println(losingTeam);

		
		
	}
}
