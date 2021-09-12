package xyz.r2turntrue.papertweaks.api

import org.bukkit.event.Listener

interface PaperTweaksImpl {

    fun isTweakEnabled(tweak: Tweak) : Boolean

    fun registerListener(listener: Listener)

    fun saveTweaks()

}