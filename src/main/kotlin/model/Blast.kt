package model

import business.DestroyAble
import config.Config
import org.itheima.kotlin.game.core.Painter

class Blast(override var x: Int, override var y: Int) :View,DestroyAble {
    override var height: Int = Config.block
    override var width: Int = Config.block
    private var imagePath = arrayListOf<String>()
    private var index = 0
    init {
        (1..32).forEach {
            imagePath.add("img/blast_$it.png")
        }
    }
    override fun draw() {
        var i = index % imagePath.size
        Painter.drawImage(imagePath[i],x,y)
        index++
    }
    override fun destroy(): Boolean {
        return index >= imagePath.size
    }

}

