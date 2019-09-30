package com.upday.task.di.builder


import com.upday.task.ui.login.LoginFragmentProvider
import com.upday.task.ui.main_page.MainActivity
import com.upday.task.ui.main_page.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, LoginFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity

}