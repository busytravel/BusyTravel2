package sample;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URLEncoder;

public class JSONCaller
{
    public String data(String root, String query, String token)
    {
        String json = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            String url = root + query +"?" + URLEncoder.encode(token,"UTF-8");

            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");

            //StringEntity params = new StringEntity("");
            //request.setEntity(params);

            HttpResponse result = httpClient.execute(request);

            json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println(obj.get("id"));
                        System.out.println(obj.toJSONString());
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println(obj.get("example"));
                    System.out.println(obj.get("fr"));
                }

            } catch (Exception e) {
                // TODO: handle exception
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
