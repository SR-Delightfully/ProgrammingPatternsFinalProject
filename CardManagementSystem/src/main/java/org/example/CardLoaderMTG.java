package org.example;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class CardLoaderMTG {
    public static void loadCardsMTG(Database db) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url("https://api.magicthegathering.io/v1/cards?pageSize=10")
            .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Gson gson = new Gson();
        ApiResponseMTG mtgResponse = gson.fromJson(json, ApiResponseMTG.class);

        for (CardMTG mtgCard : mtgResponse.cards) {
            String releaseDate = mtgCard.getReleaseDate();
            int power = mtgCard.getPower();
            int toughness = mtgCard.getToughness();
            String mana = mtgCard.getManaCost();
            String[] abilities = mtgCard.getDescription() != null ? new String[]{mtgCard.getDescription()} : new String[0];

            CardMTG card = new CardMTG();
            card.setId(mtgCard.getId());
            card.setGameType(GameType.MTG);
            card.setCardName(mtgCard.getCardName());
            card.setDescription(mtgCard.getDescription());
            card.setReleaseDate(releaseDate);
            card.setPrice(0.00);
            card.setCardType(mtgCard.getCardType());
            card.setAbilities(abilities);
            card.setPower(power);
            card.setToughness(toughness);
            card.setManaCost(mana);
            db.addCard(card);
        }
    }
}
