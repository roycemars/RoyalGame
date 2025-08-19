package com.roycemars.royalgame.core.di

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.roycemars.royalgame.feature.list.ui.ClubListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey(val value: KClass<out MavericksViewModel<*>>)

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ClubListViewModel::class)
    fun businessViewModelFactory(factory: ClubListViewModel.Factory): AssistedViewModelFactory<*, *>
}