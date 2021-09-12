package xyz.r2turntrue.papertweaks.api

import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

abstract class Tweak {

    abstract val id: String
    abstract val name: String
    abstract val icon: Material
    var config: ConfigurationSection? = null
    var enabled = false

    fun openConfig(player: Player): Boolean = false

}