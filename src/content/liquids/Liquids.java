package content.liquids;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class Liquids {
    public static Liquid honey, liquid_hydrogen, liquid_matter, liquid_oxygen, super_cryofluid;
    public static void load() {
        honey = new Liquid("honey", Color.valueOf("EEEEAA")) {{
            heatCapacity = 0.1f;
            effect = StatusEffects.slow;
            boilPoint = 0.1f;
            viscosity = 0.75f;
            coolant = false;
        }};
        liquid_hydrogen = new Liquid("liquid_hydrogen") {{
            flammability = 1f;
            explosiveness = 1f;
            coolant = false;
        }};
        liquid_matter = new Liquid("liquid_matter", Color.valueOf("C241B5")) {{
            temperature = 1.5f;
            flammability = 1.1f;
            heatCapacity = 10f;
            effect = StatusEffects.burning;
            boilPoint = 0.01f;
            viscosity = 0.01f;
            coolant = false;
        }};
        liquid_oxygen = new Liquid("liquid_oxygen") {{
            flammability = 2f;
            explosiveness = 1f;
            coolant = false;
        }};
        super_cryofluid = new Liquid("super_cryofluid", Color.valueOf("00FFD4")){{
            heatCapacity = 1.9f;
            temperature = 0.04f;
            effect = StatusEffects.freezing;
            lightColor = Color.valueOf("0097f5").a(0.4f);
            boilPoint = 0.55f;
            viscosity = 0.04f;
            gasColor = Color.valueOf("c1e8f5");
        }};
    }
}
