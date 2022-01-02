package model

import business.AttackAble
import business.BlockAble
import business.DestroyAble
import business.SufferAble
import config.Config
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import java.awt.Paint

class Wall(override var x: Int, override var y: Int) :BlockAble,SufferAble,DestroyAble{
    override var bloold: Int = 3


    override var height: Int = Config.block
    override var width: Int = Config.block

    override fun draw() {
      Painter.drawImage("img/wall.gif",x,y)
    }
    override fun notifyAttack(attackAble: AttackAble) : Array<View>? {
        bloold -= attackAble.attackPower ;
        //play audio
        Composer.play("snd/hit.wav")
        return arrayOf(Blast(x,y))
    }
    override fun destroy(): Boolean  = bloold<=0

}