package com.example.applicationex.lab9

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.*
import com.example.applicationex.lab9.IMAGE_MANIPULATION_WORK_NAME
import com.example.applicationex.lab9.TAG_OUTPUT
import com.example.applicationex.lab9.WorkerKeys
import com.example.applicationex.lab9.workers.BlurWorker
import com.example.applicationex.lab9.workers.CleanupWorker
import com.example.applicationex.lab9.workers.SaveImageToFileWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {

    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo?> =
        workManager
            .getWorkInfosByTagLiveData(TAG_OUTPUT)
            .asFlow()
            .mapNotNull { it.firstOrNull() }

    override fun applyBlur(imageUri: String, blurLevel: Int) {

        var continuation = workManager.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(CleanupWorker::class.java)
        )

        for (i in 0 until blurLevel) {
            val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
            if (i == 0) {
                blurBuilder.setInputData(
                    workDataOf(WorkerKeys.IMAGE_URI to imageUri)
                )
            }
            continuation = continuation.then(blurBuilder.build())
        }

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val saveRequest = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()

        continuation = continuation.then(saveRequest)
        continuation.enqueue()
    }

    override fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }
}