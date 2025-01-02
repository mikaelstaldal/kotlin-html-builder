package nu.staldal.kotlin.html

import org.junit.jupiter.api.Test

class CustomDSLTest : TestBase() {
    @Test
    fun test() {
        val result = partialHtml {
            myList {
                for (i in 1..3) {
                    myItem("Name $i")
                }
                for (i in 4..6) {
                    myItem("Name $i", age = i)
                }
            }
        }

        validate(
            """
		<ul class="my-list">
			<li class="my-item">
				<span>
					Name 1
				</span>
			</li>
			<li class="my-item">
				<span>
					Name 2
				</span>
			</li>
			<li class="my-item">
				<span>
					Name 3
				</span>
			</li>
			<li class="my-item">
				<span>
					Name 4
				</span>
				 - 
				<span>
					4
				</span>
			</li>
			<li class="my-item">
				<span>
					Name 5
				</span>
				 - 
				<span>
					5
				</span>
			</li>
			<li class="my-item">
				<span>
					Name 6
				</span>
				 - 
				<span>
					6
				</span>
			</li>
		</ul>
		
		""".trimIndent(), result
        )
    }
}
