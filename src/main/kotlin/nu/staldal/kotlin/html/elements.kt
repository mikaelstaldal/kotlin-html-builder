package nu.staldal.kotlin.html

fun Html.area(vararg attributes: Pair<String, Any>) = voidElement("area", *attributes)
fun Html.base(vararg attributes: Pair<String, Any>) = voidElement("base", *attributes)
fun Html.br(vararg attributes: Pair<String, Any>) = voidElement("br", *attributes)
fun Html.col(vararg attributes: Pair<String, Any>) = voidElement("col", *attributes)
fun Html.embed(vararg attributes: Pair<String, Any>) = voidElement("embed", *attributes)
fun Html.hr(vararg attributes: Pair<String, Any>) = voidElement("hr", *attributes)
fun Html.img(vararg attributes: Pair<String, Any>) = voidElement("img", *attributes)
fun Html.input(vararg attributes: Pair<String, Any>) = voidElement("input", *attributes)
fun Html.link(vararg attributes: Pair<String, Any>) = voidElement("link", *attributes)
fun Html.meta(vararg attributes: Pair<String, Any>) = voidElement("meta", *attributes)
fun Html.source(vararg attributes: Pair<String, Any>) = voidElement("source", *attributes)
fun Html.track(vararg attributes: Pair<String, Any>) = voidElement("track", *attributes)
fun Html.wbr(vararg attributes: Pair<String, Any>) = voidElement("wbr", *attributes)

fun Html.script(text: String) = rawTextElement("script", text = text)
fun Html.script(vararg attributes: Pair<String, Any>, text: String = "") =
    rawTextElement("script", *attributes, text = text)

fun Html.style(text: String) = rawTextElement("style", text = text)
fun Html.style(vararg attributes: Pair<String, Any>, text: String = "") =
    rawTextElement("style", *attributes, text = text)

inline fun Html.textarea(vararg attributes: Pair<String, Any>, crossinline block: Text.() -> Unit = {}) =
    escapableRawTextElement("textarea", *attributes, block = block)

inline fun Html.title(vararg attributes: Pair<String, Any>, crossinline block: Text.() -> Unit = {}) =
    escapableRawTextElement("title", *attributes, block = block)

