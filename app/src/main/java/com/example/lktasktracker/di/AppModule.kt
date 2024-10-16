package com.example.lktasktracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lktasktracker.ui.fragments.VMFactory
import com.example.lktasktracker.ui.fragments.ViewModelKey
import com.example.lktasktracker.ui.fragments.done.DoneViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {
    @Binds
     abstract fun bindVMFactory(factory: VMFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DoneViewModel::class)
    abstract fun bindDoneVm(vm: DoneViewModel): ViewModel
}