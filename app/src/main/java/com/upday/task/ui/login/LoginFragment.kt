package com.upday.task.ui.login

import android.os.Bundle
import android.view.View
import com.upday.task.BR
import com.upday.task.R
import com.upday.task.base.BaseFragment
import com.upday.task.databinding.FragmentLoginBinding
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginNavigator {
    override fun goBack() {
        activity?.onBackPressed()

    }

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName

    }

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel
    override fun getBindingVariable(): Int = BR.viewModel


    override fun getLayoutId(): Int = R.layout.fragment_login


    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = mViewDataBinding
        loginViewModel.setNavigator(this)

    }
}
