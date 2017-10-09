package com.ascend.training.j4qa.structure.services;

import com.ascend.training.j4qa.structure.entities.Toy;
import com.ascend.training.j4qa.structure.exceptions.ToyNotFoundException;
import com.ascend.training.j4qa.structure.repositories.ToyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ToyService {

    private ToyRepository toyRepository;

    public ToyService(ToyRepository toyRepository) {
        this.toyRepository = toyRepository;
    }

    public List<Toy> getToyList() {
        return toyRepository.findAll();
    }

    public Toy getToy(Long id) throws ToyNotFoundException {
        Toy t = toyRepository.findOne(id);
        if (t == null) {
            throw new ToyNotFoundException();
        }
        return t;
    }

    public void createToy(Toy toy) {
        toyRepository.save(toy);
    }

}
