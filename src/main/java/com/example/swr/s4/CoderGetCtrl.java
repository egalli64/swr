package com.example.swr.s4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.dao.Coder;
import com.example.swr.dao.CoderRepo;

@RestController
@RequestMapping("/s4")
public class CoderGetCtrl {
    private static final Logger log = LogManager.getLogger(CoderGetCtrl.class);

    private CoderRepo repo;

    public CoderGetCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/coders")
    public Iterable<Coder> getAll() {
        log.traceEntry("getAll");
        return repo.findAll();
    }

    @GetMapping("/coders/{id}")
    public Coder get(@PathVariable Integer id) {
        log.traceEntry("get " + id);

        /*
         * !! Unsatisfactory approach !!
         * 
         * When there is no coder with the passed id, user get a 500 status instead of 404
         * 
         * See a better solution in the next example
         */
        return repo.findById(id).orElseThrow();
    }
}
