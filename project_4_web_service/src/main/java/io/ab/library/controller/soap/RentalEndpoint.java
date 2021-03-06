package io.ab.library.controller.soap;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.ab.library.controller.soap.request.GetRentalByIdRequest;
import io.ab.library.controller.soap.request.GetRentalsByUserRequest;
import io.ab.library.controller.soap.request.UpdateRentalRequest;
import io.ab.library.controller.soap.response.GetAllRentalsResponse;
import io.ab.library.controller.soap.response.GetRentalByIdResponse;
import io.ab.library.controller.soap.response.GetRentalsByUserResponse;
import io.ab.library.controller.soap.response.UpdateRentalResponse;
import io.ab.library.service.impl.RentalServiceImpl;

@Endpoint
public class RentalEndpoint {
	private static final String NAMESPACE_URI = "http://ab.io/library";

	@Autowired
	private RentalServiceImpl rentalService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRentalsByUserRequest")
	@ResponsePayload
	public GetRentalsByUserResponse getRentalByUser(@RequestPayload GetRentalsByUserRequest request) {
		GetRentalsByUserResponse response = new GetRentalsByUserResponse();
		System.out.println("processing GetRentalsByUserRequest");

		this.rentalService.findByUser(request.getId().intValue()).forEach(rental -> {
			response.getRentals().add(rental);
		});

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateRentalRequest")
	@ResponsePayload
	public UpdateRentalResponse updateRental(@RequestPayload UpdateRentalRequest request) {
		UpdateRentalResponse response = new UpdateRentalResponse();
		System.out.println("processing updateRentalRequest");

		response.setRental(this.rentalService.update(request.getRental()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllRentalsRequest")
	@ResponsePayload
	public GetAllRentalsResponse getAllRentals() {
		System.out.println("processing getAllRentalsRequest");

		GetAllRentalsResponse response = new GetAllRentalsResponse();
		this.rentalService.findAll().forEach(rental -> {
			response.getRentals().add(rental);
		});

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRentalByIdRequest")
	@ResponsePayload
	public GetRentalByIdResponse getOne(@RequestPayload GetRentalByIdRequest request) {
		System.out.println("processing getRentalByIdRequest");

		GetRentalByIdResponse response = new GetRentalByIdResponse();
		response.setRental(
				this.rentalService.findOne(request.getAccountId().intValue(), request.getBookId().intValue()));

		return response;
	}
}
