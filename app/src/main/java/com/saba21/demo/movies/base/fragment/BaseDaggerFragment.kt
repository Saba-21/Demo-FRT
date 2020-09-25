package com.saba21.demo.movies.base.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.di.BaseFragmentComponent
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModel
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModelFactory
import com.saba21.demo.movies.main.activity.MainActivity
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import dagger.Lazy
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseDaggerFragment<VM : BaseViewModel<*, *>> : Fragment() {

    abstract val viewModelClass: KClass<out ViewModel>

    protected abstract fun getComponent(activityComponent: ActivityComponent):
            BaseFragmentComponent<out BaseDaggerFragment<VM>>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activityComponent = (activity as MainActivity).activityComponent

        @Suppress("UNCHECKED_CAST")
        val fragmentComponent =
            (getComponent(activityComponent) as BaseFragmentComponent<BaseDaggerFragment<VM>>)

        fragmentComponent.inject(this)
    }

    @Inject
    lateinit var viewModelFactory: Lazy<BaseViewModelFactory<VM>>

    private val mViewModel: VM by lazy {
        @Suppress("UNCHECKED_CAST")
        createViewModelLazy(
            viewModelClass,
            { viewModelStore },
            { viewModelFactory.get() }).value as VM
    }

    protected val viewModel: VM get() = mViewModel

}