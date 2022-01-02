package model

interface View {
    var x:Int
    var y:Int
    var height:Int
    var width:Int
    fun draw()


    fun checkCollision(x1:Int,y1:Int,w1:Int,h1:Int,
                       x2:Int,y2:Int,w2:Int,h2:Int):Boolean{
        return if (y2 + h2 <= y1) {
            false
        } else if (y1 + h2 <= y2) {
            false
        } else if (x1 + w2 <= x2) {
            false
        } else if (w2 + x2 <= x1) {
            false
        } else {
            return true
        }
    }

}