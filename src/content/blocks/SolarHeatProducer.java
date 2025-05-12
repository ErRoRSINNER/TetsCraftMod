package content.blocks;

import arc.Core;
import arc.math.Mathf;
import mindustry.Vars;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.meta.Attribute;

public class SolarHeatProducer extends HeatProducer {
    public SolarHeatProducer(String name) {
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("efficiency", (SolarHeatProducer.SolarHeatProducerBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.efficiency", entity.solarEfficiency * 100),
                        () -> Pal.lightOrange,
                        () -> entity.solarEfficiency));
    }

    public class SolarHeatProducerBuild extends HeatProducer.HeatProducerBuild {
        public float solarEfficiency = 1.0F;

        public SolarHeatProducerBuild() {
            super();
        }

        @Override
        public void updateTile() {
            this.solarEfficiency = Vars.state.rules.solarMultiplier * Mathf.maxZero(Attribute.light.env() + (Vars.state.rules.lighting ? 1.0F - Vars.state.rules.ambientLight.a : 1.0F));
            super.updateTile();
        }

        @Override
        public float efficiencyScale() {
            return solarEfficiency;
        }
    }
}
