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

		validate("""
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
		
		""".trimIndent(), html)
	}

	@Test
	fun fragment() {
		val html = htmlFragment {
			for (i in 1..3) {
				li {
					+"Item $i"
				}
			}
		}

		validate("""
		<li>
			Item 1
		</li>
		<li>
			Item 2
		</li>
		<li>
			Item 3
		</li>
		
		""".trimIndent(), html)
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
	fun notPrettyFormattingFragment() {
		val html = htmlFragment(prettyPrint = false) {
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
		val html = htmlFragment {
			comment("my comment -->")
			div {
				+"value"
			}
		}

		validate("""
		<!-- my comment &#45;&#45;> -->
		<div>
			value
		</div>
		
		""".trimIndent(), html)
	}
	
	@Test
	fun emptyElement() {
		val html = htmlFragment {
			span()
		}

		validate("""
			<span>
			</span>
		
		""".trimIndent(),
			html
		)
	}

	@Test
	fun cdata() {
		val html = htmlFragment {
			cdata("Some & xml")
		}

		validate("""
			<![CDATA[Some & xml]]>
		
		""".trimIndent(), html)
	}

	@Test
	fun cdataNesting() {
		val html = htmlFragment {
			cdata("<![CDATA[Some & xml]]>")
		}

		validate("""
			<![CDATA[<![CDATA[Some & xml]]]]><![CDATA[>]]>
		
		""".trimIndent(), html)
	}

	@Test
	fun xmlEncode() {
		val html = htmlFragment {
			span { +"&<>" }
		}

		validate("""
		<span>
			&amp;&lt;&gt;
		</span>
		
		""".trimIndent(), html)
	}

	@Test
	fun elementWithAttributes() {
		val html = htmlFragment {
			div("id" to "value", "class" to "other") {}
		}

		validate("""
			<div id="value" class="other">
			</div>
		
		""".trimIndent(),
			html
		)
	}

	@Test
	fun elementAsStringWithAttributesAndContent() {
		val html = htmlFragment {
			div("id" to "value") {
				+"Content"
			}
		}

		validate("""
			<div id="value">
				Content
			</div>
		
		""".trimIndent(),
			html
		)
	}

	@Test
	fun quoteInAttribute() {
		val html = htmlFragment {
			meta("content" to "My \" Attribute value '")
		}

		validate("""
			<meta content="My &quot; Attribute value &apos;">
			
		""".trimIndent(), html)
	}

	@Test
	fun specialCharInAttribute() {
		val html = htmlFragment {
			meta("content" to "& < > \" '")
		}

		validate("""
			<meta content="&amp; &lt; &gt; &quot; &apos;">
			
		""".trimIndent(), html)
	}

	@Test
	fun emptyAttribute() {
		val html = htmlFragment {
			input("disabled" to true)
			input("disabled" to false)
		}

		validate("""
			<input disabled>
			<input>
			
		""".trimIndent(), html)
	}

	@Test
	fun unsafe() {
		val html = htmlFragment {
			div("class" to unsafe("&")) {
				unsafeText("<&>")
			}
		}

		validate("""
			<div class="&">
				<&>
			</div>

		""".trimIndent(), html)
	}

	@Test
	fun escape() {
		val unescapedValue = """<tag> & "double" 'single' €"""

		val html = htmlFragment {
			div("test" to unescapedValue) {
				+unescapedValue
			}
		}

		validate("""
			<div test="&lt;tag&gt; &amp; &quot;double&quot; &apos;single&apos; €">
				&lt;tag&gt; &amp; "double" 'single' €
			</div>

		""".trimIndent(), html)
	}

	@Test
	fun voidElement() {
		val html = htmlFragment {
			div {
				+"first"
				br()
				+"second"
			}
		}

		validate("""
			<div>
				first
				<br>
				second
			</div>
		
		""".trimIndent(),
			html)
	}

	@Test
	fun rawTextElement() {
		val html = htmlFragment {
			script("foo & bar")
		}

		validate("""
			<script>
				foo & bar
			</script>
		
		""".trimIndent(),
			html)
	}

	@Test
	fun escapableRawTextElement() {
		val html = htmlFragment {
			title {
				+"foo & bar"
			}
		}

		validate("""
			<title>
				foo &amp; bar
			</title>
		
		""".trimIndent(),
			html)
	}
}
