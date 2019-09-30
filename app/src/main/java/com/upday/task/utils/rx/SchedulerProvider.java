package com.upday.task.utils.rx;

import io.reactivex.Scheduler;


public interface SchedulerProvider {

    Scheduler io();

    Scheduler ui();
}
