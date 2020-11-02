package net.wurstclient.hacks;

import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.*;
import net.wurstclient.Category;
import net.wurstclient.events.PacketInputListener;
import net.wurstclient.hack.Hack;

public class PacketSnifferHack extends Hack implements PacketInputListener {

    public PacketSnifferHack() {
        super("Packet Sniffer", "Logs all incoming packets.");
        this.setCategory(Category.OTHER);
    }

    @Override
    protected void onEnable() {
        EVENTS.add(PacketInputListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(PacketInputListener.class, this);
    }

    @Override
    public void onReceivedPacket(PacketInputEvent event) {
        Packet<?> packet = event.getPacket();

        if (packet instanceof EntityEquipmentUpdateS2CPacket) {
            EntityEquipmentUpdateS2CPacket p = (EntityEquipmentUpdateS2CPacket) packet;
            System.out.println("EquipmentId: " + p.getId() + " EquipmentSlot: " + p.getSlot() + " ItemStack: " + p.getStack());
        } else if (packet instanceof ExperienceBarUpdateS2CPacket) {
            ExperienceBarUpdateS2CPacket p = (ExperienceBarUpdateS2CPacket) packet;
            System.out.println("Bar progress: " + p.getBarProgress() + " Experience: " + p.getExperience() + " ExperienceLevel: " + p.getExperienceLevel());
        } else if (packet instanceof HealthUpdateS2CPacket) {
            HealthUpdateS2CPacket p = (HealthUpdateS2CPacket) packet;
            System.out.println("Health: " + p.getHealth() + " Food: " + p.getFood() + " Saturation: " + p.getSaturation());
        } else if (packet instanceof PlaySoundS2CPacket) {
            PlaySoundS2CPacket p = (PlaySoundS2CPacket) packet;
            System.out.printf("Sound id [%s] from pos: %s %s %s\n", p.getSound().getId().getPath(), p.getX(), p.getY(), p.getZ());
        } else if (packet instanceof PlaySoundIdS2CPacket) {
            PlaySoundIdS2CPacket p = (PlaySoundIdS2CPacket) packet;
            System.out.printf("Sound id [%s] from pos: %s %s %s\n", p.getSoundId().getPath(), p.getX(), p.getY(), p.getZ());
        } else if (packet instanceof PlaySoundFromEntityS2CPacket) {
            PlaySoundFromEntityS2CPacket p = (PlaySoundFromEntityS2CPacket) packet;
            System.out.printf("Sound id [%s] from entity id: %s\n", p.getSound().getId().getPath(), p.getEntityId());
        }

    }
}
