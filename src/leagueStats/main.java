package leagueStats;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.sql.Array;
import java.util.ArrayList;
public class main {
	public static void main(String [] args ) {
		String key = "RGAPI-424c6995-9201-4e7c-9047-1b5ac512fb36";
		JSONObject json = Requests.getGameByIdRaw(3473272153L, key);
		ArrayList<Integer> winningTeam = DataExtracting.getWinningTeam(json);
		ArrayList<Integer> losingTeam =  DataExtracting.getLosingTeam(json);
		HashMap<ArrayList<Integer>, Integer> trioWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> trioLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaLosing = new HashMap<ArrayList<Integer>, Integer>();
		ArrayList<Long> accountIds = DataExtracting.getAllAccountIdInAGame(json);
		System.out.println("game brut : " + json);
		System.out.println(accountIds);
		JSONObject gameHisto = Requests.getLastSoloDuoByAccountId("224453580", key);
		ArrayList<Long>  gamesIds = DataExtracting.getGamesIdForLastDays(gameHisto, 10);
		System.out.println(gamesIds);

		/*DataExtracting.addAllCompEntriesTrio(losingTeam, trioLosing);
		DataExtracting.addAllCompEntriesQuadra(losingTeam, quadraLosing);
		DataExtracting.addAllCompEntriesPenta(losingTeam, pentaLosing);
		System.out.println(trioLosing);
		System.out.println(trioLosing.size());
		System.out.println(quadraLosing);
		System.out.println(quadraLosing.size());
		System.out.println(pentaLosing);
		System.out.println(pentaLosing.size());*/
		
		
	}
}
