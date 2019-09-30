package com.upday.task.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.upday.task.R
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment(),
    BaseNavigator {
    private lateinit var mActivity: BaseActivity<*, *>
    private lateinit var mRootView: View
    lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V
    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onDetach() {
        super.getTag()?.let { mActivity.onFragmentDetached(it) }
        super.onDetach()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun showConnectionError() {
        //this is default act. you can customize it on Intended Fragment
        Toast.makeText(activity, getString(R.string.connectionError_text), Toast.LENGTH_LONG).show()

    }

    override fun showNormalError() {
        //this is default act. you can customize it on Intended Fragment
        Toast.makeText(activity, getString(R.string.normalError_text), Toast.LENGTH_LONG).show()

    }

    override fun showUnAuthorizedError() {
        //this is default act. you can customize it on Intended Fragment
        Toast.makeText(activity, getString(R.string.unAuthorizedError_text), Toast.LENGTH_LONG)
            .show()

    }
}