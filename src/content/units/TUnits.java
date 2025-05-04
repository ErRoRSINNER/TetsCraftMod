package content.units;

import mindustry.ai.UnitCommand;
import mindustry.ai.types.MinerAI;
import mindustry.content.UnitTypes;
import mindustry.ctype.Content;
import mindustry.entities.Units;
import mindustry.gen.LegsUnit;
import mindustry.gen.MechUnit;
import mindustry.gen.Unit;
import mindustry.gen.UnitEntityLegacyAlpha;
import mindustry.type.UnitType;
import mindustry.type.ammo.PowerAmmoType;

public class TUnits {

    public static UnitType UFO, miniMiner;

    public static void init(){
        initTier1();
    }

    private static void initTier1(){
        miniMiner = new UnitType("mini_miner") {
            {
                this.defaultCommand = UnitCommand.mineCommand;
                this.flying = false;
                this.speed = .5F;
                this.health = 100.0F;
                this.range = 100.0F;
                this.isEnemy = false;
                this.ammoType = new PowerAmmoType(500.0F);
                this.mineTier = 1;
                this.mineSpeed = 3F;
                constructor = UnitTypes.dagger.constructor;
                this.controller = (u) -> new MinerAI();
            }
        };
        UFO = new UnitType("ufo") {
            {
                this.speed = 3.8F;
                this.accel = 0.12F;
                this.drag = 0.04F;
                this.flying = true;
                this.health = 4.0F;
                this.hitSize = 9.0F;
                this.itemCapacity = 40;
                this.engineOffset = 5.75F;
                constructor = UnitTypes.poly.constructor;
            }
        };
    }

}
