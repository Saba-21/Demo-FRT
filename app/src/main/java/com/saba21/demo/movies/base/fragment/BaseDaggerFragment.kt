package com.saba21.demo.movies.base.fragment

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import com.saba21.demo.movies.base.viewModel.BaseViewModelFactory
import com.saba21.demo.movies.base.di.BaseFragmentComponent
import dagger.Lazy
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseDaggerFragment<VM>(
    @LayoutRes layoutRes: Int,
    viewModelClass: KClass<out ViewModel>
) : Fragment(layoutRes) {

    protected abstract val component: BaseFragmentComponent<out BaseDaggerFragment<VM>>

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        @Suppress()
        (component as BaseFragmentComponent<BaseDaggerFragment<VM>>).inject(this)
    }

    @Inject
    lateinit var viewModelFactory: Lazy<BaseViewModelFactory<VM>>

    @Inject
    lateinit var activityViewModelStore: ViewModelStore

    @Suppress("UNCHECKED_CAST")
    private val mViewModel: VM by lazy {
        createViewModelLazy(
            viewModelClass,
            { activityViewModelStore },
            { viewModelFactory.get() }
        ).value as VM
    }

    protected val viewModel: VM get() = mViewModel

}