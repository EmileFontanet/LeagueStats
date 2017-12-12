package leagueStats;

import org.json.JSONObject;

public class main {
	public static void main(String [] args ) {
		System.out.println("Hello world");
		JSONObject json = new JSONObject();
		json = Requests.getSummonerById("82719981", "RGAPI-443b75cf-3705-4d8d-b02c-d17fbddb66c3");
		System.out.println(json);
	}
}
