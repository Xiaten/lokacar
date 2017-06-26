package fr.eni.ecole.jbabinot.android.tp.lokacar;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    FlowManager.init(this);
  }
}