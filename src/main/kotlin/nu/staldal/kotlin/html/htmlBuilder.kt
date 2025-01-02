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
 * Creates a partial pretty-printed HTML, without doctype.
 * Can be used to create a partial without a single root element.
 *
 * @param block The block that defines the content of the HTML partial
 *
 * @return the generated HTML partial
 */
fun partialHtml(block: Html.() -> Unit): String =
    buildString { partialHtml(block) }

/**
 * Creates a partial HTML, without doctype.
 * Can be used to create a partial without a single root element.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML partial
 *
 * @return the generated HTML partial
 */
fun partialHtml(prettyPrint: Boolean, block: Html.() -> Unit): String =
    buildString { partialHtml(prettyPrint, block) }

/**
 * Creates a partial pretty-printed HTML, without doctype.
 * Can be used to create a partial without a single root element.
 *
 * @param block The block that defines the content of the HTML partial
 */
fun Appendable.partialHtml(block: Html.() -> Unit) {
    partialHtml(prettyPrint = true, block = block)
}

/**
 * Creates a partial HTML, without doctype.
 * Can be used to create a partial without a single root element.
 *
 * @param prettyPrint generate whitespace for readability
 * @param block The block that defines the content of the HTML partial
 */
fun Appendable.partialHtml(prettyPrint: Boolean, block: Html.() -> Unit) {
    val generator = Html(this, prettyPrint)
    generator.block()
}

fun unsafe(value: Any): Unsafe = Unsafe(value)
