package com.waylonbrown.coinaware.io

//a generic class that describes a data with a status
class Resource<T> private constructor(val status: Status,
                                      val data: T? = null,
                                      val message: String? = null) {
    
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
    
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}