package com.baraccasoftware.baraccalauncher.appobject;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<AppItem> ITEMS = new ArrayList<AppItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, AppItem> ITEM_MAP = new HashMap<String, AppItem>();



    public static void addItem(AppItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    public static void addItem(String name, String label, Drawable icon){
        addItem(new AppItem(name,label,icon));
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class AppItem {
        public String name;
        public String label;
        public Drawable icon;

        public AppItem(String name, String label, Drawable icon) {
            this.name = name;
            this.label = label;
            this.icon = icon;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
