package hu.tryharddevs.advancedkits.kits.flags;

import hu.tryharddevs.advancedkits.utils.ItemStackUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ItemStackFlag extends Flag<ItemStack>
{
	private final ItemStack defaultValue;

	public ItemStackFlag(String name, ItemStack defaultValue)
	{
		super(name);
		this.defaultValue = defaultValue;
	}

	public ItemStackFlag(String name, Material defaultValue)
	{
		super(name);
		this.defaultValue = new ItemStack(defaultValue);
	}

	public ItemStackFlag(String name)
	{
		super(name);
		this.defaultValue = new ItemStack(Material.EMERALD_BLOCK);
	}

	@Nullable
	@Override
	public ItemStack getDefault()
	{
		return defaultValue;
	}

	@Override
	public ItemStack parseInput(String input) throws InvalidFlagValueException
	{
		return null;
	}

	@Nullable
	@Override
	public ItemStack parseItem(Player player)
	{
		return player.getInventory().getItemInMainHand();
	}

	@Override
	public ItemStack unmarshal(@Nullable Object o)
	{
		try
		{
			String material = String.valueOf(o);
			return new ItemStack(Material.matchMaterial(material));
		}
		catch (ClassCastException | NullPointerException e)
		{
			return ItemStackUtil.itemFromString(String.valueOf(o));
		}
	}

	@Override
	public Object marshal(ItemStack o)
	{
		return ItemStackUtil.itemToString(o);
	}
}
