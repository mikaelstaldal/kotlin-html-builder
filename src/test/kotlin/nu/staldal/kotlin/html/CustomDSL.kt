package nu.staldal.kotlin.html

fun Html.myList(block: Html.() -> Unit) {
    ul("class" to "my-list") {
        block()
    }
}

fun Html.myItem(
    name: String,
    age: Int? = null,
) {
    li("class" to "my-item") {
        span { +name }

        age?.let {
            +" - "
            span { +age.toString() }
        }
    }
}
