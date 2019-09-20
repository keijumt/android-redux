package keijumt.redux

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import keijumt.redux.databinding.ActivityMainBinding
import keijumt.redux.redux.Reducer
import keijumt.redux.redux.Store
import keijumt.redux.redux.actioncreator.RepoActionCreator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val repoAdapter = RepoAdapter(this)
        binding.recyclerRepo.adapter = repoAdapter

        // TODO DI
        val store = Store(Reducer())
        val repoActionCreator = RepoActionCreator()

        lifecycleScope.launch {
            store.subscribe().collect {
                repoAdapter.submitList(it.repos)
            }
        }

        store.dispatch(repoActionCreator.loadRepo())
    }
}
