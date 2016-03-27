package com.karasiq.markedjs

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class MarkedOptions extends js.Object {
  /**
    * A function to highlight code blocks
    * @see [[https://github.com/chjj/marked#highlight]]
    */
  val highlight: js.Any = js.undefined

  /**
    * An object containing functions to render tokens to HTML.
    */
  val renderer: MarkedRenderer = new MarkedRenderer()

  /**
    * Enable [[https://help.github.com/articles/github-flavored-markdown GitHub flavored markdown]].
    */
  val gfm: Boolean = true

  /**
    * Enable GFM [[https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#wiki-tables tables]]. This option requires the gfm option to be true.
    */
  val tables: Boolean = true

  /**
    * Enable GFM [[https://help.github.com/articles/github-flavored-markdown#newlines line breaks]]. This option requires the gfm option to be true.
    */
  val breaks: Boolean = false

  /**
    * Conform to obscure parts of `markdown.pl` as much as possible. Don't fix any of the original markdown bugs or poor behavior.
    */
  val pedantic: Boolean = false

  /**
    * Sanitize the output. Ignore any HTML that has been input.
    */
  val sanitize: Boolean = false

  /**
    * Use smarter list behavior than the original markdown. May eventually be default with the old behavior moved into `pedantic`.
    */
  val smartLists: Boolean = true

  /**
    * Use "smart" typograhic punctuation for things like quotes and dashes.
    */
  val smartypants: Boolean = false
}
