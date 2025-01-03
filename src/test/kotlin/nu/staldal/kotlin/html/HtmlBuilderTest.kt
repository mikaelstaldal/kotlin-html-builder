package nu.staldal.kotlin.html

import org.junit.jupiter.api.Test

class HtmlBuilderTest : TestBase() {
    @Test
    fun document() {
        val html = htmlDoc {
            html("lang" to "en") {
                head {
                    title {
                        +"My title"
                    }
                }
                body {
                    comment("The list")
                    ul {
                        for (i in 0..2) {
                            element("li") {
                                +"Item $i"
                            }
                        }
                    }
                }
            }
        }

        validate(
            """
		<!DOCTYPE html>
		<html lang="en">
			<head>
				<title>
					My title
				</title>
			</head>
			<body>
				<!-- The list -->
				<ul>
					<li>
						Item 0
					</li>
					<li>
						Item 1
					</li>
					<li>
						Item 2
					</li>
				</ul>
			</body>
		</html>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun partial() {
        val html = partialHtml {
            for (i in 1..3) {
                li {
                    +"Item $i"
                }
            }
        }

        validate(
            """
		<li>
			Item 1
		</li>
		<li>
			Item 2
		</li>
		<li>
			Item 3
		</li>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun notPrettyFormattingDoc() {
        val html = htmlDoc(prettyPrint = false) {
            html {
                div()
            }
        }

        validate("""<!DOCTYPE html><html><div></div></html>""", html)
    }

    @Test
    fun notPrettyFormattingPartial() {
        val html = partialHtml(prettyPrint = false) {
            div {
                +"Hello"
            }
            span {
                +"Test"
            }
        }

        validate("""<div>Hello</div><span>Test</span>""", html)
    }

    @Test
    fun comment() {
        val html = partialHtml {
            comment("my comment -->")
            div {
                +"value"
            }
        }

        validate(
            """
		<!-- my comment &#45;&#45;> -->
		<div>
			value
		</div>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun emptyElement() {
        val html = partialHtml {
            span()
        }

        validate(
            """
			<span>
			</span>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun cdata() {
        val html = partialHtml {
            cdata("Some & xml")
        }

        validate(
            """
			<![CDATA[Some & xml]]>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun cdataNesting() {
        val html = partialHtml {
            cdata("<![CDATA[Some & xml]]>")
        }

        validate(
            """
			<![CDATA[<![CDATA[Some & xml]]]]><![CDATA[>]]>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun escapeText() {
        val html = partialHtml {
            span { +"&<>" }
        }

        validate(
            """
		<span>
			&amp;&lt;&gt;
		</span>
		
		""".trimIndent(), html
        )
    }

    @Test
    fun elementWithAttributes() {
        val html = partialHtml {
            div("id" to "value", "class" to "other") {}
        }

        validate(
            """
			<div id="value" class="other">
			</div>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun elementAsStringWithAttributesAndContent() {
        val html = partialHtml {
            div("id" to "value") {
                +"Content"
            }
        }

        validate(
            """
			<div id="value">
				Content
			</div>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun quoteInAttribute() {
        val html = partialHtml {
            meta("content" to "My \" Attribute value '")
        }

        validate(
            """
			<meta content="My &quot; Attribute value &apos;">
			
		""".trimIndent(), html
        )
    }

    @Test
    fun specialCharInAttribute() {
        val html = partialHtml {
            meta("content" to "& < > \" '")
        }

        validate(
            """
			<meta content="&amp; &lt; &gt; &quot; &apos;">
			
		""".trimIndent(), html
        )
    }

    @Test
    fun emptyAttribute() {
        val html = partialHtml {
            input("disabled" to true)
            input("disabled" to false)
        }

        validate(
            """
			<input disabled>
			<input>
			
		""".trimIndent(), html
        )
    }

    @Test
    fun unsafe() {
        val html = partialHtml {
            div("class" to nu.staldal.kotlin.html.unsafe("&")) {
                this.unsafe("<&>")
            }
        }

        validate(
            """
			<div class="&">
				<&>
			</div>

		""".trimIndent(), html
        )
    }

    @Test
    fun escape() {
        val unescapedValue = """<tag> & "double" 'single' €"""

        val html = partialHtml {
            div("test" to unescapedValue) {
                +unescapedValue
            }
        }

        validate(
            """
			<div test="&lt;tag&gt; &amp; &quot;double&quot; &apos;single&apos; €">
				&lt;tag&gt; &amp; "double" 'single' €
			</div>

		""".trimIndent(), html
        )
    }

    @Test
    fun voidElement() {
        val html = partialHtml {
            div {
                +"first"
                br()
                +"second"
            }
        }

        validate(
            """
			<div>
				first
				<br>
				second
			</div>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun rawTextElement() {
        val html = partialHtml {
            script {
                unsafe("foo & bar")
            }
        }

        validate(
            """
			<script>
				foo & bar
			</script>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun escapableRawTextElement() {
        val html = partialHtml {
            title {
                +"foo & bar"
            }
        }

        validate(
            """
			<title>
				foo &amp; bar
			</title>
		
		""".trimIndent(),
            html
        )
    }

    @Test
    fun foreignElements() {
        val html = htmlDoc {
            html {
                head {
                    title {
                        +"Foreign elements"
                    }
                }
                body {
                    svg("width" to 100, "height" to 100) {
                        "circle"(
                            "cx" to 50,
                            "cy" to 50,
                            "r" to 40,
                            "stroke" to "green",
                            "stroke-width" to 4,
                            "fill" to "yellow"
                        )
                    }
                    math {
                        "mfrac" {
                            "mn" {
                                +"1"
                            }
                            "msqrt" {
                                "mn" {
                                    +"2"
                                }
                            }
                        }
                    }
                }
            }
        }

        validate(
            """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>
                        Foreign elements
                    </title>
                </head>
                <body>
                    <svg width="100" height="100">
                        <circle cx="50" cy="50" r="40" stroke="green" stroke-width="4" fill="yellow">
                        </circle>
                    </svg>
                    <math>
                        <mfrac>
                            <mn>
                                1
                            </mn>
                            <msqrt>
                                <mn>
                                    2
                                </mn>
                            </msqrt>
                        </mfrac>
                    </math>
                </body>
            </html>

		""".trimIndent(),
            html
        )
    }
}
