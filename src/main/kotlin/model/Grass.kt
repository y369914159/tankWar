package model

import business.BlockAble
import config.Config
import org.itheima.kotlin.game.core.Painter
import java.awt.Paint

class Grass(override var x: Int, override var y: Int) :View{
    override var height: Int = Config.block
    override var width: Int = Config.block

    override fun draw() {
      Painter.drawImage("img/grass.gif",x,y)
    }

}