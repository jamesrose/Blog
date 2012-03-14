package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Post(id: Long, title: String, body: String)

object Post {
  
  var simple = {
    get[Long]("post.id") ~
    get[String]("post.title") ~
    get[String]("post.body") map {
      case id~title~body => Post(id, title, body)
    }
  }
  
  def all(): List[Post] =  {
    DB.withConnection { implicit connection => 
       SQL("select * from post").as(Post.simple *)
      }
  }
  
  def create(title: String, body: String) {
      DB.withConnection { implicit connection => 
        SQL("insert into post (title, body) values ({title}, {body})").on(
          'title -> title,
          'body  -> body
        ).executeUpdate()
        }
     }
    
  def delete(id: Long) {
    DB.withConnection { implicit connection => 
      SQL("delete from post where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
  
}