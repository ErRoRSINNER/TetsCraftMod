package content.items;

import arc.graphics.Color;
import mindustry.type.Item;

public class Items {
    public static Item concrete;

    public static void load() {
        concrete = new Item("concrete", Color.valueOf("9badb7")) {{
            hardness = 2;
            cost = 2;
        }};
    }
}
