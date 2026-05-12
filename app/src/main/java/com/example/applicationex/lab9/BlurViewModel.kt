package com.example.applicationex.lab9

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import com.example.applicationex.MainApplication
import com.example.applicationex.R
import com.example.applicationex.lab9.WorkerKeys
import com.example.applicationex.lab9.BluromaticRepository
import kotlinx.coroutines.flow.*

sealed interface BlurUiState {
    object Default : BlurUiState
    object Loading : BlurUiState
    data class Complete(val outputUri: Uri) : BlurUiState
    object Error : BlurUiState
}

class BlurViewModel(
    application: Application,
    private val bluromaticRepository: BluromaticRepository
) : AndroidViewModel(application) {

    var imageUri: String =
        Uri.parse(
            "android.resource://${application.packageName}/${R.mipmap.ic_launcher}"
        ).toString()

    init {
        // Cancel any existing work to start fresh
        bluromaticRepository.cancelWork()
    }

    val blurUiState: StateFlow<BlurUiState> =
        bluromaticRepository.outputWorkInfo
            .map { info ->
                Log.d("BlurViewModel", "WorkInfo state: ${info?.state}")
                when {
                    info == null -> {
                        Log.d("BlurViewModel", "WorkInfo is null, returning Default")
                        BlurUiState.Default
                    }
                    info.state == WorkInfo.State.SUCCEEDED -> {
                        val uri = info.outputData.getString(WorkerKeys.IMAGE_URI)
                        Log.d("BlurViewModel", "Work succeeded, URI: $uri")
                        if (uri != null) BlurUiState.Complete(Uri.parse(uri))
                        else BlurUiState.Error
                    }
                    info.state == WorkInfo.State.FAILED -> {
                        Log.d("BlurViewModel", "Work failed")
                        BlurUiState.Error
                    }
                    info.state == WorkInfo.State.CANCELLED -> {
                        Log.d("BlurViewModel", "Work cancelled")
                        BlurUiState.Error
                    }
                    info.state.isFinished -> {
                        Log.d("BlurViewModel", "Work finished but not succeeded/cancelled")
                        BlurUiState.Default
                    }
                    else -> {
                        Log.d("BlurViewModel", "Work is in progress: ${info.state}")
                        BlurUiState.Loading
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BlurUiState.Default
            )

    fun applyBlur(blurLevel: Int) {
        bluromaticRepository.applyBlur(imageUri, blurLevel)
    }

    fun cancelWork() {
        bluromaticRepository.cancelWork()
    }

    fun updateImageUri(uri: String) {
        imageUri = uri
        Log.d("BlurViewModel", "Image URI updated: $uri")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as MainApplication
                BlurViewModel(app, app.lab9Container.bluromaticRepository)
            }
        }
    }
}