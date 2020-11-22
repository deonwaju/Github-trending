package com.deonolarewaju.mobile_ui

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import com.deonolarewaju.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject constructor(): PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}