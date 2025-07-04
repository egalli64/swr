package com.example.swr.s9;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s9")
public class CoderDeleteCtrl {
    private static final Logger log = LogManager.getLogger(CoderDeleteCtrl.class);

    private CoderRepo repo;

    public CoderDeleteCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @DeleteMapping("/coders/{id}")
    public void delete(@PathVariable Integer id) {
        log.trace("delete " + id);
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Coder %d not found", id), ex);
        }
    }
}
