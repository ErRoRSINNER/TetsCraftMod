package content.blocks;

import arc.graphics.Color;
import content.items.TItems;
import content.liquids.TLiquids;
import content.units.TUnits;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFade;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;

import static mindustry.type.ItemStack.with;

public class TBlocks {
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill, bangun, govnomet, teleporter, tets_conveyor, solpanel, tets_battery, crystal_powerblock,
            estrella_de_platino, small_shield_projector, concrete_wall, concrete_wall_large, prav_wall, prav_wall_large, daew, poop_wall, battery_factory, bee_plant,
            concrete_mixer, crystalizer, shit_mixer, vermillion, tantalium_factory, mica_press, mercury_purificator, tetsonator, aacd_FIFNYA, hoover,
            beeshot, quick_fire, RMG202, superconductor_plant, absolute_zero, bingQiLingMixer, pravoslaviumMixer, tetsBridge, teslaCoil, copperPulverizer,
            erekinator, serpulinator, bardovovizator, apiary, composter, tetsBasicReconstructorEnergy, tetsBasicReconstructorAttack, tetsAdditiveReconstructorAttack, tetsAdditiveReconstructorEnergy, tetsMultiplicativeReconstructorEnergy;

    public static void load() {
        loadCrafting();
        loadDefenses();
        loadDrills();
        loadEffects();
        loadOres();
        loadPower();
        loadTurrets();
        loadDistributions();
        loadUnits();
        loadOther();
        loadMixins();
    }

