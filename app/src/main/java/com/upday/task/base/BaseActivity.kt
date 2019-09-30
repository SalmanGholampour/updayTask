package com.upday.task.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.upday.task.R
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseNavigator, BaseFragment.Callback {
    lateinit var mViewDataBinding: T
    private var mViewModel: V? = null

    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun showConnectionError() {
        //this is default act. you can customize it on Intended Activity
        Toast.makeText(this, getString(R.string.connectionError_text), Toast.LENGTH_LONG).show()

    }

    override fun showNormalError() {
        //this is default act. you can customize it on Intended Activity
        Toast.makeText(this, getString(R.string.normalError_text), Toast.LENGTH_LONG).show()

    }

    override fun showUnAuthorizedError() {
        //this is default act. you can customize it on Intended Activity
        Toast.makeText(this, getString(R.string.unAuthorizedError_text), Toast.LENGTH_LONG).show()

    }
}