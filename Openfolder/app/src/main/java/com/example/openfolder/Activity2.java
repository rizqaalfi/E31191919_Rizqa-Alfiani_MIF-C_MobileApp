package com.example.openfolder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_2);

	Button button3 = (Button) findViewById(R.id.button3);
	button3.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			openActivity3();
		}
	});
}

public void openActivity3(){
	Intent intent = new Intent(this, Activity3.class);
	startActivity(intent);
}
}