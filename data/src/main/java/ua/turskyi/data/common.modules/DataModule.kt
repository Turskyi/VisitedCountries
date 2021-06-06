package ua.turskyi.data.common.modules

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
        DatabaseModule::class,
        ApiServicesModule::class
    ]
)
class DataModule