package com.example.coreNetwork.di

import com.example.coreNetwork.apiService.ApiService
import com.example.coreNetwork.client.KtorClient
import org.koin.dsl.module

fun getCoreNetworkModule() = module {
    single { ApiService(httpClient = KtorClient.getInstance()) }
}