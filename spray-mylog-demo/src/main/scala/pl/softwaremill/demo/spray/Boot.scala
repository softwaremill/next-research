package pl.softwaremill.demo.spray

import akka.actor.{Props, ActorSystem}
import spray.servlet.WebBoot

// this class is instantiated by the servlet initializer
// it needs to have a default constructor and implement
// the spray.servlet.WebBoot trait
class Boot extends WebBoot {

  // we need an ActorSystem to host our application in
  val system = ActorSystem("sparay-mylog-demo")

  // the service actor replies to incoming HttpRequests
  val serviceActor = system.actorOf(Props[LogServiceActor])

  system.registerOnTermination {
    // put additional cleanup code here
    system.log.info("Application shut down")
  }

}