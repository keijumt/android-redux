package keijumt.redux

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import keijumt.redux.databinding.ActivityMainBinding
import keijumt.redux.redux.Store
import keijumt.redux.redux.actioncreator.RepoActionCreator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val repoAdapter = RepoAdapter(this)
        binding.recyclerRepo.adapter = repoAdapter

        val store: Store by inject()
        val repoActionCreator: RepoActionCreator by inject()

        lifecycleScope.launch {
            store.subscribe()
                .distinctUntilChanged { old, new -> old.searchRepoState.isLoading == new.searchRepoState.isLoading }
                .collect {
                    binding.isLoading = it.searchRepoState.isLoading
                }
        }

        lifecycleScope.launch {
            store.subscribe()
                .distinctUntilChanged { old, new -> old.searchRepoState.repos == new.searchRepoState.repos }
                .collect {
                    repoAdapter.submitList(it.searchRepoState.repos)
                }
        }

        store.dispatch(repoActionCreator.searchRepo("kotlin"))
    }
}
