import business.BlockAble
import business.DestroyAble
import business.FlyAble
import business.MoveAble
import config.Config
import enums.Direction
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 我的窗体
 */
class MyWindow : Window(
    "坦克大战", "img/log.jfif",
    Config.width, Config.height
) {
//    var viewList = arrayListOf<View>()
    //使用线程安全的集合
    private var viewList = CopyOnWriteArrayList<View>()
    lateinit var tank: Tank
    override fun onCreate() {
        var file = File(javaClass.getResource("/map/1.map").path)
        var row = 0;
        file.readLines().forEach() {
            var coloum = 0;
            it.toCharArray().forEach { char ->
                when (char) {
                    'W' -> {
                        viewList.add(Wall(coloum * Config.block, row * Config.block))
                    }
                    'F' -> {
                        viewList.add(Fe(coloum * Config.block, row * Config.block))
                    }
                    'G' -> {
                        viewList.add(Grass(coloum * Config.block, row * Config.block))
                    }
                    'A' -> {
                        viewList.add(Water(coloum * Config.block, row * Config.block))
                    }
                    'T' -> {
                        tank = Tank(coloum * Config.block, row * Config.block)
                        viewList.add(tank)
                    }
                }
                coloum++
            }
            row++
        }
    }

    override fun onDisplay() {
        viewList.forEach() {
            it.draw()
        }
        println(viewList.size)
    }

    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> {
                tank.move(Direction.UP)
            }
            KeyCode.S -> {
                tank.move(Direction.DOWN)
            }
            KeyCode.A -> {
                tank.move(Direction.LEFT)
            }
            KeyCode.D -> {
                tank.move(Direction.RIGHT)
            }
            KeyCode.SPACE -> {
                viewList.add(tank.shot())
            }
        }
    }

    override fun onRefresh() {
        viewList.filterIsInstance<MoveAble>().forEach(){ move ->
            var badDirection :Direction? = null
            var badBlock :BlockAble? = null
            viewList.filterIsInstance<BlockAble>().forEach(){block->
               //move 和block 是否碰撞
                var direction = move.willCollision(block)
                direction?.let {
                    badDirection = direction
                    badBlock = block
                    return@forEach
                }
            }
            move.notifyCollison(badDirection,badBlock)
        }
        viewList.filterIsInstance<FlyAble>().forEach(){
            it.autoMove()
        }
        viewList.filterIsInstance<DestroyAble>().forEach(){
            if(it.destroy()){
                viewList.remove(it)
            }
        }
    }
}

fun main() {
    Application.launch(MyWindow::class.java)
}