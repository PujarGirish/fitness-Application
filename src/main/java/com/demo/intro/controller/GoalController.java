package com.demo.intro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.intro.model.Goal;
import com.demo.intro.model.Userrr;
import com.demo.intro.repository.GoalRepository;
import com.demo.intro.repository.UserrrRepository;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

	@Autowired
	private UserrrRepository userrrRepository;
	
	@Autowired
	private GoalRepository goalRepository;
	
	@PostMapping
    public Goal setGoal(@RequestBody Goal goal) {
		Long userId = goal.getUserrr().getId();
	    Userrr userrr = userrrRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

	    goal.setUserrr(userrr); 
        return goalRepository.save(goal);
    }
    @GetMapping("/user/{userId}")
    public List<Goal> getGoals(@PathVariable Long userId) {
        return goalRepository.findByUserrrId(userId);
    }
	@GetMapping("/goals")
	public List<Goal> getgoalsbytargetvalue(@RequestParam int target){
	 return 	goalRepository.findAllByTarget(target);
	}
 }
