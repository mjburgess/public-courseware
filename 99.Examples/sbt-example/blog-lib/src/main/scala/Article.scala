case class Article(val author: String, val text: String)

object ArticleRenderer {
	def render(a: Article) = println(a.text)
}