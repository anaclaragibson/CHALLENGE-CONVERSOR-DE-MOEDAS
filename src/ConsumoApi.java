import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsumoApi {
    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double getExchangeRate(String deMoeda, String paraMoeda) throws Exception {
        String urlStr = BASE_URL + deMoeda;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Falha ao buscar dados. Código de resposta HTTP: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JsonObject json = JsonParser.parseString(content.toString()).getAsJsonObject();
        if (!json.getAsJsonObject("rates").has(paraMoeda)) {
            throw new IllegalArgumentException("Moeda " + paraMoeda + " não é suportado.");
        }

        return json.getAsJsonObject("rates").get(paraMoeda).getAsDouble();
    }
}