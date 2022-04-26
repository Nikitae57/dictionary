package ru.nikitae57.dictionary.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.common.di.AppScope
import ru.nikitae57.dictionary.core.AppResources
import ru.nikitae57.domain.core.Res

@Module
abstract class AppBindingModule {
    @Binds
    @AppScope
    abstract fun bindResources(appResources: AppResources): Res
}