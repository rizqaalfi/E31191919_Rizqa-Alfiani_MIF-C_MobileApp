package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

//	Mendeklarasikan variable
	private EditText mViewUser, mViewPassword;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);

//	Menginisialisasikan variable sesuai dengan id pada layout pada activity_login.xml
	mViewUser = findViewById(R.id.et_emailSignin);
	mViewPassword = findViewById(R.id.et_passwordSignin);

//	Menjalankan method razia() jika tombol sign in di keyboard di sentuh
	mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener(){
		@Override

//		EditorInfo menjelaskan beberapa atribut objek pengeditan teks yang berkomunikasi
//		dengan metode masukan (biasanya EditText), yang terpenting adalah jenis konten teks
//		yang dikandungnya dan posisi kursor saat ini. ( menggunakan enter )
		public boolean onEditorAction ( TextView v, int actionId, KeyEvent event){
			if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
				razia();
				return true;
			}
			return false;
		}
	});

// Menjalankan method razia() jika tombol sign in di klik
	findViewById(R.id.button_signinSignup).setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v){
			razia();
		}
	});

//	Beralih ke registerActivity jika tombol sign up di klik
	findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v){
			startActivity(new Intent( getBaseContext(), RegisterActivity.class));
		}
	});
}

// ke main activity jika data status login dari data preferences bernilai true
@Override
protected void onStart(){
	super.onStart();
	if (Preferences.getLoggedInStatus(getBaseContext())){
		startActivity(new Intent( getBaseContext(), MainActivity.class));
		finish();
	}
}

// cek inputan user dan password -> memberi akses ke mainActivity
	private void razia(){
//	Mereset semua error dan fokus menjadi default
		mViewUser.setError(null);
		mViewPassword.setError(null);
		View fokus = null;
		boolean cancel = false;

// Mengambil text dari form user dan pass dengan varible baru
		String user = mViewUser.getText().toString();
		String password = mViewPassword.getText().toString();

//		Jika form user kosong atau tidak memenuhi kriteria di method cekUser(),
//		Maka set error di form user dengan mereset varible fokus dan error di viewnya.
//		Juuga menjadikan cancel = true
		if(TextUtils.isEmpty(user)){
			mViewUser.setError("This field is required");
			fokus = mViewUser;
			cancel = true;
		}else if(!cekUser(user)){
			mViewUser.setError("This Username is not found");
			fokus = mViewUser;
			cancel = true;
		}

//		sama seperti pada pengecekan user
		if(TextUtils.isEmpty(password)){
			mViewPassword.setError("This field is required");
			fokus = mViewPassword;
			cancel = true;
		}else if(!cekPassword(password)){
			mViewPassword.setError("This password is incorrect");
			fokus = mViewPassword;
			cancel = true;
		}

//		jika cancel bernilai true, maka varible fokus akan mendapatkan fokus
//		jika bernilai false, maka akan menjalankan method masuk()
		if(cancel) fokus.requestFocus();
		else masuk();
	}

//	menuju mainActivity dan set user dan status sedang login, di Preferences
	private void masuk(){
		Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
		Preferences.setLoggedInStatus(getBaseContext(),true);
		startActivity(new Intent(getBaseContext(),MainActivity.class));
		finish();
	}

//	bernilai true, jika parameter pass sama dengan data pass di preferences
	private boolean cekPassword( String password){
		return password.equals(Preferences.getRegisteredPass(getBaseContext()));
	}

//	bernilai true, jika parameter user sama dengan data user di preferences
	private boolean cekUser( String user){
		return user.equals(Preferences.getRegisteredUser(getBaseContext()));
	}

}