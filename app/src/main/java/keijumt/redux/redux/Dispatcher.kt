package keijumt.redux.redux

class Dispatcher(
    private val store: Store
) {
    fun dispatch(action: Action) {
        store.dispatch(action)
    }
}