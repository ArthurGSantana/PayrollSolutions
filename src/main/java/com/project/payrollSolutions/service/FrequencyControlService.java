package com.project.payrollSolutions.service;

import com.project.payrollSolutions.model.FrequencyControl;
import com.project.payrollSolutions.repository.FrequencyControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrequencyControlService {

    private final FrequencyControlRepository frequencyControlRepository;

    @Autowired
    public FrequencyControlService(FrequencyControlRepository frequencyControlRepository) {
        this.frequencyControlRepository = frequencyControlRepository;
    }

//    public FrequencyControl createFrequencyControl() {
//
//    }
}
