package com.gi199.randomod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ItemClasses {
    private static RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY;
    private static ItemGroup CUSTOM_ITEM_GROUP;
    private static boolean isInitialized = false; // 正确的静态标志位

    public static void initialize() {
        if (isInitialized) return; // 避免重复初始化
        isInitialized = true;

        // 创建物品组的注册键
        CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
                Identifier.of(RandoMod.MOD_ID, "item_group"));

        // 创建并注册物品组
        CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Items.COMMAND_BLOCK))
                .displayName(Text.translatable("itemGroup.randomod"))
                .build();
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        Identifier id = Identifier.of(RandoMod.MOD_ID, name); // 使用 new Identifier
        Item item = itemFactory.apply(settings);
        Registry.register(Registries.ITEM, id, item);
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(entries -> entries.add(item));
        return item;
    }

}