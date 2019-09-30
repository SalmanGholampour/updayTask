package com.upday.task.ui.main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.upday.task.BR
import com.upday.task.R
import com.upday.task.base.BaseActivity
import com.upday.task.databinding.ActivityMainBinding
import com.upday.task.ui.login.LoginFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator,
    HasSupportFragmentInjector, RecyclerViewOnScrollListener.Listener {


    private lateinit var binding: ActivityMainBinding
    @Inject
    internal lateinit var viewModel: MainViewModel
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var imagesAdapter: ImagesAdapter
    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    override fun getBindingVariable(): Int = BR.viewModel


    override fun getLayoutId(): Int = R.layout.activity_main


    override fun getViewModel(): MainViewModel {

        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = mViewDataBinding
        viewModel.setNavigator(this)
        setUp()
        getData()

    }

    private fun setUp() {
        binding.imageRecyclerView.layoutManager = mLayoutManager
        binding.imageRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.imageRecyclerView.adapter = imagesAdapter


        binding.imageRecyclerView.addOnScrollListener(
            RecyclerViewOnScrollListener(
                this
            )
        )
    }

    override fun fetchMoreImage() {
        viewModel.fetchImages(viewModel.getImageRequest())
    }

    private fun getData() {
        if (viewModel.isAccessTokenExist()) {
            viewModel.fetchImages(viewModel.getImageRequest())
        } else {
            gotoLoginFragment()
        }
    }

    override fun gotoLoginFragment() {
        val fragment = LoginFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(LoginFragment::class.java.name)
            .add(R.id.lytMain, fragment, LoginFragment.TAG)
            .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onFragmentDetached(tag: String) {
        if (tag == LoginFragment.TAG) {
            viewModel.fetchImages(viewModel.getImageRequest())
        }
        super.onFragmentDetached(tag)
    }

    override fun showUnAuthorizedError() {
        gotoLoginFragment()
    }
}
