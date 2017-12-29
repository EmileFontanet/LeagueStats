package leagueStats;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Array;
import java.util.ArrayList;
public class main {
	public static void main(String [] args ) {
		//JSONObject json = new JSONObject();
		String key = "RGAPI-a42626df-c721-4047-8640-8fdc9e8601d1";
		JSONObject json = Requests.getGameByIdRaw(3473272153L, key);
		ArrayList<Integer> winningTeam = DataExtracting.getWinningTeam(json);
		ArrayList<Integer> losingTeam =  DataExtracting.getLosingTeam(json);
		System.out.println(json);
		System.out.println(winningTeam);
		System.out.println(losingTeam);

		
		
	}
}
