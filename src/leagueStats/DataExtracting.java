package leagueStats;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;
import java.sql.Timestamp;
import org.json.JSONException;
import java.util.HashMap;

public class DataExtracting {
	public static int getSummonnerAccountByName(JSONObject summoner) {
		try {
			return summoner.getInt("accountId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public static ArrayList getGamesIdForLastDays(JSONObject games, int numberOfDays){
		ArrayList gamesList = new ArrayList();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long now =  timestamp.getTime();
		long lastDateAuthorized = now - numberOfDays * 24*60*60*1000;
		try {
			JSONArray jsonarray = games.getJSONArray("matches");
			for(int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);
				long gameDate = jsonobj.optLong("timestamp");
				if(gameDate > lastDateAuthorized) {
					gamesList.add(jsonobj.opt("gameId"));
				}
			}
			return gamesList;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gamesList;
	}
	public static ArrayList<Integer> getWinningTeam(JSONObject game){
		ArrayList<Integer> winningTeam = new ArrayList<Integer>();
		try {
			JSONObject participants = game.getJSONObject("participants");
			for(int i = 0; i < 10; i++){
				String id = Integer.toString(i);
				if (participants.getJSONObject(id).getJSONObject("stats").getBoolean("win")){
					winningTeam.add(participants.getJSONObject(id).getInt("championId"));
				}
			}
			return winningTeam;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return winningTeam;
	}

	public static ArrayList<Integer> getLosingTeam(JSONObject game){
		ArrayList<Integer> winningTeam = new ArrayList<Integer>();
		try {
			JSONObject participants = game.getJSONObject("participants");
			for(int i = 0; i < 10; i++){
				String id = Integer.toString(i);
				if (!participants.getJSONObject(id).getJSONObject("stats").getBoolean("win")){
					winningTeam.add(participants.getJSONObject(id).getInt("championId"));
				}
			}
			return winningTeam;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return winningTeam;
	}
}
