package com.upday.task.ui.error

import com.upday.task.base.BaseNavigator
import java.io.IOException
import java.net.SocketException

class GlobalErrorHandler {
    companion object {
        fun handle(t: Throwable, navigator: BaseNavigator) {
            if (t is SocketException || t is IOException) {
                navigator.showConnectionError()
            } else {
                if (t is retrofit2.HttpException) {
                    when (t.code()) {
                        401 -> navigator.showUnAuthorizedError()
                        //you can handle all of Http errors

                        else -> navigator.showNormalError()

                    }
                } else {
                    navigator.showNormalError()
                }
            }

        }
    }

}