package content.liquids;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class TLiquids {
    public static Liquid honey, liquid_matter, super_cryofluid, mercury, red_mercury, steam;
    public static void load() {
        steam = new Liquid("steam", Color.valueOf("e6e6e6")){{
            gas = true;
            color = Color.valueOf("e6e6e6");
            gasColor = Color.valueOf("e6e6e6");
            barColor = Color.valueOf("e6e6e6");
            temperature = 1;
            heatCapacity = 1;
            flammability = 0;
            effect = StatusEffects.burning;
            viscosity = 0;
            lightColor = Color.valueOf("f2f2f2");
            coolant = false;
            boilPoint = 60;
            alwaysUnlocked = true;
        }};
        honey = new Liquid("honey", Color.valueOf("EEEEAA")) {{
            heatCapacity = 0.1f;
            effect = StatusEffects.slow;
            boilPoint = 0.1f;
            viscosity = 0.75f;
            coolant = false;
        }};
        liquid_matter = new Liquid("liquid_matter", Color.valueOf("C241B5")) {{
            temperature = 1.5f;
            flammability = 1.1f;
            explosiveness = 0.4f;
            heatCapacity = 10f;
            effect = StatusEffects.burning;
            boilPoint = 0.01f;
            viscosity = 0.01f;
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
        mercury = new Liquid("mercury", Color.valueOf("e5e7e6")){{
            heatCapacity = 0.6f;
            temperature = 0.55f;
            effect = StatusEffects.electrified;
            lightColor = Color.valueOf("e5e7e6").mul(1.1f).a(0.4f);
            boilPoint = 0.4f;
            viscosity = 0.6f;
            gasColor = Color.valueOf("e5e7e6").mul(1.1f);
        }};
        red_mercury = new Liquid("red_mercury", Color.valueOf("FF5049")){{
            heatCapacity = 0.8f;
            temperature = 0.65f;
            effect = StatusEffects.melting;
            lightColor = Color.valueOf("FF5049").a(0.4f);
            boilPoint = 0.4f;
            viscosity = 0.6f;
            gasColor = Color.valueOf("FF5049").add(Color.valueOf("e5e7e6"));
        }};
    }
}
