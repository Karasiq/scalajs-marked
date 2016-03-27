package com.karasiq.scalajstest.frontend

import com.karasiq.highlightjs.HighlightJS
import com.karasiq.markedjs.{Marked, MarkedOptions, MarkedRenderer}
import com.karasiq.taboverridejs.TabOverride
import org.scalajs.dom
import org.scalajs.jquery._

import scala.language.postfixOps
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.{JSApp, UndefOr}

@JSExport
object TestApp extends JSApp {
  val source =
    """
      |# H1
      |## H2
      |### H3
      |#### H4
      |##### H5
      |###### H6
      |
      |Alternatively, for H1 and H2, an underline-ish style:
      |
      |Alt-H1
      |======
      |
      |Alt-H2
      |------
      |Emphasis, aka italics, with *asterisks* or _underscores_.
      |
      |Strong emphasis, aka bold, with **asterisks** or __underscores__.
      |
      |Combined emphasis with **asterisks and _underscores_**.
      |
      |Strikethrough uses two tildes. ~~Scratch this.~~
      |1. First ordered list item
      |2. Another item
      |  * Unordered sub-list.
      |1. Actual numbers don't matter, just that it's a number
      |  1. Ordered sub-list
      |4. And another item.
      |
      |   You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we'll use three here to also align the raw Markdown).
      |
      |   To have a line break without a paragraph, you will need to use two trailing spaces.⋅⋅
      |   Note that this line is separate, but within the same paragraph.⋅⋅
      |   (This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)
      |
      |* Unordered list can use asterisks
      |- Or minuses
      |+ Or pluses
      |[I'm an inline-style link](https://www.google.com)
      |
      |[I'm an inline-style link with title](https://www.google.com "Google's Homepage")
      |
      |[I'm a reference-style link][Arbitrary case-insensitive reference text]
      |
      |[I'm a relative reference to a repository file](../blob/master/LICENSE)
      |
      |[You can use numbers for reference-style link definitions][1]
      |
      |Or leave it empty and use the [link text itself].
      |
      |URLs and URLs in angle brackets will automatically get turned into links.
      |http://www.example.com or <http://www.example.com> and sometimes
      |example.com (but not on Github, for example).
      |
      |Some text to show that the reference links can follow later.
      |
      |[arbitrary case-insensitive reference text]: https://www.mozilla.org
      |[1]: http://slashdot.org
      |[link text itself]: http://www.reddit.com
      |Here's our logo (hover to see the title text):
      |
      |Inline-style:
      |![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")
      |
      |Reference-style:
      |![alt text][logo]
      |
      |[logo]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 2"
      |Inline `code` has `back-ticks around` it.
      |```javascript
      |var s = "JavaScript syntax highlighting";
      |alert(s);
      |```
      |
      |```python
      |s = "Python syntax highlighting"
      |print s
      |```
      |
      |```
      |No language indicated, so no syntax highlighting.
      |But let's throw in a <b>tag</b>.
      |```
      |Colons can be used to align columns.
      |
      || Tables        | Are           | Cool  |
      || ------------- |:-------------:| -----:|
      || col 3 is      | right-aligned | $1600 |
      || col 2 is      | centered      |   $12 |
      || zebra stripes | are neat      |    $1 |
      |
      |There must be at least 3 dashes separating each header cell.
      |The outer pipes (|) are optional, and you don't need to make the
      |raw Markdown line up prettily. You can also use inline Markdown.
      |
      |Markdown | Less | Pretty
      |--- | --- | ---
      |*Still* | `renders` | **nicely**
      |1 | 2 | 3
      |
      |> Blockquotes are very handy in email to emulate reply text.
      |> This line is part of the same quote.
      |
      |Quote break.
      |
      |> This is a very long line that will still be quoted properly when it wraps. Oh boy let's keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote.
      |Three or more...
      |
      |<dl>
      |  <dt>Definition list</dt>
      |  <dd>Is something people use sometimes.</dd>
      |
      |  <dt>Markdown in HTML</dt>
      |  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
      |</dl>
      |
      |---
      |
      |Hyphens
      |
      |***
      |
      |Asterisks
      |
      |___
      |
      |Underscores
    """.stripMargin

  @JSExport
  override def main(): Unit = {
    jQuery(() ⇒ {
      val options = MarkedOptions(
        highlight = { (source: String, lang: UndefOr[String], _: js.Function) ⇒
          lang.fold(HighlightJS.highlightAuto(source))(HighlightJS.highlight(_, source)).value
        },
        renderer = MarkedRenderer(table = { (header: String, body: String) ⇒
          import scalatags.Text.all.{body => _, header => _, _}
          table(`class` := "table table-striped", thead(raw(header)), tbody(raw(body))).render
        }),
        breaks = true,
        smartypants = true
      )

      import scalatags.JsDom.all.{source => _, _}
      val textArea = textarea(`class` := "form-control", rows := 3).render
      TabOverride.tabSize(2)
      TabOverride.set(textArea)

      div(`class` := "container",
        div(`class` := "row", div(`class` := "col-md-12", div(textArea))),
        div(`class` := "row", div(`class` := "col-md-12", div(raw(Marked(source, options)))))
      ).applyTo(dom.document.body)
    })
  }
}