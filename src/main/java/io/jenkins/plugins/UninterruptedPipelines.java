package io.jenkins.plugins;

import hudson.Extension;
import hudson.model.RestartListener;
import hudson.model.Computer;

import jenkins.model.Jenkins;

import java.io.IOException;

public class UninterruptedPipelines {

    @Extension
    public static class UninterruptedPipelinesListener extends RestartListener {

        @Override
        public boolean isReadyToRestart() throws IOException, InterruptedException {
            for (Computer c : Jenkins.getInstance().getComputers()) {
                if (c.isOnline() && c.countBusy() > 0) {
                    return false;
                }
            }
            return true;
        }

    }

}
