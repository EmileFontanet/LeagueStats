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
		System.out.println("game brut : " + json);
		HashMap<ArrayList<Integer>, Integer> trioWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> trioLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaLosing = new HashMap<ArrayList<Integer>, Integer>();
		DataExtracting.addAllCompEntriesTrio(winningTeam, trioWinning);
		DataExtracting.addAllCompEntriesQuadra(winningTeam, quadraWinning);
		DataExtracting.addAllCompEntriesPenta(winningTeam, pentaWinning);
		System.out.println(trioWinning);
		System.out.println(trioWinning.size());
		System.out.println(quadraWinning);
		System.out.println(quadraWinning.size());
		System.out.println(pentaWinning);
		System.out.println(pentaWinning.size());
		
		
	}
}
