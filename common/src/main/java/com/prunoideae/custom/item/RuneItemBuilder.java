package com.prunoideae.custom.item;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import vazkii.botania.common.item.material.RuneItem;

public class RuneItemBuilder extends ItemBuilder {
    public RuneItemBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public Item createObject() {
        return new RuneItem(this.createItemProperties());
    }
}
