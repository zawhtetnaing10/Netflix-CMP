package com.zg.netflixcmp.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

// Application wide coroutine scope to handle background work
object AppCoroutineScope {
    // Creating Coroutine Scopes
    val supervisorJob = SupervisorJob()
    val ioScope = CoroutineScope(Dispatchers.IO + supervisorJob)
    val cpuScope = CoroutineScope(Dispatchers.Default + supervisorJob)

    fun cancel() {
        supervisorJob.cancel()
    }
}