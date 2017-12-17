package leagueStats;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;
import java.sql.Timestamp;
import org.json.JSONException;
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

}
