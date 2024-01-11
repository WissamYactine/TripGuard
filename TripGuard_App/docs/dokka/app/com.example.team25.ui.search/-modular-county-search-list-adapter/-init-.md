[app](../../index.md) / [com.example.team25.ui.search](../index.md) / [ModularCountySearchListAdapter](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`ModularCountySearchListAdapter(myDataset: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, root: <ERROR CLASS>, disaster: `[`Disaster`](../../com.example.team25.utils/-disaster/index.md)`)`

Adapter for RecyclerView of buttons when searching for slide/flood countys by list.
Ideally, this doesnt take `disaster` and `root` upon creation. This is only needed to
setOnClickListener for the buttons, but it would be prettier if it was done from a fragment.

