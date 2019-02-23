package io.jenkins.plugins;

import hudson.Extension;
import hudson.model.RestartListener;
import hudson.model.Computer;
import hudson.model.Executor;

import jenkins.model.Jenkins;

import java.io.IOException;

public class UninterruptedPipelines {

    @Extension
    public static class UninterruptedPipelinesListener extends RestartListener {

        @Override
        public boolean isReadyToRestart() throws IOException, InterruptedException {
            for (Computer c : Jenkins.getInstance().getComputers()) {
                if (c.isOnline()) {
                    for (Executor e : c.getAllExecutors()) {

                        long idleMillis = System.currentTimeMillis() -
                            e.getIdleStartMilliseconds();

                        if (!e.isIdle() || idleMillis < 15000) {
                            return false;
                        }

                    }
                }
            }
            return true;
        }

    }

}
