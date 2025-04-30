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
            case MTG -> apiUrl = "https://api.scryfall.com/cards?order=set&q=game:paper&unique=prints";
            default -> throw new IllegalArgumentException("Unsupported game type: " + gameType);
        }

        return fetchDataFromAPI(apiUrl);
    }

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
