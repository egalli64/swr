package com.example.swr.s8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s8")
public class CoderPatchCtrl {
    private static final Logger log = LogManager.getLogger(CoderPatchCtrl.class);

    private CoderRepo repo;

    public CoderPatchCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @PatchMapping("/coders/{id}")
    public Coder partialUpdate(@RequestBody Coder newer, @PathVariable Integer id) {
        log.trace(String.format("partialUpdate coder %d by %s", id, newer));
        return repo.findById(id).map(coder -> {
            if (newer.getFirstName() != null) {
                coder.setFirstName(newer.getFirstName());
            }
            if (newer.getLastName() != null) {
                coder.setLastName(newer.getLastName());
            }
            if (newer.getHireDate() != null) {
                coder.setHireDate(newer.getHireDate());
            }
            if (newer.getSalary() != 0.0) {
                coder.setSalary(newer.getSalary());
            }
            return repo.save(coder);
        }).orElseThrow( //
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id)));
    }
}
