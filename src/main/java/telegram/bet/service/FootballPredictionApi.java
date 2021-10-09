package telegram.bet.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FootballPredictionApi {
	
	private static String API_TZ = "Europe/London";
    private static String LOCAL_TZ = "Europe/Rome";
    private static String predictionEndpoint = "https://football-prediction-api.p.rapidapi.com/api/v2/predictions";
    
    private static String getAuthKey() {
        return System.getenv("RAPIDAPI_KEY");
    }
    
    private static ZonedDateTime getApiTimestamp() {
        return ZonedDateTime.now(ZoneId.of(API_TZ));
    }
    
    private void getPronostici() {
    	
    	
    }

}
