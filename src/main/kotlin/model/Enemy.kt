package model

import business.*
import config.Config
import enums.Direction
import org.itheima.kotlin.game.core.Painter
import kotlin.random.Random

/**
 * enemy tank
 */
class Enemy (override var x: Int, override var y: Int)
    :View,MoveAble/*,DestroyAble,SufferAble*/,FlyAble,BlockAble{


    override var currentDirection: Direction  = Direction.DOWN
    override var speed: Int = 8
    private var badDirection: Direction? = null
    override var height: Int = Config.block
    override var width: Int = Config.block
    override fun draw() {
        var d = when (currentDirection) {
            Direction.UP -> "img/enemy_1_u.gif"
            Direction.DOWN -> "img/enemy_1_d.gif"
            Direction.LEFT -> "img/enemy_1_l.gif"
            Direction.RIGHT -> "img/enemy_1_r.gif"
            else -> ""
        }
        Painter.drawImage(d, x, y)
    }
    override fun notifyCollison(direction: Direction?, block: BlockAble?) {
        this.badDirection = direction;
    }
    override fun autoMove() {
        if(currentDirection ==badDirection){
            //change direction
            currentDirection = randonDirection(badDirection)
            return
        }

        when (currentDirection) {
            Direction.UP -> {
                this.y = y - speed
            }
            Direction.DOWN -> {
                this.y = y + speed
            }
            Direction.LEFT -> {
                this.x = x - speed
            }
            Direction.RIGHT -> {
                this.x = x + speed
            }
        }
        //边界碰撞
        if (x < 0) x = 0
        if (x > Config.width - Config.block) x = Config.width - Config.block
        if (y < 0) y = 0
        if (y > Config.height - Config.block) y = Config.height - Config.block
    }
    private fun randonDirection(bad:Direction?):Direction{
        var i =  Random.nextInt(4)
        var direction = when(i){
            0-> Direction.UP
            1-> Direction.DOWN
            2-> Direction.LEFT
            3-> Direction.RIGHT
            else -> Direction.DOWN
        }
        if(direction == badDirection){
            return randonDirection(bad)
        }
        return direction
    }
}