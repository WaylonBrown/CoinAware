package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.App
import com.waylonbrown.coinaware.io.Resource
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mu.KotlinLogging
import javax.inject.Inject

private val logger = KotlinLogging.logger {}

class PortfolioViewModel : ViewModel() {
    
    private var listData = MutableLiveData<Resource<PortfolioListData>>()
    
    @Inject
    lateinit var repository: PortfolioRepository
    
    init {
        App.graph.inject(this)
    }
    
    fun getDataFromCache() {
        repository.getBTCtoUSDPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: SingleObserver<PortfolioListData> {
                override fun onSuccess(value: PortfolioListData) {
                    listData.value = Resource.success(value)
                    logger.info { "Value returned: $value"}
                }

                override fun onError(e: Throwable?) {
                    listData.value = Resource.error("Couldn't get result")
                    logger.error(e!!) { "Couldn't get result" }
                }

                override fun onSubscribe(d: Disposable?) {
                }
            })
                
    }
    
    fun fetchNewData() {
        repository.fetchNewBTCtoUSDPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: SingleObserver<PortfolioListData> {
                override fun onSuccess(value: PortfolioListData) {
                    listData.value = Resource.success(value)
                    logger.info { "Value returned: $value"}
                }

                override fun onError(e: Throwable?) {
                    listData.value = Resource.error("Couldn't get result")
                    // TODO: see why this error isn't logged if removing the subscribeOn
                    logger.error { "Couldn't get result" }
                }

                override fun onSubscribe(d: Disposable?) {
                }
            })
    }
    
    // Expose LiveData rather than MutableLiveData
    fun getLiveData(): LiveData<Resource<PortfolioListData>> = listData
}