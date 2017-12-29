package leagueStats;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
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
			JSONArray participants = game.getJSONArray("participants");		
			for(int i = 0; i < 10; i++){
				JSONObject player =  (JSONObject) participants.get(i);
				if (player.getJSONObject("stats").getBoolean("win")){
					winningTeam.add(player.getInt("championId"));
				}
			}
			winningTeam.sort(null);
			return winningTeam;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return winningTeam;
	}
	public static ArrayList<Integer> getLosingTeam(JSONObject game){
		ArrayList<Integer> losingTeam = new ArrayList<Integer>();
		try {
			JSONArray participants = game.getJSONArray("participants");		
			for(int i = 0; i < 10; i++){
				JSONObject player =  (JSONObject) participants.get(i);
				if (!player.getJSONObject("stats").getBoolean("win")){
					losingTeam.add(player.getInt("championId"));
				}
			}
			losingTeam.sort(null);
			return losingTeam;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return losingTeam;
	}
	public static void addAllCompEntriesTrio(ArrayList<Integer> completeComp, HashMap<ArrayList<Integer>, Integer> destination) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k< 5; k++) {
					if(i<j && j<k) {
						ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(completeComp.get(i), completeComp.get(j), completeComp.get(k)));
						temp.sort(null);
						if(destination.containsKey(temp)) {
							destination.put(temp, destination.get(temp) +1);
						}
						else {
							destination.put(temp, 1);
						}
					}
				}
			}
		}
	}
	public static void addAllCompEntriesQuadra(ArrayList<Integer> completeComp, HashMap<ArrayList<Integer>, Integer> destination) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k< 5; k++) {
					for(int l = 0; l<5; l++) {
						if(i<j && j<k && k<l) {
							ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(completeComp.get(i), completeComp.get(j), completeComp.get(k), completeComp.get(l)));
							temp.sort(null);
							if(destination.containsKey(temp)) {
								destination.put(temp, destination.get(temp) +1);
							}
							else {
								destination.put(temp, 1);
							}
						}
					}	
				}
			}
		}
	}
	public static void addAllCompEntriesPenta(ArrayList<Integer> completeComp, HashMap<ArrayList<Integer>, Integer> destination) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k< 5; k++) {
					for(int l = 0; l<5; l++) {
						for(int m = 0; m < 5; m++) {
							if(i<j && j<k && k<l && l<m) {
								ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(completeComp.get(i), completeComp.get(j), completeComp.get(k), completeComp.get(l), completeComp.get(m) ));
								temp.sort(null);
								if(destination.containsKey(temp)) {
									destination.put(temp, destination.get(temp) +1);
								}
								else {
									destination.put(temp, 1);
								}
							}
						}	
					}	
				}
			}
		}
	}
}
