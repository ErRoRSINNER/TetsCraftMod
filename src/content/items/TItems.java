package content.items;

import arc.graphics.Color;
import mindustry.type.Item;

public class TItems {
    public static Item battery, bee, beeq, concrete, crystal, goddamm_ingot, hyperalloy,
            mica, nihonium, pingwan, poop, superconductor, tantalium, tets_ingot, vermillion, bing_qi_ling, tets_coin;

    public static void load() {
        tets_coin = new Item("tets_coin", Color.valueOf("fef37c")) {{
            hardness = 5;
        }};
        battery = new Item("battery", Color.valueOf("9badb7")) {{
            charge = 1.5f;
            cost = 2;
        }};
        bee = new Item("bee", Color.valueOf("fdff00")) {{
            cost = 0.5f;
        }};
        beeq = new Item("beeq", Color.valueOf("fdaa00")) {{
            cost = 1.5f;
        }};
        bing_qi_ling = new Item("bing_qi_ling", Color.valueOf("#fffdd0")) {{
            cost = 0.2f;
        }};
        crystal = new Item("crystal", Color.valueOf("C6F4FF")) {{
            cost = 3;
            radioactivity = 0.1f;
            healthScaling = 1f;
        }};
        concrete = new Item("concrete", Color.valueOf("716c69")) {{
            hardness = 2;
            cost = 2;
        }};
        goddamm_ingot = new Item("goddamm_ingot", Color.valueOf("#f9ff13")) {{
            cost = 6;
            radioactivity = 2;
        }};
        hyperalloy = new Item("hyperalloy", Color.valueOf("c83ca2")) {{
            cost = 5.5f;
            radioactivity = 0.2f;
            charge = 1;
        }};
        mica = new Item("mica", Color.valueOf("86aaa8")) {{
            cost = 0.4f;
            charge = 0.01f;
        }};
        nihonium = new Item("nihonium", Color.valueOf("dab1dc")) {{
            cost = 7;
            radioactivity = 4f;
            healthScaling = 4f;
        }};
        pingwan =  new Item("pingwan", Color.valueOf("#A8A7A6")) {{
            buildable = false;
        }};
        poop = new Item("poop", Color.valueOf("#CD9232")) {{
            cost = 0.3f;
            explosiveness = 0.5f;
        }};
        superconductor = new Item("superconductor", Color.valueOf("2fc791")) {{
            cost = 1.3f;
            charge = 0.2f;
        }};
        tantalium = new Item("tantalium", Color.valueOf("7496a7")) {{
            cost = 1.2f;
        }};
        tets_ingot = new Item("tets_ingot", Color.valueOf("53e6a4")) {{
            cost = 3f;
        }};
        vermillion = new Item("vermillion", Color.valueOf("e04938")) {{
            buildable = false;
        }};
    }
}
