package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiResponse {
    private static final String POKEMON_API_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String MAGIC_API_URL = "https://api.magicthegathering.io/v1/cards";
    private static final String YUGIOH_API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php";

    public static String fetchCardData(GameType gameType) {
        String url = "";
        switch (gameType) {
            case POKEMON:
                url = POKEMON_API_URL;
                break;
            case MTG:
                url = MAGIC_API_URL;
                break;
            case YUGIOH:
                url = YUGIOH_API_URL;
                break;
            default:
                System.out.println("Unknown gameType: " + gameType);
                return null;
        }
        return fetchDataFromAPI(url);
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

