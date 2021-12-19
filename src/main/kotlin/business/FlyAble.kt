package business

import enums.Direction
import model.View

interface FlyAble :View{
    fun autoMove()
    var currentDirection:Direction
    var speed:Int

}