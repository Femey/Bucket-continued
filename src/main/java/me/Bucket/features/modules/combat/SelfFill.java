package me.Bucket.features.modules.combat;

import me.Bucket.features.modules.Module;
import me.Bucket.features.setting.Setting;
import me.Bucket.util.BlockUtil;
import me.Bucket.util.InventoryUtil;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class SelfFill
        extends Module {
    public Setting<Boolean> autoSwitch                         = this.register(new Setting<Object>("AutoSwitch", false));
    public Setting<Boolean> silent     = this.register(new Setting<Object>("Silent", false));

    private final Setting<Boolean> packet = this.register(new Setting<Boolean>("PacketPlace", Boolean.FALSE));
    private final Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", Boolean.FALSE));

    public SelfFill() {
        super("SelfFill", "SelfFills yourself in a hole.", Module.Category.COMBAT, true, false, true);
    }

    @Override
    public void onEnable() {
        SelfFill.mc.player.jump();
    }

    @Override
    public void onUpdate() {
        BlockPos pos = new BlockPos(SelfFill.mc.player.posX, SelfFill.mc.player.posY, SelfFill.mc.player.posZ);
        if (SelfFill.mc.world.getBlockState(pos.down()).getBlock() == Blocks.AIR && BlockUtil.isPositionPlaceable(pos.down(), false) == 3) {
            BlockUtil.placeBlock(pos.down(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
        }
        if (SelfFill.mc.world.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN) {
            SelfFill.mc.player.connection.sendPacket(new CPacketPlayer.Position(SelfFill.mc.player.posX, SelfFill.mc.player.posY - 1.3, SelfFill.mc.player.posZ, false));
            SelfFill.mc.player.setPosition(SelfFill.mc.player.posX, SelfFill.mc.player.posY - 1.3, SelfFill.mc.player.posZ);
            this.toggle();
        }
        int slot = mc.player.inventory.currentItem;
        if (autoSwitch.getValue()) InventoryUtil.switchToHotbarSlot(BlockObsidian.class, silent.getValue());
        EnumHand hand = InventoryUtil.getHandHolding(Item.getItemFromBlock(Blocks.OBSIDIAN));
        if (hand != null) {
            if (silent.getValue()) InventoryUtil.switchToHotbarSlot(slot, true);
            mc.player.swingArm(hand);
        }
    }
}