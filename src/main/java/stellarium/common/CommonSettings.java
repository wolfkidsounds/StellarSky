package stellarium.common;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import stellarium.config.HierarchicalNBTConfig;
import stellarium.config.IConfigHandler;
import stellarium.config.INBTConfig;

public class CommonSettings extends HierarchicalNBTConfig {
	
	public boolean serverEnabled;
	public double day, year;
	public int yearOffset, dayOffset;
	public double tickOffset;
	public double latitudeOverworld, latitudeEnder;
	public double longitudeOverworld, longitudeEnder;

	private Property propServerEnabled;
	private Property propDay, propYear;
	private Property propYearOffset, propDayOffset, propTickOffset;
	private Property propLatitudeOverworld, propLongitudeOverworld;
	private Property propLatitudeEnder, propLongitudeEnder;
	
	public CommonSettings() { }
	
	public CommonSettings(CommonSettings settingsToCopy) {
		this.serverEnabled = settingsToCopy.serverEnabled;
		this.day = settingsToCopy.day;
		this.year = settingsToCopy.year;
		this.yearOffset = settingsToCopy.yearOffset;
		this.dayOffset = settingsToCopy.dayOffset;
		this.tickOffset = settingsToCopy.tickOffset;
		this.latitudeOverworld = settingsToCopy.latitudeOverworld;
		this.longitudeOverworld = settingsToCopy.longitudeOverworld;
		this.latitudeEnder = settingsToCopy.latitudeEnder;
		this.longitudeEnder = settingsToCopy.longitudeEnder;
	}

	@Override
	public void setupConfig(Configuration config, String category) {
		config.setCategoryComment(category, "Configurations for server modifications.");
		config.setCategoryLanguageKey(category, "config.category.server");
		config.setCategoryRequiresWorldRestart(category, true);
		
		List<String> propNameList = Lists.newArrayList();
		
        propServerEnabled=config.get(category, "Server_Enabled", true);
        propServerEnabled.comment="Enables Server-Side Sky change.";
        propServerEnabled.setRequiresWorldRestart(true);
        propServerEnabled.setLanguageKey("config.property.server.serverenabled");
        propNameList.add(propServerEnabled.getName());
        
        propDay=config.get(category, "Day_Length", 24000.0);
        propDay.comment="Length of a day, in a tick.";
        propDay.setRequiresWorldRestart(true);
        propDay.setLanguageKey("config.property.server.daylength");
        propNameList.add(propDay.getName());
        
        propYear=config.get(category, "Year_Length", 365.25);
        propYear.comment="Length of a year, in a day.";
        propYear.setRequiresWorldRestart(true);
        propYear.setLanguageKey("config.property.server.yearlength");
        propNameList.add(propYear.getName());

       	propYearOffset = config.get(category, "Year_Offset", 0);
       	propYearOffset.comment = "Year offset on world starting time.";
       	propYearOffset.setRequiresWorldRestart(true);
       	propYearOffset.setLanguageKey("config.property.server.yearoffset");
        propNameList.add(propYearOffset.getName());

       	propDayOffset = config.get(category, "Day_Offset", 0);
       	propDayOffset.comment = "Day offset on world starting time.";
       	propDayOffset.setRequiresWorldRestart(true);
       	propDayOffset.setLanguageKey("config.property.server.dayoffset");
        propNameList.add(propDayOffset.getName());

       	propTickOffset = config.get(category, "Tick_Offset", 5000.0);
       	propTickOffset.comment = "Tick offset on world starting time.";
       	propTickOffset.setRequiresWorldRestart(true);
       	propTickOffset.setLanguageKey("config.property.server.tickoffset");
        propNameList.add(propTickOffset.getName());

       	propLatitudeOverworld = config.get(category, "Latitude_Overworld", 37.5);
       	propLatitudeOverworld.comment = "Latitude on Overworld, in Degrees.";
       	propLatitudeOverworld.setRequiresWorldRestart(true);
       	propLatitudeOverworld.setLanguageKey("config.property.server.latitudeoverworld");
        propNameList.add(propLatitudeOverworld.getName());

       	propLongitudeOverworld = config.get(category, "Longitude_Overworld", 0.0);
       	propLongitudeOverworld.comment = "Longitude on Overworld, in Degrees. (East is +)";
       	propLongitudeOverworld.setRequiresWorldRestart(true);
       	propLongitudeOverworld.setLanguageKey("config.property.server.longitudeoverworld");
        propNameList.add(propLongitudeOverworld.getName());

       	propLatitudeEnder = config.get(category, "Latitude_Ender", -52.5);
       	propLatitudeEnder.comment = "Latitude on Ender, in Degrees.";
       	propLatitudeEnder.setRequiresWorldRestart(true);
       	propLatitudeEnder.setLanguageKey("config.property.server.latitudeender");
        propNameList.add(propLatitudeEnder.getName());

       	propLongitudeEnder = config.get(category, "Longitude_Ender", 180.0);
       	propLongitudeEnder.comment = "Longitude on Ender, in Degrees. (East is +)";
       	propLongitudeEnder.setRequiresWorldRestart(true);
       	propLongitudeEnder.setLanguageKey("config.property.server.longitudeender");
        propNameList.add(propLongitudeEnder.getName());
        
        config.setCategoryPropertyOrder(category, propNameList);
	}

	@Override
	public void loadFromConfig(Configuration config, String category) {
        this.serverEnabled = propServerEnabled.getBoolean();
        this.day = propDay.getDouble();
        this.year = propYear.getDouble();
       	this.yearOffset = propYearOffset.getInt();
       	this.dayOffset = propDayOffset.getInt();
       	this.tickOffset = propTickOffset.getDouble();
       	this.latitudeOverworld = propLatitudeOverworld.getDouble();
       	this.longitudeOverworld = propLongitudeOverworld.getDouble();
       	this.latitudeEnder = propLatitudeEnder.getDouble();
       	this.longitudeEnder = propLongitudeEnder.getDouble();
	}

	
	public void readFromNBT(NBTTagCompound compound) {
		this.serverEnabled = compound.getBoolean("serverEnabled");
		this.day = compound.getDouble("day");
        this.year = compound.getDouble("year");
       	this.yearOffset = compound.getInteger("yearOffset");
       	this.dayOffset = compound.getInteger("dayOffset");
       	this.tickOffset = compound.getDouble("tickOffset");
       	this.latitudeOverworld = compound.getDouble("lattitudeOverworld");
       	this.longitudeOverworld = compound.getDouble("longitudeOverworld");
       	this.latitudeEnder = compound.getDouble("lattitudeEnder");
       	this.longitudeEnder = compound.getDouble("longitudeEnder");
	}

	
	public void writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("serverEnabled", this.serverEnabled);
		compound.setDouble("day", this.day);
        compound.setDouble("year", this.year);
       	compound.setInteger("yearOffset", this.yearOffset);
       	compound.setInteger("dayOffset", this.dayOffset);
       	compound.setDouble("tickOffset", this.tickOffset);
       	compound.setDouble("lattitudeOverworld", this.latitudeOverworld);
       	compound.setDouble("longitudeOverworld", this.longitudeOverworld);
       	compound.setDouble("lattitudeEnder", this.latitudeEnder);
       	compound.setDouble("longitudeEnder", this.longitudeEnder);
	}

	@Override
	public INBTConfig copy() {
		CommonSettings settings = new CommonSettings(this);
		this.applyCopy(settings);
		return settings;
	}
}
