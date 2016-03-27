import scalatags.Text.all._

object TestPageAssets {
  def index: String = {
    "<!DOCTYPE html>" + html(
      head(
        base(href := "/"),
        meta(name := "viewport", content := "width=device-width, initial-scale=1.0")
      ),
      body(
        // Empty
      )
    )
  }
  
  def highlightJsLanguages = Vector(
    "bash", "clojure", "coffeescript", "cpp", "cs", "d", "delphi", "erlang", "fsharp",
    "go", "groovy", "haskell", "java", "javascript", "json", "lua", "lisp", "markdown",
    "objectivec", "perl", "php", "python", "ruby", "rust", "scala", "scheme", "sql",
    "swift", "typescript", "css", "xml"
  )

  def highlightJsStyle = "github"
}