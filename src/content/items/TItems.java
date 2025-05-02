package content.items;

import arc.graphics.Color;
import mindustry.type.Item;

public class TItems {
    public static Item battery, bee, beeq, capsule_of_oxigen, capsule_of_hydrogen, concrete, crystal, expl_mixture, goddamm_ingot, hyperalloy, low_quality_crystal,
            M_13_5, mica, neoch_pravos, nihonium, pingwan, poop, pravoslaviy, RM_20_17, superconductor, tantalium, tets_ingot, toxium, vermillion;

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
        capsule_of_oxigen = new Item("capsule_of_oxigen") {{
            flammability = 3;
            explosiveness = 1;
            buildable = false;
        }};
        capsule_of_hydrogen = new Item("capsule_of_hydrogen") {{
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
        expl_mixture = new Item("expl_mixture") {{
            flammability = 10;
            explosiveness = 5;
            buildable = false;
        }};
        goddamm_ingot = new Item("goddamm_ingot") {{
            cost = 6;
            radioactivity = 2;
        }};
        hyperalloy = new Item("hyperalloy") {{
            cost = 5.5f;
            radioactivity = 0.2f;
            charge = 1;
        }};
        low_quality_crystal =
        M_13_5 = new Item("M_13_5") {{
            radioactivity = 0.05f;
            buildable = false;
        }};
        mica = new Item("mica") {{
            cost = 0.4f;
            charge = 0.01f;
        }};
        neoch_pravos = new Item("neoch_pravos") {{
            hardness = 3;
            radioactivity = 0.1f;
            buildable = false;
        }};
        nihonium = new Item("nihonium", Color.valueOf("DAB1DC")) {{
            cost = 7;
            radioactivity = 4f;
            healthScaling = 4f;
        }};
        pingwan =  new Item("pingwan") {{
            buildable = false;
        }};
        poop = new Item("poop") {{
            buildable = false;
        }};
        pravoslaviy = new Item("pravoslaviy") {{
            cost = 3;
            radioactivity = 0.5f;
        }};
        RM_20_17 = new Item("RM_20_17") {{
            cost = 2;
            radioactivity = 1.2f;
            flammability = 1.6f;
            explosiveness = 1.1f;
            healthScaling = -1f;
        }};
        superconductor = new Item("superconductor") {{
            cost = 1.2f;
            charge = 0.2f;
        }};
        tantalium = new Item("tantalium") {{
            cost = 1.01f;
        }};
        tets_ingot = new Item("tets_ingot") {{
            cost = 1.98f;
        }};
        toxium = new Item("toxium") {{
            cost = -1.1f;
            flammability = 2;
            explosiveness = 3;
        }};
        vermillion = new Item("vermillion") {{
            buildable = false;
        }};
    }
}
