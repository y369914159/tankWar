package business

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
    fun willCollision(block :BlockAble):Direction?
    fun notifyCollison(direction: Direction?,block: BlockAble?)

}