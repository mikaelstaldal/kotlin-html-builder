# Kotlin HTML Builder

This library can be used to build HTML documents from Kotlin code.
It is inspired by [kotlinx.html](https://github.com/Kotlin/kotlinx.html) and
[Kotlin XML builder](https://github.com/redundent/kotlin-xml-builder)

Contrary to kotlinx.html, there is no compile-time validation of the element order,
that responsibility is on the library user. There is also no DOM support, only streaming 
to an `Appendable` (or as a convenience generate a `String`).

Instead, this library aims to be simple and fast. The HTML is generated and streamed to the 
output on-the-fly, without any intermediate tree representation.

## License

Copyright 2025 Mikael Ståldal.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Importing

To use this library in your project, you need to add the dependency to your build tool configuration.

Since this library is not (yet) published to Maven Central (or any other public repository), you need to
build it and install to your local Maven repository before you can use it in any other project:
```shell
gradle publishToMavenLocal
```

### Gradle

Add the following dependency to your `build.gradle` if you're using Groovy DSL:

```groovy
dependencies {
    implementation 'nu.staldal:kotlin-html-builder:[VERSION]'
}
```

If you're using the Kotlin DSL (`build.gradle.kts`), use:

```kotlin
dependencies {
    implementation("nu.staldal:kotlin-html-builder:[VERSION]")
}
```

### Maven

Add the dependency inside your `<dependencies>` section in `pom.xml`:

```xml

<dependency>
    <groupId>nu.staldal</groupId>
    <artifactId>kotlin-html-builder</artifactId>
    <version>[VERSION]</version>
</dependency>
```

Replace `[VERSION]` with the actual version of the library.

## Usage

```kotlin
val output: Appendable = // ... 
output.htmlDoc {
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
```
produces
```html
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
```

```kotlin
val theHtml: String = partialHtml {
    for (i in 1..3) {
        li {
            +"Item $i"
        }
    }
}
```
produces
```html
<li>
    Item 1
</li>
<li>
    Item 2
</li>
<li>
    Item 3
</li>
```

## Unsafe
Text and attribute values are by default escaped to produce valid HTML. This can be selectively disabled:
```kotlin
val theHtml = partialHtml {
    div("class" to unsafe("&")) {
        unsafe("<&>")
    }
}
```
produces
```html
<div class="&">
    <&>
</div>
```

The content of the [raw text elements](https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements) 
`<script>` and `<style>` are not escaped.
