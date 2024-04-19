package com.expensetracker.models.services;

import com.expensetracker.models.Repository.FeedbackRepository;
import org.bson.types.ObjectId;

public class FeedbackService {
    private final FeedbackRepository feedbackRepository = new FeedbackRepository();

    public void save(ObjectId id, String input) {
        feedbackRepository.save(id, input);
    }
}
