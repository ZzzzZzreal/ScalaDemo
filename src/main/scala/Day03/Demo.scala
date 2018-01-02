package Day03

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  *AkkaActor
  *自己运行来玩玩，理解中间的逻辑顺序
  */
object Demo extends App{

  val as = ActorSystem("aaaa")
  val mas = as.actorOf(Props[MyActorShow])
  val masa = as.actorOf(Props[MyActorShowA])
  mas!("凯总",masa)

}

class MyActorShow extends Actor{
  override def receive: Receive = {
    case x:String =>{
      println(s"1收到来自MyActorShowA的消息：$x")
      println("2MyActorShow发送了：")
      val msg = Console.readLine()
      sender!msg
    }
    case (x:String,y:ActorRef) => {println(s"3发送了$x");y!x}
  }
}

class MyActorShowA extends Actor{
  override def receive: Receive = {
    case x:String => {
      println(s"4收到了来自MyActorShow的消息:$x")
      println("5MyActorShowA说:")
      val msg = Console.readLine()
      sender!msg
    }
  }
}
