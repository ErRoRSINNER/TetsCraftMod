package content.blocks.torque;

import mindustry.world.Block;
import mindustry.world.meta.BlockGroup;

public class TorqueBlock extends Block {
    public TorqueBlock(String name) {
        super(name);
        this.update = true;
        this.solid = true;
        this.hasPower = true;
        this.group = BlockGroup.power;
    }
}
