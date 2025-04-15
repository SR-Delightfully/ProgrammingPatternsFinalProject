package org.example;

import java.util.HashMap;
import java.util.LinkedList;

public class Database<T> {
    private HashMap<String, Card> entries;
    private LinkedList<T> entryLogs;

    public Database() {
        entries = new HashMap<>();
        entryLogs = new LinkedList<>();
    }

    public void addCard(Card card) {
        entries.put(card.getCardID(), card);
    }
}
