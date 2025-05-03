package content.blocks;

import content.items.TItems;
import content.liquids.TLiquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class TBlocks {
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill, bangun, govnomet, teleporter, tets_conveyor, solpanel, tets_battery, crystal_powerblock,
            estrella_de_platino, small_shield_projector, concrete_wall, concrete_wall_large, prav_wall, prav_wall_large, daew, poop_wall, battery_factory, bee_plant,
            concrete_mixer, crystalizer, shit_mixer, vermillion, neoch_pravos, tantalium_factory, mica_press, mercury_purificator, tetsonator, aacd_FIFNYA, hoover;

    public static void load() {
        loadItemCrafting();
        loadLiquidCrafting();
        loadDefenses();
        loadDrills();
        loadEffects();
        loadOres();
        loadPower();
        loadTurrets();
        loadDistributions();
        loadOther();
    }
    private static void loadItemCrafting() {
        shit_mixer = new GenericCrafter("shit_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            hasItems = true;
            craftTime = 190.0F;
            outputItem = new ItemStack(TItems.poop, 7);
            size = 3;
            health = 420;
            hasPower = true;
            craftEffect = Fx.vapor;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 1.1f, true));
            consumeLiquid(Liquids.water, 0.33f);
            consumePower(0.07F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.sporePod, 3, Items.lead, 2, Items.copper, 1));
        }};
        battery_factory = new GenericCrafter("battery_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            hasItems = true;
            craftTime = 90.0F;
            outputItem = new ItemStack(TItems.battery, 7);
            size = 2;
            health = 420;
            hasPower = true;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.silicon, 2, Items.lead, 2, Items.copper, 1));
        }};
        tetsonator = new GenericCrafter("tetsonator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 80, TItems.concrete, 125, Items.metaglass, 40));
            craftTime = 44.44F;
            outputItem = new ItemStack(TItems.tets_ingot, 2);
            size = 2;
            health = 420;
            hasPower = hasItems = hasLiquids = true;
            craftEffect = Fx.freezing;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.titanium, 1, TItems.tantalium, 1));
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.04444f, TLiquids.mercury, 0.2f));
        }};
        tantalium_factory = new Separator("tantalium_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, Items.titanium, 30, Items.graphite, 40, Items.copper, 120));
            craftTime = 100.0F;
            results = ItemStack.with(TItems.tantalium, 10, new ItemStack(TItems.tantalium, 2), 5, Items.scrap, 1);
            size = 2;
            health = 420;
            hasPower = hasItems = hasLiquids = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.28F);
            consumeItems(ItemStack.with(Items.titanium, 1, Items.graphite, 1));
            consumeLiquid(Liquids.water, 0.1f);
        }};
        crystalizer = new GenericCrafter("crystalizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.silicon, 14, TItems.tantalium, 15, Items.metaglass, 10));
            craftTime = 180.0F;
            outputItem = new ItemStack(TItems.crystal, 1);
            health = 120;
            hasPower = hasLiquids = hasItems = true;
            craftEffect = Fx.healWave;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.0F);
            consumeItems(ItemStack.with(TItems.mica, 1, Items.silicon, 1));
            consumeLiquid(TLiquids.mercury, 0.033f);
        }};
        mercury_purificator = new GenericCrafter("mercury_purificator") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.graphite, 15, Items.metaglass, 10));
            craftTime = 30.0F;
            outputLiquid = new LiquidStack(TLiquids.mercury, 0.1f);
            health = 120;
            hasLiquids = hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeItems(ItemStack.with(TItems.vermillion, 2));
        }};
        bee_plant = new Separator("bee-plant") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 100, Items.graphite, 50, Items.silicon, 5));
            results = ItemStack.with(TItems.bee, 10, TItems.beeq, 1);
            hasPower = true;
            craftTime = 60.0F;
            size = 3;
            itemCapacity = 20;
            consumePower(0.2F);
            consumeItem(TItems.bing_qi_ling, 1);
            consumeLiquid(Liquids.water, 0.04F);
        }};
        mica_press = new GenericCrafter("mica-press") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            hasItems = true;
            craftTime = 290.0F;
            outputItem = new ItemStack(TItems.mica, 3);
            size = 2;
            health = 420;
            hasPower = true;
            craftEffect = Fx.smokeCloud;
            drawer = new DrawMulti(new DrawDefault());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.graphite, 2, Items.silicon, 2));
        }};
        concrete_mixer = new Separator("concrete_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 60, Items.silicon, 12, Items.lead, 35));
            results = ItemStack.with(TItems.concrete, 7, Items.scrap, 2);
            hasPower = true;
            craftTime = 20.0F;
            size = 2;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotor", 3,true));
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sand, 3, Items.lead, 1, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.1F);
        }};
    }
    private static void loadLiquidCrafting() {

    }
    private static void loadDefenses() {
        concrete_wall = new Wall("concrete_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.concrete, 50));
            health = 220 * 3;
        }};
        concrete_wall_large = new Wall("concrete_wall-large") {{
            requirements(Category.defense, ItemStack.mult(TBlocks.concrete_wall.requirements, 4));
            health = 220 * 3 * 4;
            size = 2;
        }};
        prav_wall = new Wall("prav_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.pravoslaviy, 33, TItems.beeq, 3));
            health = 333 * 3;
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.lasercharge;
        }};
        prav_wall_large = new Wall("prav_wall-large") {{
            requirements(Category.defense, ItemStack.mult(TBlocks.prav_wall.requirements, 4));
            health = 333 * 3 * 4;
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.lasercharge;
            size = 2;
        }};
        daew = new ShieldWall("DAEW") {{
            requirements(Category.defense, ItemStack.with(TItems.crystal, 5, TItems.tets_ingot, 15, TItems.tantalium, 110, TItems.superconductor, 12));
            size = 2;
        }};
        poop_wall = new Wall("poop_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.poop, 2));
            size = 2;
            health = 10;
            this.researchCostMultiplier = 200F;
        }};
    }
    private static void loadDrills() {
        homoDrill = new Drill("homo-drill") {{
            requirements(Category.production, ItemStack.with(Items.copper, 25, Items.lead, 5)); //TODO
            tier = 2;
            drillTime = 450.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100});
            consumeLiquid(Liquids.water, 0.06F).boost();
            health = 100;
        }};
        miniDrill = new Drill("mini-drill") {{
            requirements(Category.production, ItemStack.with(TItems.concrete, 12, TItems.tantalium, 10)); //TODO
            tier = 4;
            drillTime = 180.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{TItems.concrete, 100, TItems.tantalium, 60});
            consumeLiquid(Liquids.water, 0.08F).boost();

            health = 120;
        }};
        nihonDrill = new Drill("nihon-drill") {{
            requirements(Category.production, ItemStack.with(TItems.nihonium, 240, Items.lead, 24)); //TODO
            tier = 6;
            drillTime = 200.0F;
            size = 5;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.water, 0.1F).boost();

            health = 180;
        }};
        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(TItems.concrete, 320, TItems.tets_ingot, 69, TItems.battery, 30));
            tier = 5;
            size = 3;
            drillTime = 50;
            consumeLiquid(Liquids.water, 0.1F).boost();
        }};
    }
    private static void loadEffects() {
        small_shield_projector = new ForceProjector("small_shield_projector") {{
            requirements(Category.effect, ItemStack.with(TItems.tantalium, 50, Items.titanium, 75, Items.silicon, 70, TItems.battery, 10));
            size = 2;
            radius = 75F;
            shieldHealth = 750.0F;
            cooldownNormal = 1.5F;
            cooldownLiquid = 1.2F;
            cooldownBrokenBase = 0.35F;
            consumeItem(TItems.bee, 8).boost();
            phaseRadiusBoost = 40.0F;
            phaseShieldBoost = 10;
            consumePower(3.0F);
        }};
        estrella_de_platino = new OverdriveProjector("estrella_de_platino") {{
            requirements(Category.effect, ItemStack.with(TItems.battery, 1, TItems.tets_ingot, 3, TItems.concrete, 10, TItems.superconductor, 12));
            consumePower(3F);
            consumeItem(TItems.bee, 5).boost();
            speedBoostPhase = 0.2f;
            phaseRangeBoost = 10;
            range = 45.0F;
            speedBoost = 1.5F;
            useTime = 400.0F;
            consumeItems(ItemStack.with(TItems.battery, 1));
        }};
    }
    private static void loadOres() {
        vermillion = new OreBlock("vermillion", TItems.vermillion){
            {
                this.oreDefault = true;
                this.oreThreshold = 0.9F;
                this.oreScale = 15.3F;
            }
        };
        neoch_pravos = new OreBlock("neoch-pravos", TItems.neoch_pravos){
            {
                this.oreDefault = true;
                this.oreThreshold = 0.95F;
                this.oreScale = 9.47619F;
            }
        };
    }
    private static void loadPower() {
        solpanel = new SolarGenerator("solpanel") {{
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 25, TItems.tantalium, 15, TItems.battery, 10, Items.silicon, 55));
            size = 2;
            powerProduction = 1.1F;
            hasPower = true;
            baseExplosiveness = 6;
            consumePowerBuffered(10000);
        }};
        tets_battery = new Battery("tets_battery") {{
            size = 2;
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 15, TItems.tantalium, 15, TItems.battery, 50, Items.silicon, 30));
            consumePowerBuffered(70000);
            baseExplosiveness = 7;
        }};
        crystal_powerblock = new Battery("crystal_powerblock") {{
            size = 2;
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 55, TItems.tantalium, 30, TItems.battery, 10,TItems.crystal, 110, Items.silicon, 40, TItems.superconductor, 10));
            consumePowerBuffered(404000);
            baseExplosiveness = 7;
        }};
    }
    private static void loadTurrets() {
        hoover = new ItemTurret("hoover") {{
            requirements(Category.turret, with(TItems.tantalium, 200, TItems.battery, 400, Items.thorium, 25, TItems.crystal, 25, TItems.mica, 100));
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 1;
            reload = 220;
            shootCone = 1;
            rotateSpeed = 20;
            shootSound = Sounds.laserbig;
            shoot = new ShootPattern() {{shots = 2; shotDelay = 2;}};
            range = 500;
            ammo(TItems.crystal, new BasicBulletType(19, 200) {{
                healPercent = 1;
                healAmount = 500;
                status = StatusEffects.disarmed;
                hitSize = 6;
                width = 3;
                height = 50;
                shootEffect = Fx.lightningShoot;
                hitEffect = Fx.hitFlameSmall;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 100;
                ammoMultiplier = 4;
                fragBullets = 4;
                fragBullet = new BasicBulletType(1.9f, 20) {{
                    healPercent = 1;
                    healAmount = 50;
                    status = StatusEffects.disarmed;
                    hitSize = 3;
                    width = 1;
                    height = 5;
                    shootEffect = Fx.lightningShoot;
                    hitEffect = Fx.hitFlameSmall;
                    pierce = true;
                    pierceBuilding = true;
                    pierceCap = 10;
                }};
            }},
                    TItems.battery, new FlakBulletType(12, 10) {{
                        status = StatusEffects.electrified;
                        splashDamage = 75;
                        splashDamageRadius = 99;
                        splashDamagePierce = true;
                        lightningDamage = 20;
                        lightningLength = 7;
                        hitSize = 6;
                        width = 3;
                        height = 50;
                        hitEffect = Fx.hitFlameSmall;
                        pierceCap = 25;
                        ammoMultiplier = 1.4f;
                    }}
            );
        }};
        aacd_FIFNYA = new ItemTurret("aacd_FIFNYA") {{
            requirements(Category.turret, with(TItems.concrete, 125, TItems.tantalium, 20, Items.metaglass, 25));
            targetAir = true;
            targetGround = false;
            size = 3;
            inaccuracy = 25;
            reload = 12;
            shootCone = 30;
            rotateSpeed = 5;
            ammoPerShot = 3;
            xRand = 4;
            range = 178.98f;
            shootY = 4.5F;
            this.shoot = new ShootBarrel() {
                {
                    this.barrels = new float[]{
                            -4.0F, -1.25F, 0.0F,
                            -2.0F, -1.25F, 0.0F,
                             2.0F, -1.25F, 0.0F,
                             4.0F, -1.25F, 0.0F};
                    this.shots = 4;
                    this.shotDelay = 3.0F;
                }
            };
            ammo(Items.sand, new MissileBulletType(30, 6) {{
                status = StatusEffects.tarred;
                hitSize = 7;
                width = 4;
                height = 4;
                shootEffect = Fx.shootPyraFlame;
                hitEffect = Fx.hitFlameSmall;
                ammoMultiplier = 3.4f;
            }});
        }};
        bangun = new ItemTurret("bangun") {{
            requirements(Category.turret, with(TItems.concrete, 150, TItems.tantalium, 135, TItems.tets_ingot, 60));
            ammo(TItems.concrete, new ArtilleryBulletType(1.5f, 500, "shell") {{
                knockback = 1f;
                lifetime = 800f;
                width = height = 30f;
                collidesTiles = false;
                splashDamageRadius = 3000f;
                hitEffect = Fx.blastExplosion;
                splashDamage = 145f;
                fragBullet = new BasicBulletType(2.5f, 115, "shell"){{
                    width = 10f;
                    height = 12f;
                    shrinkY = 1f;
                    lifetime = 30f;
                    despawnEffect = Fx.absorb;
                    hitEffect = Fx.plasticExplosionFlak;
                    collidesAir = false;
                    fragBullet = new BasicBulletType(1.5f, 15, "shell"){{
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
            targetAir = false;
            size = 8;
            range = 1000;
            minRange = 100f;
            rotateSpeed = 0.15f;
            inaccuracy = 10f;
            reload = 360f;
            ammoEjectBack = 50f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 4;
            velocityRnd = 0.7f;
            recoil = 9f;
            shake = 5f;
            shootSound = Sounds.bang;
        }};
        govnomet = new ItemTurret("govnomet") {{
            requirements(Category.turret, with(TItems.concrete, 50, TItems.poop, 20, TItems.tets_ingot, 1));
            ammo(TItems.poop, new ArtilleryBulletType(2.5f, 1, "poop") {{
                knockback = 4f;
                lifetime = 800f;
                width = height = 10f;
                collidesTiles = false;
                splashDamageRadius = 4f;
                hitEffect = Fx.blastExplosion;
                splashDamage = 1f;
                fragBullet = new BasicBulletType(2.5f, 1, "poop"){{
                    width = 5f;
                    height = 5f;
                    shrinkY = 1f;
                    lifetime = 30f;
                    despawnEffect = Fx.absorb;
                    hitEffect = Fx.plasticExplosionFlak;
                    collidesAir = true;
                    fragBullet = new BasicBulletType(1.5f, 1, "poop"){{
                        width = 2f;
                        height = 2f;
                        shrinkY = 1f;
                        lifetime = 15f;
                        despawnEffect = Fx.flakExplosionBig;
                        collidesAir = true;
                    }};
                    fragBullets = 5;
                }};
                fragBullets = 10;
            }});
            targetAir = true;
            size = 4;
            range = 100;
            minRange = 10f;
            rotateSpeed = 0.15f;
            inaccuracy = 2f;
            reload = 30f;
            ammoEjectBack = 20f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 4;
            velocityRnd = 0.7f;
            recoil = 4f;
            shake = 2f;
            shootSound = Sounds.bang;
        }};

    }
    private static void loadDistributions() {
        tets_conveyor = new Conveyor("tets-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItems.battery, 1, TItems.tets_ingot, 1, TItems.concrete, 1));
            itemCapacity = 4;
            speed = 0.1f;
            displayedSpeed = 9.0F;
            health = 260;
        }};
        teleporter = new MassDriver("teleporter") {{
            requirements(Category.distribution, ItemStack.with(TItems.tets_ingot, 30, TItems.battery, 13, TItems.hyperalloy, 5));
            for (ItemStack stack : requirements){
                health += (int) ((stack.item.cost * stack.item.cost + 1) * stack.amount);

            }
            size = 1;
            range = 1280;
            reload = 10;
            shake = 0;
            bulletSpeed = 20;
            shootEffect = Fx.lightningShoot;
            smokeEffect = Fx.colorSpark;
            receiveEffect = Fx.mineBig;
            shootSound = Sounds.pew;
            bullet = new MassDriverBolt() {{
                collidesTiles = false;
                lifetime = 1f;
                despawnEffect = Fx.dropItem;
                hitEffect = Fx.hitLaser;
            }};
        }};
    }
    private static void loadOther() {

    }
}
