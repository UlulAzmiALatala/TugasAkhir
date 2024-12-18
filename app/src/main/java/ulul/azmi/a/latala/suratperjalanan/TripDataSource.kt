package ulul.azmi.a.latala.suratperjalanan

import ulul.azmi.a.latala.suratperjalanan.data.TripData

class TripDataSource {
    private var tripList = mutableListOf<TripData>()

    fun getTrips(): List<TripData> = tripList

    fun getTripById(id: Int): TripData? = tripList.find { it.id == id }

    fun addTrip(trip: TripData) {
        tripList.add(trip)
    }

    fun deleteTrip(id: Int) {
        tripList.removeIf { it.id == id }
    }

    fun updateTrip(updatedTrip: TripData) {
        val index = tripList.indexOfFirst { it.id == updatedTrip.id }
        if (index != -1) {
            tripList[index] = updatedTrip
        }
    }
}
