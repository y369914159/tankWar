package business

import model.View

/**
 * 攻击接口
 */
interface AttackAble :View{
    val owner :View
    val attackPower:Int
    fun isConllision(sufferAble: SufferAble) :Boolean
    fun notifyAttack(sufferAble: SufferAble)
}