package keijumt.redux

import com.google.common.truth.Truth.assertThat
import keijumt.redux.redux.AppAction
import keijumt.redux.redux.AppState
import keijumt.redux.redux.Reducer
import keijumt.redux.redux.state.SearchRepoState
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class ReducerSpec : Spek({
    Feature("ReducerSpec") {
        val reducer by memoized { Reducer() }

        Scenario("SearchReposAction.SearchLoadingでstateが正常に更新される") {
            lateinit var state: AppState

            Given("searchRepoState.isLoadingがfalse") {
                state = AppState(searchRepoState = SearchRepoState(isLoading = false))
            }

            When("reduce()") {
                state = reducer.reduce(
                    state,
                    AppAction.SearchReposAction.SearchLoading(true)
                )
            }

            Then("searchRepoState.isLoadingがtrue") {
                assertThat(state.searchRepoState.isLoading).isTrue()
            }
        }

        Scenario("SearchReposAction.SearchReposでstateが正常に更新される") {
            lateinit var state: AppState

            Given("searchRepoState.reposがemptyList") {
                state = AppState(searchRepoState = SearchRepoState(repos = emptyList()))
            }

            When("reduce()") {
                state = reducer.reduce(
                    state,
                    AppAction.SearchReposAction.SearchRepos(getReposDummyData(10))
                )
            }

            Then("searchRepoState.reposの値が更新される") {
                assertThat(state.searchRepoState.repos).hasSize(10)
            }
        }
    }
})