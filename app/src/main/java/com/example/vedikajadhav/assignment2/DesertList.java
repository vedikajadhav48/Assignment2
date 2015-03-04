package com.example.vedikajadhav.assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vedika Jadhav on 2/16/2015.
 */
public class DesertList {
    /**
     * An array of dessert items.
     */
    public static List<Desert> ITEMS = new ArrayList<Desert>();

    /**
     * A map of items, by ID.
     */
    public static Map<String, Desert> ITEM_MAP = new HashMap<String, Desert>();

    static {
        // Add 5 sample items.
        addItem(new Desert("1", "Cupcake"));
        addItem(new Desert("2", "Donut"));
        addItem(new Desert("3", "Gingerbread"));
        addItem(new Desert("4", "Ice Cream"));
        addItem(new Desert("5", "Jelly Bean"));
    }

    private static void addItem(Desert item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Desert {
        public String id;
        private String mDesertName;

        public Desert(String id, String desertName) {
            this.id = id;
            this.mDesertName = desertName;
        }

        @Override
        public String toString() {
            return mDesertName;
        }

        public String getDesertName() {
            return mDesertName;
        }

        public void setDesertName(String desertName) {
            mDesertName = desertName;
        }
    }
}
