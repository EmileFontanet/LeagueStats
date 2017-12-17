package leagueStats;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Array;
import java.util.ArrayList;
public class main {
	public static void main(String [] args ) {
		JSONObject json = new JSONObject();
		json = Requests.getLastSoloDuoByAccountId("39752045", "RGAPI-2a4c6a46-fcc4-43c4-b1c4-4c4cc0cbb775");
		ArrayList gameIds = DataExtracting.getGamesIdForLastDays(json, 4);
		System.out.println(gameIds);
		
		
	}
}
