package cz.uhk.fim.rssreader.model;

import java.util.ArrayList;
import java.util.List;

public class RSSList {

    private List<RSSItem> itemList = new ArrayList<>();

    public void addItem(RSSItem item) {
        itemList.add(item);
    }

    public RSSItem getItem(int index) {
        return itemList.get(index);
    }

    public void removeItem(int index) {
        itemList.remove(index);
    }

    public void clearAll(){
        itemList.clear();
    }

    public List<RSSItem> getAllItems(){
        return itemList;
    }
}
