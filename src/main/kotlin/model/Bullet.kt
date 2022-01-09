package model

import business.AttackAble
import business.DestroyAble
import business.FlyAble
import business.SufferAble
import config.Config
import enums.Direction
import ext.checkCollision
import org.itheima.kotlin.game.core.Painter

class Bullet(override val owner: View, direction: Direction, create: (bulletWidth: Int, bulletHeight: Int) -> Pair<Int, Int>)
    : View, FlyAble, DestroyAble ,AttackAble{


    override var x: Int = 0
    override var y: Int = 0
    override var height: Int = Config.block
    override var width: Int = Config.block
    override var attackPower: Int =1//攻击力
    private var isDestoryed = false
    private var imgPath = when (direction) {
        Direction.UP -> "img/shot_bottom.gif"
        Direction.DOWN -> "img/shot_top.gif"
        Direction.LEFT -> "img/shot_right.gif"
        Direction.RIGHT -> "img/shot_left.gif"
    }
    init {
        val size = Painter.size(imgPath)
        width = size[0]
        height = size[1]
        val pair = create.invoke(width, height)
        x = pair.first
        y = pair.second
    }
    override fun draw() {
        Painter.drawImage(imgPath, x, y)
    }
    override var currentDirection: Direction = direction
    override var speed: Int = 8
    override fun autoMove() {
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

    }
    override fun destroy(): Boolean {
        if(isDestoryed) return true
        return x > Config.width || x < -width || y < -height || y > Config.height
    }

    override fun isConllision(sufferAble: SufferAble): Boolean {
//        return checkCollision(sufferAble.x,sufferAble.y,sufferAble.width,sufferAble.height,
//                                x,y,width,height)
        return  checkCollision(sufferAble)
    }

    override fun notifyAttack(sufferAble: SufferAble) {
        isDestoryed = true
    }
}