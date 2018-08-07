package com.cg.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Dining;
import com.cg.entity.Resort;
import com.cg.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController {
	private static final Logger LOGGER = LogManager.getLogger("BookingController.class");

	@Autowired
	private BookingService bookingService;

	@PostMapping("/resort/{guestID}")
	public ResponseEntity<Resort> bookResort(@RequestBody Resort resort, @PathVariable long guestID) {
		LOGGER.debug("BookingController:Debugging bookResort method");
		LOGGER.info("BookingController:	Executing Resort Booking for a Guest.");
		Resort bookedResort = bookingService.bookResort(resort, guestID);
		if (bookedResort != null) {
			LOGGER.info("Resort Booked for a Guest and bookedResort Entity returned.");
			return new ResponseEntity<Resort>(bookedResort, HttpStatus.CREATED);
		} else {
			LOGGER.warn("Resort booking failed for a Guest.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/resort/{resortBookingId}")
	public ResponseEntity<Resort> updateBookResort(@RequestBody Resort resort, @PathVariable long resortBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookResort method");
		LOGGER.info("BookingController:	Executing updateBookResort for a Guest.");
		Resort r = bookingService.updateBookResort(resort, resortBookingId);
		if (r != null) {
			LOGGER.info("Booked Resort for a Guest is updated.");
			return new ResponseEntity<Resort>(r, HttpStatus.CREATED);
		} else {
			LOGGER.warn("Booked Resort for a Guest is not updated.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancel/resort/{resortBookingId}")
	public ResponseEntity<Resort> cancelBookResort(@PathVariable long resortBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookResort method");
		LOGGER.info("BookingController:	Executing cancelBookResort for a Guest.");
		Resort r = bookingService.cancelBookResort(resortBookingId);
		if (r != null) {
			LOGGER.info("BookingController:	Booked Resort for a Guest is canceled.");
			return new ResponseEntity<Resort>(r, HttpStatus.OK);
		} else {
			LOGGER.warn("BookingController:	Booked Resort for a Guest is not canceled.");
			return new ResponseEntity<Resort>(HttpStatus.BAD_REQUEST);
		}
	}

	/* Dining */

	@PostMapping("/dining/{guestID}")
	public ResponseEntity<Dining> bookDining(@RequestBody Dining dining, @PathVariable long guestID) {
		LOGGER.debug("BookingController:Debugging bookDining method");
		LOGGER.info("Executing Dining Booking for a Guest.");
		Dining bookDining = bookingService.bookDining(dining, guestID);
		if (bookDining != null) {
			LOGGER.info("BookingController:	Dining Booked for a Guest.");
			return new ResponseEntity<Dining>(bookDining, HttpStatus.CREATED);
		} else {
			LOGGER.warn("BookingController:	Dining not booked for a Guest.");
			return new ResponseEntity<Dining>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/dining/{diningBookingId}")
	public ResponseEntity<Dining> updateBookedDining(@RequestBody Dining dining, @PathVariable long diningBookingId) {
		LOGGER.debug("BookingController:Debugging updateBookedDining method");
		LOGGER.info("BookingController:Updating Dining Booking for a Guest.");
		Dining d = bookingService.updateBookedDining(dining, diningBookingId);
		if (d != null) {
			LOGGER.info("BookingController:Booked Resort for a Guest is updated.");
			return new ResponseEntity<Dining>(d, HttpStatus.CREATED);
		} else {
			LOGGER.warn("BookingController:Booked Dining for a Guest is not updated.");
			return new ResponseEntity<Dining>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancel/dining/{diningBookingId}")
	public ResponseEntity<Dining> cancelBookedDining(@PathVariable long diningBookingId) {
		LOGGER.debug("BookingController:Debugging cancelBookedDining method");
		LOGGER.info("BookingController:Cancelling Dining Booking for a Guest.");
		Dining d = bookingService.cancelBookedDining(diningBookingId);
		if (d != null) {
			LOGGER.info("BookingController:Cancelled Dining Booking for a Guest.");
			return new ResponseEntity<Dining>(d, HttpStatus.OK);
		} else {
			LOGGER.error("BookingController:Booked Dining for a Guest is not cancelled.");
			return new ResponseEntity<Dining>(HttpStatus.BAD_REQUEST);
		}
	}
}
