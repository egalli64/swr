package com.example.swr.s11;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s08.Coder;
import com.example.swr.s08.CoderRepo;

@RestController
public class CoderGetCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderGetCtrl.class);

    private CoderRepo repo;

    public CoderGetCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/s11/coders")
    public List<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }

    @GetMapping("/s11/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.trace("get " + id);

        /*
         * !! Unsatisfactory approach !!
         * 
         * When id is missing, user get a 500 status instead of 404
         * 
         * See a better solution in the next example
         */
        return repo.findById(id).orElseThrow();
    }
}
