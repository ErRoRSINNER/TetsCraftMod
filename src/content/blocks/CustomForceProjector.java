package content.blocks;

import arc.Events;
import arc.func.Cons;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Intersector;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.logic.Ranged;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeCoolant;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

//TODO Переделать. Сделать нормальным
public class CustomForceProjector extends ForceProjector {

    public final int timerUse;
    public float phaseUseTime;
    public float phaseRadiusBoost;
    public float phaseShieldBoost;
    public float radius;
    public int sides;
    public float shieldRotation;
    public float shieldHealth;
    public float cooldownNormal;
    public float cooldownLiquid;
    public float cooldownBrokenBase;
    public float coolantConsumption;
    public boolean consumeCoolant;
    public Effect absorbEffect;
    public Effect shieldBreakEffect;
    public TextureRegion topRegion;
    @Nullable
    public Consume itemConsumer;
    @Nullable
    public Consume coolantConsumer;
    protected static CustomForceProjector.ForceBuild paramEntity;
    protected static Effect paramEffect;
    protected static final Cons<Bullet> shieldConsumer = (bullet) -> {
        if (bullet.team != paramEntity.team && bullet.type.absorbable && Intersector.isInRegularPolygon(((CustomForceProjector)paramEntity.block).sides, paramEntity.x, paramEntity.y, paramEntity.realRadius(), ((CustomForceProjector)paramEntity.block).shieldRotation, bullet.x, bullet.y)) {
            bullet.absorb();
            paramEffect.at(bullet);
            paramEntity.hit = 1.0F;
            CustomForceProjector.ForceBuild var10000 = paramEntity;
            var10000.buildup += bullet.damage;
        }

    };

    //Modified
    public float transparency;

    public CustomForceProjector(String name) {
        super(name);
        this.timerUse = this.timers++;
        this.phaseUseTime = 350.0F;
        this.phaseRadiusBoost = 80.0F;
        this.phaseShieldBoost = 400.0F;
        this.radius = 101.7F;
        this.sides = 6;
        this.shieldRotation = 0.0F;
        this.shieldHealth = 700.0F;
        this.cooldownNormal = 1.75F;
        this.cooldownLiquid = 1.5F;
        this.cooldownBrokenBase = 0.35F;
        this.coolantConsumption = 0.1F;
        this.consumeCoolant = true;
        this.absorbEffect = Fx.absorb;
        this.shieldBreakEffect = Fx.shieldBreak;
        this.update = true;
        this.solid = true;
        this.group = BlockGroup.projectors;
        this.hasPower = true;
        this.hasLiquids = true;
        this.hasItems = true;
        this.envEnabled |= 2;
        this.ambientSound = Sounds.shield;
        this.ambientSoundVolume = 0.08F;
        if (this.consumeCoolant) {
            this.consume(this.coolantConsumer = new ConsumeCoolant(this.coolantConsumption)).boost().update(false);
        }

        //Modified
        this.transparency = 125f;
    }

    public void init() {
        this.updateClipRadius(this.radius + this.phaseRadiusBoost + 3.0F);
        super.init();
    }

    public void setBars() {
        super.setBars();
        //this.addBar("shield", (entity) -> (new Bar("stat.shieldhealth", Pal.accent, () -> entity.broken ? 0.0F : 1.0F - entity.buildup / (this.shieldHealth + this.phaseShieldBoost * entity.phaseHeat))).blink(Color.white));
    }

    public boolean outputsItems() {
        return false;
    }

    public void setStats() {
        boolean consItems = this.itemConsumer != null;
        if (consItems) {
            this.stats.timePeriod = this.phaseUseTime;
        }

        super.setStats();
        this.stats.add(Stat.shieldHealth, this.shieldHealth, StatUnit.none);
        this.stats.add(Stat.cooldownTime, (float)((int)(this.shieldHealth / this.cooldownBrokenBase / 60.0F)), StatUnit.seconds);
        if (consItems) {
            Consume var3 = this.itemConsumer;
            if (var3 instanceof ConsumeItems) {
                ConsumeItems coni = (ConsumeItems)var3;
                this.stats.remove(Stat.booster);
                this.stats.add(Stat.booster, StatValues.itemBoosters("+{0} " + StatUnit.shieldHealth.localized(), this.stats.timePeriod, this.phaseShieldBoost, this.phaseRadiusBoost, coni.items, this::consumesItem));
                this.stats.add(Stat.booster, StatValues.speedBoosters("", this.coolantConsumption, Float.MAX_VALUE, true, this::consumesLiquid));
            }
        }

    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        //super.drawPlace(x, y, rotation, valid);
        Draw.color(Pal.gray);
        Lines.stroke(3.0F);
        Lines.poly((float)(x * 8) + this.offset, (float)(y * 8) + this.offset, this.sides, this.radius, this.shieldRotation);
        Draw.color(Color.gray);
        Lines.stroke(1.0F);
        Lines.poly((float)(x * 8) + this.offset, (float)(y * 8) + this.offset, this.sides, this.radius, this.shieldRotation);
        Draw.color();
    }

    public class ForceBuild extends ForceProjector.ForceBuild implements Ranged {
        public boolean broken = true;
        public float buildup;
        public float radscl;
        public float hit;
        public float warmup;
        public float phaseHeat;

        public ForceBuild() {
        }

        public float range() {
            return this.realRadius();
        }

        public boolean shouldAmbientSound() {
            return !this.broken && this.realRadius() > 1.0F;
        }

        public void onRemoved() {
            float radius = this.realRadius();
            if (!this.broken && radius > 1.0F) {
                Fx.forceShrink.at(this.x, this.y, radius, this.team.color);
            }

            super.onRemoved();
        }

