package com.example.recipely.Listeners;

import com.example.recipely.Models.InstructionResponse;

import java.util.List;

public interface instructionListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
