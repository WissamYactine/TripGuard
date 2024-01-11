[app](../../index.md) / [com.example.team25.persistence](../index.md) / [SavedLocationDao](./index.md)

# SavedLocationDao

`interface SavedLocationDao`

Dao for SavedLocation.

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(savedLocation: `[`SavedLocation`](../-saved-location/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [find](find.md) | `abstract fun find(areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, disaster: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`SavedLocation`](../-saved-location/index.md) |
| [getAll](get-all.md) | `abstract fun getAll(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`SavedLocation`](../-saved-location/index.md)`>` |
| [insertAll](insert-all.md) | `abstract fun insertAll(vararg savedLocations: `[`SavedLocation`](../-saved-location/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [update](update.md) | `abstract fun update(areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, disaster: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, status: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
