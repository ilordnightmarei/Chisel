package team.chisel.common.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import team.chisel.client.gui.PreviewType;

public class NBTUtil {

    private static final @Nonnull String KEY_TAG = "chiseldata";
    private static final @Nonnull String KEY_TARGET = "target";
    private static final @Nonnull String KEY_PREVIEW_TYPE = "preview";
    private static final @Nonnull String KEY_SELECTION_SLOT = "selectslot";
    private static final @Nonnull String KEY_TARGET_SLOT = "targetslot";
    private static final @Nonnull String KEY_ROTATE = "rotate";

    @SuppressWarnings("null")
    public static @Nonnull NBTTagCompound getTag(@Nonnull ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Warning suppressed: tag is guaranteed to be set from above code
        return stack.getTagCompound();
    }

    public static @Nonnull NBTTagCompound getChiselTag(@Nonnull ItemStack stack) {
        return getTag(stack).getCompoundTag(KEY_TAG);
    }
    
    public static void setChiselTag(@Nonnull ItemStack stack, @Nullable NBTTagCompound tag) {
        getTag(stack).setTag(KEY_TAG, tag);
    }

    public static @Nullable ItemStack getChiselTarget(@Nonnull ItemStack stack) {
        return ItemStack.loadItemStackFromNBT(getChiselTag(stack).getCompoundTag(KEY_TARGET));
    }

    public static void setChiselTarget(@Nonnull ItemStack chisel, @Nullable ItemStack target) {
        getChiselTag(chisel).setTag(KEY_TARGET, target == null ? new NBTTagCompound() : target.writeToNBT(new NBTTagCompound()));
    }
    
    @SuppressWarnings("null") // Can't use type annotations with JSR
    public static @Nonnull PreviewType getHitechType(@Nonnull ItemStack stack) {
        return PreviewType.values()[getChiselTag(stack).getInteger(KEY_PREVIEW_TYPE)];
    }

    public static void setHitechType(@Nonnull ItemStack stack, int type) {
        getChiselTag(stack).setInteger(KEY_PREVIEW_TYPE, type);
    }
    
    public static int getHitechSelection(@Nonnull ItemStack stack) {
        NBTTagCompound tag = getChiselTag(stack);
        return tag.hasKey(KEY_SELECTION_SLOT) ? tag.getInteger(KEY_SELECTION_SLOT) : -1;
    }

    public static void setHitechSelection(@Nonnull ItemStack chisel, int slot) {
        getChiselTag(chisel).setInteger(KEY_SELECTION_SLOT, slot);
    }
    
    public static int getHitechTarget(@Nonnull ItemStack stack) {
        NBTTagCompound tag = getChiselTag(stack);
        return tag.hasKey(KEY_TARGET_SLOT) ? tag.getInteger(KEY_TARGET_SLOT) : -1;
    }

    public static void setHitechTarget(@Nonnull ItemStack chisel, int slot) {
        getChiselTag(chisel).setInteger(KEY_TARGET_SLOT, slot);
    }
    
    public static boolean getHitechRotate(@Nonnull ItemStack stack) {
        return getChiselTag(stack).getBoolean(KEY_ROTATE);
    }

    public static void setHitechRotate(@Nonnull ItemStack chisel, boolean rotate) {
        getChiselTag(chisel).setBoolean(KEY_ROTATE, rotate);
    }
}
