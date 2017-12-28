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
import timber.log.Timber
import javax.inject.Inject

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
                    Timber.i("Value returned: $value")
                }

                override fun onError(e: Throwable?) {
                    listData.value = Resource.error("Couldn't get result")
                    Timber.e(e, "Couldn't get result")
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
                    Timber.i("Value returned: $value")
                }

                override fun onError(e: Throwable?) {
                    listData.value = Resource.error("Couldn't get result")
                    Timber.e(e, "Couldn't get result")
                }

                override fun onSubscribe(d: Disposable?) {
                }
            })
    }
    
    // Expose LiveData rather than MutableLiveData
    fun getLiveData(): LiveData<Resource<PortfolioListData>> = listData
}