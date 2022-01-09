package model

interface View {
    var x:Int
    var y:Int
    var height:Int
    var width:Int
    fun draw()

    /**
     * 1 attach
     * 2 suffer
     */
    fun checkCollision(x1:Int,y1:Int,w1:Int,h1:Int,
                       x2:Int,y2:Int,w2:Int,h2:Int):Boolean{
        return when {
            y2 + h2 <= y1 ->
                false
            y1 + h1 <= y2 ->
                false
            x2 + w2 <= x1 ->
                false
            else -> x1 + w1 > x2
        }
    }

}