package com.example.leave_unused_publishment.Common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Global {
  public static int height=0;
  public static int width=0;
  public static void MeasureListview(ListView listView) {    
      ListAdapter listAdapter = listView.getAdapter();    
      
      if (listAdapter == null) {    
          return;    
      }    
      int totalHeight = 0;    
      for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()�������������Ŀ    
          View listItem = listAdapter.getView(i, null, listView);    
          listItem.measure(0, 0); // ��������View �Ŀ��    
          totalHeight += listItem.getMeasuredHeight(); // ͳ������������ܸ߶�    
      }    
      ViewGroup.LayoutParams params = listView.getLayoutParams();    
      params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
      listView.setLayoutParams(params);    
  } 
}
