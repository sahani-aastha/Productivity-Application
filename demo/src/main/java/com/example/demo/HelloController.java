package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend to connect
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NoteRepository noteRepository;

    // Sign Up
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }

    // Add Task
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // Get Tasks
    @GetMapping("/tasks/{userId}")
    public List<Task> getTasks(@PathVariable int userId) {
        return taskRepository.findByUserId(userId);
    }

    // Update Task
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setIsCompleted(task.getIsCompleted());
            return taskRepository.save(existingTask);
        }
        return null;
    }

    // Delete Task
    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable int id) {
        taskRepository.deleteById(id);
        return "Task deleted successfully!";
    }

    // Add Note
    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    // Get Notes
    @GetMapping("/notes/{userId}")
    public List<Note> getNotes(@PathVariable int userId) {
        return noteRepository.findByUserId(userId);
    }
}
