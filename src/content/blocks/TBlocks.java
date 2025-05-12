package content.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.EnumSet;
import arc.struct.Seq;
import content.items.TItems;
import content.liquids.TLiquids;
import content.units.TUnits;
import mindustry.content.*;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.distribution.Router;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.logic.LogicDisplay;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeCoolant;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BuildVisibility;
import multicraft.IOEntry;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import static mindustry.type.ItemStack.with;

public class TBlocks {

    //Energy
    public static Block batteryArray, tetsPowerNode, solpanel, tets_battery, crystal_powerblock,
            tetsBasicReconstructorEnergy, tetsBasicReconstructorAttack, tetsAdditiveReconstructorAttack,
            tetsAdditiveReconstructorEnergy, tetsMultiplicativeReconstructorEnergy, hidingShield, steamTurbine;

    // Crafting
    public static Block solarHeater, solarBoiler, boiler, electric_boiler, customSteamGenerator, battery_factory, bee_plant, concrete_mixer, crystalizer, shit_mixer,
            tantalium_factory, mica_press, mercury_purificator, tetsonator, superconductor_plant, absolute_zero, bingQiLingMixer, pravoslaviumMixer, erekinator,
            serpulinator, bardovovizator, apiary, composter, copperPulverizer, fusion_reactor, atmosphericCondenser;

    // Drills
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill;

    // Turrets
    public static Block bangun, govnomet, aacd_FIFNYA, hoover, beeshot, quick_fire, RMG202, teslaCoil, cirnoGun, goddamn_gun;

    // Walls
    public static Block daew, poop_wall, concrete_wall, concrete_wall_large, prav_wall, prav_wall_large;

    // Distributions
    public static Block smallContainer, teleporter, tets_conveyor, tantal_router, tetsBridge;

    // Effects
    public static Block made_in_heaven, small_shield_projector, vault, vault_big;

    // Fake 2 by 2
    public static Block fakeCopperWallLarge, fakeTitaniumWallLarge, fakeThoriumWallLarge, fakeGraphitePress, fakeKiln, fakeMechanicalDrill, fakePneumaticDrill,
            fakePlastaniumCompressor, fakeSiliconSmelter, fakeSteamGenerator, fakeThermalGenerator, fakeLancer, fakeScatter;

    // Fake 3 by 3
    public static Block fakeCoreShard, fakeBatteryLarge, fakeMultiPress, fakeSiliconCrucible, fakeRipple, fakeFuse, fakeThoriumReactor, fakeSolarPanelLarge;

    // Other
    public static Block tantal_mine, tets_display, tets_processor, vermillion, tetsOre;


