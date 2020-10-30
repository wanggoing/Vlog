package com.android.girish.vlog.chatheads.chatheads;

import java.util.ArrayList;
import java.util.List;

public class GenreDataFactory {


  public static List<VlogModel> generateLogs() {
    ArrayList<VlogModel> logDataList = new ArrayList<>();
    int priority = VlogModel.VERBOSE;
    String tag = "";
    String logMessage = "";
    for (int i = 0; i < 30; i++) {
      if (i % 7 == 0) {
        priority = VlogModel.WARN;
        tag = "##7##" + i;
        logMessage = "We are adding this warning log for " + i + "th index "
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................";
      } else if (i % 5 == 0) {
        priority = VlogModel.INFO;
        tag = "##5##" + i;
        logMessage = "We are adding this info log for " + i + "th index "
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................";
      } else if (i % 3 == 0) {
        priority = VlogModel.ERROR;
        tag = "##3##" + i;
        logMessage = "We are adding this error log for " + i + "th index "
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................";
      } else if (i % 2 == 0) {
        priority = VlogModel.DEBUG;
        tag = "##2##" + i;
        logMessage = "We are adding this debug log for " + i + "th index "
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................";
      } else {
        priority = VlogModel.VERBOSE;
        tag = "#####" + i;
        logMessage = "We are adding this Verbose log for " + i + "th index "
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................"
                + "......................";
      }
      VlogModel vlog = new VlogModel(priority, tag, logMessage);
      logDataList.add(vlog);
    }
    return logDataList;
  }

}

