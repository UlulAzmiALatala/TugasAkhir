package ulul.azmi.a.latala.suratperjalanan

import ulul.azmi.a.latala.suratperjalanan.data.TripData

class TripRepository {
    private val dataSource = TripDataSource()

    fun getAllTrips(): List<TripData> {
        return dataSource.getTrips()
    }

    fun getTripById(id: Int): TripData? {
        return dataSource.getTripById(id)
    }

    fun addTrip(trip: TripData) {
        dataSource.addTrip(trip)
    }

    fun deleteTrip(id: Int) {
        dataSource.deleteTrip(id)
    }

    fun updateTrip(trip: TripData) {
        dataSource.updateTrip(trip)
    }
}
