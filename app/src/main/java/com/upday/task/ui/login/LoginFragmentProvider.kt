package com.upday.task.ui.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentProvider {
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    internal abstract fun provideLoginFragmentFactory(): LoginFragment
}