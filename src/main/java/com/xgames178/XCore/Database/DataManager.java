package com.xgames178.XCore.Database;

import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.libs.jline.internal.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by jpdante on 02/05/2017.
 */
public class DataManager {
    private final DataProvider dataProvider;
    private final Thread worker;
    private final BlockingQueue<DataTask> tasks = new PriorityBlockingQueue<DataTask>();
    private final Object TASK_LOCK = new Object();

    public DataManager(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        this.worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Runnable nextJob = tasks.take();
                        synchronized (TASK_LOCK) {
                            nextJob.run();
                        }
                    } catch (InterruptedException e) {
                        // the thread was interrupted while we are waiting for more work, which doesn't seem to be there currently -> let's finish then
                        break;
                    }
                }
                Log.info("Database thread was successfully stopped.");
            }
        });
        this.worker.start();
    }

    public void addTask(final DataTask task) {
        Validate.notNull(task);
        if (!tasks.offer(task)) {
            throw new IllegalStateException("Database-Job-Queue seems to be full! This shouldn't happen.. :/");
        }
    }

    public void shutdown() {
        this.worker.interrupt();
        // Let's give the worker time to finish it's work before continuing shutting down:
        final long start = System.currentTimeMillis();
        while (this.worker.isAlive()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // wait max 5 seconds then shutdown anyways..:
            if (System.currentTimeMillis() - start > 5000) {
                Log.warn("Waited over 5 seconds for " + (this.tasks.size() + 1) + " remaining database tasks to complete. Shutting down anyways now..");
                break;
            }
        }
        long timeWaited = System.currentTimeMillis() - start;
        if (timeWaited > 1) Log.info("Waited " + timeWaited + "ms for the database tasks (probably stats saving) to finish.");
    }
}
