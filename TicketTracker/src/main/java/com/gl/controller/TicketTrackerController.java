package com.gl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.entity.TicketTracker;
import com.gl.service.TicketTrackerService;

@Controller
@RequestMapping("/ticketTracker")
public class TicketTrackerController {

	private TicketTrackerService ticketTrackerService;
	
	public TicketTrackerController(TicketTrackerService theemployeeService) {
		ticketTrackerService = theemployeeService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listticketTracker(Model theModel) {
		
		// get employee from database
		List<TicketTracker> theTicketTracker = ticketTrackerService.findAll();
		
		// add to the spring model
		theModel.addAttribute("ticketTracker", theTicketTracker);
		
		return "ticketTracker/list-ticketTracker";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		TicketTracker theTicketTracker = new TicketTracker();
		
		theModel.addAttribute("ticketTracker", theTicketTracker);
		
		return "ticketTracker/ticketTracker_form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("ticketTrackerId") int theId,
									Model theModel) {
		
		// get the Employee from the service
		TicketTracker theTicketTracker = ticketTrackerService.findById(theId);
		
		// set employee as a model attribute 
		theModel.addAttribute("ticketTracker", theTicketTracker);
		
		// send over to our form
		return "ticketTracker/ticketTracker_form";			
	}
	
	
	@PostMapping("/save")
	public String saveTicketTracker(@ModelAttribute("ticketTracker") TicketTracker theTicketTracker) {
		
		// save the ticketTracker
		ticketTrackerService.save(theTicketTracker);
		
		return "redirect:/ticketTracker/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("ticketTrackerId") int theId) {
		
		// delete the ticketTracker
		ticketTrackerService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/ticketTracker/list";
		
	}
}


















