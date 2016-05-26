package hu.tryharddood.advancedkits.Kits;

import hu.tryharddood.advancedkits.AdvancedKits;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Kit
{
    private final YamlConfiguration saveFile;
    private       String            kitname;

    private ArrayList<ItemStack> itemStacks = new ArrayList<>();
    private ArrayList<ItemStack> armor      = new ArrayList<>();

    private Boolean visible;
    private Boolean permonly;
    private Boolean clearinv;
    private Boolean firstjoin;

    private String permission;

    private Material icon;

    private Integer cost;
    private Integer uses;

    private Double delay;

    private ArrayList<String> worlds   = new ArrayList<>();
    private ArrayList<String> commands = new ArrayList<>();

    private Boolean save = false;

    public Kit(String kitname)
    {
        this.kitname = kitname;
        saveFile = YamlConfiguration.loadConfiguration(getSaveFile());
    }

    public String getName()
    {
        return this.kitname;
    }

    public ArrayList<ItemStack> getItemStacks()
    {
        return this.itemStacks;
    }

    public Boolean AddItem(ItemStack a)
    {
        itemStacks.add(a);

        List<Map<String, Object>> list = itemStacks.stream().map(ItemStack::serialize).collect(Collectors.toList());
        setProperty("Items", list);
        return true;
    }

    public void createKit(List<ItemStack> itemStacks, List<ItemStack> armors)
    {
        setSave(true);
        setVisible(true);
        setPermonly(false);
        setClearinv(false);
        setCost(0);
        setDelay(0.0);
        setIcon(Material.EMERALD_BLOCK);
        itemStacks.forEach(this::AddItem);
        armors.forEach(this::AddArmor);
        setSave(false);
        KitManager.load();
    }

    public ArrayList<ItemStack> getArmor()
    {
        return this.armor;
    }

    public void setArmor(ArrayList<ItemStack> armor)
    {
        this.armor = armor;

        List<Map<String, Object>> list = this.armor.stream().map(ItemStack::serialize).collect(Collectors.toList());
        setProperty("Armor", list);
    }

    public Boolean AddArmor(ItemStack a)
    {
        this.armor.add(a);

        List<Map<String, Object>> list = this.armor.stream().map(ItemStack::serialize).collect(Collectors.toList());
        setProperty("Armor", list);
        return true;
    }

    public File getSaveFile()
    {
        File file = new File(AdvancedKits.getInstance().getDataFolder() + File.separator + "kits" + File.separator + kitname + ".yml");
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }

    public YamlConfiguration getYaml()
    {
        return this.saveFile;
    }

    private void setProperty(String key, Object value)
    {
        if (!isSave()) return;

        try
        {
            saveFile.load(getSaveFile());
            saveFile.set(key, value);
            saveFile.save(getSaveFile());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            AdvancedKits.log("Couldn't set property '" + key + "' for zone '" + this.kitname + "'");
        }
    }

    public ArrayList<String> getWorlds()
    {
        return this.worlds;
    }

    public void AddWorld(String w1)
    {
        if (!this.worlds.contains(w1)) this.worlds.add(w1);

        setProperty("Flags.Worlds", this.worlds);
    }

    public void RemoveWorld(String w1)
    {
        if (this.worlds.contains(w1)) this.worlds.remove(w1);

        setProperty("Flags.Worlds", this.worlds);
    }

    public ArrayList<String> getCommands()
    {
        return this.commands;
    }

    public void AddCommand(String w1)
    {
        if (!this.commands.contains(w1)) this.commands.add(w1);

        setProperty("Flags.Commands", this.commands);
    }

    public void RemoveCommand(String w1)
    {
        if (this.commands.contains(w1)) this.commands.remove(w1);

        setProperty("Flags.Commands", commands);
    }

    private Boolean isSave()
    {
        return save;
    }

    public void setSave(Boolean save)
    {
        this.save = save;
    }

    public Boolean isVisible()
    {
        return visible;
    }

    public void setVisible(Boolean visible)
    {
        this.visible = visible;
        setProperty("Flags.Visible", visible);
    }

    public Boolean isPermonly()
    {
        return permonly;
    }

    public void setPermonly(Boolean permonly)
    {
        this.permonly = permonly;
        setProperty("Flags.PermissionOnly", permonly);
    }

    public Boolean isClearinv()
    {
        return clearinv;
    }

    public void setClearinv(Boolean clearinv)
    {
        this.clearinv = clearinv;
        setProperty("Flags.ClearInv", clearinv);
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
        setProperty("Flags.Permission", permission);
    }

    public Material getIcon()
    {
        return icon;
    }

    public void setIcon(Material icon)
    {
        this.icon = icon;
        setProperty("Flags.Icon", icon.toString());
    }

    public Integer getCost()
    {
        return cost;
    }

    public void setCost(Integer cost)
    {
        this.cost = cost;
        setProperty("Flags.Cost", cost);
    }

    public Integer getUses()
    {
        return uses;
    }

    public void setUses(Integer uses)
    {
        this.uses = uses;
        setProperty("Flags.Uses", uses);
    }

    public Double getDelay()
    {
        return delay;
    }

    public void setDelay(Double delay)
    {
        this.delay = delay;
        setProperty("Flags.Delay", delay);
    }

    public Boolean isFirstjoin()
    {
        return firstjoin;
    }

    public void setFirstjoin(Boolean firstjoin)
    {
        this.firstjoin = firstjoin;
        setProperty("Flags.FirstJoin", visible);
    }
}
