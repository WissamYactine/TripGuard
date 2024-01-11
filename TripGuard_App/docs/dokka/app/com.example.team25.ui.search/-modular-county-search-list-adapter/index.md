[app](../../index.md) / [com.example.team25.ui.search](../index.md) / [ModularCountySearchListAdapter](./index.md)

# ModularCountySearchListAdapter

`class ModularCountySearchListAdapter`

Adapter for RecyclerView of buttons when searching for slide/flood countys by list.
Ideally, this doesnt take `disaster` and `root` upon creation. This is only needed to
setOnClickListener for the buttons, but it would be prettier if it was done from a fragment.

### Types

| Name | Summary |
|---|---|
| [MyViewHolder](-my-view-holder/index.md) | `class MyViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ModularCountySearchListAdapter(myDataset: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, root: <ERROR CLASS>, disaster: `[`Disaster`](../../com.example.team25.utils/-disaster/index.md)`)`<br>Adapter for RecyclerView of buttons when searching for slide/flood countys by list. Ideally, this doesnt take `disaster` and `root` upon creation. This is only needed to setOnClickListener for the buttons, but it would be prettier if it was done from a fragment. |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ModularCountySearchListAdapter.MyViewHolder`](-my-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: <ERROR CLASS>, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ModularCountySearchListAdapter.MyViewHolder`](-my-view-holder/index.md) |
