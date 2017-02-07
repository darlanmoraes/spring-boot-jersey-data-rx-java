package com.boot.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * Created by darlan on 07/02/17.
 */
@Component
public class PersonsImpl implements Persons {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void list(@Suspended AsyncResponse res) {
        Observable.create(s -> {
            try {
                s.onNext(personRepository.findAll());
            } catch (Exception e) {
                s.onError(e);
            }
            s.onCompleted();
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe(res::resume, res::resume);
    }

    @Override
    public void post(@Suspended AsyncResponse res,
                     final Person person) {
        Observable.create(s -> {
            try {
                s.onNext(personRepository.save(person));
            } catch (Exception e) {
                s.onError(e);
            }
            s.onCompleted();
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe(res::resume, res::resume);
    }

}
