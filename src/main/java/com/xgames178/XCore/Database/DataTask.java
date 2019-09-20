package com.xgames178.XCore.Database;

/**
 * Created by jpdante on 02/05/2017.
 */
public abstract class DataTask implements Runnable, Comparable<DataTask> {

    public enum Priority {
        HIGH(1),
        NORMAL(0),
        LOW(-1);

        private final int priority;

        private Priority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }
    }

    /*
    * Known DataTask priorities:
    * LoadAllPlayerStatsTask - HIGH
    *
    * SaveAllPlayerStatsChangesTask - NORMAL
    * SavePlayerStatsChangesTask - NORMAL
    *
    * RefreshPlayerStatsTask - LOW
    *
    *
    */

    private final Priority priority;

    public DataTask(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(DataTask otherTask) {
        return otherTask.getPriority().getPriority() - this.priority.getPriority();
    }

    @Override
    public abstract void run();

}