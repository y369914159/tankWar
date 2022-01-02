package business

import config.Config
import enums.Direction
import model.View

interface MoveAble :View{
    val currentDirection:Direction
    val speed:Int
    /**
     * 判断是否要发生碰撞
     * 如果发生碰撞，返回要发生碰撞的方向
     * 如果不发生碰撞，返回null
     */
    fun willCollision(block :BlockAble):Direction?{
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
        if (x < 0) return Direction.LEFT
        if (x > Config.width - Config.block) return Direction.RIGHT
        if (y < 0) return Direction.UP
        if (y > Config.height - Config.block) return Direction.DOWN
        var checkCollision = checkCollision(block.x, block.y, block.width, block.width,
            x, y, width, height)
        return if(checkCollision) currentDirection else null
    }
    fun notifyCollison(direction: Direction?,block: BlockAble?)

}