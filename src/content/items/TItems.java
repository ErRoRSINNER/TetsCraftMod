package content.items;

import arc.graphics.Color;
import mindustry.type.Item;

public class TItems {
    public static Item battery, bee, beeq, capsule_of_oxigen, capsule_of_hydrogen, concrete, crystal, expl_mixture, goddamm_ingot, hyperalloy, low_quality_crystal,
            M_13_5, mica, neoch_pravos, nihonium, pingwan, poop, pravoslaviy, reinforced_glass, RM_20_17, superconductor, tantalium, tets_ingot, toxium, vermillion;

    public static void load() {
        battery = new Item("battery", Color.valueOf("9badb7")) {{
            charge = 1.5f;
            cost = 3;
        }};
        bee = new Item("bee", Color.valueOf("fdff00")) {{
            cost = 0.5f;
        }};
        beeq = new Item("beeq", Color.valueOf("fdaa00")) {{
            cost = 1.5f;
        }};
        capsule_of_oxigen = new Item("capsule_of_oxigen", Color.valueOf("#6dd5f8")) {{
            flammability = 3;
            explosiveness = 1;
            buildable = false;
        }};
        capsule_of_hydrogen = new Item("capsule_of_hydrogen", Color.valueOf("#0992f6")) {{
            flammability = 6;
            explosiveness = 1.5f;
            buildable = false;
        }};
        crystal = new Item("crystal", Color.valueOf("C6F4FF")) {{
            cost = 5;
            radioactivity = 0.1f;
            healthScaling = 1f;
        }};
        concrete = new Item("concrete", Color.valueOf("9badb7")) {{
            hardness = 2;
            cost = 2;
        }};
        expl_mixture = new Item("expl_mixture", Color.valueOf("#006fff")) {{
            flammability = 10;
            explosiveness = 5;
            buildable = false;
        }};
        goddamm_ingot = new Item("goddamm_ingot", Color.valueOf("#f9ff13")) {{
            cost = 6;
            radioactivity = 2;
        }};
        hyperalloy = new Item("hyperalloy", Color.valueOf("#bdff00")) {{
            cost = 5.5f;
            radioactivity = 0.2f;
            charge = 1;
        }};
        low_quality_crystal = new Item("low_quality_crystal", Color.valueOf("ffffff")) {{
            cost = 1;
        }};
        M_13_5 = new Item("M_13_5", Color.valueOf("#e5e7e6")) {{
            radioactivity = 0.05f;
            buildable = false;
        }};
        mica = new Item("mica", Color.valueOf("#9CA7A7")) {{
            cost = 0.4f;
            charge = 0.01f;
        }};
        neoch_pravos = new Item("neoch_pravos", Color.valueOf("#C1C43B")) {{
            hardness = 3;
            radioactivity = 0.1f;
            buildable = false;
        }};
        nihonium = new Item("nihonium", Color.valueOf("#DAB1DC")) {{
            cost = 7;
            radioactivity = 4f;
            healthScaling = 4f;
        }};
        pingwan =  new Item("pingwan", Color.valueOf("#A8A7A6")) {{
            buildable = false;
        }};
        poop = new Item("poop", Color.valueOf("#CD9232")) {{
            cost = 0.3f;
        }};
        pravoslaviy = new Item("pravoslaviy", Color.valueOf("#FFFCA3")) {{
            cost = 3;
            radioactivity = 0.5f;
        }};
        reinforced_glass = new Item("reinforced_glass", Color.valueOf("#82B5FF")) {{
            cost = 3;
            radioactivity = 0.1f;
        }};
        RM_20_17 = new Item("RM_20_17", Color.valueOf("#FF5049")) {{
            cost = 2;
            radioactivity = 1.2f;
            flammability = 1.6f;
            explosiveness = 1.1f;
            healthScaling = -1f;
        }};
        superconductor = new Item("superconductor", Color.valueOf("#FBD504")) {{
            cost = 1.2f;
            charge = 0.2f;
        }};
        tantalium = new Item("tantalium", Color.valueOf("#989195")) {{
            cost = 1.01f;
        }};
        tets_ingot = new Item("tets_ingot", Color.valueOf("#38EDD7")) {{
            cost = 1.98f;
        }};
        toxium = new Item("toxium", Color.valueOf("#F565CE")) {{
            cost = -1.1f;
            flammability = 2;
            explosiveness = 3;
        }};
        vermillion = new Item("vermillion", Color.valueOf("#F40B34")) {{
            buildable = false;
        }};
    }
}
