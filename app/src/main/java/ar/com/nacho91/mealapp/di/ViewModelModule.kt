package ar.com.nacho91.mealapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.com.nacho91.mealapp.ui.MealViewModel
import ar.com.nacho91.mealapp.ui.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MealViewModel::class)
    internal abstract fun mealViewModel(viewModel: MealViewModel): ViewModel

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

}