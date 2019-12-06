package ar.com.nacho91.mealapp.di

import ar.com.nacho91.mealapp.MealApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApiModule::class, ViewModelModule::class, ActivityModule::class])
interface MealApplicationComponent {
    fun inject(app: MealApplication)
}