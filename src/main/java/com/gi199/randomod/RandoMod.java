package com.gi199.randomod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class RandoMod implements ModInitializer {
    public static final String MOD_ID = "randomod"; // 改为全小写
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ItemClasses.initialize();
        ItemClasses.register("secret_item", SecretItem::new, new Item.Settings()); // 注册名为 secret_item 的物品
        LOGGER.info("Mod initialized!");
    }

}
