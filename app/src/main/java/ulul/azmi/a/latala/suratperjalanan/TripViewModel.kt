package ulul.azmi.a.latala.suratperjalanan

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ulul.azmi.a.latala.suratperjalanan.data.TripData

class TripViewModel : ViewModel() {
    var tripList: List<TripData> by mutableStateOf(emptyList())
        private set

    private val repository = TripRepository()

    fun saveTrip(nama: String, tujuan: String, tanggal: String, keterangan: String) {
        val newTrip = TripData(
            id = (tripList.size + 1),
            nama = nama,
            tujuan = tujuan,
            tanggal = tanggal,
            keterangan = keterangan
        )
        repository.addTrip(newTrip)
        tripList = repository.getAllTrips()
    }

    fun deleteTrip(id: Int) {
        repository.deleteTrip(id)
        tripList = repository.getAllTrips()
    }

    fun editTrip(updatedTrip: TripData) {
        repository.updateTrip(updatedTrip)
        tripList = repository.getAllTrips()
    }

    fun getTripById(id: Int?): TripData? {
        return repository.getTripById(id ?: -1)
    }
}
