import loggers.ConsoleLogger;

import java.util.ArrayList;

public class Board {
    public static final String ITEM_ALREADY_IN_THE_LIST = "Item already in the list";

    private final ArrayList<BoardItem> items;

    public Board() {
        items = new ArrayList<>();
    }

    public void addItem(BoardItem item) {
        if (this.items.contains(item)){
            throw new IllegalArgumentException(ITEM_ALREADY_IN_THE_LIST);
        }

        items.add(item);
    }

    public int totalItems(){
        return items.size();
    }

    public void displayHistory(ConsoleLogger logger) {
        for (BoardItem item : items) {
           logger.log(item.getHistory());
        }
    }


}
