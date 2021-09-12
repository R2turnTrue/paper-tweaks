package xyz.r2turntrue.papertweaks.api

import org.bukkit.event.Listener

object PaperTweaks {

    lateinit var impl: PaperTweaksImpl
    val tweaks = ArrayList<Tweak>()

    fun setImplmentation(impl: PaperTweaksImpl) {
        this.impl = impl
    }

    fun afterRegistered() {
        for (tweak in tweaks) {
            tweak.enabled = impl.isTweakEnabled(tweak)
            tweaks[tweaks.indexOf(tweak)] = tweak
            if(tweak is Listener) {
                impl.registerListener(tweak)
            }
        }
    }

    fun setTweakEnabled(tweak: Tweak, boolean: Boolean) {
        tweak.enabled = boolean
        tweaks[tweaks.indexOf(tweak)] = tweak
        impl.saveTweaks()
    }

    fun save() {
        impl.saveTweaks()
    }

    fun registerTweak(tweak: Tweak) {
        tweaks.add(tweak)
    }

    fun unregisterTweak(tweakId: String) {
        tweaks.removeIf { t -> t.name == tweakId }
    }

}