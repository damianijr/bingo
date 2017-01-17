package com.bingo.core;

/**
 * Created by damianijr on 1/13/17.
 */
public class TimerActionExecutor {

    private static boolean executing = false;

    public static synchronized void execute(final TimerAction executor) {
        if (isExecuting()) {
            return;
        }

        final Thread executorThread = new Thread(() -> {
            try {
                executing = true;
                executor.action(Thread.currentThread());
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                executing = false;
            }
        });
        executorThread.start();
    }

    public static boolean isExecuting() {
        return executing;
    }

    public interface TimerAction {
        public void action(Thread thread) throws Exception;
    }
}
