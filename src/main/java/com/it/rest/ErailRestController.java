package com.it.rest;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.request.Passanger;
import com.it.response.Ticket;

@RestController 
public class ErailRestController {

	private Map<Integer, Ticket> tickets = new HashMap<>();
	//http://localhost:9092/ticket
	@PostMapping(value = "/ticket", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public Ticket bookTicket(@RequestBody Passanger passanger) {

		// logic to bookTicket

		Ticket t = new Ticket();
		Random r = new Random();
		int ticketId = r.nextInt();

		t.setTicketId(ticketId);
		t.setFrom(passanger.getFrom());
		t.setTo(passanger.getTo());
		t.setTktCost("1500.00 INR");
		t.setTrainNum(passanger.getTrainNum());
		t.setTicketStatus("CONFORMED");
		
		tickets.put(ticketId, t);
		return t;
	}
	
	@GetMapping(value = "/ticket/{ticketId}",
			produces = {"application/xml","application/json"}
			)
	public Ticket getTicket(@PathVariable Integer ticketId) {
		if(tickets.containsKey(ticketId)) {
			System.out.println("ticket Data: "+tickets.get(ticketId).toString());
			return tickets.get(ticketId);
		}
		return null;
	}
	@GetMapping(value = "/tickets")
	public List<Ticket> getAllTickets() {
		return new ArrayList<>(tickets.values());
	}

}
