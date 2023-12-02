

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kittycat.catapi.data.Cat

class CatViewModel : ViewModel() {
    private val _bibleData = MutableLiveData<List<Cat>>()
    val bibleData: LiveData<List<Cat>> get() = _bibleData

    fun updateBibleData(newData: List<Cat>) {
        _bibleData.value = newData
    }
}