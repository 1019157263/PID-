package com.mycompany.myapppid;

import android.app.*;
import android.os.*;
import android.widget.SeekBar.*;
import android.widget.*;
import java.util.*;
import org.xml.sax.*;
import java.util.ArrayList;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.charts.*;

public class MainActivity extends Activity implements OnSeekBarChangeListener
{




	@Override
	public void onProgressChanged(SeekBar p1, int p2, boolean p3)
	{
		// TODO: Implement this method
		//P=(SeekBar)findViewById(R.id.P);

		TextView textView = (TextView) findViewById(R.id.mainTextView1);


		SeekBar PP = (SeekBar) findViewById(R.id.P);
		SeekBar II = (SeekBar) findViewById(R.id.I);
		SeekBar DD = (SeekBar) findViewById(R.id.D);
		textView.setText("比例参数P:"+PP.getProgress()+"\n"+"积分参数I:"+II.getProgress()+"\n"+"微分参数D:"+DD.getProgress());
		ss = (TextView) findViewById(R.id.sss);
		ss.setText("init:"+init.getProgress()+"\n"+"input:"+input.getProgress()+"\n"+"out:"+out.getProgress());

	}

	@Override
	public void onStartTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onStopTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}


    SeekBar P;
	SeekBar I;
	SeekBar D;
	SeekBar init;
	SeekBar input;
	SeekBar out;
	TextView ss;
	@Override
    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		P=(SeekBar)findViewById(R.id.P);
		P.setMax(100);
		I=(SeekBar)findViewById(R.id.I);
		I.setMax(100);
		D=(SeekBar)findViewById(R.id.D);
		D.setMax(100);
		init=(SeekBar)findViewById(R.id.init);
		init.setMax(100);
		input=(SeekBar)findViewById(R.id.input);
		out=(SeekBar)findViewById(R.id.out);
		out.setMax(100);
		P.setOnSeekBarChangeListener(this);
		I.setOnSeekBarChangeListener(this);
		D.setOnSeekBarChangeListener(this);
		init.setOnSeekBarChangeListener(this);
		input.setOnSeekBarChangeListener(this);
		out.setOnSeekBarChangeListener(this);
		ss = (TextView) findViewById(R.id.sss);
		ss.setText("init:"+init.getProgress()+"\n"+"input:"+input.getProgress()+"\n"+"out:"+out.getProgress());

		
	    LineChart chart = (LineChart) findViewById(R.id.chart1);//绑
		List<Entry> entries = new ArrayList<>();
		for (int i = 0; i < 12; i++)
			entries.add(new Entry(i, new Random().nextInt(300)));
		LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
		
		LineData lineData = new LineData(dataSet);
		chart.setData(lineData);
		chart.invalidate(); // refresh
		
		
		
		new Thread() {
			public void run() {
				try {
					System.out.println("这是新线程");
					//P=(SeekBar)findViewById(R.id.P);
					double en=input.getProgress()-init.getProgress();
					double ek1=0;
					int ix;
					String aa;
					String bb="";
					int inta=0;
					//LineChart chart = (LineChart) findViewById(R.id.chart1);//绑
					
					List<Entry> entries = new ArrayList<>();
					while(true){

						double Kp=P.getProgress()/10;
						double Ti=I.getProgress()/10;
						double Td=D.getProgress()/10;
						double inits=init.getProgress();
						double inputs=input.getProgress();
						double outs=out.getProgress();					
						double ek=inputs-inits;
						double ekx=ek-ek1;
						LineChart chart = (LineChart) findViewById(R.id.chart1);//绑
						
					    ek1=ek;
						en=en+ek;

						double uk=(Kp*ek)+(((Kp*(outs*0.01))/Ti)*en)+((Kp*Td)/(outs*0.01))*ekx;
						sleep(10);
						//System.out.println(uk);
						ix=(int) uk;

						//System.out.println(ix);
						init.setProgress(ix/10);
						/*
						 aa=(ix/10)+"";
						 bb=bb+aa+",";
						 System.out.println(bb);
						 */
						 entries.add(new Entry(inta, ix));
						 inta=inta+1;

						LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset

						LineData lineData = new LineData(dataSet);
						chart.setData(lineData);
						chart.invalidate(); // refresh
					}
				}catch(Exception e){
					System.out.println("出错");
				}
			}	

		}.start();


    }

}
