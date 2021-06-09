package com.example.crud_volley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.crud_volley.Util.AppController;
import com.example.crud_volley.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Delete extends AppCompatActivity{
	//pembuatan variable
	EditText deleteID;
	Button btnDelete;
	ProgressDialog pd;

	@Override
protected  void onCreate ( Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
// menginisialisasi variable dari activiti_delete
		deleteID = (EditText) findViewById(R.id.username_param);
		btnDelete = ( Button) findViewById(R.id.btn_delete);
		pd = new ProgressDialog(com.example.crud_volley.Delete.this);

// menjalankan fungsi deleteData() jika btDelete di klik
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				deleteData();
			}
		});
	}

//	method deleteData
private void deleteData()
{
	pd.setMessage("Delete Data ...");
	pd.setCancelable(false);
	pd.show();

	StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE, new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							pd.cancel();
							Log.d("volley","response : " + response.toString());
							try {
								JSONObject res = new JSONObject(response);
								Toast.makeText(com.example.crud_volley.Delete.this,"pesan : " +res.getString("message"),
												Toast.LENGTH_SHORT).show();

							} catch (JSONException e) {
								e.printStackTrace();
							}

							startActivity(new Intent(com.example.crud_volley.Delete.this,MainActivity.class));
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							pd.cancel();
							Log.d("volley", "error : " + error.getMessage());
							Toast.makeText(com.example.crud_volley.Delete.this, "pesan : gagal menghapus data",
											Toast.LENGTH_SHORT).show();
						}
					}){
		@Override
		protected Map<String, String> getParams() throws AuthFailureError {
			Map<String, String> map = new HashMap<>();
			map.put("username",deleteID.getText().toString());
			return map;
		}
	};

	AppController.getInstance().addToRequestQueue(delReq);
}

}
