package content.blocks;

import arc.Core;
import arc.math.Mathf;
import mindustry.Vars;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

public class LiquidAccurateSolarCrafter extends GenericCrafter {

    /**
     * Правильно рассчитывает количество входящих и выходящих жидкостей.
     * Подвержен влиянию солнца
     */
    public LiquidAccurateSolarCrafter(String name) {
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("efficiency", (LiquidAccurateSolarCrafterBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.efficiency", entity.solarEfficiency * 100),
                        () -> Pal.lightOrange,
                        () -> entity.solarEfficiency));
    }

    @Override
    public void setStats(){
        //TODO Сделать правильное отображение потребления входящей жидкости
        stats.timePeriod = craftTime;
        super.setStats();
        stats.remove(Stat.productionTime);
        stats.remove(Stat.output);

        if((hasItems && itemCapacity > 0) || outputItems != null){
            stats.add(Stat.productionTime, craftTime / 60f, StatUnit.seconds);
        }

        if(outputItems != null){
            stats.add(Stat.output, StatValues.items(craftTime, outputItems));
        }

        if(outputLiquids != null){
            stats.add(Stat.output, StatValues.liquids(craftTime / 60f, outputLiquids));
        }
    }

    public class LiquidAccurateSolarCrafterBuild extends GenericCrafter.GenericCrafterBuild {
        public float solarEfficiency = 1.0F;

        public LiquidAccurateSolarCrafterBuild() {
            super();
        }

        @Override
        public void updateTile() {
            this.solarEfficiency = Vars.state.rules.solarMultiplier * Mathf.maxZero(Attribute.light.env() + (Vars.state.rules.lighting ? 1.0F - Vars.state.rules.ambientLight.a : 1.0F));
            super.updateTile();
        }

        @Override
        public float efficiencyScale() {
            return solarEfficiency * (60 / craftTime);
        }
    }
}
