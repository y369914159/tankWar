import javafx.application.Application
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

/**
 * 我的窗体
 */
class MyWindow : Window() {
    override fun onCreate() {
        println("窗体创建")
    }
    override fun onDisplay() {
        //窗体渲染的回调
    }
    override fun onKeyPressed(event: KeyEvent) {
        println("按键")
    }
    override fun onRefresh() {
    }
}

fun main() {
    Application.launch(MyWindow::class.java)
}