# Uninterrupted Pipelines

A simple plugin for Jenkins that does not permit restarting or stopping the
server until all currently executing tasks have completed.

By default, `safeExit`/`safeRestart` will allow Jenkins to interrupt
asynchronous jobs — pipelines — because in most cases, they can be resumed once
the Jenkins master is back up. However, restarting a master-only Jenkins install
results in interrupted jobs that are frequently stuck upon restart.
