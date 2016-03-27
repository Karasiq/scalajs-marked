package com.karasiq.taboverridejs

import org.scalajs.dom
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

/**
  * @see [[https://github.com/wjbryant/taboverride]]
  */
@js.native
@JSName("tabOverride")
object TabOverride extends TabOverride

@js.native
trait TabOverride extends js.Object {
  def set(textArea: dom.html.TextArea): Unit = js.native
  def tabSize(size: Int = ???): Int = js.native
  def autoIndent(value: Boolean = ???): Boolean = js.native
  def tabKey(key: Int = ???, mod: js.Array[String] = ???): Int = js.native
  def addExtension(hook: String, function: js.Function): Unit = js.native

  def delay(delay: Int = ???): Int = js.native
  def escape(escape: Boolean = ???): Boolean = js.native
  def style: TabOverrideStyle = js.native
}

@js.native
trait TabOverrideStyle extends js.Object {
  def apply(enabled: Boolean = ???): Boolean = js.native
  def enabledClass(cls: String = ???): String = js.native
  def activeClass(cls: String = ???): String = js.native
  def hardTabSize(tab: Int = ???): Int = js.native
  def utils: TabOverrideStyleUtils = js.native
}

@js.native
trait TabOverrideStyleUtils extends js.Object {
  def hardTabSizeSupported: Boolean = js.native
  def addEnabledClass(e: Element): Unit = js.native
  def addActiveClass(e: Element): Unit = js.native
  def removeEnabledClass(e: Element): Unit = js.native
  def removeActiveClass(e: Element): Unit = js.native
  def updateEnabledClass(cls: String = ???): Unit = js.native
  def updateActiveClass(cls: String = ???): Unit = js.native
  def addTabSizeCSSSelector(cls: String = ???): Unit = js.native
  def removeTabSizeCSSSelector(cls: String = ???): Unit = js.native
}