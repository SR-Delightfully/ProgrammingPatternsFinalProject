package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class CardController {
    private Database database;

    public CardController(Database database) {
        this.database = database;
    }

    // CRUD Operations
    public void addCard(Card card) {
        database.addCard(card);
    }

    public void updateCard(String cardID, Card card) {
        database.updateCard(cardID, card);
    }

    public void removeCard(String cardID) {
        database.removeCard(cardID);
    }

    // ------- Search Methods -------

    /**
     * Search cards by name (case-insensitive).
     */
    public List<Card> searchCardsByName(String keyword) {
        return database.getAllCards().stream()
                .filter(card -> card.getCardName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filter cards by game type.
     */
    public List<Card> filterCardsByGameType(GameType gameType) {
        return database.getAllCards().stream()
                .filter(card -> card.getGameType() == gameType)
                .collect(Collectors.toList());
    }

    /**
     * Filter cards by release year.
     */
    public List<Card> filterCardsByYear(String year) {
        return database.getAllCards().stream()
                .filter(card -> card.getReleaseDate().equals(year))
                .collect(Collectors.toList());
    }

    // ------- Sorting Methods -------

    /**
     * Sort cards by name (Alphabetically).
     */
    public List<Card> getCardsSortedByName() {
        return database.getAllCards().stream()
                .sorted(Comparator.comparing(Card::getCardName))
                .collect(Collectors.toList());
    }

    /**
     * Sort cards by game type (Alphabetically).
     */
    public List<Card> getCardsSortedByGameType() {
        return database.getAllCards().stream()
                .sorted(Comparator.comparing(Card::getGameType))
                .collect(Collectors.toList());
    }

    /**
     * Sort cards by release year (Oldest to Newest).
     */
    public List<Card> getCardsSortedByYearAsc() {
        return database.getAllCards().stream()
                .sorted(Comparator.comparing(Card::getReleaseDate))
                .collect(Collectors.toList());
    }

    /**
     * Sort cards by release year (Newest to Oldest).
     */
    public List<Card> getCardsSortedByYearDesc() {
        return database.getAllCards().stream()
                .sorted(Comparator.comparing(Card::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }

    public  List<Card> filterAndSearchPokemonCards(String typeFilter, String searchKeyword) {
        return getAllCards().stream()
                .filter(card -> card.getGameType() == GameType.POKEMON)  // Filter for Pokémon cards only
                .filter(card -> card.getCardName().toLowerCase().contains(searchKeyword.toLowerCase()))  // Filter by search keyword
                .collect(Collectors.toList());
    }

    public  List<Card> filterAndSearchMTGCards(String colorFilter, String typeFilter, String searchKeyword) {
        return getAllCards().stream()
                .filter(card -> card.getGameType() == GameType.MTG)  // Filter for MTG cards only
                .filter(card -> card.getCardName().toLowerCase().contains(searchKeyword.toLowerCase()))  // Filter by search keyword
                .collect(Collectors.toList());
    }

    public  List<Card> getAllCards() {
        return database.getAllCards();  // Assuming you have a method to fetch all cards from the database
    }

}