    private static void loadCrafting() {
        bardovovizator = new GenericCrafter("bardovovizator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 30, TItems.tantalium, 70, TItems.concrete, 160, TItems.tets_ingot, 30, Items.metaglass, 130));
            outputLiquid = new LiquidStack(TLiquids.red_mercury, 0.01f);
            craftTime = 40.0F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquids(LiquidStack.with(TLiquids.mercury, 0.4f, Liquids.oil, 0.1f));
            consumeItems(ItemStack.with(TItems.vermillion, 5, TItems.bee, 1));
            consumePower(7.7F);
            alwaysUnlocked = true;
        }};
        erekinator = new Separator("erekinator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 40, TItems.concrete, 120, Items.metaglass, 20));
            craftTime = 40F;
            results = ItemStack.with(Items.copper, 3, Items.lead, 2, Items.titanium, 1);
            size = 3;
            health = 420;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.beryllium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        serpulinator = new Separator("serpulinator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 40, TItems.concrete, 120, Items.metaglass, 20));
            craftTime = 40F;
            results = ItemStack.with(Items.beryllium, 3, Items.tungsten, 1);
            size = 3;
            health = 420;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.titanium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        copperPulverizer = new GenericCrafter("copper_pulverizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 20, Items.lead, 30));
            outputItem = new ItemStack(Items.scrap, 2);
            craftEffect = Fx.pulverize;
            craftTime = 40.0F;
            updateEffect = Fx.pulverizeSmall;
            hasItems = this.hasPower = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 3, true), new DrawRegion("-top"));
            this.ambientSound = Sounds.grinding;
            this.ambientSoundVolume = 0.025F;
            this.consumeItem(Items.copper, 3);
            this.consumePower(0.45F);
            alwaysUnlocked = true;
        }};
        pravoslaviumMixer = new GenericCrafter("pravoslavium_mixer") {{
            requirements(Category.crafting, ItemStack.with(TItems.tantalium, 100, Items.thorium, 25, TItems.concrete, 50));
            craftTime = 100.0F;
            outputItem = new ItemStack(TItems.goddamm_ingot, 1);
            size = 3;
            health = 800;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 0.4f, true));
            consumeLiquid(Liquids.water, 0.5f);
            consumePower(1F);
            consumeItems(ItemStack.with(TItems.tets_coin, 8));
            alwaysUnlocked = true;
        }};
        bingQiLingMixer = new GenericCrafter("bing_qi_ling_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 20, Items.lead, 40, Items.graphite, 20, Items.copper, 40));
            craftTime = 120.0F;
            outputItem = new ItemStack(TItems.bing_qi_ling, 1);
            size = 2;
            health = 220;
            craftEffect = Fx.colorSparkBig;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sporePod, 2));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.15f));
            alwaysUnlocked = true;
        }};
        absolute_zero = new GenericCrafter("absolute_zero") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 30, TItems.tantalium, 70, TItems.concrete, 160, TItems.tets_ingot, 30, Items.metaglass, 130));
            outputLiquid = new LiquidStack(TLiquids.super_cryofluid, 0.034f);
            craftTime = 40.0F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.3f, TLiquids.mercury, 0.2f));
            consumeItems(ItemStack.with(Items.phaseFabric, 2));
            consumePower(3.31F);
            alwaysUnlocked = true;
        }};
        superconductor_plant = new GenericCrafter("superconductor_plant") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 20, TItems.tantalium, 80, TItems.concrete, 60, TItems.tets_ingot, 20, TItems.battery, 30));
            craftTime = 96.0F;
            outputItem = new ItemStack(TItems.superconductor, 2);
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquid(TLiquids.super_cryofluid, 0.1f);
            consumePower(5.2F);
            consumeItems(ItemStack.with(TItems.tets_ingot, 2, TItems.mica, 2, Items.copper, 3, Items.metaglass, 1));
            alwaysUnlocked = true;
        }};
        shit_mixer = new GenericCrafter("shit_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            craftTime = 190.0F;
            outputItem = new ItemStack(TItems.poop, 7);
            size = 3;
            health = 420;
            craftEffect = Fx.vapor;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 1.1f, true));
            consumeLiquid(Liquids.water, 0.33f);
            consumePower(0.07F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.sporePod, 3, Items.lead, 2, Items.copper, 1));
            alwaysUnlocked = true;
        }};
        battery_factory = new GenericCrafter("battery_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            craftTime = 90.0F;
            outputItem = new ItemStack(TItems.battery, 1);
            size = 2;
            health = 420;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.silicon, 2, Items.lead, 2, Items.copper, 1));
            alwaysUnlocked = true;
        }};
        tetsonator = new GenericCrafter("tetsonator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 80, TItems.concrete, 125, Items.metaglass, 40, TItems.battery, 40));
            craftTime = 44.44F;
            outputItem = new ItemStack(TItems.tets_ingot, 2);
            size = 3;
            health = 420;
            craftEffect = Fx.freezing;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.titanium, 1, TItems.tantalium, 1));
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.04444f, TLiquids.mercury, 0.2f));
            alwaysUnlocked = true;
        }};
        tantalium_factory = new Separator("tantalium_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, Items.titanium, 30, Items.graphite, 40, Items.copper, 120));
            craftTime = 100.0F;
            results = ItemStack.with(TItems.tantalium, 18, Items.scrap, 1);
            size = 2;
            health = 420;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.2F);
            consumeItems(ItemStack.with(Items.titanium, 1, Items.graphite, 1));
            consumeLiquid(Liquids.water, 0.1f);
            alwaysUnlocked = true;
        }};
        crystalizer = new GenericCrafter("crystalizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.silicon, 14, TItems.tantalium, 15, Items.metaglass, 10));
            craftTime = 180.0F;
            size = 2;
            outputItem = new ItemStack(TItems.crystal, 1);
            health = 120;
            craftEffect = Fx.healWave;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.0F);
            consumeItems(ItemStack.with(TItems.mica, 1, Items.silicon, 1));
            consumeLiquid(TLiquids.mercury, 0.033f);
            alwaysUnlocked = true;
        }};
        mercury_purificator = new GenericCrafter("mercury_purificator") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.graphite, 15, Items.metaglass, 10));
            craftTime = 30.0F;
            size = 2;
            outputLiquid = new LiquidStack(TLiquids.mercury, 0.01f);
            health = 120;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeItems(ItemStack.with(TItems.vermillion, 2));
            alwaysUnlocked = true;
        }};
        bee_plant = new Separator("bee-plant") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 100, Items.graphite, 50, Items.silicon, 5));
            results = ItemStack.with(TItems.bee, 10, TItems.beeq, 1);
            craftTime = 60.0F;
            size = 3;
            itemCapacity = 20;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.2F);
            consumeItem(TItems.bing_qi_ling, 1);
            consumeLiquid(Liquids.water, 0.04F);
            alwaysUnlocked = true;
        }};
        apiary = new GenericCrafter("apiary") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 60, Items.copper, 100, TItems.bing_qi_ling, 50));
            craftTime = 500.0F;
            liquidOutputDirections = new int[]{1, 3};
            outputLiquids = LiquidStack.with(TLiquids.honey, 0.025f, Liquids.oil, 0.01f);
            drawer = new DrawMulti(new DrawDefault());
            consumeItems(ItemStack.with(TItems.bee, 10, TItems.beeq, 5, TItems.bing_qi_ling, 12));
            consumeLiquid(Liquids.water, 0.035f);
            size = 2;
            alwaysUnlocked = true;
        }};
        composter = new GenericCrafter("composter") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 40, TItems.bing_qi_ling, 25, TItems.poop, 300));
            craftTime = 100.0F;
            outputLiquid = new LiquidStack(Liquids.arkycite, 0.3f);
            drawer = new DrawMulti(new DrawDefault());
            consumeItems(ItemStack.with(TItems.bee, 3, TItems.poop, 10));
            consumeLiquid(TLiquids.honey, 0.3f);
            size = 2;
            alwaysUnlocked = true;
        }};
        mica_press = new GenericCrafter("mica-press") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            craftTime = 290.0F;
            outputItem = new ItemStack(TItems.mica, 1);
            size = 2;
            health = 420;
            craftEffect = Fx.smokeCloud;
            drawer = new DrawMulti(new DrawDefault());
            consumePower(0.333F);
            consumeItems(ItemStack.with(Items.graphite, 1, Items.silicon, 1, Items.metaglass, 1));
            alwaysUnlocked = true;
        }};
        concrete_mixer = new GenericCrafter("concrete_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 60, Items.silicon, 12, Items.lead, 35));
            outputItems = ItemStack.with(TItems.concrete, 3, Items.scrap, 1);
            craftTime = 66.6F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotor", 3, true));
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sand, 3, Items.lead, 1, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.1F);
            alwaysUnlocked = true;
        }};
    }

    private static void loadDefenses() {
        concrete_wall = new Wall("concrete_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.concrete, 50));
            health = 322 * 3;
            alwaysUnlocked = true;
        }};
        concrete_wall_large = new Wall("concrete_wall-large") {{
            requirements(Category.defense, ItemStack.mult(TBlocks.concrete_wall.requirements, 4));
            health = 322 * 3 * 4;
            size = 2;
            alwaysUnlocked = true;
        }};
        prav_wall = new Wall("prav_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.goddamm_ingot, 33, TItems.beeq, 3));
            health = (int) (333 * 4.5f);
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.lasercharge;
            alwaysUnlocked = true;
        }};
        prav_wall_large = new Wall("prav_wall-large") {{
            requirements(Category.defense, ItemStack.mult(TBlocks.prav_wall.requirements, 4));
            health = (int) (333 * 4 * 4.5f);
            chanceDeflect = 0.33f;
            lightningChance = 0.33f;
            deflectSound = Sounds.lasercharge;
            size = 2;
            alwaysUnlocked = true;
        }};
        daew = new ShieldWall("DAEW") {{
            requirements(Category.defense, ItemStack.with(TItems.crystal, 5, TItems.tets_ingot, 15, TItems.tantalium, 110, TItems.superconductor, 12, TItems.nihonium, 5));
            size = 2;
            health = 2500;
            shieldHealth = 2280;
            regenSpeed = 5;
            outputsPower = false;
            hasPower = true;
            consumesPower = true;
            conductivePower = true;
            glowColor = TItems.nihonium.color.cpy().a(0.666f);
            consumePower(0.05f);
            alwaysUnlocked = true;
        }};
        poop_wall = new Wall("poop_wall") {{
            requirements(Category.defense, ItemStack.with(TItems.poop, 2));
            size = 2;
            health = 10;
            alwaysUnlocked = true;
        }};
    }

    private static void loadDrills() {
        homoDrill = new Drill("homo-drill") {{
            requirements(Category.production, ItemStack.with(Items.copper, 20, Items.lead, 5)); //TODO
            tier = 2;
            drillTime = 400.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100});
            consumeLiquid(Liquids.water, 0.06F).boost();
            health = 100;
            alwaysUnlocked = true;
        }};
        miniDrill = new Drill("mini-drill") {{
            requirements(Category.production, ItemStack.with(TItems.concrete, 12, TItems.tantalium, 10));
            tier = 4;
            drillTime = 130.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{TItems.concrete, 100, TItems.tantalium, 60});
            consumeLiquid(Liquids.water, 0.08F).boost();
            consumePower(0.15f);
            health = 120;
            alwaysUnlocked = true;
        }};
        nihonDrill = new Drill("nihon-drill") {{
            requirements(Category.production, ItemStack.with(TItems.nihonium, 240, Items.lead, 24));
            tier = 6;
            drillTime = 200.0F;
            size = 5;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.cryofluid, 0.1F).boost();
            consumePower(4.3f);
            health = 180;
            alwaysUnlocked = true;
        }};
        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(TItems.concrete, 320, TItems.tets_ingot, 69, TItems.battery, 30));
            tier = 5;
            size = 3;
            drillTime = 50;
            consumePower(3.3f);
            consumeLiquid(Liquids.cryofluid, 0.1F).boost();
            alwaysUnlocked = true;
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
            alwaysUnlocked = true;
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
            alwaysUnlocked = true;
        }};
    }

    private static void loadOres() {
        vermillion = new OreBlock("vermillion", TItems.vermillion) {
            {
                this.oreDefault = true;
                this.oreThreshold = 0.9F;
                this.oreScale = 15.3F;
                alwaysUnlocked = true;
            }
        };
    }

    private static void loadPower() {
        solpanel = new SolarGenerator("solpanel") {{
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 25, TItems.tantalium, 15, TItems.battery, 10, Items.silicon, 55));
            size = 2;
            powerProduction = 1.15F;
            hasPower = true;
            baseExplosiveness = 6;
            alwaysUnlocked = true;
        }};
        tets_battery = new Battery("tets_battery") {{
            size = 2;
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 15, TItems.tantalium, 15, TItems.battery, 50, Items.silicon, 30));
            consumePowerBuffered(70000);
            baseExplosiveness = 7;
            alwaysUnlocked = true;
        }};
        crystal_powerblock = new Battery("crystal_powerblock") {{
            size = 2;
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 55, TItems.tantalium, 30, TItems.battery, 10, TItems.crystal, 110, Items.silicon, 40, TItems.superconductor, 10));
            consumePowerBuffered(404000);
            baseExplosiveness = 7;
            alwaysUnlocked = true;
        }};
    }

    private static void loadTurrets() {
        teslaCoil = new PowerTurret("tesla_coil") {{
            requirements(Category.turret, ItemStack.with(Items.copper, 400, Items.titanium, 200, TItems.battery, 200));
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
            shootSound = Sounds.laser;
            consumePower(24.0F);
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
            requirements(Category.turret, with(TItems.tantalium, 700, TItems.tets_ingot, 125, Items.metaglass, 525, Items.graphite, 250));
            size = 2;
            range = 200;
            reload = 2.0F;
            velocityRnd = 0.1F;
            inaccuracy = 5.0F;
            recoil = 1.0F;
            shootCone = 60.0F;
            liquidCapacity = 150.0F;
            shootEffect = Fx.shootLiquid;
            ammo(TLiquids.mercury, new LiquidBulletType(TLiquids.mercury) {{
                lifetime = 49.0F;
                speed = 4.0F;
                knockback = 1.3F;
                puddleSize = 8.0F;
                orbSize = 4.0F;
                damage = 12.5F;
                drag = 0.005F;
                ammoMultiplier = 0.4F;
                statusDuration = 240.0F;
                status = StatusEffects.melting;
            }}, TLiquids.red_mercury, new LiquidBulletType(TLiquids.red_mercury) {{
                lifetime = 49.0F;
                speed = 3.0F;
                knockback = 1.3F;
                puddleSize = 8.0F;
                orbSize = 4.0F;
                damage = 33F;
                drag = 0.005F;
                ammoMultiplier = 0.4F;
                statusDuration = 240.0F;
                status = StatusEffects.unmoving;
            }});
            alwaysUnlocked = true;
        }};
        quick_fire = new ItemTurret("quick-fire") {{
            requirements(Category.turret, with(Items.lead, 200, Items.graphite, 25, Items.metaglass, 25, Items.copper, 125));
            size = 2;
            inaccuracy = 12;
            reload = 4;
            shootCone = 2;
            rotateSpeed = 3;
            shootSound = Sounds.pew;
            xRand = 3;
            range = 105;
            ammo(Items.copper, new BasicBulletType(4, 10) {{
                        hitSize = 4;
                        width = 4;
                        height = 10;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                    }},
                    Items.coal, new FireBulletType(4, 8) {{

                        lifetime = 55;
                        reloadMultiplier = 0.9f;
                        hitSize = 7;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3;
                        status = StatusEffects.burning;
                        pierce = true;
                        keepVelocity = false;
                        hittable = false;
                    }},
                    TItems.poop, new FlakBulletType(4.5f, 2) {{
                        shoot = new ShootBarrel() {{
                            barrels = new float[]{1.75f, 1.75f, 1.75f};
                        }};
                        shoot.shots = 3;
                        splashDamage = 7;
                        ammoMultiplier = 4;
                        reloadMultiplier = 0.3333f;
                        status = StatusEffects.slow;
                    }});
            alwaysUnlocked = true;
        }};
        beeshot = new ItemTurret("beeshot") {{
            requirements(Category.turret, with(TItems.mica, 200, TItems.bee, 525, TItems.beeq, 25, Items.copper, 125, Items.scrap, 225));
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 10;
            reload = 240;
            shootCone = 2;
            rotateSpeed = 4;
            shootSound = Sounds.shoot;
            xRand = 3;
            this.shoot = new ShootBarrel() {
                {
                    this.barrels = new float[]{
                            -3.5F, -2.0F, 0.0F,
                            -2.5F, -3.0F, 0.0F,
                            -1.5F, -2.0F, 0.0F,
                            -0.5F, -3.0F, 0.0F,
                            0.0F, -2.0F, 0.0F,
                            0.5F, -3.0F, 0.0F,
                            1.5F, -2.0F, 0.0F,
                            2.5F, -3.0F, 0.0F,
                            3.5F, -2.0F, 0.0F};
                    this.shots = 11;
                    this.shotDelay = 7.0F;
                }
            };
            range = 300;
            ammo(TItems.bee, new MissileBulletType(1, 3.5f, "tets-craft-mod-bee") {{
                        backColor = new Color(0, 0, 0, 0);
                        splashDamage = 15;
                        splashDamageRadius = 16;
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
                    TItems.beeq, new MissileBulletType(0.6f, 8.5f, "tets-craft-mod-beeq") {{
                        backColor = new Color(0, 0, 0, 0);
                        splashDamage = 41;
                        splashDamageRadius = 24;
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
            requirements(Category.turret, with(TItems.tantalium, 200, TItems.battery, 400, Items.thorium, 25, TItems.crystal, 25, TItems.mica, 100));
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 1;
            reload = 220;
            shootCone = 1;
            rotateSpeed = 6;
            shootSound = Sounds.laserbig;
            shoot = new ShootPattern() {{
                shots = 2;
                shotDelay = 2;
            }};
            range = 500;
            ammo(TItems.crystal, new BasicBulletType(19, 200) {{
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
                        pierceCap = 5;
                        ammoMultiplier = 1.4f;
                    }}
            );
            alwaysUnlocked = true;
        }};
        aacd_FIFNYA = new ItemTurret("aacd_FIFNYA") {{
            requirements(Category.turret, with(TItems.concrete, 125, TItems.tantalium, 20, Items.metaglass, 25));
            targetAir = true;
            targetGround = false;
            size = 3;
            inaccuracy = 5;
            reload = 24;
            shootCone = 30;
            rotateSpeed = 5;
            ammoPerShot = 3;
            xRand = 4;
            range = 220f;
            shootY = 4.5F;
            this.shoot = new ShootBarrel() {
                {
                    this.barrels = new float[]{
                            -1.5F, -2.25F, 0.0F,
                            -1.5F, -2.25F, 0.0F,
                            1.5F, -2.25F, 0.0F,
                            1.5F, -2.25F, 0.0F};
                    this.shots = 4;
                    this.shotDelay = 3.0F;
                }
            };
            ammo(Items.sand, new MissileBulletType(6, 12) {{
                status = StatusEffects.slow;
                hitSize = 7;
                width = 4;
                height = 4;
                shootEffect = Fx.shootPyraFlame;
                hitEffect = Fx.hitFlameSmall;
                ammoMultiplier = 3.4f;
            }});
            alwaysUnlocked = true;
        }};
        bangun = new ItemTurret("bangun") {{
            requirements(Category.turret, with(TItems.concrete, 1500, TItems.tantalium, 334, TItems.tets_ingot, 160));
            ammo(TItems.concrete, new ArtilleryBulletType(3.5f, 100, "shell") {{
                knockback = 1f;
                lifetime = 8000f;
                width = height = 30f;
                collidesTiles = false;
                splashDamageRadius = 400f;
                hitEffect = Fx.blastExplosion;
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
            alwaysUnlocked = true;
        }};
        govnomet = new ItemTurret("govnomet") {{
            requirements(Category.turret, with(TItems.concrete, 50, TItems.poop, 20, TItems.tantalium, 1));
            ammo(TItems.poop, new ArtilleryBulletType(2.5f, 3, "poop") {{
                knockback = 4f;
                lifetime = 800f;
                width = height = 10f;
                collidesTiles = false;
                splashDamageRadius = 4f;
                hitEffect = Fx.blastExplosion;
                splashDamage = 1f;
                fragBullet = new BasicBulletType(4f, 3, "poop") {{
                    width = 5f;
                    height = 5f;
                    shrinkY = 1f;
                    lifetime = 30f;
                    despawnEffect = Fx.absorb;
                    hitEffect = Fx.plasticExplosionFlak;
                    collidesAir = true;
                    fragBullet = new BasicBulletType(2f, 3, "poop") {{
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
            shootSound = Sounds.bang;
            alwaysUnlocked = true;
        }};

    }

    private static void loadDistributions() {
        tetsBridge = new BufferedItemBridge("tets_bridge-conveyor") {{
                this.requirements(Category.distribution, ItemStack.with(TItems.battery, 4, TItems.tets_ingot, 4, TItems.concrete, 8));
                this.fadeIn = this.moveArrows = false;
                this.range = 16;
                this.speed = 30.0F;
                this.arrowSpacing = 7.0F;
                this.bufferCapacity = 28;
            }};
        tets_conveyor = new Conveyor("tets-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItems.battery, 1, TItems.tets_ingot, 1, TItems.concrete, 1));
            itemCapacity = 4;
            speed = 0.14f;
            displayedSpeed = 9.0F;
            health = 260;
            alwaysUnlocked = true;
        }};
        teleporter = new MassDriver("teleporter") {{
            requirements(Category.distribution, ItemStack.with(TItems.tets_ingot, 30, TItems.battery, 13, TItems.hyperalloy, 5));
            for (ItemStack stack : requirements) {
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
            alwaysUnlocked = true;
        }};
    }

    private static void loadUnits(){
        //T1
        tetsBasicReconstructorAttack = new Reconstructor("tets_basic_reconstructor_attack") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60}));
                this.size = 3;
                health = 300;
                this.consumePower(1.0F);
                this.consumeItems(ItemStack.with(new Object[]{Items.pyratite, 10, Items.silicon, 10}));
                this.constructTime = 300.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.smallArtillery}});
            }
        };
        tetsBasicReconstructorEnergy = new Reconstructor("tets_basic_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60}));
                this.size = 3;
                health = 300;
                this.consumePower(1.0F);
                this.consumeItems(ItemStack.with(new Object[]{TItems.battery, 5, Items.graphite, 10}));
                this.constructTime = 300.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.miniMiner}});
            }
        };


        //T2
        tetsAdditiveReconstructorAttack = new Reconstructor("tets_additive_reconstructor_attack") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItems.concrete, 100}));
                this.size = 3;
                health = 600;
                this.consumePower(3.0F);
                this.consumeItems(ItemStack.with(new Object[]{Items.titanium, 10, Items.silicon, 20, Items.blastCompound, 20}));
                this.constructTime = 1200.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.smallArtillery, TUnits.mediumArtillery}});
            }
        };
        tetsAdditiveReconstructorEnergy = new Reconstructor("tets_additive_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItems.battery, 80}));
                this.size = 3;
                health = 600;
                this.consumePower(3.0F);
                this.consumeItems(ItemStack.with(new Object[]{Items.beryllium, 10, Items.graphite, 20, TItems.battery, 40}));
                this.constructTime = 1200.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.UFO, TUnits.UF1_Energy}, {TUnits.miniMiner, TUnits.mediMiner}});
            }
        };

        //T3
        tetsMultiplicativeReconstructorEnergy = new Reconstructor("tets_multiplicative_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{Items.tungsten, 200, Items.lead, 120, Items.titanium, 80, TItems.battery, 80}));
                this.size = 5;
                health = 1000;
                this.consumePower(4.0F);
                this.consumeItems(ItemStack.with(new Object[]{Items.beryllium, 80, Items.tungsten, 60, TItems.battery, 40, TItems.tantalium, 20}));
                //this.consumeLiquid(Liquids.cryofluid, 1f);
                this.constructTime = 2400.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.UF1_Energy, TUnits.UF2_Energy}});
            }
        };
    }

    private static void loadOther() {

    }

    private static void loadMixins() {
        ((UnitFactory) Blocks.groundFactory).plans.add(new UnitFactory.UnitPlan(TUnits.walkingBaseUnit, 2100.0F, ItemStack.with(new Object[]{Items.silicon, 30, Items.lead, 30, Items.copper, 30})));
        ((UnitFactory) Blocks.airFactory).plans.add(new UnitFactory.UnitPlan(TUnits.UFO, 180.0F, ItemStack.with(new Object[]{Items.silicon, 5, TItems.battery, 2})));
    }
}
