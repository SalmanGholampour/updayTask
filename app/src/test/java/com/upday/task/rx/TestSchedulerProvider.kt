package com.upday.task.rx

import com.upday.task.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : SchedulerProvider {
    override fun io(): Scheduler {
        return mTestScheduler
    }

    override fun ui(): Scheduler {
        return mTestScheduler
    }


}