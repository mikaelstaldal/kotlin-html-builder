package nu.staldal.kotlin.html

@HtmlDsl
interface Text: RawText {
    /**
     * Adds the given string as text to the document.
     *
     * The text will be escaped as necessary.
     *
     * @receiver text to be added to the document
     */
    operator fun String.unaryPlus()

    /**
     * Appends the given string as text to the document.
     *
     * The text will be escaped as necessary.
     *
     * @param text text to be added to the document
     */
    fun text(text: String)
}
