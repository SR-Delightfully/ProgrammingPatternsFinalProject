package org.example;

import java.util.HashMap;
import java.util.LinkedList;

public class Database<T> {
    private HashMap<String,String> entries;
    private LinkedList<T> entryLogs;
}
