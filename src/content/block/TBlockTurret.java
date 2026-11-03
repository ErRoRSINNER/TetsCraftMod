package content.block;

import arc.graphics.Color;
import arc.math.Interp;
import content.item.TItem;
import content.liquids.TLiquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.consumers.ConsumeCoolant;
import mindustry.world.draw.DrawTurret;

import static content.util.Util.setHealth;
import static mindustry.type.ItemStack.with;

public class TBlockTurret {

    public static Block bangun, govnomet, aacd_FIFNYA, hoover, beeshot, quick_fire, RMG202, teslaCoil, cirnoGun, goddamn_gun;

    protected static void loadTurrets() {
        goddamn_gun = new ItemTurret("goddamn_gun") {{
            requirements(Category.turret, with(TItem.tantalium, 33, TItem.goddamm_ingot, 33, TItem.bee, 33, TItem.beeq, 3));
            setHealth(this, 0.85f);
            size = 3;
            maxAmmo = 333;
            reload = 60;
            ammoPerShot = 33;
            shoot.shots = 33;
            range = 333;
            recoilTime = 6;
            rotateSpeed = 3;
            inaccuracy = 33.3f;
            shootCone = 33.3f;
            coolant = new ConsumeCoolant(0.333f);
            ammo(TItem.bee, new BasicBulletType(3.33f, 33.3f) {{
                lifetime = 111;
                splashDamage = 33.3f;
                splashDamageRadius = 33.3f;
                width = height = hitSize = 33;
                knockback = 3.33f;
                shrinkInterp = Interp.smooth;
                status = StatusEffects.shocked;
                ammoMultiplier = 3;
            }}, TItem.beeq, new BasicBulletType(3.33f, 44.4f) {{
                lifetime = 111;
                splashDamage = 33.3f;
                splashDamageRadius = 44.4f;
                width = height = hitSize = 33;
                knockback = 3.33f;
                shrinkInterp = Interp.smooth;
                status = StatusEffects.shocked;
                reloadMultiplier = 0.777f;
                ammoMultiplier = 3.33f;
            }}, TItem.goddamm_ingot, new BasicBulletType(3.33f, 77.7f) {{
                lifetime = 111;
                splashDamage = 44.4f;
                splashDamageRadius = 77.7f;
                width = height = hitSize = 33;
                knockback = 33.3f;
                shrinkInterp = Interp.smooth;
                status = StatusEffects.unmoving;
                reloadMultiplier = 0.666f;
                ammoMultiplier = 33;
            }});
            alwaysUnlocked = true;
        }};
        cirnoGun = new LiquidTurret("cirno_gun") {{
            //afflict
            requirements(Category.turret, with(Items.titanium, 99, TItem.bing_qi_ling, 99, Items.graphite, 99));
            ammo(
                    Liquids.water, new PointLaserBulletType() {{
                        knockback = 0.0F;
                        damage = 0.009F;
                        ammoMultiplier = 0.3F;
                        statusDuration = 540.0F;
                        color = Liquids.cryofluid.color;
                        status = StatusEffects.freezing;
                    }},
                    TLiquids.radioactive_waste, new PointLaserBulletType() {{
                        knockback = 0.0F;
                        damage = 4F;
                        ammoMultiplier = 0.5F;
                        statusDuration = 1000.0F;
                        color = Liquids.cyanogen.color;
                        status = StatusEffects.corroded;
                    }});
            setHealth(this);
            size = 3;
            range = 300;
            reload = 4.0F;
            velocityRnd = 0.0F;
            inaccuracy = 5.0F;
            recoil = 1.0F;
            shootCone = 60.0F;
            liquidCapacity = 150.0F;
            shootEffect = Fx.shootLiquid;
            unitSort = UnitSorts.strongest;

            drawer = new DrawTurret("cirno_gun") {{
                parts.add(new RegionPart("-barrel") {
                    {
                        progress = PartProgress.constant(1);
                        mirror = false;
                        under = false;
                        moveX = 0.0F;
                        moveY = 12.0F;
                    }
                }, new RegionPart("-wing") {
                    {
                        progress = PartProgress.warmup;
                        mirror = true;
                        under = false;
                        moveX = -17.0F;
                        moveY = 0.0F;
                        xScl = 1.8f;
                        yScl = 1.8f;
                    }
                }, new RegionPart("-wing") {
                    {
                        progress = PartProgress.warmup;
                        mirror = true;
                        under = false;
                        moveRot = -45f; //Upper Pair
                        moveX = -17.0F;
                        moveY = 10.0F;
                        xScl = 1.8f;
                        yScl = 1.8f;
                    }
                }, new RegionPart("-wing") {
                    {
                        progress = PartProgress.warmup;
                        mirror = true;
                        under = false;
                        moveRot = 45f; //Lower pair
                        moveX = -17.0F;
                        moveY = -10.0F;
                        xScl = 1.8f;
                        yScl = 1.8f;
                    }
                });
            }};
            alwaysUnlocked = true;
        }};

        teslaCoil = new PowerTurret("tesla_coil") {{
            requirements(Category.turret, ItemStack.with(Items.copper, 400, Items.titanium, 200, TItem.battery, 200));
            setHealth(this, 0.75f);
            range = 240.0F;
            shoot.firstShotDelay = 60.0F;
            recoil = 2.0F;
            reload = 2000.0F;
            shake = 3.0F;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 2;
            scaledHealth = 320.0F;
            targetAir = true;
            targetGround = false;
            moveWhileCharging = true;
            accurateDelay = false;
            shootSound = Sounds.shootLaser;
            consumePower(24.0F);
            coolant = new ConsumeCoolant(0.1f);
            coolantMultiplier = 1;
            shootType = new LightningBulletType() {
                {
                    damage = 600;
                    chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);
                    buildingDamageMultiplier = 0.01F;
                    hitEffect = Fx.hitLancer;
                    hitSize = 8.0F;
                    lifetime = 16.0F;
                    drawSize = 400.0F;
                    collidesAir = true;
                    collideFloor = false;
                    collidesGround = false;
                    ammoMultiplier = 1.0F;
                    pierceCap = 8;
                }
            };
        }};
        RMG202 = new LiquidTurret("RMG202") {{
            requirements(Category.turret, with(TItem.tantalium, 700, TItem.tets_ingot, 125, Items.metaglass, 525, Items.graphite, 250));
            setHealth(this, 0.56f);
            size = 2;
            range = 300;
            reload = 2.0F;
            velocityRnd = 0.1F;
            inaccuracy = 5.0F;
            recoil = 1.0F;
            shootCone = 60.0F;
            liquidCapacity = 150.0F;
            shootEffect = Fx.shootLiquid;
            ammo(TLiquids.mercury, new LiquidBulletType(TLiquids.mercury) {{
                lifetime = 75;
                speed = 4.0F;
                knockback = 1.3F;
                puddleSize = 8.0F;
                orbSize = 4.0F;
                damage = 78.5F;
                drag = 0.005F;
                ammoMultiplier = 0.4F;
                statusDuration = 240.0F;
                status = StatusEffects.melting;
            }}, TLiquids.red_mercury, new LiquidBulletType(TLiquids.red_mercury) {{
                lifetime = 100;
                speed = 3.0F;
                knockback = 1.3F;
                puddleSize = 8.0F;
                orbSize = 4.0F;
                damage = 133F;
                drag = 0.005F;
                ammoMultiplier = 0.4F;
                statusDuration = 240.0F;
                status = StatusEffects.unmoving;
            }}, TLiquids.radioactive_waste, new LiquidBulletType(TLiquids.radioactive_waste) {{
                lifetime = 50;
                speed = 3.0F;
                knockback = 0.5F;
                puddleSize = 9.0F;
                orbSize = 4.0F;
                damage = 53F;
                drag = 0.005F;
                ammoMultiplier = 0.4F;
                statusDuration = 740.0F;
                status = StatusEffects.corroded;
            }});
            alwaysUnlocked = true;
        }};
        quick_fire = new ItemTurret("quick-fire") {{
            requirements(Category.turret, with(Items.lead, 75, Items.graphite, 25, Items.metaglass, 25, Items.copper, 125));
            setHealth(this);
            size = 2;
            inaccuracy = 15;
            reload = 5;
            shootCone = 20;
            rotateSpeed = 3;
            shootSound = Sounds.shootLaser;
            range = 160;
            coolant = new ConsumeCoolant(0.1f);
            ammo(Items.copper, new BasicBulletType(4, 7) {{
                        hitSize = 4;
                        width = 4;
                        height = 10;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                    }},
                    Items.coal, new FireBulletType(3.5f, 12) {{
                        lifetime = 55;
                        reloadMultiplier = 0.9f;
                        hitSize = 7;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3;
                        status = StatusEffects.burning;
                    }},
                    Items.pyratite, new FireBulletType(3f, 30) {{
                        lifetime = 50;
                        hitSize = 7;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 4;
                        status = StatusEffects.burning;
                        statusDuration = 60 * 20;
                    }},
                    Items.silicon, new BasicBulletType(2.75f, 26) {{
                        lifetime = 72;
                        hitSize = 4;
                        width = 4;
                        height = 5;
                        rangeChange = 40;
                        reloadMultiplier = 0.80f;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3;
                        homingPower = 0.08f;
                        homingRange = 50f;
                    }},
                    TItem.poop, new FlakBulletType(4.5f, 4) {{
                        splashDamage = 12;
                        ammoMultiplier = 4;
                        reloadMultiplier = 0.3333f;
                        status = StatusEffects.slow;
                    }});
            alwaysUnlocked = true;
        }};
        beeshot = new ItemTurret("beeshot") {{
            requirements(Category.turret, with(TItem.mica, 200, TItem.bee, 525, TItem.beeq, 25, Items.copper, 125, Items.scrap, 225));
            setHealth(this, 0.742f);
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 15;
            reload = 240;
            shootCone = 30;
            rotateSpeed = 4;
            shootSound = Sounds.shoot;
            xRand = 3;
            coolant = new ConsumeCoolant(0.1f);
            shoot = new ShootBarrel() {
                {
                    barrels = new float[]{
                            -3.5F, -2.0F, 0.0F,
                            -2.5F, -3.0F, 0.0F,
                            -1.5F, -2.0F, 0.0F,
                            -0.5F, -3.0F, 0.0F,
                            0.0F, -2.0F, 0.0F,
                            0.5F, -3.0F, 0.0F,
                            1.5F, -2.0F, 0.0F,
                            2.5F, -3.0F, 0.0F,
                            3.5F, -2.0F, 0.0F};
                    shots = 11;
                    shotDelay = 7.0F;
                }
            };
            range = 300;
            ammo(TItem.bee, new MissileBulletType(1, 15.5f, "tets-craft-mod-bee") {{
                        backColor = new Color(0, 0, 0, 0);
                        rotationOffset = 90;
                        splashDamage = 50;
                        splashDamageRadius = 50;
                        knockback = 0.2f;
                        status = StatusEffects.shocked;
                        homingPower = 0.2f;
                        hitSize = 7;
                        width = 10;
                        height = 10;
                        lifetime = 250;
                        shootEffect = Fx.steam;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 1f;
                    }},
                    TItem.beeq, new MissileBulletType(0.6f, 18.5f, "tets-craft-mod-beeq") {{
                        backColor = new Color(0, 0, 0, 0);
                        rotationOffset = 90;
                        splashDamage = 99;
                        splashDamageRadius = 80;
                        knockback = 1.4f;
                        status = StatusEffects.shocked;
                        homingPower = 0.3f;
                        hitSize = 10;
                        width = 12;
                        height = 12;
                        lifetime = 450;
                        shootEffect = Fx.steam;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3f;
                    }});
            alwaysUnlocked = true;
        }};
        hoover = new ItemTurret("hoover") {{
            requirements(Category.turret, with(TItem.tantalium, 220, TItem.battery, 200, Items.thorium, 25, TItem.crystal, 5, TItem.superconductor, 25));
            setHealth(this, 0.6657f);
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 0.1f;
            reload = 300;
            shootCone = 5;
            rotateSpeed = 1;
            shootSound = Sounds.shootLaser;
            range = 1000;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItem.crystal, new BasicBulletType(19, 200) {{
                        lifetime = 1000 / 18f;
                        absorbable = false;
                        healPercent = 100;
                        healAmount = 500;
                        status = StatusEffects.disarmed;
                        hitSize = 6;
                        width = 3;
                        height = 50;
                        shootEffect = Fx.lightningShoot;
                        hitEffect = Fx.hitFlameSmall;
                        pierce = true;
                        pierceBuilding = true;
                        pierceCap = 10;
                        ammoMultiplier = 4;
                        shoot = new ShootPattern() {{
                            shots = 2;
                            shotDelay = 2;
                        }};
                        fragBullets = 4;
                        fragBullet = new BasicBulletType(1.9f, 20) {{
                            healPercent = 100;
                            healAmount = 50;
                            status = StatusEffects.disarmed;
                            hitSize = 3;
                            width = 1;
                            height = 5;
                            shootEffect = Fx.lightningShoot;
                            hitEffect = Fx.hitFlameSmall;
                            pierce = true;
                            pierceBuilding = true;
                            pierceCap = 5;
                        }};

                        intervalBullet = new BasicBulletType(1, 5) {{
                            lifetime = 20;
                            width = height = 4;

                            trailEffect = Fx.colorTrail;
                        }};
                        bulletInterval = 1f;
                        intervalBullets = 2;
                        intervalSpread = 30 + 400 / speed;
                        intervalAngle = 180f;
                        intervalRandomSpread = 1.5f / speed;
                    }},
                    TItem.battery, new BasicBulletType(12, 50) {{
                        lifetime = 1000 / 11f;
                        status = StatusEffects.electrified;
                        absorbable = false;
                        splashDamage = 75;
                        splashDamageRadius = 99;
                        splashDamagePierce = true;
                        lightningDamage = 20;
                        lightningLength = 7;
                        hitSize = 6;
                        width = 3;
                        height = 50;
                        hitEffect = Fx.hitFlameSmall;
                        pierceCap = 5;
                        ammoMultiplier = 1.4f;

                        intervalBullet = new BasicBulletType(1, 5) {{
                            lifetime = 20;
                            width = height = 4;

                            trailEffect = Fx.colorTrail;
                        }};
                        bulletInterval = 1f;
                        intervalBullets = 2;
                        intervalSpread = 30 + 400 / speed;
                        intervalAngle = 180f;
                        intervalRandomSpread = 1.5f / speed;
                    }},
                    TItem.superconductor, new BasicBulletType(20, 220) {{
                        lifetime = 1000 / 19f;
                        status = StatusEffects.shocked;
                        absorbable = false;
                        reloadMultiplier = 1.15f;
                        splashDamage = 50;
                        splashDamageRadius = 10;
                        splashDamagePierce = true;
                        hitSize = 6;
                        width = 3;
                        height = 50;
                        hitEffect = Fx.hitFlameSmall;
                        pierce = true;
                        ammoMultiplier = 2.5f;

                        intervalBullet = new BasicBulletType(1, 5) {{
                            lifetime = 20;
                            width = height = 4;

                            trailEffect = Fx.colorTrail;
                        }};

                        bulletInterval = 1f;
                        intervalBullets = 2;
                        intervalSpread = 30 + 400 / speed;
                        intervalAngle = 180f;
                        intervalRandomSpread = 1.5f / speed;
                    }},
                    TItem.tantalium, new BasicBulletType(13, 100) {{
                        lifetime = 1000 / 12f;
                        status = StatusEffects.corroded;
                        absorbable = false;
                        reloadMultiplier = 0.95f;
                        splashDamage = 25;
                        splashDamageRadius = 15;
                        splashDamagePierce = true;
                        hitSize = 6;
                        width = 3;
                        height = 50;
                        hitEffect = Fx.hitFlameSmall;
                        pierceCap = 10;
                        ammoMultiplier = 1f;

                        intervalBullet = new BasicBulletType(1, 5) {{
                            lifetime = 20;
                            width = height = 4;

                            trailEffect = Fx.colorTrail;
                        }};
                        bulletInterval = 1f;
                        intervalBullets = 2;
                        intervalSpread = 30 + 400 / speed;
                        intervalAngle = 180f;
                        intervalRandomSpread = 1.5f / speed;
                    }}
            );
            alwaysUnlocked = true;
        }};
        aacd_FIFNYA = new ItemTurret("aacd_FIFNYA") {{
            requirements(Category.turret, with(TItem.concrete, 125, TItem.tantalium, 40, Items.metaglass, 30));
            setHealth(this);
            targetAir = true;
            targetGround = false;
            size = 3;
            inaccuracy = 5;
            reload = 48;
            shootCone = 30;
            rotateSpeed = 5;
            ammoPerShot = 3;
            xRand = 4;
            range = 220f;
            shootY = 4.5F;
            coolant = new ConsumeCoolant(0.1f);
            shoot = new ShootBarrel() {
                {
                    barrels = new float[]{
                            -1.5F, -2.25F, 0.0F,
                            -1.5F, -2.25F, 0.0F,
                            1.5F, -2.25F, 0.0F,
                            1.5F, -2.25F, 0.0F};
                    shots = 4;
                    shotDelay = 3.0F;
                }
            };
            ammo(Items.sand, new MissileBulletType(6, 24) {{
                        status = StatusEffects.slow;
                        hitSize = 7;
                        width = 4;
                        height = 4;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3.4f;
                    }},
                    Items.silicon, new MissileBulletType(6, 30) {{
                        lifetime = 50f;
                        status = StatusEffects.electrified;
                        hitSize = 7;
                        width = 4;
                        height = 4;
                        rangeChange = 100;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 2f;
                    }}
            );
            alwaysUnlocked = true;
        }};
        bangun = new ItemTurret("bangun") {{
            requirements(Category.turret, with(TItem.concrete, 1500, TItem.tantalium, 334, TItem.tets_ingot, 160));
            setHealth(this);
            targetAir = false;
            size = 8;
            range = 1000;
            minRange = 70f;
            rotateSpeed = 0.15f;
            inaccuracy = 10f;
            reload = 360f;
            ammoEjectBack = 50f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 10;
            velocityRnd = 0.7f;
            recoil = 9f;
            shake = 5f;
            shootSound = Sounds.explosion;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItem.concrete, new ArtilleryBulletType(7.5f, 100, "shell") {{
                knockback = 4f;
                lifetime = 8000f;
                width = height = 30f;
                collidesTiles = false;
                splashDamageRadius = 400f;
                hitEffect = Fx.explosion;
                splashDamage = 145f;
                fragBullet = new FlakBulletType(3.5f, 115) {{
                    width = 10f;
                    height = 12f;
                    shrinkY = 1f;
                    lifetime = 30f;
                    despawnEffect = Fx.absorb;
                    hitEffect = Fx.plasticExplosionFlak;
                    collidesAir = false;
                    fragBullet = new FlakBulletType(1.5f, 15) {{
                        width = 4f;
                        height = 5f;
                        shrinkY = 1f;
                        lifetime = 15f;
                        despawnEffect = Fx.flakExplosionBig;
                        collidesAir = false;
                    }};
                    fragBullets = 3;
                }};
                fragBullets = 11;
            }});
            alwaysUnlocked = true;
        }};
        govnomet = new ItemTurret("govnomet") {{
            requirements(Category.turret, with(TItem.concrete, 50, TItem.poop, 20, TItem.tantalium, 1));
            setHealth(this, 0.7f);
            targetAir = true;
            size = 4;
            range = 200;
            minRange = 10f;
            rotateSpeed = 0.6f;
            inaccuracy = 1f;
            reload = 30f;
            ammoEjectBack = 20f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 4;
            velocityRnd = 0.7f;
            recoil = 4f;
            shake = 2f;
            shootSound = Sounds.explosion;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItem.poop, new ArtilleryBulletType(2.5f, 3, "poop") {{
                knockback = 4f;
                lifetime = 800f;
                width = height = 10f;
                collidesTiles = false;
                splashDamageRadius = 16f;
                hitEffect = Fx.blastExplosion;
                splashDamage = 4f;
                fragBullet = new BasicBulletType(4f, 3, "poop") {{
                    width = 5f;
                    height = 5f;
                    shrinkY = 1f;
                    lifetime = 30f;
                    despawnEffect = Fx.absorb;
                    hitEffect = Fx.blastExplosion;
                    collidesAir = true;
                    fragBullet = new BasicBulletType(2f, 3, "poop") {{
                        width = 2f;
                        height = 2f;
                        shrinkY = 1f;
                        lifetime = 15f;
                        despawnEffect = Fx.blastExplosion;
                        collidesAir = true;
                    }};
                    fragBullets = 5;
                }};
                fragBullets = 10;
            }});
            alwaysUnlocked = true;
        }};
    }
}
