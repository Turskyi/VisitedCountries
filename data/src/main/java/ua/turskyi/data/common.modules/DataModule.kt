package ua.turskyi.data.common.modules

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        RepositoriesModule::class,
        DatabaseModule::class,
        ApiServicesModule::class
    ]
)
class DataModule