    public static Block test1, test2;

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
        loadFake();
        loadMixins();
        test1 = new LaserTurret("cirno_gunv") {
            {
                this.firingMoveFract = 1.5F;
                this.shootDuration = 230.0F;
                this.shootSound = Sounds.laserbig;
                this.loopSound = Sounds.beam;
                this.loopSoundVolume = 1.0F;
                this.envEnabled |= 2;

                requirements(Category.turret, BuildVisibility.sandboxOnly, with(Items.titanium, 999, TItems.battery, 999, TItems.bing_qi_ling, 999, Items.graphite, 999));
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
                coolant = new ConsumeLiquid(Liquids.water, 0.1f);
                linearWarmup = true;
                this.shootType = new ContinuousLaserBulletType(78.0F) {
                    {
                        this.length = 300.0F;
                        this.hitEffect = Fx.hitLaser;
                        this.hitColor = Pal.techBlue;
                        this.status = StatusEffects.freezing;
                        this.drawSize = 420.0F;
                        this.incendChance = 0.4F;
                        this.incendSpread = 5.0F;
                        this.incendAmount = 1;
                        this.ammoMultiplier = 1.0F;
                        colors = new Color[]{Liquids.cryofluid.color, Liquids.water.color, Liquids.cryofluid.gasColor};
                        width = 3;
                    }
                };
                this.scaledHealth = 200.0F;
                this.consumePower(17.0F);
                this.drawer = new DrawTurret("cirno_gun") {{
                    this.parts.add(new RegionPart("-barrel") {
                        {
                            this.progress = PartProgress.constant(1);
                            this.mirror = false;
                            this.under = false;
                            this.moveX = 0.0F;
                            this.moveY = 12.0F;
                        }
                    }, new RegionPart("-wing") {
                        {
                            this.progress = PartProgress.warmup;
                            this.mirror = true;
                            this.under = false;
                            this.moveX = -17.0F;
                            this.moveY = 0.0F;
                            this.xScl = 1.8f;
                            this.yScl = 1.8f;
                        }
                    }, new RegionPart("-wing") {
                        {
                            this.progress = PartProgress.warmup;
                            this.mirror = true;
                            this.under = false;
                            this.moveRot = -45f; //Upper Pair
                            this.moveX = -17.0F;
                            this.moveY = 10.0F;
                            this.xScl = 1.8f;
                            this.yScl = 1.8f;
                        }
                    }, new RegionPart("-wing") {
                        {
                            this.progress = PartProgress.warmup;
                            this.mirror = true;
                            this.under = false;
                            this.moveRot = 45f; //Lower pair
                            this.moveX = -17.0F;
                            this.moveY = -10.0F;
                            this.xScl = 1.8f;
                            this.yScl = 1.8f;
                        }
                    });
                }};
            }
        };
    }

    private static void loadCrafting() {
        atmosphericCondenser = new GenericCrafter("atmospheric_condenser") {{
            requirements(Category.crafting, ItemStack.with(TItems.concrete, 500, Items.titanium, 80));
            health = 1200;
            size = 5;
            outputLiquid = new LiquidStack(Liquids.water, 1 / 60f);
            alwaysUnlocked = true;
        }};
        solarHeater = new SolarHeatProducer("solar_heater") {{
            requirements(Category.crafting, with(Items.lead, 80, Items.tungsten, 10, Items.oxide, 5));
            size = 2;
            heatOutput = 0.5f;
            itemCapacity = 0;
            health = 250;
            alwaysUnlocked = true;
        }};
        solarBoiler = new LiquidAccurateSolarCrafter("solar_boiler") {{
            this.requirements(Category.crafting, ItemStack.with(Items.lead, 160, Items.graphite, 30));
            size = 3;
            this.health = 320;
            this.craftTime = 240;

            this.consumeLiquid(Liquids.water, 0.1f);
            this.outputLiquid = new LiquidStack(TLiquids.steam, 0.1F);
            alwaysUnlocked = true;
        }};
        boiler = new HeatCrafter("boiler") {
            {
                size = 2;
                this.requirements(Category.crafting, ItemStack.with(Items.copper, 40, Items.lead, 80));
                this.health = 180;
                this.craftTime = 60.0F;
                this.heatRequirement = 5.0F;

                this.consumeLiquid(Liquids.water, 0.4f);
                this.outputLiquid = new LiquidStack(TLiquids.steam, 0.5F);
                alwaysUnlocked = true;
            }
        };
        electric_boiler = new GenericCrafter("electric_boiler") {
            {
                size = 2;
                this.requirements(Category.crafting, ItemStack.with(Items.copper, 40, Items.lead, 40, Items.graphite, 30, Items.silicon, 30, TItems.battery, 10));
                this.health = 200;
                this.outputLiquid = new LiquidStack(TLiquids.steam, 0.1F);
                this.craftTime = 30.0F;
                this.consumePower(1.0F);
                this.consumeLiquid(Liquids.water, 0.1f);
                alwaysUnlocked = true;
            }
        };
        customSteamGenerator = new MultiCrafter("custom_steam_generator") {
            {
                this.requirements(Category.power, ItemStack.with(Items.copper, 60, Items.graphite, 30, Items.lead, 40, Items.silicon, 40));
                this.size = 2;
                craftEffect = Fx.generatespark;
                this.ambientSound = Sounds.smelter;
                this.ambientSoundVolume = 0.06F;
                flags = EnumSet.of(BlockFlag.reactor, BlockFlag.generator, BlockFlag.factory);
                alwaysUnlocked = true;
                this.drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion(), new DrawRegion("-turbine") {
                    {
                        this.rotateSpeed = 2.0F;
                    }
                }, new DrawRegion("-turbine") {
                    {
                        this.rotateSpeed = -2.0F;
                        this.rotation = 45.0F;

                    }
                }, new DrawRegion("-cap"));

                resolvedRecipes = Seq.with(
                        new Recipe(
                                new IOEntry(
                                        Seq.with(ItemStack.with(Items.coal, 1)),
                                        Seq.with(LiquidStack.with(Liquids.water, 0.1))
                                ),
                                new IOEntry(
                                        Seq.with(),
                                        Seq.with(),
                                        5.5f
                                ), 90
                        ),
                        new Recipe(
                                new IOEntry(
                                        Seq.with(ItemStack.with(Items.coal, 1)),
                                        Seq.with(LiquidStack.with(Liquids.water, 0.1))
                                ),
                                new IOEntry(
                                        Seq.with(),
                                        Seq.with(LiquidStack.with(TLiquids.steam, 0.1f))
                                ), 60
                        )
                );
            }
        };
        bardovovizator = new GenericCrafter("bardovovizator") {{
            requirements(Category.crafting, ItemStack.with(TItems.battery, 140, TItems.tantalium, 30, TItems.concrete, 160, TItems.tets_ingot, 30, TItems.crystal, 50));
            setHealth(this, 0.3f);
            outputLiquid = new LiquidStack(TLiquids.red_mercury, 0.03f);
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
            setHealth(this);
            craftTime = 40F;
            results = ItemStack.with(Items.copper, 3, Items.lead, 2, Items.titanium, 1);
            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.beryllium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        serpulinator = new Separator("serpulinator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 40, TItems.concrete, 120, Items.metaglass, 20));
            setHealth(this);
            craftTime = 40F;
            results = ItemStack.with(Items.beryllium, 3, Items.tungsten, 1);
            size = 3;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.titanium, 1));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.04444f));
            alwaysUnlocked = true;
        }};
        copperPulverizer = new GenericCrafter("copper_pulverizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 20, Items.lead, 30));
            setHealth(this, 0.666f);
            outputItem = new ItemStack(Items.scrap, 2);
            craftEffect = Fx.pulverize;
            craftTime = 40.0F;
            updateEffect = Fx.pulverizeSmall;
            hasItems = this.hasPower = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 3, true), new DrawRegion("-top"));
            ambientSound = Sounds.grinding;
            ambientSoundVolume = 0.025F;
            consumeItem(Items.copper, 3);
            consumePower(0.45F);
            alwaysUnlocked = true;
        }};
        pravoslaviumMixer = new MultiCrafter("pravoslavium_mixer") {{
            requirements(Category.crafting, ItemStack.with(TItems.tantalium, 100, Items.thorium, 25, TItems.concrete, 50));
            setHealth(this);

            resolvedRecipes = Seq.with(
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.tets_coin, 8, TItems.tantalium, 1)),
                                    Seq.with(LiquidStack.with(Liquids.water, 2f)),
                                    3 * 60
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.goddamm_ingot, 1)),
                                    Seq.with()
                            ),
                            120
                    ),
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.tets_coin, 2, TItems.bee, 3)),
                                    Seq.with(LiquidStack.with(TLiquids.honey, 0.05f)),
                                    3 * 60
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.beeq, 2)),
                                    Seq.with()
                            ),
                            50
                    )
            );

            size = 3;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 0.4f, true));
            alwaysUnlocked = true;
        }};
        fusion_reactor = new MultiCrafter("fusion_reactor") {{
            requirements(Category.crafting, ItemStack.with(
                    TItems.tantalium, 1000, Items.thorium, 900, TItems.concrete, 1500,
                    TItems.tets_ingot, 300, TItems.superconductor, 2000, Items.surgeAlloy, 700,
                    Items.graphite, 4000));
            setHealth(this, 0.333f);
            flags = EnumSet.of(BlockFlag.reactor, BlockFlag.generator, BlockFlag.factory);

            resolvedRecipes = Seq.with(
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(Items.surgeAlloy, 6, Items.phaseFabric, 16, TItems.tets_ingot, 7)),
                                    Seq.with(LiquidStack.with(TLiquids.super_cryofluid, 0.5f, TLiquids.red_mercury, 0.3f, TLiquids.honey, 1.4f)),
                                    32 * 60,
                                    30
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.hyperalloy, 1)),
                                    Seq.with()
                            ),
                            400
                    ),

                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(Items.copper, 18, TItems.mica, 24, TItems.tantalium, 14)),
                                    Seq.with(LiquidStack.with(TLiquids.mercury, 0.5f)),
                                    5f * 60,
                                    20
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(Items.surgeAlloy, 42)),
                                    Seq.with(LiquidStack.with(Liquids.slag, 1.5f))
                            ),
                            40
                    ),

                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(Items.thorium, 18, TItems.tets_ingot, 24)),
                                    Seq.with(LiquidStack.with(TLiquids.super_cryofluid, 0.3333f)),
                                    58 * 60,
                                    160
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.nihonium, 15)),
                                    Seq.with(LiquidStack.with(Liquids.neoplasm, 1f))
                            ),
                            320
                    ),
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TItems.nihonium, 3, Items.thorium, 13)),
                                    Seq.with(LiquidStack.with(TLiquids.super_cryofluid, 0.35f)),
                                    11 * 60
                            ),
                            new IOEntry(
                                    Seq.with(),
                                    Seq.with(LiquidStack.with(Liquids.neoplasm, 0.441f)),
                                    49 * 61, 75
                            ),
                            320 / 2.5f
                    ),
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(Items.phaseFabric, 3, Items.thorium, 5)),
                                    Seq.with(LiquidStack.with(TLiquids.super_cryofluid, 0.05f)),
                                    11 * 32
                            ),
                            new IOEntry(
                                    Seq.with(),
                                    Seq.with(LiquidStack.with(Liquids.neoplasm, 0.1f)),
                                    35 * 12, 20
                            ),
                            60
                    )
            );

            maxEfficiency = 9.99f;

            ambientSound = Sounds.pulse;
            craftEffect = new MultiEffect(Fx.mineImpactWave, Fx.neoplasiaSmoke, Fx.regenParticle);
            loopSound = Sounds.hum;
            loopSoundVolume = 1.1f;

            canOverdrive = false;

            itemCapacity = 100;
            liquidCapacity = 300;

            size = 6;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade(), new DrawHeatOutput());
            alwaysUnlocked = true;
        }};
        bingQiLingMixer = new GenericCrafter("bing_qi_ling_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 20, Items.lead, 40, Items.graphite, 20, Items.copper, 40));
            setHealth(this);
            craftTime = 120.0F;
            outputItem = new ItemStack(TItems.bing_qi_ling, 1);
            size = 2;
            craftEffect = Fx.colorSparkBig;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sporePod, 2));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.15f));
            alwaysUnlocked = true;
        }};
        absolute_zero = new GenericCrafter("absolute_zero") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 30, TItems.tantalium, 70, TItems.concrete, 160, TItems.tets_ingot, 30, Items.metaglass, 130));
            setHealth(this, 0.3f);
            outputLiquid = new LiquidStack(TLiquids.super_cryofluid, 0.05f);
            craftTime = 40.0F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.05f, TLiquids.mercury, 0.05f));
            consumeItems(ItemStack.with(Items.phaseFabric, 1));
            consumePower(2.31F);
            alwaysUnlocked = true;
        }};
        superconductor_plant = new GenericCrafter("superconductor_plant") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 50, TItems.tantalium, 80, TItems.concrete, 60, TItems.tets_ingot, 70, TItems.battery, 130));
            setHealth(this, 0.6f);
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
            setHealth(this, 0.3f);
            craftTime = 190.0F;
            outputItem = new ItemStack(TItems.poop, 3);
            size = 3;
            craftEffect = Fx.vapor;
            loopSound = Sounds.bioLoop;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 1.1f, true));
            consumeLiquid(Liquids.water, 0.1f);
            consumePower(0.07F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.sporePod, 3, Items.lead, 1, Items.copper, 1));
            alwaysUnlocked = true;
        }};
        battery_factory = new GenericCrafter("battery_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            setHealth(this);
            craftTime = 90.0F;
            outputItem = new ItemStack(TItems.battery, 1);
            size = 2;
            craftEffect = Fx.lightningCharge;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.2F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.silicon, 2, Items.lead, 2, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.066f);
            alwaysUnlocked = true;
        }};
        tetsonator = new GenericCrafter("tetsonator") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, TItems.tantalium, 80, TItems.concrete, 125, Items.metaglass, 40, TItems.battery, 40));
            setHealth(this, 0.5f);
            craftTime = 144.44444F;
            outputItem = new ItemStack(TItems.tets_ingot, 2);
            size = 3;
            craftEffect = Fx.freezing;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(1.45F);
            consumeItems(ItemStack.with(Items.titanium, 1, TItems.tantalium, 1));
            consumeLiquids(LiquidStack.with(Liquids.cryofluid, 0.2f, TLiquids.mercury, 0.1f));
            alwaysUnlocked = true;
        }};
        tantalium_factory = new Separator("tantalium_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 40, Items.titanium, 30, Items.graphite, 40, Items.copper, 120));
            setHealth(this);
            craftTime = 100.0F;
            results = ItemStack.with(TItems.tantalium, 18, Items.scrap, 4);
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.2F);
            consumeItems(ItemStack.with(Items.titanium, 1, Items.graphite, 1));
            consumeLiquid(Liquids.water, 0.1f);
            alwaysUnlocked = true;
        }};
        crystalizer = new GenericCrafter("crystalizer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.silicon, 14, TItems.tantalium, 15, Items.metaglass, 10));
            setHealth(this);
            craftTime = 180.0F;
            size = 2;
            outputItem = new ItemStack(TItems.crystal, 1);
            craftEffect = Fx.healWave;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(2.0F);
            consumeItems(ItemStack.with(TItems.mica, 1, Items.silicon, 1));
            consumeLiquid(TLiquids.mercury, 0.033f);
            alwaysUnlocked = true;
        }};
        mercury_purificator = new GenericCrafter("mercury_purificator") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 30, Items.graphite, 15, Items.metaglass, 10));
            setHealth(this);
            craftTime = 30.0F;
            size = 2;
            outputLiquid = new LiquidStack(TLiquids.mercury, 0.045f);
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumeItems(ItemStack.with(TItems.vermillion, 3));
            alwaysUnlocked = true;
        }};
        bee_plant = new Separator("bee-plant") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 100, Items.graphite, 50, Items.silicon, 5));
            setHealth(this);
            results = ItemStack.with(TItems.bee, 7, TItems.beeq, 1);
            craftTime = 180.0F;
            size = 3;
            itemCapacity = 20;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade());
            consumePower(0.2F);
            consumeItem(TItems.bing_qi_ling, 2);
            consumeLiquid(Liquids.water, 0.04F);
            alwaysUnlocked = true;
        }};
        apiary = new GenericCrafter("apiary") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 60, Items.copper, 100, TItems.bing_qi_ling, 50));
            setHealth(this);
            craftTime = 500.0F;
            liquidOutputDirections = new int[]{1, 3};
            outputLiquids = LiquidStack.with(TLiquids.honey, 0.025f, Liquids.oil, 0.01f);
            drawer = new DrawMulti(new DrawDefault());
            consumeItems(ItemStack.with(TItems.bee, 9, TItems.beeq, 3, TItems.bing_qi_ling, 4));
            consumeLiquid(Liquids.water, 0.035f);
            size = 2;
            alwaysUnlocked = true;
        }};
        composter = new GenericCrafter("composter") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 40, TItems.bing_qi_ling, 25, TItems.poop, 300));
            setHealth(this);
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
            setHealth(this);
            craftTime = 290.0F;
            outputItem = new ItemStack(TItems.mica, 1);
            size = 2;
            craftEffect = Fx.smokeCloud;
            drawer = new DrawMulti(new DrawDefault());
            consumePower(0.333F);
            consumeItems(ItemStack.with(Items.graphite, 1, Items.silicon, 1, Items.metaglass, 1));
            alwaysUnlocked = true;
        }};
        concrete_mixer = new GenericCrafter("concrete_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 60, Items.silicon, 12, Items.lead, 35));
            setHealth(this);
            outputItems = ItemStack.with(TItems.concrete, 2, Items.scrap, 1);
            craftTime = 77.7F;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotor", 3, true));
            consumePower(0.4F);
            consumeItems(ItemStack.with(Items.sand, 4, Items.lead, 1, Items.copper, 1));
            consumeLiquid(Liquids.water, 0.1F);
            alwaysUnlocked = true;
        }};
    }

    private static void loadDefenses() {
        hidingShield = new CustomForceProjector("hiding_shield") {{
            requirements(Category.defense, ItemStack.with(Items.thorium, 20, TItems.tantalium, 50, TItems.battery, 120));
            size = 3;
            health = 250;
            transparency = 255f;
            sides = 4;
            shieldRotation = 45;
            this.radius = 85;
            this.shieldHealth = 120;
            this.cooldownNormal = 3.5F;
            this.cooldownLiquid = 2.8F;
            this.cooldownBrokenBase = 0.35F;
            this.consumePower(4.0F);
            alwaysUnlocked = true;
        }};
        tantal_mine = new ShockMine("tantal_mine") {{
            requirements(Category.effect, ItemStack.with(TItems.tantalium, 10, TItems.battery, 12));
            hasShadow = false;
            health = 150;
            damage = 45.0F;
            tileDamage = 14.0F;
            length = 12;
            tendrils = 13;
        }};
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
            requirements(Category.defense, ItemStack.mult(TBlocks.prav_wall.requirements, 5));
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
            requirements(Category.production, ItemStack.with(TItems.nihonium, 240, TItems.tets_ingot, 1000));
            setHealth(this);
            tier = 6;
            drillTime = 20.0F;
            size = 5;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.cryofluid, 0.2F).boost();
            consumePower(5.3f);
            alwaysUnlocked = true;
        }};
        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(TItems.concrete, 320, TItems.tets_ingot, 69, TItems.battery, 50, TItems.tantalium, 120));
            setHealth(this);
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
            requirements(Category.effect, ItemStack.with(TItems.tantalium, 45, Items.silicon, 35, TItems.battery, 10));
            setHealth(this);
            size = 2;
            radius = 80F;
            sides = 4;
            shieldRotation = 45;
            shieldHealth = 750.0F;
            coolantConsumer = new ConsumeCoolant(0.1f);
            cooldownNormal = 1.5F;
            cooldownLiquid = 1.2F;
            cooldownBrokenBase = 0.35F;
            consumeItem(TItems.bee, 8).boost();
            phaseRadiusBoost = 70.0F;
            phaseShieldBoost = 20;
            consumePower(3.0F);
            alwaysUnlocked = true;
        }};
        made_in_heaven = new OverdriveProjector("made_in_heaven") {{
            requirements(Category.effect, ItemStack.with(TItems.battery, 800, TItems.tets_ingot, 100, TItems.concrete, 170, TItems.superconductor, 70));
            setHealth(this, 0.1f);
            consumePower(5F);
            consumeItem(TItems.goddamm_ingot, 6).boost();
            size = 4;
            speedBoostPhase = 0.654321f;
            phaseRangeBoost = 150;
            range = 800.0F;
            speedBoost = 2.5F;
            useTime = 400.0F;
            consumeItems(ItemStack.with(TItems.bee, 5));
            alwaysUnlocked = true;
        }};
        smallContainer = new StorageBlock("small_container") {{
            requirements(Category.effect, ItemStack.with(TItems.concrete, 100, Items.titanium, 25));
            health = 100;
            size = 1;
            itemCapacity = 50;
            alwaysUnlocked = true;
        }};
        vault = new StorageBlock("vault") {{
            requirements(Category.effect, ItemStack.with(TItems.concrete, 500, TItems.tantalium, 125));
            setHealth(this, 0.4f);
            size = 4;
            itemCapacity = 4000;
        }};
        vault_big = new StorageBlock("tantal_vault") {{
            requirements(Category.effect, ItemStack.with(TItems.tantalium, 512, TItems.tets_ingot, 75, TItems.concrete, 300));
            setHealth(this, 0.45f);
            size = 5;
            itemCapacity = 9000;
        }};
    }

    private static void loadOres() {
        vermillion = new OreBlock("vermillion", TItems.vermillion) {{
            oreDefault = true;
            oreThreshold = 0.86F;
            oreScale = 16.3F;
            alwaysUnlocked = true;
        }};
        tetsOre = new OreBlock("tetsore", TItems.tets_coin) {{
            oreDefault = true;
            oreThreshold = 0.95F;
            oreScale = 23.3F;
            alwaysUnlocked = true;
        }};
    }

    private static void loadPower() {
        steamTurbine = new PowerGenerator("steam_turbine") {{
            requirements(Category.power, ItemStack.with(TItems.battery, 160, Items.silicon, 60, Items.lead, 100, Items.metaglass, 50));
            health = 400;
        }};
        batteryArray = new Battery("battery_array") {{
            requirements(Category.power, ItemStack.with(TItems.battery, 100, Items.silicon, 30, Items.lead, 50));
            setHealth(this);
            size = 2;
            consumePowerBuffered(100000);
            baseExplosiveness = 7;
            alwaysUnlocked = true;
        }};
        solpanel = new SolarGenerator("solpanel") {{
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 25, TItems.tantalium, 15, TItems.battery, 10, Items.silicon, 55));
            setHealth(this);
            size = 2;
            powerProduction = 1.15F;
            hasPower = true;
            baseExplosiveness = 6;
            alwaysUnlocked = true;
        }};
        tets_battery = new Battery("tets_battery") {{
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 15, TItems.tantalium, 15, TItems.battery, 50, Items.silicon, 30));
            setHealth(this);
            size = 2;
            consumePowerBuffered(300000);
            baseExplosiveness = 8;
            alwaysUnlocked = true;
        }};
        crystal_powerblock = new Battery("crystal_powerblock") {{
            requirements(Category.power, ItemStack.with(TItems.tets_ingot, 55, TItems.tantalium, 30, TItems.battery, 10, TItems.crystal, 110, Items.silicon, 40, TItems.superconductor, 10));
            setHealth(this, 0.33f);
            size = 2;
            consumePowerBuffered(707000);
            baseExplosiveness = 8;
            alwaysUnlocked = true;
        }};
    }

    private static void loadTurrets() {
        goddamn_gun = new ItemTurret("goddamn_gun") {{
            requirements(Category.turret, with(TItems.tantalium, 33, TItems.goddamm_ingot, 33, TItems.bee, 33, TItems.beeq, 3));
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
            ammo(TItems.bee, new BasicBulletType(3.33f, 33.3f) {{
                lifetime = 111;
                splashDamage = 33.3f;
                splashDamageRadius = 33.3f;
                width = height = hitSize = 33;
                knockback = 3.33f;
                shrinkInterp = Interp.smooth;
                status = StatusEffects.shocked;
                ammoMultiplier = 3;
            }}, TItems.beeq, new BasicBulletType(3.33f, 44.4f) {{
                lifetime = 111;
                splashDamage = 33.3f;
                splashDamageRadius = 44.4f;
                width = height = hitSize = 33;
                knockback = 3.33f;
                shrinkInterp = Interp.smooth;
                status = StatusEffects.shocked;
                reloadMultiplier = 0.777f;
                ammoMultiplier = 3.33f;
            }}, TItems.goddamm_ingot, new BasicBulletType(3.33f, 77.7f) {{
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
            requirements(Category.turret, with(Items.titanium, 99, TItems.bing_qi_ling, 99, Items.graphite, 99));
            ammo(Liquids.water, new PointLaserBulletType() {{
                knockback = 0.0F;
                damage = 0.009F;
                ammoMultiplier = 0.3F;
                statusDuration = 540.0F;
                color = Liquids.cryofluid.color;
                status = StatusEffects.freezing;
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
            this.unitSort = UnitSorts.strongest;

            this.drawer = new DrawTurret("cirno_gun") {{
                this.parts.add(new RegionPart("-barrel") {
                    {
                        this.progress = PartProgress.constant(1);
                        this.mirror = false;
                        this.under = false;
                        this.moveX = 0.0F;
                        this.moveY = 12.0F;
                    }
                }, new RegionPart("-wing") {
                    {
                        this.progress = PartProgress.warmup;
                        this.mirror = true;
                        this.under = false;
                        this.moveX = -17.0F;
                        this.moveY = 0.0F;
                        this.xScl = 1.8f;
                        this.yScl = 1.8f;
                    }
                }, new RegionPart("-wing") {
                    {
                        this.progress = PartProgress.warmup;
                        this.mirror = true;
                        this.under = false;
                        this.moveRot = -45f; //Upper Pair
                        this.moveX = -17.0F;
                        this.moveY = 10.0F;
                        this.xScl = 1.8f;
                        this.yScl = 1.8f;
                    }
                }, new RegionPart("-wing") {
                    {
                        this.progress = PartProgress.warmup;
                        this.mirror = true;
                        this.under = false;
                        this.moveRot = 45f; //Lower pair
                        this.moveX = -17.0F;
                        this.moveY = -10.0F;
                        this.xScl = 1.8f;
                        this.yScl = 1.8f;
                    }
                });
            }};
            alwaysUnlocked = true;
        }};

        teslaCoil = new PowerTurret("tesla_coil") {{
            requirements(Category.turret, ItemStack.with(Items.copper, 400, Items.titanium, 200, TItems.battery, 200));
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
            shootSound = Sounds.laser;
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
            requirements(Category.turret, with(TItems.tantalium, 700, TItems.tets_ingot, 125, Items.metaglass, 525, Items.graphite, 250));
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
            }});
            alwaysUnlocked = true;
        }};
        quick_fire = new ItemTurret("quick-fire") {{
            requirements(Category.turret, with(Items.lead, 200, Items.graphite, 25, Items.metaglass, 25, Items.copper, 125));
            setHealth(this);
            size = 2;
            inaccuracy = 35;
            reload = 4;
            shootCone = 20;
            rotateSpeed = 3;
            shootSound = Sounds.pew;
            xRand = 0.1f;
            range = 160;
            coolant = new ConsumeCoolant(0.1f);
            ammo(Items.copper, new BasicBulletType(4, 10) {{
                        hitSize = 4;
                        width = 4;
                        height = 10;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                    }},
                    Items.coal, new BulletType(3.5f, 16) {{
                        lifetime = 55;
                        reloadMultiplier = 0.9f;
                        hitSize = 7;
                        shootEffect = Fx.shootPyraFlame;
                        hitEffect = Fx.hitFlameSmall;
                        ammoMultiplier = 3;
                        status = StatusEffects.burning;
                        keepVelocity = false;
                        hittable = false;
                        pierce = true;
                        collidesAir = false;
                    }},
                    TItems.poop, new FlakBulletType(4.5f, 4) {{
                        shoot = new ShootAlternate() {{
                            spread = 4.7f;
                            shots = 4;
                            barrels = 4;
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
            ammo(TItems.bee, new MissileBulletType(1, 15.5f, "tets-craft-mod-bee") {{
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
                    TItems.beeq, new MissileBulletType(0.6f, 18.5f, "tets-craft-mod-beeq") {{
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
            requirements(Category.turret, with(TItems.tantalium, 220, TItems.battery, 200, Items.thorium, 25, TItems.crystal, 5, TItems.superconductor, 25));
            setHealth(this, 0.6657f);
            targetAir = false;
            targetGround = true;
            size = 2;
            inaccuracy = 0.1f;
            reload = 300;
            shootCone = 5;
            rotateSpeed = 1;
            shootSound = Sounds.laserbig;
            range = 1000;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItems.crystal, new BasicBulletType(19, 200) {{
                        lifetime = 1000/18f;
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
                    }},
                    TItems.battery, new BasicBulletType(12, 50) {{
                        lifetime = 1000/11f;
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
                    }},
                    TItems.superconductor, new BasicBulletType(20, 500) {{
                        lifetime = 1000/19f;
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
                    }},
                    TItems.tantalium, new BasicBulletType(13, 100) {{
                        lifetime = 1000/12f;
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
                    }}
            );
            alwaysUnlocked = true;
        }};
        aacd_FIFNYA = new ItemTurret("aacd_FIFNYA") {{
            requirements(Category.turret, with(TItems.concrete, 125, TItems.tantalium, 40, Items.metaglass, 30));
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
            ammo(Items.sand, new MissileBulletType(6, 24) {{
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
            shootSound = Sounds.bang;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItems.concrete, new ArtilleryBulletType(3.5f, 100, "shell") {{
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
            requirements(Category.turret, with(TItems.concrete, 50, TItems.poop, 20, TItems.tantalium, 1));
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
            shootSound = Sounds.bang;
            coolant = new ConsumeCoolant(0.1f);
            ammo(TItems.poop, new ArtilleryBulletType(2.5f, 3, "poop") {{
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

    private static void loadDistributions() {
        tantal_router = new Router("tantal_router") {{
            requirements(Category.distribution, ItemStack.with(TItems.tantalium, 4, TItems.concrete, 8));
            setHealth(this);
            speed = 12;
            buildCostMultiplier = 4f;
            size = 3;
        }};
        tetsBridge = new BufferedItemBridge("tets_bridge-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItems.battery, 4, TItems.tets_ingot, 4, TItems.concrete, 8));
            setHealth(this);
            fadeIn = moveArrows = false;
            range = 16;
            speed = 30.0F;
            arrowSpacing = 7.0F;
            bufferCapacity = 28;
        }};
        tets_conveyor = new Conveyor("tets-conveyor") {{
            requirements(Category.distribution, ItemStack.with(TItems.battery, 1, TItems.tets_ingot, 1, TItems.concrete, 1));
            setHealth(this);
            separateItemCapacity = true;
            speed = 0.55f;
            displayedSpeed = 9.0F;
            health = 260;
            alwaysUnlocked = true;
            bridgeReplacement = tetsBridge;

        }};
        teleporter = new MassDriver("teleporter") {{
            requirements(Category.distribution, ItemStack.with(TItems.tets_ingot, 300, TItems.battery, 130, TItems.hyperalloy, 6));
            setHealth(this);
            size = 1;
            range = 12800;
            reload = 3;
            shake = 0;
            bulletSpeed = 220;
            shootEffect = Fx.lightningShoot;
            smokeEffect = Fx.colorSpark;
            receiveEffect = Fx.mineBig;
            shootSound = Sounds.pew;
            consumePower(3f);
            bullet = new MassDriverBolt() {{
                collidesTiles = false;
                lifetime = 100f;
                despawnEffect = Fx.dropItem;
                hitEffect = Fx.hitLaser;
            }};
            alwaysUnlocked = true;
        }};
    }

    private static void loadUnits() {
        //T1
        tetsBasicReconstructorAttack = new Reconstructor("tets_basic_reconstructor_attack") {
            {
                this.requirements(Category.units, ItemStack.with(Items.copper, 100, Items.lead, 60));
                this.size = 3;
                health = 300;
                this.consumePower(1.0F);
                this.consumeItems(ItemStack.with(Items.pyratite, 10, Items.silicon, 10));
                this.constructTime = 300.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.smallArtillery}});
                alwaysUnlocked = true;
            }
        };
        tetsBasicReconstructorEnergy = new Reconstructor("tets_basic_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(Items.copper, 100, Items.lead, 60));
                this.size = 3;
                health = 300;
                this.consumePower(1.0F);
                this.consumeItems(ItemStack.with(TItems.battery, 5, Items.graphite, 10));
                this.constructTime = 300.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.walkingBaseUnit, TUnits.miniMiner}});
                alwaysUnlocked = true;
            }
        };


        //T2
        tetsAdditiveReconstructorAttack = new Reconstructor("tets_additive_reconstructor_attack") {
            {
                this.requirements(Category.units, ItemStack.with(Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItems.concrete, 100));
                this.size = 3;
                health = 600;
                this.consumePower(3.0F);
                this.consumeItems(ItemStack.with(Items.titanium, 10, Items.silicon, 20, Items.blastCompound, 20));
                this.constructTime = 1200.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.smallArtillery, TUnits.mediumArtillery}});
                alwaysUnlocked = true;
            }
        };
        tetsAdditiveReconstructorEnergy = new Reconstructor("tets_additive_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(Items.copper, 200, Items.lead, 120, Items.titanium, 80, TItems.battery, 160, TItems.tantalium, 100));
                this.size = 3;
                health = 600;
                this.consumePower(3.0F);
                this.consumeItems(ItemStack.with(Items.beryllium, 10, Items.graphite, 20, TItems.battery, 80));
                this.constructTime = 1200.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.UFO, TUnits.UF1_Energy}, {TUnits.miniMiner, TUnits.mediMiner}});
                alwaysUnlocked = true;
            }
        };

        //T3
        tetsMultiplicativeReconstructorEnergy = new Reconstructor("tets_multiplicative_reconstructor_energy") {
            {
                this.requirements(Category.units, ItemStack.with(Items.tungsten, 400, Items.lead, 240, Items.titanium, 200, TItems.battery, 250, TItems.tantalium, 200));
                this.size = 5;
                health = 1000;
                this.consumePower(4.0F);
                this.consumeItems(ItemStack.with(Items.beryllium, 80, Items.tungsten, 60, TItems.battery, 120, TItems.tantalium, 40));
                //this.consumeLiquid(Liquids.cryofluid, 1f);
                this.constructTime = 2400.0F;
                this.upgrades.addAll(new UnitType[][]{{TUnits.UF1_Energy, TUnits.UF2_Energy}});
                alwaysUnlocked = true;
            }
        };
    }

    private static void loadOther() {
        tets_display = new LogicDisplay("tets_display") {{
            requirements(Category.logic, with(TItems.tantalium, 300, TItems.tets_ingot, 150, TItems.battery, 200, Items.metaglass, 150, Items.phaseFabric, 80));
            setHealth(this, 0.12f);

            displaySize = 7 * 32 - 8 - 8;

            size = 7;
        }};

        tets_processor = new LogicBlock("tets_processor") {{
            requirements(Category.logic, with(TItems.tantalium, 400, TItems.tets_ingot, 250, TItems.battery, 350, TItems.superconductor, 175, Items.surgeAlloy, 100));
            setHealth(this, 0.1f);

            consumeLiquid(TLiquids.super_cryofluid, 0.02f);
            consumePower(0.1f);
            liquidCapacity = 100;

            instructionsPerTick = 48;
            range = 9 * 64;
            size = 4;
        }};
    }

    private static void loadFake() {
        fakeCopperWallLarge = new Wall("fake_copper_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeTitaniumWallLarge = new Wall("fake_titanium_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeThoriumWallLarge = new Wall("fake_thorium_wall_large") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeMechanicalDrill = new Wall("fake_mechanical_drill") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakePneumaticDrill = new Wall("fake_pneumatic_drill") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
        }};
        fakeGraphitePress = new Wall("fake_graphite_press") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakeKiln = new Wall("fake_kiln") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakePlastaniumCompressor = new Wall("fake_plastanium_compressor") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakeSiliconSmelter = new Wall("fake_silicon_smelter") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakeSteamGenerator = new Wall("fake_steam_generator") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakeThermalGenerator = new Wall("fake_thermal_generator") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 80, Items.lead, 20));
            health = 420;
            alwaysUnlocked = true;
            size = 2;
            this.consumePower(0.05F);
        }};
        fakeLancer = new PowerTurret("fake_lancer") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 80, Items.lead, 20}));
                health = 420;
                alwaysUnlocked = true;
                this.size = 2;
                this.range = 165.0F;
                this.shoot.firstShotDelay = 999999.0F;
                this.recoil = 0.0F;
                this.reload = 999999.0F;
                this.shake = 2.0F;
                this.targetAir = false;
                this.moveWhileCharging = false;
                this.consumePower(0.05F);
                this.shootEffect = Fx.none;
                this.smokeEffect = Fx.none;
                this.shootType = new LaserBulletType(0.0F) {{
                    this.shootEffect = Fx.none;
                    this.smokeEffect = Fx.none;
                    this.hitEffect = Fx.none;
                    this.chargeEffect = Fx.none;
                }};
            }
        };
        fakeScatter = new PowerTurret("fake_scatter") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 80, Items.lead, 20}));
                health = 420;
                this.size = 2;
                alwaysUnlocked = true;
                this.range = 220.0F;
                this.shoot.firstShotDelay = 999999.0F;
                this.recoil = 0.0F;
                this.reload = 999999.0F;
                this.targetAir = true;
                this.targetGround = false;
                this.consumePower(0.05F);
                this.shootEffect = Fx.none;
                this.smokeEffect = Fx.none;
                this.shootType = new LaserBulletType(0.0F) {{
                    this.shootEffect = Fx.none;
                    this.smokeEffect = Fx.none;
                    this.hitEffect = Fx.none;
                    this.chargeEffect = Fx.none;
                }};
            }
        };
        //

        fakeBatteryLarge = new Battery("fake_battery_large") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 120, Items.lead, 50}));
                health = 820;
                this.size = 3;
                this.consumePowerBuffered(0.0F);
                this.baseExplosiveness = 0.0F;
            }
        };
        fakeSolarPanelLarge = new SolarGenerator("fake_solar_panel_large") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 120, Items.lead, 50}));
                health = 820;
                this.size = 3;
                this.consumePowerBuffered(0.0F);
                this.baseExplosiveness = 0.0F;
            }
        };
        fakeCoreShard = new Wall("fake_core_shard") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            this.itemCapacity = 0;
        }};
        fakeMultiPress = new Wall("fake_multi_press") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            this.consumePower(0.05F);
        }};
        fakeSiliconCrucible = new Wall("fake_silicon_crucible") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            this.consumePower(0.05F);
        }};
        fakeThoriumReactor = new Wall("fake_thorium_reactor") {{
            requirements(Category.defense, ItemStack.with(Items.copper, 120, Items.lead, 50));
            health = 820;
            alwaysUnlocked = true;
            size = 3;
            this.consumePower(0.05F);
        }};
        fakeFuse = new PowerTurret("fake_fuse") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 120, Items.lead, 50}));
                health = 820;
                this.size = 3;
                alwaysUnlocked = true;
                this.range = 90.0F;
                this.shoot.firstShotDelay = 999999.0F;
                this.recoil = 0.0F;
                this.reload = 999999.0F;
                this.targetAir = true;
                this.targetGround = true;
                this.consumePower(0.05F);
                this.shootEffect = Fx.none;
                this.smokeEffect = Fx.none;
                this.shootType = new LaserBulletType(0.0F) {{
                    this.shootEffect = Fx.none;
                    this.smokeEffect = Fx.none;
                    this.hitEffect = Fx.none;
                    this.chargeEffect = Fx.none;
                }};
            }
        };
        fakeRipple = new PowerTurret("fake_ripple") {
            {
                this.requirements(Category.defense, ItemStack.with(new Object[]{Items.copper, 120, Items.lead, 50}));
                health = 820;
                this.size = 3;
                alwaysUnlocked = true;
                this.range = 290.0F;
                this.shoot.firstShotDelay = 999999.0F;
                this.recoil = 0.0F;
                this.reload = 999999.0F;
                this.targetAir = false;
                this.targetGround = true;
                this.consumePower(0.05F);
                this.shootEffect = Fx.none;
                this.smokeEffect = Fx.none;
                this.shootType = new LaserBulletType(0.0F) {{
                    this.shootEffect = Fx.none;
                    this.smokeEffect = Fx.none;
                    this.hitEffect = Fx.none;
                    this.chargeEffect = Fx.none;
                }};
            }
        };
    }

    private static void loadMixins() {
        ((UnitFactory) Blocks.groundFactory).plans.add(new UnitFactory.UnitPlan(TUnits.walkingBaseUnit, 2100.0F, ItemStack.with(Items.silicon, 30, Items.lead, 30, Items.copper, 30)));
        ((UnitFactory) Blocks.airFactory).plans.add(new UnitFactory.UnitPlan(TUnits.UFO, 230.0F, ItemStack.with(Items.silicon, 5, TItems.battery, 2)));

        ((ItemTurret) Blocks.duo).ammoTypes.put(TItems.bee, new BasicBulletType(1.3f, 10) {{
            lifetime = ((ItemTurret) Blocks.duo).range / speed + 10;
            pierce = true;
            weaveMag = 10;
            weaveScale = 0.1f;
            range = 100f;
        }});
        ((ItemTurret) Blocks.duo).ammoTypes.put(TItems.beeq, new BasicBulletType(2.3f, 0) {{
            lifetime = ((ItemTurret) Blocks.duo).range / speed + 10;
            healAmount = 1;
            healPercent = 100;
            homingPower = 1;
            homingRange = 100;
            collidesTiles = collidesTeam = true;
        }});

        //Blocks.steamGenerator.buildVisibility = BuildVisibility.editorOnly;
    }

    private static void setHealth(Block block) {
        setHealth(block, 1);
    }

    private static void setHealth(Block block, float mult) {
        float health = 69;
        if (block.requirements.length > 0) {
            for (ItemStack stack : block.requirements) {
                health += (stack.item.cost * stack.item.cost + 1) * stack.amount * mult;
            }
            block.health = (int) health;
            return;
        }
        block.health = 150;
    }
}
