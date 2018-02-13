// Generated code from Butter Knife. Do not modify!
package com.example.toshiba.vhealth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.mikephil.charting.charts.LineChart;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisualitationActivity_ViewBinding implements Unbinder {
  private VisualitationActivity target;

  @UiThread
  public VisualitationActivity_ViewBinding(VisualitationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisualitationActivity_ViewBinding(VisualitationActivity target, View source) {
    this.target = target;

    target.clv = Utils.findRequiredViewAsType(source, R.id.clv, "field 'clv'", TextView.class);
    target.chartClv = Utils.findRequiredViewAsType(source, R.id.chart_clv, "field 'chartClv'", LineChart.class);
    target.ct = Utils.findRequiredViewAsType(source, R.id.ct, "field 'ct'", TextView.class);
    target.chartCt = Utils.findRequiredViewAsType(source, R.id.chart_ct, "field 'chartCt'", LineChart.class);
    target.imp = Utils.findRequiredViewAsType(source, R.id.imp, "field 'imp'", TextView.class);
    target.chartImp = Utils.findRequiredViewAsType(source, R.id.chart_imp, "field 'chartImp'", LineChart.class);
    target.rpm = Utils.findRequiredViewAsType(source, R.id.rpm, "field 'rpm'", TextView.class);
    target.chartERPM = Utils.findRequiredViewAsType(source, R.id.chart_eRPM, "field 'chartERPM'", LineChart.class);
    target.speed = Utils.findRequiredViewAsType(source, R.id.speed, "field 'speed'", TextView.class);
    target.chartVs = Utils.findRequiredViewAsType(source, R.id.chart_vs, "field 'chartVs'", LineChart.class);
    target.iat = Utils.findRequiredViewAsType(source, R.id.iat, "field 'iat'", TextView.class);
    target.chartIat = Utils.findRequiredViewAsType(source, R.id.chart_iat, "field 'chartIat'", LineChart.class);
    target.maf = Utils.findRequiredViewAsType(source, R.id.maf, "field 'maf'", TextView.class);
    target.chartMAF = Utils.findRequiredViewAsType(source, R.id.chart_MAF, "field 'chartMAF'", LineChart.class);
    target.txtName1 = Utils.findRequiredViewAsType(source, R.id.txt_name1, "field 'txtName1'", TextView.class);
    target.txtColon1 = Utils.findRequiredViewAsType(source, R.id.txt_colon1, "field 'txtColon1'", TextView.class);
    target.txtValue1 = Utils.findRequiredViewAsType(source, R.id.txt_value1, "field 'txtValue1'", TextView.class);
    target.txtName2 = Utils.findRequiredViewAsType(source, R.id.txt_name2, "field 'txtName2'", TextView.class);
    target.txtColon2 = Utils.findRequiredViewAsType(source, R.id.txt_colon2, "field 'txtColon2'", TextView.class);
    target.txtValue2 = Utils.findRequiredViewAsType(source, R.id.txt_value2, "field 'txtValue2'", TextView.class);
    target.txtName3 = Utils.findRequiredViewAsType(source, R.id.txt_name3, "field 'txtName3'", TextView.class);
    target.txtColon3 = Utils.findRequiredViewAsType(source, R.id.txt_colon3, "field 'txtColon3'", TextView.class);
    target.txtValue3 = Utils.findRequiredViewAsType(source, R.id.txt_value3, "field 'txtValue3'", TextView.class);
    target.txtName4 = Utils.findRequiredViewAsType(source, R.id.txt_name4, "field 'txtName4'", TextView.class);
    target.txtColon4 = Utils.findRequiredViewAsType(source, R.id.txt_colon4, "field 'txtColon4'", TextView.class);
    target.txtValue4 = Utils.findRequiredViewAsType(source, R.id.txt_value4, "field 'txtValue4'", TextView.class);
    target.txtName5 = Utils.findRequiredViewAsType(source, R.id.txt_name5, "field 'txtName5'", TextView.class);
    target.txtColon5 = Utils.findRequiredViewAsType(source, R.id.txt_colon5, "field 'txtColon5'", TextView.class);
    target.txtValue5 = Utils.findRequiredViewAsType(source, R.id.txt_value5, "field 'txtValue5'", TextView.class);
    target.txtName6 = Utils.findRequiredViewAsType(source, R.id.txt_name6, "field 'txtName6'", TextView.class);
    target.txtColon6 = Utils.findRequiredViewAsType(source, R.id.txt_colon6, "field 'txtColon6'", TextView.class);
    target.txtValue6 = Utils.findRequiredViewAsType(source, R.id.txt_value6, "field 'txtValue6'", TextView.class);
    target.txtName7 = Utils.findRequiredViewAsType(source, R.id.txt_name7, "field 'txtName7'", TextView.class);
    target.txtColon7 = Utils.findRequiredViewAsType(source, R.id.txt_colon7, "field 'txtColon7'", TextView.class);
    target.txtValue7 = Utils.findRequiredViewAsType(source, R.id.txt_value7, "field 'txtValue7'", TextView.class);
    target.txtName8 = Utils.findRequiredViewAsType(source, R.id.txt_name8, "field 'txtName8'", TextView.class);
    target.txtColon8 = Utils.findRequiredViewAsType(source, R.id.txt_colon8, "field 'txtColon8'", TextView.class);
    target.txtValue8 = Utils.findRequiredViewAsType(source, R.id.txt_value8, "field 'txtValue8'", TextView.class);
    target.txtName9 = Utils.findRequiredViewAsType(source, R.id.txt_name9, "field 'txtName9'", TextView.class);
    target.txtColon9 = Utils.findRequiredViewAsType(source, R.id.txt_colon9, "field 'txtColon9'", TextView.class);
    target.txtValue9 = Utils.findRequiredViewAsType(source, R.id.txt_value9, "field 'txtValue9'", TextView.class);
    target.txtName10 = Utils.findRequiredViewAsType(source, R.id.txt_name10, "field 'txtName10'", TextView.class);
    target.txtColon10 = Utils.findRequiredViewAsType(source, R.id.txt_colon10, "field 'txtColon10'", TextView.class);
    target.txtValue10 = Utils.findRequiredViewAsType(source, R.id.txt_value10, "field 'txtValue10'", TextView.class);
    target.txtName11 = Utils.findRequiredViewAsType(source, R.id.txt_name11, "field 'txtName11'", TextView.class);
    target.txtColon11 = Utils.findRequiredViewAsType(source, R.id.txt_colon11, "field 'txtColon11'", TextView.class);
    target.txtValue11 = Utils.findRequiredViewAsType(source, R.id.txt_value11, "field 'txtValue11'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisualitationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clv = null;
    target.chartClv = null;
    target.ct = null;
    target.chartCt = null;
    target.imp = null;
    target.chartImp = null;
    target.rpm = null;
    target.chartERPM = null;
    target.speed = null;
    target.chartVs = null;
    target.iat = null;
    target.chartIat = null;
    target.maf = null;
    target.chartMAF = null;
    target.txtName1 = null;
    target.txtColon1 = null;
    target.txtValue1 = null;
    target.txtName2 = null;
    target.txtColon2 = null;
    target.txtValue2 = null;
    target.txtName3 = null;
    target.txtColon3 = null;
    target.txtValue3 = null;
    target.txtName4 = null;
    target.txtColon4 = null;
    target.txtValue4 = null;
    target.txtName5 = null;
    target.txtColon5 = null;
    target.txtValue5 = null;
    target.txtName6 = null;
    target.txtColon6 = null;
    target.txtValue6 = null;
    target.txtName7 = null;
    target.txtColon7 = null;
    target.txtValue7 = null;
    target.txtName8 = null;
    target.txtColon8 = null;
    target.txtValue8 = null;
    target.txtName9 = null;
    target.txtColon9 = null;
    target.txtValue9 = null;
    target.txtName10 = null;
    target.txtColon10 = null;
    target.txtValue10 = null;
    target.txtName11 = null;
    target.txtColon11 = null;
    target.txtValue11 = null;
  }
}