        public void pickedUp() {
            super.pickedUp();
            this.radscl = this.warmup = 0.0F;
        }

        public boolean inFogTo(Team viewer) {
            return false;
        }

        public void updateTile() {
            boolean phaseValid = CustomForceProjector.this.itemConsumer != null && CustomForceProjector.this.itemConsumer.efficiency(this) > 0.0F;
            this.phaseHeat = Mathf.lerpDelta(this.phaseHeat, (float)Mathf.num(phaseValid), 0.1F);
            if (phaseValid && !this.broken && this.timer(CustomForceProjector.this.timerUse, CustomForceProjector.this.phaseUseTime) && this.efficiency > 0.0F) {
                this.consume();
            }

            this.radscl = Mathf.lerpDelta(this.radscl, this.broken ? 0.0F : this.warmup, 0.05F);
            if (Mathf.chanceDelta((double)(this.buildup / CustomForceProjector.this.shieldHealth * 0.1F))) {
                Fx.reactorsmoke.at(this.x + Mathf.range(4.0F), this.y + Mathf.range(4.0F));
            }

            this.warmup = Mathf.lerpDelta(this.warmup, this.efficiency, 0.1F);
            if (this.buildup > 0.0F) {
                float scale = !this.broken ? CustomForceProjector.this.cooldownNormal : CustomForceProjector.this.cooldownBrokenBase;
                if (CustomForceProjector.this.coolantConsumer != null && CustomForceProjector.this.coolantConsumer.efficiency(this) > 0.0F) {
                    CustomForceProjector.this.coolantConsumer.update(this);
                    scale *= CustomForceProjector.this.cooldownLiquid * (1.0F + (this.liquids.current().heatCapacity - 0.4F) * 0.9F);
                }

                this.buildup -= this.delta() * scale;
            }

            if (this.broken && this.buildup <= 0.0F) {
                this.broken = false;
            }

            if (this.buildup >= CustomForceProjector.this.shieldHealth + CustomForceProjector.this.phaseShieldBoost * this.phaseHeat && !this.broken) {
                this.broken = true;
                this.buildup = CustomForceProjector.this.shieldHealth;
                CustomForceProjector.this.shieldBreakEffect.at(this.x, this.y, this.realRadius(), this.team.color);
                if (this.team != Vars.state.rules.defaultTeam) {
                    Events.fire(EventType.Trigger.forceProjectorBreak);
                }
            }

            if (this.hit > 0.0F) {
                this.hit -= 0.2F * Time.delta;
            }

            this.deflectBullets();
        }

        public void deflectBullets() {
            float realRadius = this.realRadius();
            if (realRadius > 0.0F && !this.broken) {
                CustomForceProjector.paramEntity = this;
                CustomForceProjector.paramEffect = CustomForceProjector.this.absorbEffect;
                Groups.bullet.intersect(this.x - realRadius, this.y - realRadius, realRadius * 2.0F, realRadius * 2.0F, CustomForceProjector.shieldConsumer);
            }

        }

        public float realRadius() {
            return (CustomForceProjector.this.radius + this.phaseHeat * CustomForceProjector.this.phaseRadiusBoost) * this.radscl;
        }

        public double sense(LAccess sensor) {
            if (sensor == LAccess.heat) {
                return (double)this.buildup;
            } else if (sensor == LAccess.shield) {
                return this.broken ? (double)0.0F : (double)Math.max(CustomForceProjector.this.shieldHealth + CustomForceProjector.this.phaseShieldBoost * this.phaseHeat - this.buildup, 0.0F);
            } else {
                return super.sense(sensor);
            }
        }

        public void draw() {
            super.draw();
            if (this.buildup > 0.0F) {
                Draw.alpha(this.buildup / CustomForceProjector.this.shieldHealth * 0.75F);
                Draw.z(31.0F);
                Draw.blend(Blending.additive);
                //Draw.rect(CustomForceProjector.this.topRegion, this.x, this.y);
                Draw.blend();
                Draw.z(30.0F);
                Draw.reset();
            }

            this.drawShield();
        }

        public void drawShield() {
            if (!this.broken) {
                float radius = this.realRadius();
                if (radius > 0.001F) {
                    Draw.color(this.team.color, Color.white, Mathf.clamp(this.hit));
                    if (Vars.renderer.animateShields) {
                        Draw.z(transparency + 0.001F * this.hit);
                        Fill.poly(this.x, this.y, CustomForceProjector.this.sides, radius, CustomForceProjector.this.shieldRotation);
                    } else {
                        Draw.z(transparency);
                        Lines.stroke(1.5F);
                        Draw.alpha(0.09F + Mathf.clamp(0.08F * this.hit));
                        Fill.poly(this.x, this.y, CustomForceProjector.this.sides, radius, CustomForceProjector.this.shieldRotation);
                        Draw.alpha(1.0F);
                        Lines.poly(this.x, this.y, CustomForceProjector.this.sides, radius, CustomForceProjector.this.shieldRotation);
                        Draw.reset();
                    }
                }
            }

            Draw.reset();
        }

        public void write(Writes write) {
            super.write(write);
            write.bool(this.broken);
            write.f(this.buildup);
            write.f(this.radscl);
            write.f(this.warmup);
            write.f(this.phaseHeat);
        }

        public void read(Reads read, byte revision) {
            super.read(read, revision);
            this.broken = read.bool();
            this.buildup = read.f();
            this.radscl = read.f();
            this.warmup = read.f();
            this.phaseHeat = read.f();
        }
    }
}
