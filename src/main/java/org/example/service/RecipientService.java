package org.example.service;

import org.example.config.MatchCriteriaConfig;
import org.example.model.Recipient;
import org.example.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipientService {
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private MatchCriteriaConfig matchCriteriaConfig;

    public Recipient addRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    public List<Recipient> searchRecipient(Recipient criteria) {
        List<Recipient> all = recipientRepository.findAll();
        Map<String, Integer> weights = matchCriteriaConfig.getWeights();
        return all.stream()
                .filter(r -> matchScore(r, criteria, weights) > 0)
                .sorted((r1, r2) -> Integer.compare(
                        matchScore(r2, criteria, weights),
                        matchScore(r1, criteria, weights)))
                .collect(Collectors.toList());
    }

    private int matchScore(Recipient r, Recipient c, Map<String, Integer> weights) {
        int score = 0;
        if (Objects.equals(r.getFirstName(), c.getFirstName())) score += weights.getOrDefault("firstName", 0);
        if (Objects.equals(r.getLastName(), c.getLastName())) score += weights.getOrDefault("lastName", 0);
        if (Objects.equals(r.getEmail(), c.getEmail())) score += weights.getOrDefault("email", 0);
        if (Objects.equals(r.getPhone(), c.getPhone())) score += weights.getOrDefault("phone", 0);
        if (Objects.equals(r.getAddress(), c.getAddress())) score += weights.getOrDefault("address", 0);
        if (Objects.equals(r.getCity(), c.getCity())) score += weights.getOrDefault("city", 0);
        if (Objects.equals(r.getZip(), c.getZip())) score += weights.getOrDefault("zip", 0);
        return score;
    }
}

