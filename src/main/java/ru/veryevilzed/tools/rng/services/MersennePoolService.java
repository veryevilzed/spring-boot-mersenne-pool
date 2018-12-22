package ru.veryevilzed.tools.rng.services;

import org.apache.commons.math3.random.MersenneTwister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MersennePoolService {

    final int poolSize;

    public MersenneTwister createInstance() {
        return new MersenneTwister(new Date().getTime());
    }

    Queue<MersenneTwister> pool;

    public int size() { return pool.size(); }

    @Autowired
    public MersennePoolService(@Value("${mersenne.poolsize:100}") int poolSize) {
        this.poolSize = poolSize;
        pool = new ConcurrentLinkedQueue<>();
        for(int i=0;i<this.poolSize;i++)
            this.pool.add(this.createInstance());
    }

    public MersenneTwister get() {
        MersenneTwister item;
        if (this.pool.size() == 0)
            item = createInstance();
        else
            item = pool.poll();
        pool.add(item);
        return item;
    }

    public MersenneTwister pool() {
        if (this.pool.size() == 0)
            return createInstance();
        return this.pool.poll();
    }

    public MersenneTwister add(MersenneTwister item) {
        this.pool.add(item);
        return item;
    }
}
