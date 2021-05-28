package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

//	deklarasi dan inisialisasi label nama di layout mainAct
	TextView nama = findViewById(R.id.tv_namaMain);

//	set label nama dengan data user login dari preferences
	nama.setText(Preferences.getLoggedInUser(getBaseContext()));

//	men set status dan user yang sedang login menjadi default atau kosong di data preferences.
//	menuju ke LoginActivity
	findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v){
//			Menghapus status login dan kembali ke loginActivity
			Preferences.clearLoggedInUser(getBaseContext());
			startActivity(new Intent(getBaseContext(),LoginActivity.class));
			finish();
		}
	});
}
}