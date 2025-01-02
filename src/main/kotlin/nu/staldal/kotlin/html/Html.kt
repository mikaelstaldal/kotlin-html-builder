package nu.staldal.kotlin.html

/**
 * Generate HTML according to the [HTML syntax](https://html.spec.whatwg.org/multipage/syntax.html#writing).
 *
 * Instances of this class are not thread-safe, and should only be used once.
 *
 * @property out where to write the generated HTML
 * @property prettyPrint generate whitespace for readability
 */
@HtmlDsl
class Html(val out: Appendable, val prettyPrint: Boolean = true) : Text {
    private var indentLevel: Int = 0

    /**
     * Adds the HTML doctype declaration `<!DOCTYPE html>`.
     */
    fun doctype() {
        out.append("<!DOCTYPE html>")
        eol()
    }

    /**
     * Adds a [void element](https://html.spec.whatwg.org/multipage/syntax.html#void-elements)
     * with the specific name. This method allows you to specify optional attributes.
     * Void elements cannot have any content, and will only generate a start tag.
     *
     * ```
     * voidElement("input", "type" to "text")
     * ```
     *
     * @param name name of the element.
     * @param attributes attributes to add to this element. Can be omitted.
     */
    fun voidElement(name: String, vararg attributes: Pair<String, Any>) {
        makeStartTag(name, attributes)
    }

    /**
     * Adds a [raw text element](https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements)
     * with the specific name to the parent.
     * ```
     * rawTextElement("script", "Some text")
     * ```
     * The content of the element is treated as raw text and will not be escaped.
     *
     * @param name name of the element.
     * @param text raw text content
     */
    fun rawTextElement(name: String, text: String) {
        element(name) {
            unsafeText(text)
        }
    }

    /**
     * Adds a [raw text element](https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements)
     * with the specific name to the parent.
     * ```
     * rawTextElement("script", "type" to "module", "Some text")
     * ```
     *
     * The content of the element is treated as raw text and will not be escaped.
     *
     * @param name name of the element.
     * @param attributes attributes to add to this element.
     * @param text raw text content
     */
    fun rawTextElement(name: String, vararg attributes: Pair<String, Any>, text: String) {
        element(name, *attributes) {
            unsafeText(text)
        }
    }

    /**
     * Adds a [escapable raw text element](https://html.spec.whatwg.org/multipage/syntax.html#escapable-raw-text-elements)
     * with the specific name to the parent.
     * ```
     * escapableRawTextElement("textarea", "name" to "the-area") {
     *     +"Some text"
     * }
     * ```
     *
     * @param name name of the element.
     * @param attributes attributes to add to this element. Can be omitted.
     * @param block block that defines the content of the element.
     */
    inline fun escapableRawTextElement(
        name: String,
        vararg attributes: Pair<String, Any>,
        crossinline block: Text.() -> Unit = {}
    ) {
        element(name, *attributes, block = block)
    }

    /**
     * Adds a [normal element](https://html.spec.whatwg.org/multipage/syntax.html#normal-elements)
     * with the specific name to the parent. This method allows you to specify optional attributes and content
     * ```
     * element("div", "class" to "alert") {
     *    // ...
     * }
     * ```
     *
     * @param name name of the element.
     * @param attributes attributes to add to this element. Can be omitted.
     * @param block block that defines the content of the element.
     */
    inline fun element(
        name: String,
        vararg attributes: Pair<String, Any>,
        crossinline block: Html.() -> Unit = {}
    ) {
        startTag(name, attributes)
        block()
        endTag(name)
    }

    /**
     * @param name name of the element.
     * @param attributes attributes to add to this element. Can be omitted.
     */
    fun startTag(name: String, attributes: Array<out Pair<String, Any>>) {
        makeStartTag(name, attributes)
        indentLevel++
    }

    private fun makeStartTag(name: String, attributes: Array<out Pair<String, Any>>) {
        indent()
        out.append('<')
        out.append(name)
        for ((key, value) in attributes) {
            attribute(key, value)
        }
        out.append('>')
        eol()
    }

    /**
     * @param name name of the element.
     */
    fun endTag(name: String) {
        indentLevel--

        indent()
        out.append("</")
        out.append(name)
        out.append('>')
        eol()
    }

    private fun attribute(key: String, value: Any) {
        if (value is Boolean) {
            if (value) {
                out.append(' ')
                out.append(key)
            }
        } else {
            out.append(' ')
            out.append(key)
            out.append("=\"")
            out.append(escapeAttribute(value))
            out.append('"')
        }
    }

    private fun escapeAttribute(value: Any) = if (value is Unsafe) {
        value.value.toString()
    } else {
        value.toString()
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("\'", "&apos;")
    }

    /**
     * Adds the given string as text to the document.
     *
     * @receiver text to be added to the document
     */
    override operator fun String.unaryPlus() = text(this)

    /**
     * Appends the given string as text to the document.
     *
     * @param text text to be added to the document
     */
    override fun text(text: String) {
        indent()
        out.append(escapeText(text))
        eol()
    }

    private fun escapeText(value: String) = value
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")

    /**
     * Appends the given string as text to the document without escaping.
     *
     * @param text text to be added to the document
     */
    override fun unsafeText(text: String) {
        indent()
        out.append(text)
        eol()
    }

    /**
     * Adds the supplied text as a CDATA node
     *
     * @param text  text of the CDATA node.
     */
    override fun cdata(text: String) {
        indent()
        out.append(CDATA_START)
        // split CDATA_END into two pieces so parser doesn't recognize it
        out.append(text.replace(CDATA_END, "]]${CDATA_END}${CDATA_START}>"))
        out.append(CDATA_END)
        eol()
    }

    /**
     * Adds an HTML comment to the document.
     * ```
     * comment("my comment")
     * ```
     *
     * @param text text of the comment. This text will be rendered unescaped except for replace `--` with `&#45;&#45;`
     */
    fun comment(comment: String) {
        indent()
        out.append("<!-- ")
        out.append(comment.replace("--", "&#45;&#45;"))
        out.append(" -->")
        eol()
    }

    private fun indent() {
        if (prettyPrint) {
            repeat(indentLevel) {
                out.append('\t')
            }
        }
    }

    private fun eol() {
        if (prettyPrint) {
            out.append(System.lineSeparator())
        }
    }
}
