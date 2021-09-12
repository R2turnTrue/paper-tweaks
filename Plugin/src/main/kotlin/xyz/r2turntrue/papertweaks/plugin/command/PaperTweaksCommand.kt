package xyz.r2turntrue.papertweaks.plugin.command

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import xyz.r2turntrue.papertweaks.api.PaperTweaks

object PaperTweaksCommand {

    fun init(kommand: PluginKommand) {
        kommand.register("papertweaks", "pt", "tweaks") {
            requires { isOp }

            then("config") {
                for (tweak in PaperTweaks.tweaks) {
                    then(tweak.id) {
                        requires { isPlayer }
                        executes { ctx ->
                            val player = ctx.source.player
                            if(!tweak.openConfig(player))
                                player.sendMessage(Component.text("해당 트윅은 설정창을 지원하지 않습니다!", NamedTextColor.RED))
                        }
                    }
                }
            }

            then("save") {
                executes {
                    PaperTweaks.save()
                    sender.sendMessage(Component.text("저장 완료!", NamedTextColor.GREEN))
                }
            }

            then("enable") {
                for (tweak in PaperTweaks.tweaks) {
                    then(tweak.id) {
                        then("bool" to bool()) {
                            executes { ctx ->
                                val bool: Boolean by ctx
                                PaperTweaks.setTweakEnabled(tweak, bool)
                                sender.sendMessage(Component.text("${bool}로 ${tweak.name} 트윅의 활성화 상태가 변경되었습니다!", NamedTextColor.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

}