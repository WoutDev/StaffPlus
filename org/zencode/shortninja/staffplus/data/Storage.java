package org.zencode.shortninja.staffplus.data;

import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.zencode.shortninja.staffplus.StaffPlus;

public class Storage
{
	private static FileConfiguration config = StaffPlus.get().config;
	
	public String pluginKey = config.getString("plugin-key");
	public String generalPrefix = config.getString("general-prefix");
	
	public String chatPermission = config.getString("chat");
	public String modePermission = config.getString("mode");
	public String reportPermission = config.getString("report");
	public String warnPermission = config.getString("warn");
	public String vanishPermission = config.getString("vanish");
	public String staffChatPermission = config.getString("sc");
	public String blockCommandPermission = config.getString("block");
	public String notifyPermission = config.getString("notify");
	public String lockdownPermission = config.getString("lockdown");
	public String wildCardPermission = config.getString("wild-card");
	
	public boolean reportsEnabled = config.getBoolean("reports-module.enabled");
	public String reportsPrefix = config.getString("reports-module.prefix");
	public String reportsSoundEffect = config.getString("reports-module.sound");
	public int reportsCooldown = config.getInt("reports-module.cooldown") * 1000;
	
	public boolean warningsEnabled = config.getBoolean("warnings-module.enabled");
	public String warningsPrefix = config.getString("warnings-module.prefix");
	public String warningsSoundEffect = config.getString("warnings-module.sound");
	public int warningsMaximum = config.getInt("warnings-module.maximum");
	public String warningsBanCommand = config.getString("warnings-module.ban-command");
	
	public boolean staffChatEnabled = config.getBoolean("staff-chat-module.enabled");
	public String staffChatPrefix = config.getString("staff-chat-module.prefix");
	
	public boolean vanishEnabled = config.getBoolean("vanish-module.enabled");
	
	public boolean chatEnabled = config.getBoolean("chat-module.enabled");
	
	public boolean modeBlockManipulationDisabled = config.getBoolean("staff-mode.disable-block-manipulation");
	public boolean modeInventoryInteractionDisabled = config.getBoolean("staff-mode.disable-inventory-interaction");
	public boolean modeInvisible = config.getBoolean("staff-mode.invisible");
	public boolean modeInvincible = config.getBoolean("staff-mode.invincible");
	public boolean modeFlight = config.getBoolean("staff-mode.flight");
	public boolean modeOriginalLocation = config.getBoolean("staff-mode.original-location");
	public boolean modeOnLogin = config.getBoolean("staff-mode.enable-on-login");
	
	public boolean compassEnabled = config.getBoolean("staff-mode.compass-module.enabled");
	public int compassSlot = config.getInt("staff-mode.compass-module.slot") - 1;
	public int compassSpeed = config.getInt("staff-mode.compass-module.speed");
	
	public boolean teleportEnabled = config.getBoolean("staff-mode.teleport-module.enabled");
	public int teleportSlot = config.getInt("staff-mode.teleport-module.slot") - 1;
	
	public boolean modeVanishEnabled = config.getBoolean("staff-mode.vanish-module.enabled");
	public int vanishSlot = config.getInt("staff-mode.vanish-module.slot") - 1;
	
	public boolean reportsPaperEnabled = config.getBoolean("staff-mode.reports-module.enabled");
	public int reportsPaperSlot = config.getInt("staff-mode.reports-module.slot") - 1;
	
	public boolean freezeEnabled = config.getBoolean("staff-mode.freeze-module.enabled");
	public int freezeSlot = config.getInt("staff-mode.freeze-module.slot") - 1;
	public String freezeMessage = config.getString("staff-mode.freeze-module.message");
	public boolean freezePreventChat = config.getBoolean("staff-mode.freeze-module.prevent-chat");
	public boolean freezeInvincible = config.getBoolean("staff-mode.freeze-module.invincible");
	
	public boolean cpsEnabled = config.getBoolean("staff-mode.cps-module.enabled");
	public int cpsSlot = config.getInt("staff-mode.cps-module.slot") - 1;
	public int cpsTime = config.getInt("staff-mode.cps-module.time");
	
	public boolean customEnabled = config.getBoolean("staff-mode.custom-module.enabled");
	public int customSlot = config.getInt("staff-mode.custom-module.slot") - 1;
	public String customItem = config.getString("staff-mode.custom-module.item");
	public String customName = config.getString("staff-mode.custom-module.name");
	private String customLoreCommas = config.getString("staff-mode.custom-module.lore");
	public List<String> customLoreList = Arrays.asList(customLoreCommas.split("\\s*,\\s*"));
	public String customCommand = config.getString("staff-mode.custom-module.command");
	
	public boolean inventoryEnabled = config.getBoolean("staff-mode.inventory-module.enabled");
	public int inventorySlot = config.getInt("staff-mode.inventory-module.slot") - 1;
	public boolean inventoryOptions = config.getBoolean("staff-mode.inventory-module.options");
	public boolean inventoryExtraInfo = config.getBoolean("staff-mode.inventory-module.extra-info");
	
	public boolean followEnabled = config.getBoolean("staff-mode.follow-module.enabled");
	public int followSlot = config.getInt("staff-mode.follow-module.slot") - 1;
	
	public boolean alertsNameEnabled = config.getBoolean("alert-options.name-notify");
	public boolean alertsMentionEnabled = config.getBoolean("alert-options.mention-notify");
	public String alertsSound = config.getString("alert-options.sound");
	public boolean alertsXrayEnabled = config.getBoolean("alert-options.xray-alerts.enabled");
	private String alertsXrayBlocksCommas = config.getString("alert-options.xray-alerts.blocks");
	public List<String> alertsXrayBlocksList = Arrays.asList(alertsXrayBlocksCommas.split("\\s*,\\s*"));
	
	private String staffCommas = config.getString("staff-options.staff");
	public List<String> staffList = Arrays.asList(staffCommas.split("\\s*,\\s*"));
	
	private String blockedCommandsCommas = config.getString("staff-options.blocked-commands");
	public List<String> blockedCommandsList = Arrays.asList(blockedCommandsCommas.split("\\s*,\\s*"));
	
	public String lockdownMessage = config.getString("staff-options.lockdown-message");
}
