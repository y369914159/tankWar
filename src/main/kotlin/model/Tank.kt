package model

import business.BlockAble
import business.MoveAble
import config.Config
import enums.Direction
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : MoveAble {
    override var height: Int = Config.block
    override var width: Int = Config.block
    override var currentDirection = Direction.UP
    override var speed = 8//每步走的像素点数
    private var badDirection: Direction? = null
    override fun willCollision(block: BlockAble): Direction? {
        //如已经发生碰撞无法移动，使用下一步的值进行预测下一步是否发生碰撞
        var x = this.x
        var y = this.y
        when (currentDirection) {
            Direction.UP -> {
                y -= speed
            }
            Direction.DOWN -> {
                y += speed
            }
            Direction.LEFT -> {
                x -= speed
            }
            Direction.RIGHT -> {
                x += speed
            }
        }
//        println("下一步发生的x,y坐标为 $x , $y")
//        println("阻塞块x,y坐标为 ${block.x} , ${block.y}")
//        println("阻塞块宽度${block.height}")
        //不发生碰撞的逻辑
        return if (block.y + block.height <= y) {
            null
        } else if (y + block.height <= block.y) {
            null
        } else if (x + block.width <= block.x) {
            null
        } else if (block.width + block.x <= x) {
            null
        } else {
            return currentDirection
        }
    }

    override fun notifyCollison(direction: Direction?, block: BlockAble?) {
        this.badDirection = direction;
    }

    override fun draw() {
        var d = when (currentDirection) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
            else -> ""
        }
        Painter.drawImage(d, x, y)
    }

    fun move(direction: Direction) {
        //如果要朝向已被阻挡的方向则不允许移动
        if (direction == badDirection) return
        if (direction != this.currentDirection) {
            this.currentDirection = direction
            return
        }


        when (direction) {
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
        this.currentDirection = direction
        //边界碰撞
        if (x < 0) x = 0
        if (x > Config.width - Config.block) x = Config.width - Config.block
        if (y < 0) y = 0
        if (y > Config.height - Config.block) y = Config.height - Config.block
    }

    fun shot(): Bullet {
        return Bullet(currentDirection) { bulletWidth, bulletHeight ->
            var tankX = x
            var tankY = y
            var tankWidth = width
            var bulletX = 0
            var bulletY = 0
            when (currentDirection) {
                Direction.UP -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = tankX + (tankWidth - bulletWidth) / 2
                    bulletY = tankY + tankWidth - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = tankX - bulletWidth / 2
                    bulletY = tankY + (tankWidth - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = tankX + tankWidth - bulletWidth / 2
                    bulletY = tankY + tankWidth / 2 - bulletHeight / 2
                }
            }
            Pair(bulletX, bulletY)
        }

    }


}