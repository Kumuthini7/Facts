package com.example.facts.network

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Kumuthini.N on 08-08-2020
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
private val NETWORK_EXECUTOR = Executors.newFixedThreadPool(3)
private val MAIN_EXECUTOR = MainThreadExecutor()


fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}

fun mainThread(f: () -> Unit) {
    MAIN_EXECUTOR.execute(f)
}

private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}
