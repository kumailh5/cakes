package com.kumail.cakes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumail.cakes.data.model.Cake
import com.kumail.cakes.data.repository.CakesRepository
import com.kumail.cakes.network.ApiResponse
import com.kumail.cakes.util.SingleLiveEvent
import com.kumail.cakes.util.formatCakes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kumailhussain on 16/11/2021.
 */
@HiltViewModel
class MainViewModel @Inject internal constructor(private val cakesRepository: CakesRepository) :
    ViewModel() {

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: SingleLiveEvent<String> = _errorMessage

    private val _cakesList = MutableLiveData<List<Cake>>()
    val cakesList: MutableLiveData<List<Cake>> = _cakesList

    init {
        getCakesList()
    }

    private fun getCakesList() {
        viewModelScope.launch {
            when (val result = cakesRepository.getCakesList()) {
                is ApiResponse.Success -> _cakesList.postValue(result.data!!.formatCakes())
                is ApiResponse.Empty -> Timber.d(result.toString())
                is ApiResponse.NetworkError -> {
                    _errorMessage.postValue(result.errorResponse.errorMessage)
                    Timber.e(result.errorResponse.toString())
                }
                is ApiResponse.ExceptionError -> {
                    _errorMessage.postValue(result.errorResponse.message)
                    Timber.e(result.errorResponse.toString())
                }
            }
        }
    }
}