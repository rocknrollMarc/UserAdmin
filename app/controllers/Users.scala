package controllers

import play.api.mvc.Controller

object Users extends Controller {

  val users: scala.collection.mutable.Map[String, User] = new HashMap

  def add = Action {
    Ok(vies.html.users.add(form))
  }

  def save = Action { implicit request =>
    val user = form.bindFormRequest.get
    users.put(user.id, user)
    Ok("User successfully created!")
  }

  def list = Action {
    Ok(views.html.users.list(users.values))
  }

  def save = Action {implicit request =>
    val user = form.bindFromRequest.get
    users.put(user.id, user)
    Redirect(routes.Users.list)
  }

  def edit(id: Int) = Action {
    val bindedForm = form.fill(users.get(id).get)
      Ok(views.html.users.edit(bindedForm))
  }

  def update(id: Int) = Action { implicit request =>
    val user = form.bindFromRequest.get
    user.id = id
    users.put(id, user)
    Redirect(routes.Users.list)
  }

  def delete(id: Int) = Action {
    user.remove(id)
      Redirect(routes.Users.list)
  }

  val form = Form(
    mapping(
        "username" -> text,
        "email" -> text)(User.apply)(User.unapply))


}
