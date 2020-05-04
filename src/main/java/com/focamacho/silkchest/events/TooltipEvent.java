package com.focamacho.silkchest.events;

import com.focamacho.silkchest.SilkChest;
import net.minecraft.block.Block;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TooltipEvent {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        for(Block block : SilkChest.silkBlocks) {
          if(event.getItemStack().getItem().getRegistryName().equals(block.getRegistryName())) {
              if(event.getItemStack().hasTag() && event.getItemStack().getTag().contains("BlockEntityTag")) {
                  NonNullList<ItemStack> itemsList = NonNullList.withSize(200, ItemStack.EMPTY);
                  ItemStackHelper.loadAllItems(event.getItemStack().getTag().getCompound("BlockEntityTag"), itemsList);
                  int i = 0;
                  int j = 0;
                  for(ItemStack item : itemsList) {
                      if(!item.isEmpty()) {
                          if(i < 5) {
                              i++;
                              event.getToolTip().add(new StringTextComponent(item.getDisplayName().getFormattedText() + " x" + item.getCount()));
                          }
                          j++;
                      }
                  }
                  if(i >= 5 && j - i > 0) event.getToolTip().add(new TranslationTextComponent("container.shulkerBox.more", j - i));
                  return;
              }
            }
        }
    }
}
