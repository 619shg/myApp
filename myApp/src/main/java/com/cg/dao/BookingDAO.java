package com.cg.dao;

import java.util.List;

import com.cg.entity.Dining;
import com.cg.entity.Resort;

public interface BookingDAO {

	public Resort bookResort(Resort resort, long guestID);

	public Resort updateBookResort(Resort resort, long resortBookingId);

	public Resort cancelBookResort(long resortBookingId);

	public Dining bookDining(Dining dining, long guestID);

	public Dining updateBookedDining(Dining d, long diningBookingId);

	public Dining cancelBookedDining(long diningBookingId);

	public List<Resort> getAllResortDetails(long guestID);

	public List<Dining> getAllDiningDetails(long guestID);

}
