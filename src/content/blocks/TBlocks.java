package content.blocks;

import content.items.TItems;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.MassDriverBolt;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Separator;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFade;
import mindustry.world.draw.DrawMulti;

import static mindustry.type.ItemStack.with;

public class TBlocks {
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill, bangun, govnomet, teleporter, tets_conveyor, solpanel, tets_battery, crystal_powerblock,
            estrella_de_platino, small_shield_projector, concrete_wall, concrete_wall_large, prav_wall, prav_wall_large, daew, poop_wall, battery_factory, bee_plant,
            concrete_mixer;


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
        battery_factory = new GenericCrafter("battery_factory") {{
            requirements(Category.crafting, ItemStack.with(Items.silicon, 24, Items.lead, 80, Items.graphite, 20, Items.copper, 140));
            hasItems = true;
            liquidCapacity = 60.0F;
            craftTime = 50.0F;
            outputItem = new ItemStack(TItems.battery, 2);
            size = 2;
            health = 420;
            hasPower = hasLiquids = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFade(), new DrawBlurSpin());
            consumeLiquid(Liquids.cryofluid, 0.3F);
            consumePower(3.0F);
            consumeItems(ItemStack.with(Items.coal, 2, Items.silicon, 2, Items.lead, 2, Items.copper, 1));
        }};
        bee_plant = new Separator("bee-plantq") {{
            requirements(Category.crafting, ItemStack.with(Items.scrap, 40, Items.copper, 100, Items.graphite, 50, Items.silicon, 5));
            results = ItemStack.with(TItems.bee, 10, TItems.beeq, 1);
            hasPower = true;
            craftTime = 55.0F;
            size = 3;
            itemCapacity = 20;
            consumePower(1.0F);
            consumeItem(TItems.poop, 3);
            consumeLiquid(Liquids.water, 0.04F);
        }};
        concrete_mixer = new Separator("concrete_mixer") {{
            requirements(Category.crafting, ItemStack.with(Items.copper, 60, Items.silicon, 12, Items.titanium, 12, Items.lead, 35));
            results = ItemStack.with(TItems.concrete, 7, Items.scrap, 2);
            hasPower = true;
            craftTime = 21.0F;
            size = 2;
            itemCapacity = 30;
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
            requirements(Category.production, ItemStack.with(Items.copper, 24)); //TODO
            tier = 2;
            drillTime = 500.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100});
            consumeLiquid(Liquids.water, 0.06F).boost();

            health = 100;
        }};
        miniDrill = new Drill("mini-drill") {{
            requirements(Category.production, ItemStack.with(Items.copper, 24, Items.lead, 24)); //TODO
            tier = 3;
            drillTime = 400.0F;
            size = 1;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.water, 0.08F).boost();

            health = 120;
        }};
        nihonDrill = new Drill("nihon-drill") {{
            requirements(Category.production, ItemStack.with(Items.copper, 24, Items.lead, 24)); //TODO
            tier = 6;
            drillTime = 200.0F;
            size = 5;
            //this.researchCost = ItemStack.with(new Object[]{Items.copper, 100, Items.lead, 60});
            consumeLiquid(Liquids.water, 0.1F).boost();

            health = 180;
        }};
        tetsDrill = new Drill("tets-drill") {{
            requirements(Category.production, ItemStack.with(Items.thorium, 12));
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
            size = 10;
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
            ammo(TItems.poop, new ArtilleryBulletType(1.5f, 1, "poop") {{
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
