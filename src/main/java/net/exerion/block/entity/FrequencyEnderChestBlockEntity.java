/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtAccounter
 *  net.minecraft.nbt.NbtIo
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.storage.LevelResource
 *  net.minecraft.world.level.storage.ValueInput
 *  net.minecraft.world.level.storage.ValueOutput
 *  org.jetbrains.annotations.Nullable
 */
package net.exerion.block.entity;

import com.mojang.serialization.DynamicOps;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.exerion.block.entity.ModBlockEntityTypes;
import net.exerion.menu.FrequencyEnderChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class FrequencyEnderChestBlockEntity
extends BlockEntity
implements MenuProvider {
    private int color1 = 0;
    private int color2 = 0;
    private int color3 = 0;
    protected final ContainerData dataAccess = new ContainerData(this){
        final /* synthetic */ FrequencyEnderChestBlockEntity this$0;
        {
            FrequencyEnderChestBlockEntity frequencyEnderChestBlockEntity = this$0;
            Objects.requireNonNull(frequencyEnderChestBlockEntity);
            this.this$0 = frequencyEnderChestBlockEntity;
        }

        public int get(int index) {
            return switch (index) {
                case 0 -> this.this$0.color1;
                case 1 -> this.this$0.color2;
                case 2 -> this.this$0.color3;
                default -> 0;
            };
        }

        public void set(int index, int value) {
            switch (index) {
                case 0: {
                    this.this$0.color1 = value;
                    break;
                }
                case 1: {
                    this.this$0.color2 = value;
                    break;
                }
                case 2: {
                    this.this$0.color3 = value;
                }
            }
            this.this$0.setChanged();
        }

        public int getCount() {
            return 3;
        }
    };

    public FrequencyEnderChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FREQUENCY_ENDER_CHEST_ENTITY, pos, state);
    }

    public int getColor1() {
        return this.color1;
    }

    public int getColor2() {
        return this.color2;
    }

    public int getColor3() {
        return this.color3;
    }

    public void setColor1(int val) {
        this.color1 = val;
        this.setChanged();
    }

    public void setColor2(int val) {
        this.color2 = val;
        this.setChanged();
    }

    public void setColor3(int val) {
        this.color3 = val;
        this.setChanged();
    }

    public SimpleContainer getEnderInventory() {
        return EnderStorageManager.getInventory(this.level, this.color1, this.color2, this.color3);
    }

    public Component getDisplayName() {
        return Component.translatable((String)"container.exerion.frequency_ender_chest");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new FrequencyEnderChestMenu(containerId, playerInventory, (Container)this.getEnderInventory(), this, this.dataAccess);
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color1", this.color1);
        output.putInt("Color2", this.color2);
        output.putInt("Color3", this.color3);
        if (this.level != null) {
            EnderStorageManager.save(this.level);
        }
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color1 = input.getIntOr("Color1", 0);
        this.color2 = input.getIntOr("Color2", 0);
        this.color3 = input.getIntOr("Color3", 0);
    }

    public static class EnderStorageManager {
        private static final Map<String, SimpleContainer> INVENTORIES = new HashMap<String, SimpleContainer>();
        private static boolean loaded = false;

        public static SimpleContainer getInventory(final Level level, int c1, int c2, int c3) {
            if (level != null && !loaded) {
                EnderStorageManager.load(level);
            }
            String freq = c1 + "-" + c2 + "-" + c3;
            return INVENTORIES.computeIfAbsent(freq, f -> new SimpleContainer(27){

                public void setChanged() {
                    super.setChanged();
                    if (level != null) {
                        EnderStorageManager.save(level);
                    }
                }
            });
        }

        public static void save(Level level) {
            if (level.isClientSide() || level.getServer() == null) {
                return;
            }
            try {
                File file = level.getServer().getWorldPath(LevelResource.ROOT).resolve("exerion_ender_inventories.nbt").toFile();
                CompoundTag tag = new CompoundTag();
                CompoundTag invs = new CompoundTag();
                for (Map.Entry<String, SimpleContainer> entry : INVENTORIES.entrySet()) {
                    ListTag list = new ListTag();
                    SimpleContainer container = entry.getValue();
                    for (int i = 0; i < container.getContainerSize(); ++i) {
                        ItemStack stack = container.getItem(i);
                        if (stack.isEmpty()) continue;
                        CompoundTag itemTag = new CompoundTag();
                        itemTag.putByte("Slot", (byte)i);
                        CompoundTag stackNbt = (CompoundTag)ItemStack.CODEC.encodeStart((DynamicOps)level.registryAccess().createSerializationContext((DynamicOps)NbtOps.INSTANCE), (Object)stack).getOrThrow();
                        itemTag.put("Item", (Tag)stackNbt);
                        list.add((Object)itemTag);
                    }
                    invs.put(entry.getKey(), (Tag)list);
                }
                tag.put("Inventories", (Tag)invs);
                NbtIo.writeCompressed((CompoundTag)tag, (Path)file.toPath());
            }
            catch (Exception e) {
                System.err.println("[Exerion] Failed to save ender inventories: " + e.getMessage());
            }
        }

        public static void load(final Level level) {
            if (level.isClientSide() || level.getServer() == null) {
                return;
            }
            loaded = true;
            try {
                File file = level.getServer().getWorldPath(LevelResource.ROOT).resolve("exerion_ender_inventories.nbt").toFile();
                if (!file.exists()) {
                    return;
                }
                CompoundTag tag = NbtIo.readCompressed((Path)file.toPath(), (NbtAccounter)NbtAccounter.unlimitedHeap());
                CompoundTag invs = tag.getCompoundOrEmpty("Inventories");
                for (String key : invs.keySet()) {
                    ListTag list = invs.getListOrEmpty(key);
                    SimpleContainer container = new SimpleContainer(27){

                        public void setChanged() {
                            super.setChanged();
                            EnderStorageManager.save(level);
                        }
                    };
                    for (int i = 0; i < list.size(); ++i) {
                        CompoundTag itemTag = list.getCompoundOrEmpty(i);
                        int slot = itemTag.getByteOr("Slot", (byte)0) & 0xFF;
                        CompoundTag stackNbt = itemTag.getCompoundOrEmpty("Item");
                        ItemStack stack = (ItemStack)ItemStack.CODEC.parse((DynamicOps)level.registryAccess().createSerializationContext((DynamicOps)NbtOps.INSTANCE), (Object)stackNbt).getOrThrow();
                        if (slot >= container.getContainerSize()) continue;
                        container.setItem(slot, stack);
                    }
                    INVENTORIES.put(key, container);
                }
            }
            catch (Exception e) {
                System.err.println("[Exerion] Failed to load ender inventories: " + e.getMessage());
            }
        }
    }
}

