package content.units;

import mindustry.ai.UnitCommand;
import mindustry.ai.types.MinerAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BombBulletType;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.world.meta.BlockFlag;

public class TUnits {

    //Basic (T0)
    public static UnitType UFO, walkingBaseUnit;

    //T1
    public static UnitType miniMiner, smallArtillery;

    //T2
    public static UnitType UF1_Energy, mediMiner, mediumArtillery;

    //T3
    public static UnitType UF2_Energy;

    public static void init(){
        initBasicTier();
        initTier1();
        initTier2();
        initTier3();
    }

    private static void initBasicTier(){
        walkingBaseUnit = new UnitType("walking_unit_base") {
            {
                this.speed = 0.8F;
                this.hitSize = 8.0F;
                this.health = 50.0F;
                constructor = UnitTypes.dagger.constructor;
            }
        };
    }

    private static void initTier1(){
        smallArtillery = new UnitType("small_artillery") {
            {
                this.speed = 0.6F;
                this.hitSize = 8.0F;
                this.health = 120.0F;
                this.weapons.add(new Weapon("large-weapon") {
                    {
                        this.reload = 13.0F;
                        this.x = 4.0F;
                        this.y = 2.0F;
                        this.top = false;
                        this.ejectEffect = Fx.casing1;
                        this.bullet = new BasicBulletType(3.5F, 18.0F) {
                            {
                                this.width = 7.0F;
                                this.height = 9.0F;
                                this.lifetime = 35.0F;
                                buildingDamageMultiplier = 1.4f;
                            }
                        };
                    }
                });
                constructor = UnitTypes.dagger.constructor;
            }
        };
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
                this.itemCapacity = 60;
                constructor = UnitTypes.dagger.constructor;
                this.controller = (u) -> new MinerAI();
            }
        };
        UFO = new UnitType("ufo") {
            {
                this.speed = 3.8F;
                this.accel = 0.12F;
                this.drag = 0.05F;
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
        mediumArtillery = new UnitType("medium_artillery") {
            {
                this.speed = 0.3F;
                this.hitSize = 8.0F;
                this.health = 150.0F;
                this.weapons.add(new Weapon("large-weapon") {
                    {
                        this.reload = 300.0F;
                        this.x = 4.0F;
                        this.y = 2.0F;
                        this.top = false;
                        this.ejectEffect = Fx.casing1;
                        this.bullet = new BasicBulletType(4.0F, 40.0F) {
                            {
                                this.width = 7.0F;
                                this.height = 9.0F;
                                this.lifetime = 60.0F;
                                buildingDamageMultiplier = 1.6f;
                            }
                        };
                    }
                });
                constructor = UnitTypes.dagger.constructor;
            }
        };
        UF1_Energy = new UnitType("uf1_energy") {
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
                        this.reload = 5.0F;
                        this.shootCone = 0.0F;
                        this.ejectEffect = Fx.teleport;
                        this.inaccuracy = 5.0F;
                        this.ignoreRotation = true;
                        this.shootSound = Sounds.none;
                        this.bullet = new BombBulletType(60.0F, 16.0F) {
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
                this.itemCapacity = 120;
                constructor = UnitTypes.quasar.constructor;
                this.controller = (u) -> new MinerAI();
            }
        };
    }

    private static void initTier3(){
        UF2_Energy = new UnitType("uf2_energy") {
            {
                this.speed = 3.2F;
                this.accel = 0.8F;
                this.drag = 0.05F;
                this.flying = true;
                this.health = 340.0F;
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
                        this.reload = 4F;
                        this.shootCone = 0.0F;
                        this.ejectEffect = Fx.teleport;
                        this.inaccuracy = 5.0F;
                        this.ignoreRotation = true;
                        this.shootSound = Sounds.none;
                        this.bullet = new BombBulletType(70.0F, 32.0F) {
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
