package com.example.leave_unused_publishment.Common;

import com.example.leave_unused_publishment.FirstPageActivity;
import com.example.leave_unused_publishment.PublishActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Global {
  public static int height=0;
  public static int width=0;
  public static PublishActivity pubactivity=null;
  public static FirstPageActivity firstpageactivity=null;
  public static String zone[]={"ȫ��","��ɽ��ѧ","�㶫������ó��ѧ","����ʦ����ѧ","��������ѧ","�Ǻ�����ѧԺ","��������ѧԺ","���ݹ�ҵ��ѧ","���ݴ�ѧ","������ҽҩ��ѧ","�㶫ҩѧԺ"};
  public static String group[]={"�鼮","���õ���","������Ʒ","���г�","�ֻ�","����","�������","����"};
  public static String sort[]={"Ĭ������","���·���","�۸����","�۸����"};
  public static String select[]={"0-50","50-100","100-200","200����"};
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
