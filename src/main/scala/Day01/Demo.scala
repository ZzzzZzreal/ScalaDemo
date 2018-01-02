package Day01

import java.io.{File, PrintWriter}
import java.util.concurrent.TimeUnit

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  *Scala代码笔记
  */

/*
  * scala中class相当于java的class
  */
class Day01(name: String, age: Int) {

  //main方法
  //  def main(args: Array[String]): Unit = {
  //    println("Hello World!")
  //  }

  //require() 方法用在对参数的检验上，不通过则抛出 IllegalArgumentException
  require(age !=0)

  var name1 = name
  var age1 = age
  private var gender = false

  /**
    * class Day01(name:String,age:Int) 带参类的声明是这个类的主构造器
    * 在类里面可以添加从构造器来定义各种参数不同的构造，参数可以比主构造器多，也可以少。格式为 def this(...)
    * 如果从构造器参数比主构造器少，则需要给定比主构造器少的参数的默认值
    * 如果从比主参数多，则需要定义变量接收参数的值
    */
  def this(name: String) = this(name, 20)
  def this(age: Int) = this(" ", age)
  def this() = this("M", 1)
  def this(name: String, age: Int, gender: Boolean) = {
    this(name, age)
    this.gender = gender
  }

  def showInfo() = {
    print(name1);
    print("-");
    print(age1);
    print("-");
    println(gender)
  }
}

/**
  * class Day01 和 object Day01 形成伴生对象，两者之间所有的东西都是互通的，包括私有的成员
  */
object Day01 {
  /*  方法（函数）定义的格式为 --->  def 方法名（变量：数据类型，变量2：数据类型，...）[：返回值类型]={方法体}
      返回值类型可以省略 ---> def 方法名（变量：类型）={方法体}
        返回不需要写return，默认返回代码逻辑的最后一行的值*/
  def show(name: String) = {
    name.length
  }
}

/**
  * scala中的object是静态类，main方法只能写在object中
  * 执行程序有两种方法 1）写main方法  2）object 继承App
  */
object Demo extends App {

  //scala中+ - < > : 等很多标识符被方法化，可以直接.()使用
  println(1 + 2, 1.<(2))
  //运行结果：(3,true)

  //调用方法可以 类名.方法（参数）或者 类名 方法 参数
  println(Day01.show("name"))
  println(Day01 show "bitch")
  //运行结果：4
  //        5

  val d = new Day01()
  d.showInfo()
  d.age1 = 18
  d.name1 = "SB"
  d.showInfo()
  new Day01("GG", 1, true).showInfo()
  //运行结果：M-1-false
  //        SB-18-false
  //        GG-1-true


  //while 做循环，if做判断来实现java中的break和continue操作
  var flag = true
  var m = 1
  while (flag) {
    m += 1
    if (!(m > 10 && m < 100)) {
      println(m)
    }
    if (m > 200) flag = false
  }

  //for循环遍历字符串
  "string".foreach(println)

  //类似java中的增强for
  for (a <- List(1, 3, 4, 2, 5)) println(a)

  //定义数组的两种方式
  val arr = new Array[String](3)
  arr(0) = "a"
  arr(0) = "b"
  arr(1) = "a"
  arr(2) = "a"

  val arr1 = Array.apply(1, "a", "b", "c", 1, true)
  println(1 == arr1(0))
  //运行结果:true
  //mkString遍历集合，以“，”分隔
  println(arr1.mkString(","))
  //运行结果:1,a,b,c,1,true

  val x = ListBuffer[Any]()
  x.append(1, 2, 3, 4, 5)
  x.append(3, 4, 5, 5, 6)
  x.toList
  val list = List[Any]("a", "c", 1, true)
  //  “：：：”只能用于连接两个List类型的集合
  println(List(1) ::: list)
  //  “：：” 把新元素添加到现有列表的最前端
  println(1 :: list :: 1 :: Nil)
  println(list)
  //运行结果:List(1, a, c, 1, true)
  //        List(1, List(a, c, 1, true), 1)
  //        List(a, c, 1, true)

  val pair = (1, 2, 3, 4, 5, "a")
  //._1表示1索引上的内容，._2表示2索引上的内容
  println(pair._1, pair._2)
  //运行结果:(1,2)

  val list1 = List("a b", "c d", "f")
  list1.flatMap(_.split(" ")).foreach(println)
  //运行结果:a
  //        b
  //        c
  //        d
  //        e
  //        f

  /**
    * 传值函数与传名函数
    * 传值函数值执行一次参数中的方法，将返回值作为参数调用主函数
    * 传名函数先执行主函数，遇到参数的时候执行一次参数里的函数，遇到几次执行几次
    */
    def time() = {
      println("获取时间，单位为纳秒")
      var time = System.nanoTime
      println(time)
      time
    }
    def delayed( t: => Long ) = {
      println("在 delayed 方法内")
      println("参数： " + t)
      TimeUnit.SECONDS.sleep(10)
      t
    }
    println(delayed(time()))
  //运行结果:在 delayed 方法内
  //        获取时间，单位为纳秒
  //        28315735518822
  //        参数： 28315735518822
  //        获取时间，单位为纳秒
  //        28325736472328
  //        28325736472328

  def show(f:Int*): Unit =println(f.mkString(","))
  show(1,2,444,1111,8)
  show(1,2,444,1111,8,8)
  //运行结果:1,2,444,1111,8
  //        1,2,444,1111,8,8

  /**
    * assert() 或 assume() 方法在对中间结果或私有方法的参数进行检验，不成功则抛出 AssertionError 异常，
    * 至于是用 assert() 或是 assume() 方法，就各取所好了，Scala 给出的原则如下：
    *  This method differs from assert only in the intent expressed:
    *  assert contains a predicate which needs to be proven, while assume contains an axiom for a static checker
    *  说的是 assert() 包含一下需证明的条件，assume() 代表的是一个公理性的论断。
    */
  val dd = new Day01()
  assert(dd.name1 != "M")
  dd.showInfo()
  //Exception in thread "main" java.lang.AssertionError: assertion failed

  /**
    * Scala 进行文件写操作，直接用的都是 java中 的 I/O 类 （java.io.File)
    */
  val writer = new PrintWriter(new File("test.txt" ))
  writer.write("scala")
  writer.close()

  //使用 Scala 的 Source 类及伴生对象来读取文件内容
  Source.fromFile("test.txt").foreach{print}


}
