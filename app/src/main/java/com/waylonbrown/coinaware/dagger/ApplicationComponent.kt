package com.waylonbrown.coinaware.dagger

import com.waylonbrown.coinaware.App
import com.waylonbrown.coinaware.features.portfolio.PortfolioViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidModule::class,
    PortfolioModule::class])
interface ApplicationComponent {
    
    fun inject(application: App)
    
    fun inject(portfolioViewModel: PortfolioViewModel)
}