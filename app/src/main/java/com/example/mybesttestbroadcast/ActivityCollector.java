package com.example.mybesttestbroadcast;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜松阳 on 2018/10/20.
 * 通过该类来管理所有的活动，例如关闭所有的Activity。
 * 在该类中存在addActivity()、removeActivity()、finishAll()。
 */

public class ActivityCollector {
   public static List<Activity> activities = new ArrayList<>();

   public static void addActivity(Activity activity){
       activities.add(activity);
   }

   public static void removeActivity(Activity activity){
       activities.remove(activity);
   }

   public static void finishAll(){
       for (Activity activity : activities){
           if (!activity.isFinishing()){
               activity.finish();
           }
       }
       activities.clear();
   }
}
