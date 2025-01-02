package nu.staldal.kotlin.html

@HtmlDsl
interface Text {
    /**
     * Adds the given string as text to the document.
     *
     * @receiver text to be added to the document
     */
    operator fun String.unaryPlus()

    /**
     * Appends the given string as text to the document.
     *
     * @param text text to be added to the document
     */
    fun text(text: String)

    /**
     * Appends the given string as text to the document without escaping.
     *
     * @param text text to be added to the document
     */
    fun unsafeText(text: String)

    /**
     * Adds the supplied text as a CDATA node
     *
     * @param text  text of the CDATA node.
     */
    fun cdata(text: String)
}
