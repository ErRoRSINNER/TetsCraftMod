package util;

import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;

public class IOEntryWrapper {

    private ItemStack[] items;
    private LiquidStack[] liquids;
    private float power;

    public IOEntryWrapper(ItemStack[] items, LiquidStack[] liquids, float power){
        this.items = items;
        this.liquids = liquids;
        this.power = power;
    }

    public IOEntryWrapper(ItemStack[] items, LiquidStack[] liquids){
        this.items = items;
        this.liquids = liquids;
    }

    public static IOEntry of(ItemStack[] items, LiquidStack[] liquids, float power){
        IOEntry entry = of(items, liquids);
        entry.power = power;

        return entry;
    }

    public static IOEntry of(ItemStack[] items, LiquidStack[] liquids){
        IOEntry entry = new IOEntry();
        entry.items = items;
        entry.fluids = liquids;

        return entry;
    }
}
