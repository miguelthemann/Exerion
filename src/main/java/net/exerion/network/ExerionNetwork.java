/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.codec.StreamCodec
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type
 *  net.minecraft.resources.Identifier
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.inventory.AbstractContainerMenu
 */
package net.exerion.network;

import net.exerion.block.entity.ProgressionTerminalBlockEntity;
import net.exerion.menu.ProgressionTerminalMenu;
import net.exerion.progression.ModAttachments;
import net.exerion.progression.TechnologyEra;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ExerionNetwork {
    public static final CustomPacketPayload.Type<AdvanceEraPayload> ADVANCE_ERA_PACKET_TYPE = new CustomPacketPayload.Type(Identifier.fromNamespaceAndPath((String)"exerion", (String)"advance_era"));
    public static final CustomPacketPayload.Type<SyncEraPayload> SYNC_ERA_PACKET_TYPE = new CustomPacketPayload.Type(Identifier.fromNamespaceAndPath((String)"exerion", (String)"sync_era"));

    public static void registerServer() {
        PayloadTypeRegistry.serverboundPlay().register(ADVANCE_ERA_PACKET_TYPE, AdvanceEraPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ADVANCE_ERA_PACKET_TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            context.server().execute(() -> {
                ProgressionTerminalMenu terminalMenu;
                ProgressionTerminalBlockEntity be;
                AbstractContainerMenu menu = player.containerMenu;
                if (menu instanceof ProgressionTerminalMenu && (be = (terminalMenu = (ProgressionTerminalMenu)menu).getBlockEntity()) != null) {
                    be.tryAdvanceEra(player);
                }
            });
        });
    }

    public static void syncEraToClient(ServerPlayer player, TechnologyEra era) {
        ServerPlayNetworking.send((ServerPlayer)player, (CustomPacketPayload)new SyncEraPayload(era.getId()));
    }

    public static void registerClient() {
        PayloadTypeRegistry.clientboundPlay().register(SYNC_ERA_PACKET_TYPE, SyncEraPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(SYNC_ERA_PACKET_TYPE, (payload, context) -> context.client().execute(() -> {
            if (context.client().player != null) {
                TechnologyEra era = TechnologyEra.fromId(payload.eraId());
                context.client().player.setAttached(ModAttachments.PLAYER_ERA, (Object)era);
            }
        }));
    }

    public static void sendAdvanceEraPacket() {
        ClientPlayNetworking.send((CustomPacketPayload)new AdvanceEraPayload());
    }

    public record AdvanceEraPayload() implements CustomPacketPayload
    {
        public static final StreamCodec<FriendlyByteBuf, AdvanceEraPayload> CODEC = StreamCodec.unit((Object)new AdvanceEraPayload());

        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return ADVANCE_ERA_PACKET_TYPE;
        }
    }

    public record SyncEraPayload(String eraId) implements CustomPacketPayload
    {
        public static final StreamCodec<FriendlyByteBuf, SyncEraPayload> CODEC = CustomPacketPayload.codec((payload, buf) -> buf.writeUtf(payload.eraId()), buf -> new SyncEraPayload(buf.readUtf()));

        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return SYNC_ERA_PACKET_TYPE;
        }
    }
}