inline fun Html.a(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("a", *attributes, block = block)

inline fun Html.abbr(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("abbr", *attributes, block = block)

inline fun Html.address(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("address", *attributes, block = block)

inline fun Html.article(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("article", *attributes, block = block)

inline fun Html.aside(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("aside", *attributes, block = block)

inline fun Html.audio(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("audio", *attributes, block = block)

inline fun Html.b(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("b", *attributes, block = block)

inline fun Html.bdi(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("bdi", *attributes, block = block)

inline fun Html.bdo(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("bdo", *attributes, block = block)

inline fun Html.blockquote(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("blockquote", *attributes, block = block)

inline fun Html.body(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("body", *attributes, block = block)

inline fun Html.button(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("button", *attributes, block = block)

inline fun Html.canvas(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("canvas", *attributes, block = block)

inline fun Html.caption(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("caption", *attributes, block = block)

inline fun Html.cite(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("cite", *attributes, block = block)

inline fun Html.code(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("code", *attributes, block = block)

inline fun Html.colgroup(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("colgroup", *attributes, block = block)

inline fun Html.data(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("data", *attributes, block = block)

inline fun Html.datalist(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("datalist", *attributes, block = block)

inline fun Html.dd(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("dd", *attributes, block = block)

inline fun Html.del(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("del", *attributes, block = block)

inline fun Html.details(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("details", *attributes, block = block)

inline fun Html.dfn(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("dfn", *attributes, block = block)

inline fun Html.dialog(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("dialog", *attributes, block = block)

inline fun Html.div(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("div", *attributes, block = block)

inline fun Html.dl(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("dl", *attributes, block = block)

inline fun Html.dt(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("dt", *attributes, block = block)

inline fun Html.em(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("em", *attributes, block = block)

inline fun Html.fieldset(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("fieldset", *attributes, block = block)

inline fun Html.figcaption(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("figcaption", *attributes, block = block)

inline fun Html.figure(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("figure", *attributes, block = block)

inline fun Html.footer(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("footer", *attributes, block = block)

inline fun Html.form(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("form", *attributes, block = block)

inline fun Html.h1(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h1", *attributes, block = block)

inline fun Html.h2(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h2", *attributes, block = block)

inline fun Html.h3(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h3", *attributes, block = block)

inline fun Html.h4(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h4", *attributes, block = block)

inline fun Html.h5(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h5", *attributes, block = block)

inline fun Html.h6(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("h6", *attributes, block = block)

inline fun Html.head(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("head", *attributes, block = block)

inline fun Html.header(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("header", *attributes, block = block)

inline fun Html.hgroup(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("hgroup", *attributes, block = block)

inline fun Html.html(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("html", *attributes, block = block)

inline fun Html.i(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("i", *attributes, block = block)

inline fun Html.iframe(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("iframe", *attributes, block = block)

inline fun Html.ins(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("ins", *attributes, block = block)

inline fun Html.kbd(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("kbd", *attributes, block = block)

inline fun Html.label(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("label", *attributes, block = block)

inline fun Html.legend(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("legend", *attributes, block = block)

inline fun Html.li(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("li", *attributes, block = block)

inline fun Html.main(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("main", *attributes, block = block)

inline fun Html.map(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("map", *attributes, block = block)

inline fun Html.mark(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("mark", *attributes, block = block)

inline fun Html.menu(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("menu", *attributes, block = block)

inline fun Html.meter(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("meter", *attributes, block = block)

inline fun Html.nav(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("nav", *attributes, block = block)

inline fun Html.noscript(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("noscript", *attributes, block = block)

inline fun Html.`object`(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("object", *attributes, block = block)

inline fun Html.ol(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("ol", *attributes, block = block)

inline fun Html.optgroup(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("optgroup", *attributes, block = block)

inline fun Html.option(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("option", *attributes, block = block)

inline fun Html.output(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("output", *attributes, block = block)

inline fun Html.p(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("p", *attributes, block = block)

inline fun Html.picture(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("picture", *attributes, block = block)

inline fun Html.pre(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("pre", *attributes, block = block)

inline fun Html.progress(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("progress", *attributes, block = block)

inline fun Html.q(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("q", *attributes, block = block)

inline fun Html.rp(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("rp", *attributes, block = block)

inline fun Html.rt(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("rt", *attributes, block = block)

inline fun Html.ruby(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("ruby", *attributes, block = block)

inline fun Html.s(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("s", *attributes, block = block)

inline fun Html.samp(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("samp", *attributes, block = block)

inline fun Html.search(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("search", *attributes, block = block)

inline fun Html.section(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("section", *attributes, block = block)

inline fun Html.select(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("select", *attributes, block = block)

inline fun Html.slot(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("slot", *attributes, block = block)

inline fun Html.small(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("small", *attributes, block = block)

inline fun Html.span(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("span", *attributes, block = block)

inline fun Html.strong(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("strong", *attributes, block = block)

inline fun Html.sub(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("sub", *attributes, block = block)

inline fun Html.summary(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("summary", *attributes, block = block)

inline fun Html.sup(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("sup", *attributes, block = block)

inline fun Html.table(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("table", *attributes, block = block)

inline fun Html.tbody(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("tbody", *attributes, block = block)

inline fun Html.td(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("td", *attributes, block = block)

inline fun Html.template(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("template", *attributes, block = block)

inline fun Html.tfoot(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("tfoot", *attributes, block = block)

inline fun Html.th(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("th", *attributes, block = block)

inline fun Html.thead(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("thead", *attributes, block = block)

inline fun Html.time(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("time", *attributes, block = block)

inline fun Html.tr(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("tr", *attributes, block = block)

inline fun Html.u(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("u", *attributes, block = block)

inline fun Html.ul(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("ul", *attributes, block = block)

inline fun Html.`var`(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("var", *attributes, block = block)

inline fun Html.video(vararg attributes: Pair<String, Any>, crossinline block: Html.() -> Unit = {}) =
    element("video", *attributes, block = block)
