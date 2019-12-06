package ar.com.nacho91.mealapp.di

import ar.com.nacho91.mealapp.ui.MealActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeYourAndroidInjector(): MealActivity
}