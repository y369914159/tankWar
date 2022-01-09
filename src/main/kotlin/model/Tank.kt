package model

import business.*
import config.Config
import enums.Direction
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : MoveAble,BlockAble ,SufferAble,DestroyAble{
    override var height: Int = Config.block
    override var width: Int = Config.block
    override var currentDirection = Direction.UP
    override var speed =16//每步走的像素点数
    private var badDirection: Direction? = null

    override var bloold: Int = 3

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
    private var lastShotTime = 0L
    private var lastInterval = 200
    fun shot(): Bullet? {
        val currentTimeMillis = System.currentTimeMillis()
        if(currentTimeMillis - lastShotTime < lastInterval) return null
        lastShotTime = currentTimeMillis
        return Bullet(this,currentDirection) { bulletWidth, bulletHeight ->
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
    override fun notifyAttack(attackAble: AttackAble): Array<View>? {
        bloold -= attackAble.attackPower ;
        //play audio
        Composer.play("snd/hit.wav")
        return arrayOf(Blast(x,y))
    }
    override fun destroy(): Boolean {
        return bloold<=0
    }
}