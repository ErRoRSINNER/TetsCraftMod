package content.blocks.custom;

import arc.Events;
import arc.math.Mathf;
import arc.util.Nullable;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.game.EventType;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class CustomNuclearReactor extends NuclearReactor {

    @Nullable
    public LiquidStack outputLiquid;
    public boolean explodeOnFull;

    public CustomNuclearReactor(String name) {
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();
        if (this.outputLiquid != null) {
            this.addLiquidBar(this.outputLiquid.liquid);
        }
    }

    @Override
    public void init() {
        super.init();
        if (this.outputLiquid != null) {
            this.outputsLiquid = true;
            this.hasLiquids = true;
        }

        if (this.explodeOnFull && this.outputLiquid != null && this.explosionPuddleLiquid == null) {
            this.explosionPuddleLiquid = this.outputLiquid.liquid;
        }
    }

    @Override
    public void setStats() {
        super.setStats();
        if (this.outputLiquid != null) {
            this.stats.add(Stat.output, StatValues.liquid(this.outputLiquid.liquid, this.outputLiquid.amount * 60.0F, true));
        }
    }

    public class CustomNuclearReactorBuild extends NuclearReactor.NuclearReactorBuild {

        public CustomNuclearReactorBuild(){
            super();
        }

        @Override
        public void updateTile() {
            int fuel = this.items.get(CustomNuclearReactor.this.fuelItem);
            float fullness = (float)fuel / (float)CustomNuclearReactor.this.itemCapacity;
            this.productionEfficiency = fullness;
            if (fuel > 0 && this.enabled) {
                this.heat += fullness * CustomNuclearReactor.this.heating * Math.min(this.delta(), 4.0F);
                if (this.timer(CustomNuclearReactor.this.timerFuel, CustomNuclearReactor.this.itemDuration / this.timeScale)) {
                    this.consume();
                }
            } else {
                this.productionEfficiency = 0.0F;
            }

            //MODIFIED
            if (this.heat > 0.0F) {
                float maxUsed = Math.min(this.liquids.get(Liquids.cryofluid), this.heat / CustomNuclearReactor.this.coolantPower);
                this.heat -= maxUsed * CustomNuclearReactor.this.coolantPower;
                this.liquids.remove(Liquids.cryofluid, maxUsed);
            }

            if (this.heat > CustomNuclearReactor.this.smokeThreshold) {
                float smoke = 1.0F + (this.heat - CustomNuclearReactor.this.smokeThreshold) / (1.0F - CustomNuclearReactor.this.smokeThreshold);
                if (Mathf.chance((double)smoke / (double)20.0F * (double)this.delta())) {
                    Fx.reactorsmoke.at(this.x + Mathf.range((float)(CustomNuclearReactor.this.size * 8) / 2.0F), this.y + Mathf.range((float)(CustomNuclearReactor.this.size * 8) / 2.0F));
                }
            }

            this.heat = Mathf.clamp(this.heat);
            if (this.heat >= 0.999F) {
                Events.fire(EventType.Trigger.thoriumReactorOverheat);
                this.kill();
            }

            //ADDED
            if (CustomNuclearReactor.this.outputLiquid != null) {
                float added = Math.min(this.productionEfficiency * this.delta() * CustomNuclearReactor.this.outputLiquid.amount, CustomNuclearReactor.this.liquidCapacity - this.liquids.get(CustomNuclearReactor.this.outputLiquid.liquid));
                this.liquids.add(CustomNuclearReactor.this.outputLiquid.liquid, added);
                this.dumpLiquid(CustomNuclearReactor.this.outputLiquid.liquid);
                if (CustomNuclearReactor.this.explodeOnFull && this.liquids.get(CustomNuclearReactor.this.outputLiquid.liquid) >= CustomNuclearReactor.this.liquidCapacity - 0.01F) {
                    this.kill();
                    Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                }
            }
        }
    }
}
