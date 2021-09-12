package xyz.r2turntrue.papertweaks.plugin

import io.github.monun.kommand.kommand
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import xyz.r2turntrue.papertweaks.api.PaperTweaks
import xyz.r2turntrue.papertweaks.api.PaperTweaksImpl
import xyz.r2turntrue.papertweaks.api.Tweak
import xyz.r2turntrue.papertweaks.plugin.command.PaperTweaksCommand

/**
 * @author R2turnTrue
 */
class PaperTweaksPlugin : JavaPlugin(), PaperTweaksImpl {

    override fun onEnable() {
        PaperTweaks.setImplmentation(this)
        PaperTweaks.afterRegistered()
        kommand {
            PaperTweaksCommand.init(this)
        }
    }

    override fun isTweakEnabled(tweak: Tweak): Boolean = if(config.isConfigurationSection(tweak.id)) config.getConfigurationSection(tweak.id)!!.getBoolean("isEnabled") else false

    override fun registerListener(listener: Listener) {
        server.pluginManager.registerEvents(listener, this)
    }

    override fun saveTweaks() {
        for (tweak in PaperTweaks.tweaks) {
            val sect = if(config.isConfigurationSection(tweak.id)) config.getConfigurationSection(tweak.id)!! else config.createSection(tweak.id)
            sect.set("enabled", tweak.enabled)
            if(tweak.config != null) {
                for (value in tweak.config!!.getValues(false)) {
                    sect.set(value.key, value.value)
                }
            }
        }
    }

}