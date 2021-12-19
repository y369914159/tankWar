package business

import model.View

interface DestroyAble :View{
    fun destroy(): Boolean
}