package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiResponse {
    public static String fetchCardData(GameType gameType) {
        String apiUrl;

        switch (gameType) {
            case POKEMON -> apiUrl = "https://api.pokemontcg.io/v2/cards?page=1&pageSize=20";
            case MTG -> apiUrl = "https://api.scryfall.com/cards/search?q=type:creature\n";
            default -> throw new IllegalArgumentException("Unsupported game type: " + gameType);
        }

        return fetchDataFromAPI(apiUrl);
    }

    /**
     * Used to fetch information from an API and convert it to a string for further parsing later
     * @param url refers to the Key for the API you would like to fetch from
     * @return returns a string with the API's response
     */
    private static String fetchDataFromAPI(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body() != null ? response.body().string() : null;
            } else {
                System.out.println("API call failed: " + response.message());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
