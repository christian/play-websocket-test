package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import akka.actor._
import play.api.libs.streams.ActorFlow
import akka.stream.{Materializer, OverflowStrategy, StreamTcpException}
import akka.util.ByteString

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(implicit system: ActorSystem, materializer: Materializer) extends Controller {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request =>
    play.Logger.info("hi")
    Ok(views.html.index())
  }

  def socket = WebSocket.accept[ByteString, ByteString] { request =>
    play.Logger.info("client connected")
    ActorFlow.actorRef(out => MyWebSocketActor.props(out))
  }
}


object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

class MyWebSocketActor(out: ActorRef) extends Actor {
  def receive = {
    case msg: ByteString =>
      play.Logger.info("Message size: " + msg.length)
      // out ! ("I received your message: " + msg)
  }
}
