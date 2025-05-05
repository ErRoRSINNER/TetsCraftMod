package content.units;

import mindustry.ai.UnitCommand;
import mindustry.ai.types.MinerAI;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.ctype.Content;
import mindustry.entities.Units;
import mindustry.entities.bullet.BombBulletType;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.world.meta.BlockFlag;

public class TUnits {

    //T1
    public static UnitType UFO, miniMiner;
    //T2
    public static UnitType UF1, mediMiner;
    //T3
    public static UnitType UF2;

    public static void init(){
        initTier1();
        initTier2();
        initTier3();
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

    private static void initTier2(){
        UF1 = new UnitType("uf1") {
            {
                this.speed = 3.4F;
                this.accel = 0.10F;
                this.drag = 0.05F;
                this.flying = true;
                this.health = 100.0F;
                armor = 4f;
                this.hitSize = 9.0F;
                this.itemCapacity = 80;
                this.engineOffset = 7.8F;
                this.targetFlags = new BlockFlag[]{BlockFlag.unitAssembler, null};
                constructor = UnitTypes.poly.constructor;
                this.ammoType = new PowerAmmoType(1500);
                this.weapons.add(new Weapon() {
                    {
                        this.minShootVelocity = 0.75F;
                        this.x = 3.0F;
                        this.shootY = 0.0F;
                        this.reload = 6.0F;
                        this.shootCone = 180.0F;
                        this.ejectEffect = Fx.teleport;
                        this.inaccuracy = 15.0F;
                        this.ignoreRotation = true;
                        this.shootSound = Sounds.none;
                        this.bullet = new BombBulletType(40.0F, 6.0F) {
                            {
                                this.width = 10.0F;
                                this.height = 14.0F;
                                this.hitEffect = Fx.hitLaserBlast;
                                this.shootEffect = Fx.none;
                                this.smokeEffect = Fx.none;
                                this.status = StatusEffects.shocked;
                                this.statusDuration = 120.0F;
                            }
                        };
                    }
                });
            }
        };
        mediMiner = new UnitType("medi_miner") {
            {
                this.defaultCommand = UnitCommand.mineCommand;
                this.flying = false;
                this.speed = 1.5F;
                this.health = 300.0F;
                this.range = 150.0F;
                this.isEnemy = false;
                this.ammoType = new PowerAmmoType(1000.0F);
                this.mineTier = 2;
                this.mineSpeed = 6F;
                constructor = UnitTypes.quasar.constructor;
                this.controller = (u) -> new MinerAI();
            }
        };
    }

    private static void initTier3(){
        UF2 = new UnitType("uf2") {
            {
                this.speed = 3.2F;
                this.accel = 0.8F;
                this.drag = 0.F;
                this.flying = true;
                this.health = 400.0F;
                armor = 12f;
                this.hitSize = 9.0F;
                this.itemCapacity = 80;
                this.engineOffset = 7.8F;
                this.targetFlags = new BlockFlag[]{BlockFlag.battery, null};
                constructor = UnitTypes.poly.constructor;
                this.ammoType = new PowerAmmoType(1500);
                this.weapons.add(new Weapon() {
                    {
                        this.minShootVelocity = 0.75F;
                        this.x = 3.0F;
                        this.shootY = 0.0F;
                        this.reload = 4.0F;
                        this.shootCone = 180.0F;
                        this.ejectEffect = Fx.teleport;
                        this.inaccuracy = 15.0F;
                        this.ignoreRotation = true;
                        this.shootSound = Sounds.none;
                        this.bullet = new BombBulletType(70.0F, 8.0F) {
                            {
                                this.width = 10.0F;
                                this.height = 14.0F;
                                this.hitEffect = Fx.hitLaserBlast;
                                this.shootEffect = Fx.none;
                                this.smokeEffect = Fx.none;
                                this.status = StatusEffects.shocked;
                                this.statusDuration = 120.0F;
                            }
                        };
                    }
                });
            }
        };
    }

}
