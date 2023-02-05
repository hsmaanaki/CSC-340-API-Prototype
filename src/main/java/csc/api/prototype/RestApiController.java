/*
 * Hasan Maanaki
 * API Prototype
 * RestAPI with endpoint /geo returns geolocation data
 * Last Modified: 2/5/23
 * I followed the UNCG Academic Integrity Policy on this assignment.
 */
package csc.api.prototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestAPI with endpoint /geo returns geolocation data
 *
 * @author Hasan
 */
@RestController
public class RestApiController {

    /**
     * Returns requester IP and geolocation data
     */
    @GetMapping("/geo")
    public Object getGeo() {
        try {
            String url = "https://geo.ipify.org/api/v2/country?apiKey=at_NRAGmjmvrLxgTlPyeB9ew1pPV6xeS";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonGeo = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonGeo);

            //Print the whole response to console.
            System.out.println(root);

            //Parse out the most important info from the response.
            String geoIP = root.get("ip").asText();
            String geoCountry = root.get("location").get("country").asText();
            String geoCity = root.get("location").get("region").asText();
            String geoISP = root.get("isp").asText();

            System.out.println("IP: " + geoIP);
            System.out.println("Country: " + geoCountry);
            System.out.println("City: " + geoCity);
            System.out.println("ISP: " + geoISP);
            return root;

        } catch (JsonProcessingException ex) {
            return "error in /geo";
        }
    }

}
