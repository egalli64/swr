package com.example.swr.s10;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swr.s08.Coder;
import com.example.swr.s08.CoderRepo;

@RestController
public class CoderCtorCtrl {
    private static final Logger log = LoggerFactory.getLogger(CoderCtorCtrl.class);

    private CoderRepo repo;

    @Autowired
    public CoderCtorCtrl(CoderRepo repo) {
        this.repo = repo;
    }

    // more than a ctor, the one that should be used by Spring should be autowired
    public CoderCtorCtrl(CoderRepo repo, String message) {
        log.info(message);
        this.repo = repo;
    }

    @GetMapping("/s10c/coders")
    public List<Coder> getAll() {
        log.trace("getAll");
        return repo.findAll();
    }
}
