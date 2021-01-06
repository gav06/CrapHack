package me.gav06.craphack.mods;

import me.gav06.craphack.Module;
import me.gav06.craphack.events.Event;
import me.gav06.craphack.events.listeners.EventClientTick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KillAura extends Module {
    public KillAura(String name, int key, Category c) {
        super(name, key, c);
    }

    private Entity target = null;

    public void onEvent(Event e) {
        if (e instanceof EventClientTick) {
            List<Entity> targets = mc.world.loadedEntityList.stream()
                    .filter(Objects::nonNull)
                    .filter(entity -> entity != mc.player)
                    .filter(entity -> mc.player.getDistance(entity) <= 5)
                    .filter(entity -> !entity.isDead)
                    .filter(Entity::isEntityAlive)
                    .filter(this::attackCheck)
                    .sorted(Comparator.comparing(entity -> mc.player.getDistance(entity)))
                    .collect(Collectors.toList());
            if (targets.size() == 0)
                return;
            targets.forEach(this::attack);
        }
    }


    public void attack(Entity entity) {
        if (entity.isDead)
            return;

        if (mc.player.getCooledAttackStrength(0) >= 1) {
            mc.playerController.attackEntity(mc.player, entity);
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        target = entity;
    }


    public boolean attackCheck(Entity entity) {
        if (entity instanceof EntityPlayer) {
            if (((EntityPlayer) entity).getHealth() > 0)
                return true;
        } else if (entity instanceof EntityMob) {
            if (((EntityMob) entity).getHealth() > 0)
                return true;
        } else if (entity instanceof EntityAnimal) {
            if (((EntityAnimal) entity).getHealth() > 0)
                return true;
        }
        return false;
    }
}
