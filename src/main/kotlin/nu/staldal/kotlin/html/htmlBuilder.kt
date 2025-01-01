package nu.staldal.kotlin.html

/**
 * Creates a complete pretty-printed HTML document with a `<!DOCTYPE html>` doctype.
 *
 * @param block The block that defines the content of the HTML document
 *
 * @return the generated HTML document
 */
fun htmlDoc(
    block: Html.() -> Unit
): String = buildString { htmlDoc(block) }

/**
 * Creates a complete HTML document with a `<!DOCTYPE html>` doctype.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML document
 *
 * @return the generated HTML document
 */
fun htmlDoc(
    prettyPrint: Boolean,
    block: Html.() -> Unit
): String = buildString { htmlDoc(prettyPrint, block) }

/**
 * Creates a complete pretty-printed HTML document with a `<!DOCTYPE html>` doctype.
 *
 * @param block The block that defines the content of the HTML
 */
fun Appendable.htmlDoc(
    block: Html.() -> Unit
) = htmlDoc(prettyPrint = true, block)

/**
 * Creates a complete HTML document with a `<!DOCTYPE html>` doctype.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML
 */
fun Appendable.htmlDoc(
    prettyPrint: Boolean,
    block: Html.() -> Unit
) {
    val generator = Html(this, prettyPrint)
    generator.doctype()
    generator.block()
}

/**
 * Creates a pretty-printed HTML fragment, without doctype.
 * Can be used to create a fragment without a single root element.
 *
 * @param block The block that defines the content of the HTML fragment
 *
 * @return the generated HTML fragment
 */
fun htmlFragment(block: Html.() -> Unit): String =
    buildString { htmlFragment(block) }

/**
 * Creates an HTML fragment, without doctype.
 * Can be used to create a fragment without a single root element.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML fragment
 *
 * @return the generated HTML fragment
 */
fun htmlFragment(prettyPrint: Boolean, block: Html.() -> Unit): String =
    buildString { htmlFragment(prettyPrint, block) }

/**
 * Creates a pretty-printed HTML fragment, without doctype.
 * Can be used to create a fragment without a single root element.
 *
 * @param block The block that defines the content of the HTML fragment
 */
fun Appendable.htmlFragment(block: Html.() -> Unit) {
    htmlFragment(prettyPrint = true, block = block)
}

/**
 * Creates an HTML fragment, without doctype.
 * Can be used to create a fragment without a single root element.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML fragment
 */
fun Appendable.htmlFragment(prettyPrint: Boolean, block: Html.() -> Unit) {
    val generator = Html(this, prettyPrint)
    generator.block()
}

fun unsafe(value: Any): Unsafe = Unsafe(value)
