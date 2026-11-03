package content.block;

import content.block.custom.CustomForceProjector;
import content.item.TItem;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.blocks.defense.ShockMine;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.consumers.ConsumeCoolant;

import static content.util.Util.setHealth;

public class TBlockUtility {
    public static Block made_in_heaven, small_shield_projector, hidingShield, smallContainer, vault, vault_big, tantal_mine;

    protected static void loadUtil() {
        small_shield_projector = new ForceProjector("small_shield_projector") {{
            requirements(Category.effect, ItemStack.with(TItem.tantalium, 45, Items.silicon, 35, TItem.battery, 10));
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
            consumeItem(TItem.bee, 8).boost();
            phaseRadiusBoost = 70.0F;
            phaseShieldBoost = 20;
            consumePower(3.0F);
            alwaysUnlocked = true;
        }};
        made_in_heaven = new OverdriveProjector("made_in_heaven") {{
            requirements(Category.effect, ItemStack.with(TItem.battery, 800, TItem.tets_ingot, 100, TItem.concrete, 170, TItem.superconductor, 70));
            setHealth(this, 0.1f);
            consumePower(5F);
            consumeItem(TItem.goddamm_ingot, 6).boost();
            size = 4;
            speedBoostPhase = 0.654321f;
            phaseRangeBoost = 150;
            range = 800.0F;
            speedBoost = 2.5F;
            useTime = 400.0F;
            consumeItems(ItemStack.with(TItem.bee, 5));
            alwaysUnlocked = true;
        }};
        smallContainer = new StorageBlock("small_container") {{
            requirements(Category.effect, ItemStack.with(TItem.concrete, 100, Items.titanium, 25));
            health = 100;
            size = 1;
            itemCapacity = 50;
            alwaysUnlocked = true;
        }};
        vault = new StorageBlock("vault") {{
            requirements(Category.effect, ItemStack.with(TItem.concrete, 500, TItem.tantalium, 125));
            setHealth(this, 0.4f);
            size = 4;
            itemCapacity = 4000;
        }};
        vault_big = new StorageBlock("tantal_vault") {{
            requirements(Category.effect, ItemStack.with(TItem.tantalium, 512, TItem.tets_ingot, 75, TItem.concrete, 300));
            setHealth(this, 0.45f);
            size = 5;
            itemCapacity = 9000;
        }};
        hidingShield = new CustomForceProjector("hiding_shield") {{
            requirements(Category.defense, ItemStack.with(Items.thorium, 20, TItem.tantalium, 50, TItem.battery, 120));
            size = 3;
            health = 250;
            transparency = 255f;
            sides = 4;
            shieldRotation = 45;
            radius = 85;
            shieldHealth = 120;
            cooldownNormal = 3.5F;
            cooldownLiquid = 2.8F;
            cooldownBrokenBase = 0.35F;
            consumePower(4.0F);
            alwaysUnlocked = true;
        }};
        tantal_mine = new ShockMine("tantal_mine") {{
            requirements(Category.effect, ItemStack.with(TItem.tantalium, 10, TItem.battery, 12));
            hasShadow = false;
            health = 150;
            damage = 45.0F;
            tileDamage = 14.0F;
            length = 12;
            tendrils = 13;
        }};
    }
}
