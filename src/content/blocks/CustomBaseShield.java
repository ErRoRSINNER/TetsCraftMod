package content.blocks;

import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.world.blocks.defense.BaseShield;

public class CustomBaseShield extends BaseShield {

    public float radius;
    public int sides = 24;
    protected static BaseShieldBuild paramBuild;
    protected static final Cons<Bullet> bulletConsumer = (bullet) -> {
        if (bullet.team != paramBuild.team && bullet.type.absorbable && bullet.within(paramBuild, paramBuild.radius())) {
            bullet.absorb();
        }

    };
    protected static final Cons<Unit> unitConsumer = (unit) -> {
        float overlapDst = unit.hitSize / 2.0F + paramBuild.radius() - unit.dst(paramBuild);
        if (overlapDst > 0.0F) {
            if (overlapDst > unit.hitSize * 1.5F) {
                unit.kill();
            } else {
                unit.vel.setZero();
                unit.move(Tmp.v1.set(unit).sub(paramBuild).setLength(overlapDst + 0.01F));
                if (Mathf.chanceDelta((double)(0.12F * Time.delta))) {
                    Fx.circleColorSpark.at(unit.x, unit.y, paramBuild.team.color);
                }
            }
        }

    };

    public CustomBaseShield(String name, int radius) {
        super(name);
        this.radius = radius;
        this.hasPower = true;
        this.update = this.solid = true;
        this.rebuildable = false;
    }

    public void init() {
        super.init();
        this.updateClipRadius(this.radius);
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle((float)(x * 8) + this.offset, (float)(y * 8) + this.offset, this.radius, Vars.player.team().color);
    }

    public class BaseShieldBuild extends Building {
        public boolean broken = false;
        public float hit = 0.0F;
        public float smoothRadius;

        public BaseShieldBuild() {
        }

        public void updateTile() {
            this.smoothRadius = Mathf.lerpDelta(this.smoothRadius, CustomBaseShield.this.radius * this.efficiency, 0.05F);
            float rad = this.radius();
            if (rad > 1.0F) {
                CustomBaseShield.paramBuild = this;
                Groups.bullet.intersect(this.x - rad, this.y - rad, rad * 2.0F, rad * 2.0F, BaseShield.bulletConsumer);
                Units.nearbyEnemies(this.team, this.x, this.y, rad + 10.0F, BaseShield.unitConsumer);
            }

        }

        public float radius() {
            return this.smoothRadius;
        }

        public void drawSelect() {
            super.drawSelect();
            Drawf.dashCircle(this.x, this.y, CustomBaseShield.this.radius, this.team.color);
        }

        public void draw() {
            super.draw();
            this.drawShield();
        }

        public boolean inFogTo(Team viewer) {
            return false;
        }

        public void drawShield() {
            if (!this.broken) {
                float radius = this.radius();
                //Draw.z(125.0F);
                Draw.color(Color.gray, Color.white, Mathf.clamp(this.hit));
                if (Vars.renderer.animateShields) {
                    Fill.poly(this.x, this.y, CustomBaseShield.this.sides, radius);
                } else {
                    Lines.stroke(1.5F);
                    //Draw.alpha(0.09F + Mathf.clamp(0.08F * this.hit));
                    Draw.alpha(1.0F);
                    Fill.poly(this.x, this.y, CustomBaseShield.this.sides, radius);
                    Lines.poly(this.x, this.y, CustomBaseShield.this.sides, radius);
                    Draw.reset();
                }
            }

            Draw.reset();
        }

        public byte version() {
            return 1;
        }

        public void write(Writes write) {
            super.write(write);
            write.f(this.smoothRadius);
            write.bool(this.broken);
        }

        public void read(Reads read, byte revision) {
            super.read(read);
            if (revision >= 1) {
                this.smoothRadius = read.f();
                this.broken = read.bool();
            }

        }
    }
}
