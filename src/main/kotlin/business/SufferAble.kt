package business

import model.View

/**
 * 接受接口
 */
interface SufferAble :View{
    val bloold:Int
    fun notifyAttack(attackAble: AttackAble) : Array<View>?
}