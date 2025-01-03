package nu.staldal.kotlin.html

@HtmlDsl
interface RawText {
    /**
     * Appends the given string as text to the document without escaping.
     *
     * @param text text to be added to the document
     */
    fun unsafe(text: String)
}
