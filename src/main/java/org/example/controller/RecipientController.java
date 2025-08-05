package org.example.controller;

import org.example.model.Recipient;
import org.example.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipients")
public class RecipientController {
    @Autowired
    private RecipientService recipientService;

    @PostMapping("/add")
    public Recipient addRecipient(@RequestBody Recipient recipient) {
        return recipientService.addRecipient(recipient);
    }

    @PostMapping("/search")
    public List<Recipient> searchRecipient(@RequestBody Recipient criteria) {
        return recipientService.searchRecipient(criteria);
    }
}

