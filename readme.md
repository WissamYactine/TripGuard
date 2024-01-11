## Generating documentation with dokka

Here is an example taken from [a medium article](https://medium.com/@julesrosser/auto-generate-kotlin-android-documentation-with-dokka-382248c03283) on how comments in code turn into pretty documentation:

```kotlin
/**
 * Example of a class comment.
 *
 * @param app: Example of a param comment.
 */
abstract class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {

    /**
     * Example of a property comment.
     */
    private val callbackRegistry: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    /**
     * Example of a function comment.
     *
     * @return Application: Example of a return comment.
     */
    fun app() = getApplication<Application>()
}
```

For more advanced use, see [here](https://kotlinlang.org/docs/reference/kotlin-doc.html).

Code documentation should be generated (if possible) on every commit.
To generate markdown documentation from comments in code, run `./gradlew dokka`.
The documentation will be generated in `team25app/docs/dokka`.
Note that the documentation also has to be added to the git index when committing.
