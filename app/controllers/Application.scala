package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import models.Post

object Application extends Controller {
  
  val postForm = Form(
    tuple(
      "title" -> text,
      "body"  -> text
    )
  )
  
  def index = Action {
    Ok(views.html.index(Post.all(), postForm))
  }
  
  def newPost = Action { implicit request =>
    postForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Post.all(), errors)),
      { 
        case (title, body) => 
          Post.create(title, body)
          Redirect(routes.Application.index)
      }
    )
  }
  
  def deletePost(id: Long) = Action {
    Post.delete(id)
    Redirect(routes.Application.index)
  }
  
}