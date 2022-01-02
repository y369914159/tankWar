package ext

import model.View

/**
 * extend View function :checkCollison
 */
fun View.checkCollision(view:View):Boolean{
    return checkCollision(x,y,width,height,view.x,view.y,view.width,view.height)
}