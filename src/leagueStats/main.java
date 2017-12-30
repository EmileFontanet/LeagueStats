package leagueStats;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class main {
	public static void main(String [] args ) {
		//Predef variables used for the storing of datas or the requests
		String key = "RGAPI-424c6995-9201-4e7c-9047-1b5ac512fb36";
		long startingAccountId = 35570648L;
		int numberOfDaysToAnalyze = 10;
		ArrayList<Long> usedGameIds= new ArrayList<>();
		ArrayList<Long> usedAccountIds= new ArrayList<>();
		ArrayList<Long> gameIdsToUse= new ArrayList<>();
		ArrayList<Long> accountIdsToUse= new ArrayList<>();
		accountIdsToUse.add(startingAccountId);
		HashMap<ArrayList<Integer>, Integer> trioWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> trioLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> quadraLosing = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaWinning = new HashMap<ArrayList<Integer>, Integer>();
		HashMap<ArrayList<Integer>, Integer> pentaLosing = new HashMap<ArrayList<Integer>, Integer>();

		//Begin of the algorithm to get the stats
		int i = 0;

		while(i  < accountIdsToUse.size() && Requests.totalNumberOfRequests<50) {// liste sur tous les account id a utiliser
			JSONObject gameHistoFlex = Requests.getLastFlexByAccountId(accountIdsToUse.get(i), key);
			JSONObject gameHistoSoloDuo = Requests.getLastSoloDuoByAccountId(accountIdsToUse.get(i), key);
			ArrayList<Long> flexGameIds = DataExtracting.getGamesIdForLastDays(gameHistoFlex, numberOfDaysToAnalyze);
			ArrayList<Long> soloDuoGameIds = DataExtracting.getGamesIdForLastDays(gameHistoSoloDuo, numberOfDaysToAnalyze);
			ArrayList<Long> gameIds = new ArrayList<>();
			gameIds.addAll(flexGameIds);
			gameIds.addAll(soloDuoGameIds);
			for(int ite = 0; ite < gameIds.size(); ite ++){
				if(!usedGameIds.contains(gameIds.get(ite))){//si la game n'a jamais été utilisée
					gameIdsToUse.add(gameIds.get(ite));
					usedGameIds.add(gameIds.get(ite));
				}
			}
			int gamesIterator = 0;
			while(gamesIterator < gameIdsToUse.size()){// on fait maitenant la boucle sur toute l'array
				//contenant tous les ids de game a utiliser jusqu'a les avoir tous utilisés
				JSONObject gameTemp = Requests.getGameByIdRaw(gameIdsToUse.get(gamesIterator), key);
				ArrayList<Integer> winningTeamTemp = DataExtracting.getWinningTeam(gameTemp);
				ArrayList<Integer> losingTeamTemp = DataExtracting.getLosingTeam(gameTemp);
				DataExtracting.addAllCompEntriesTrio(winningTeamTemp, trioWinning);
				DataExtracting.addAllCompEntriesTrio(losingTeamTemp, trioLosing);
				DataExtracting.addAllCompEntriesQuadra(winningTeamTemp, quadraWinning);
				DataExtracting.addAllCompEntriesQuadra(losingTeamTemp, quadraLosing);
				DataExtracting.addAllCompEntriesPenta(winningTeamTemp, pentaWinning);
				DataExtracting.addAllCompEntriesPenta(losingTeamTemp, pentaLosing);

				ArrayList<Long> accountIdsTemp = DataExtracting.getAllAccountIdInAGame(gameTemp);
				for(int ite = 0; ite < accountIdsTemp.size(); ite ++){
					if(!usedAccountIds.contains(accountIdsTemp.get(ite))){
						accountIdsToUse.add(accountIdsTemp.get(ite));
						usedAccountIds.add(accountIdsTemp.get(ite));
					}
				}

				gamesIterator++;
			}
			gameIdsToUse.clear();
			i++;
		}
		HashMap<ArrayList<Integer>, Float> winrateTrio = DataExtracting.getWinrateMap(trioWinning, trioLosing);
		HashMap<ArrayList<Integer>, Float> winrateQuadra = DataExtracting.getWinrateMap(quadraWinning, quadraLosing);
		HashMap<ArrayList<Integer>, Float> winratePenta = DataExtracting.getWinrateMap(pentaWinning, pentaLosing);
		LinkedHashMap<ArrayList<Integer>, Float> sortedWinrateTrio = DataExtracting.sortHashMapByValues(winrateTrio);
		LinkedHashMap<ArrayList<Integer>, Float> sortedWinrateQuadra = DataExtracting.sortHashMapByValues(winrateQuadra);
		LinkedHashMap<ArrayList<Integer>, Float> sortedWinratePenta = DataExtracting.sortHashMapByValues(winratePenta);

		System.out.println(sortedWinrateTrio);
		System.out.println(sortedWinrateQuadra);
		System.out.println(sortedWinratePenta);

	}
}
