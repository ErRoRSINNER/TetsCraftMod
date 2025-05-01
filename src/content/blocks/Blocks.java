package content.blocks;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.production.Drill;

import static mindustry.type.ItemStack.with;

public class Blocks {
    public static Block homoDrill, miniDrill, nihonDrill, tetsDrill, bangun;


    public static void load() {

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
        bangun = new ItemTurret("bangun") {{
            requirements(Category.turret, with(content.items.Items.concrete, 150, content.items.Items.tantalium, 135, content.items.Items.tets_ingot, 60));
            ammo(content.items.Items.concrete, new ArtilleryBulletType(1.5f, 500, "shell") {{
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
                    hitEffect = Fx.dynamicExplosion;
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
    }
}
