package com.ajdi.xyzreader.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Yassin Ajdi
 * @since 4/16/2019.
 */
public class AppExecutors {

  private static final int THREAD_COUNT = 5;
  private static volatile AppExecutors sInstance;
  private final Executor diskIO;

  private final Executor mainThread;

  private final Executor networkIO;

  public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
    this.diskIO = diskIO;
    this.networkIO = networkIO;
    this.mainThread = mainThread;
  }

  public static AppExecutors getInstance() {
    if (sInstance == null) {
      synchronized (AppExecutors.class) {
        if (sInstance == null) {
          sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            new MainThreadExecutor());
        }
      }
    }
    return sInstance;
  }

  public Executor diskIO() {
    return diskIO;
  }

  public Executor mainThread() {
    return mainThread;
  }

  public Executor networkIO() {
    return networkIO;
  }

  public static class MainThreadExecutor implements Executor {
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable command) {
      mainThreadHandler.post(command);
    }
  }
}
