package model

import business.BlockAble
import config.Config
import org.itheima.kotlin.game.core.Painter
import java.awt.Paint

class Water(override var x: Int, override var y: Int) :View  , BlockAble {
    override var height: Int = Config.block
    override var width: Int = Config.block

    override fun draw() {
      Painter.drawImage("img/water.gif",x,y)
    }

}