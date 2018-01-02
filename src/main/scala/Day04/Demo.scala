package Day04

/**
  * Implicit
  * Scala的隐式转换
  */
object Demo extends App {

  class SwingType {
    def wantLearned(sw: String) = println("兔子已经学会了" + sw)
  }

  object swimming {
    implicit def learningType(s: AminalType) = new SwingType
  }

  class AminalType {
    def show() = println("-----")
  }

  import swimming._

  val a = new AminalType
  a.show()
  a.wantLearned("狗刨式")

  //-----以上都是因式转换的普通用法，包括隐式转换参数，隐式声明数据类型转换，通过导包来完成隐式转换

  /**
    * 以下代码通过使用隐式类实现将一句话每个单词首字母大写其它字母小写的功能，感受一下
    */
  object Stringutils {

    implicit class StringImprovement(val s: String) { //隐式类
      def increment = s.map(x => (x + 1).toChar)

      def firstUpper = s(0).toUpper.toString + s.substring(1).toLowerCase

      def allFirstUpper = {
        val words = s.split(" ")
        words.map(f => {
          if (f.contains(",")) {
            f.split(",").map(_.firstUpper).mkString(",")
          } else f.firstUpper
        }).mkString(" ")
      }

      def show(id: Int) = println(id, s)
    }

  }

  import Stringutils._

  println("qUJIa is a boy,but,aUIOhjkl he love boys!!!!".allFirstUpper)
  //-------上面这个例子，其实就是将一个类隐式声明，然后这个类里的所有方法，只要在import的情况下，可以直接使用----//

  /**
    * 以上代码的运行结果：
    * -----
    * 兔子已经学会了狗刨式
    * Qujia Is A Boy,But,Auiohjkl He Love Boys!!!!
    */
}
