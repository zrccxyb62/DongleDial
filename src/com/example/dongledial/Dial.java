package com.example.dongledial;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dial extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10; /** *号 */
	private Button button11; /** 0 */
	private Button button12; /** # */
	private Button button13; /** 拨号 */
	private Button button14; /** 挂断 */
	private Button button15; /** X */
	private EditText editText1;
	private TextView textView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dial);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button6 = (Button)findViewById(R.id.button6);
		button7 = (Button)findViewById(R.id.button7);
		button8 = (Button)findViewById(R.id.button8);
		button9 = (Button)findViewById(R.id.button9);
		button10 = (Button)findViewById(R.id.button10);
		button11 = (Button)findViewById(R.id.button11);
		button12 = (Button)findViewById(R.id.button12);
		button13 = (Button)findViewById(R.id.button13);
		button14 = (Button)findViewById(R.id.button14);
		button15 = (Button)findViewById(R.id.button15);
		editText1 = (EditText)findViewById(R.id.editText1);
		textView2 = (TextView)findViewById(R.id.textView2);
		
		editText1.clearFocus(); //删除焦点，不弹出键盘
		
		button1.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("1");

            }         

        });
		
		button2.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("2");

            }         

        });
		
		button3.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("3");

            }         

        });
		
		button4.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("4");

            }         

        });
		
		button5.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("5");

            }         

        });
		
		button6.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("6");

            }         

        });
		
		button7.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("7");

            }         

        });
		
		button8.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("8");

            }         

        });
		
		button9.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("9");

            }         

        });
		
		button10.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("*");

            }         

        });
		
		button11.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("0");

            }         

        });
		
		button12.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                editText1.append("#");

            }         

        });
		
		button13.setOnClickListener(new Button.OnClickListener(){ 
			Call callno = new Call();
            @Override

            public void onClick(View v) {
            	int no = 0;
            	if(editText1.getText().toString().length()>0){
            		no = Integer.parseInt(editText1.getText().toString().trim());
            		callno.callnumber(no);
            		textView2.setText("");
            	}
            	else
            		textView2.setText("号码不为空");
            	
            }         

        });
		
		button14.setOnClickListener(new Button.OnClickListener(){ 
			Call callno = new Call();
            @Override

            public void onClick(View v) {
            	
            	int re = callno.callnumber(0);
            }         

        });
		
		button15.setOnClickListener(new Button.OnClickListener(){ 

            @Override

            public void onClick(View v) {

            	int index=editText1.getSelectionStart();   //获取Edittext光标所在位置  
            	String str=editText1.getText().toString();  
            	if (!str.equals("")) 
            		editText1.getText().delete(index-1,index);  
            }         

        });
		
		button15.setOnLongClickListener(new Button.OnLongClickListener(){
			
			@Override
			
			public boolean onLongClick(View v){
				editText1.setText("");
				return false;
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dial, menu);
		return true;
	}

}
