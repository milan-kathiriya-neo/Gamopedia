package org.example.gameopedia.di

import com.example.coreNetwork.di.getCoreNetworkModule
import com.example.game.data.di.getGameDataModule
import com.example.game.domain.di.getGameDomainModule
import com.example.game.ui.di.getGameUiModule
import com.example.search.data.di.getSearchDataModule
import com.example.search.domain.di.getSearchDomainModule
import com.example.search.ui.di.getSearchUiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication)->Unit)? = null){
    startKoin {
        modules(
            getCoreNetworkModule(),
            getGameDataModule(),
            getGameDomainModule(),
            getGameUiModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
        )
    }
}