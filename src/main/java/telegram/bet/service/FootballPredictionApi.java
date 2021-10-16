package telegram.bet.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class FootballPredictionApi {
	
	private static String API_TZ = "Europe/London";
    private static String LOCAL_TZ = "Europe/Rome";
    
    private final Logger log = LogManager.getLogger(FootballPredictionApi.class);
    
    @Value("${url}")
    private String predictionEndpoint;
    
    @Value("${rapidKey}")
    private String rapidKey;
    
    @Value("${rapidHost}")
    private String rapidHost;
    
    private static ZonedDateTime getApiTimestamp() {
        return ZonedDateTime.now(ZoneId.of(API_TZ));
    }
    
    public void getPronostici() {
    	String tomorrow = getApiTimestamp().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
    	
    	HttpResponse<JsonNode> response = null;
		try {
			response = Unirest
			        .get(predictionEndpoint)
			        .header("X-RapidAPI-Key", rapidKey)
//			        .queryString("federation", "UEFA")
			        .queryString("market", "classic")
			        .queryString("iso_date", tomorrow)
			        .asJson();
		} catch (UnirestException e) {
			log.error("Errore nella chiamata del servizio", e);
		}
		
        JSONArray jsonArray = response.getBody().getObject().getJSONArray("data");
        
        if (jsonArray == null) {
            log.debug("Nessun risultato");
        }

        List jsonAsList = new ArrayList();

		jsonArray.forEach(j -> jsonAsList.add(j));
		
        
        log.debug(jsonArray);
    }
}
