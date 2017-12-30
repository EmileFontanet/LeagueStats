package leagueStats;
import org.json.JSONObject;
import java.util.*;
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
	public static ArrayList getGamesIdForLastDays(JSONObject gamesHistory, int numberOfDays){
		ArrayList gamesList = new ArrayList();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long now =  timestamp.getTime();
		long lastDateAuthorized = now - numberOfDays * 24*60*60*1000;
		try {
			JSONArray jsonarray = gamesHistory.getJSONArray("matches");
			for(int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);
				long gameDate = jsonobj.optLong("timestamp");
				if(gameDate > lastDateAuthorized) {
					gamesList.add(jsonobj.opt("gameId"));
				}
			}
			gamesList.sort(null);
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
	public static ArrayList<Long> getAllAccountIdInAGame(JSONObject game){
		ArrayList<Long> accountIds = new ArrayList<Long>();
		try {
			JSONArray participants = game.getJSONArray("participantIdentities");
			for(int i = 0; i < 10; i++){
				JSONObject playertemp =  (JSONObject) participants.get(i);
				JSONObject player = (JSONObject) playertemp.get("player");
				accountIds.add(player.getLong("currentAccountId"));
			}
			return accountIds;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return accountIds;
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
	public static LinkedHashMap<ArrayList<Integer>, Float> sortHashMapByValues(
			HashMap<ArrayList<Integer>, Float> passedMap) {
		List<ArrayList<Integer>> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Float> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues, Collections.reverseOrder());
		//Collections.sort(mapKeys);

		LinkedHashMap<ArrayList<Integer>, Float> sortedMap =
				new LinkedHashMap<>();

		Iterator<Float> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			float val = valueIt.next();
			Iterator<ArrayList<Integer>> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				ArrayList<Integer> key = keyIt.next();
				float comp1 = passedMap.get(key);
				float comp2 = val;

				if (comp1==comp2) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}
	public static HashMap<ArrayList<Integer>, Float> getWinrateMap (HashMap<ArrayList<Integer>, Integer> winMap, HashMap<ArrayList<Integer>, Integer> loseMap){
		HashMap<ArrayList<Integer>, Float> winrateMap = new HashMap<>();
		for(HashMap.Entry<ArrayList<Integer>, Integer> entry : winMap.entrySet()){
			ArrayList<Integer> key = entry.getKey();
			if(!winrateMap.containsKey(key)){
				if(loseMap.containsKey(key)){
					float winrate = winMap.get(key) / (winMap.get(key) + loseMap.get(key));
					winrateMap.put(key, winrate);
				}
				else{
					winrateMap.put(key, 1f);
				}
			}
		}
		for(HashMap.Entry<ArrayList<Integer>, Integer> entry : loseMap.entrySet()){
			ArrayList<Integer> key = entry.getKey();
			if(!winrateMap.containsKey(key)){
				if(winMap.containsKey(key)){
					float winrate = winMap.get(key) / (winMap.get(key) + loseMap.get(key));
					winrateMap.put(key, winrate);
				}
				else{
					winrateMap.put(key, 0f);
				}
			}
		}
		return winrateMap;
	}
}
