package com.appify.pupload;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;

import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;


public class UploadService extends AsyncTask{
	
	
	//deprecated --- NO SE USA
	public static void uploadIMG() throws ClientProtocolException, IOException {
		String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
		HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

	    HttpPost httppost = new HttpPost("http://184.169.163.121:8080/rafael.carrascal/upload");
	    File file = new File(dir+"/toUpload.jpg");
	    FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");
	    httppost.setEntity(reqEntity);
	    reqEntity.setContentType("binary/octet-stream");
	    System.out.println("executing request " + httppost.getRequestLine());
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    System.out.println(response.getStatusLine());
	    
	    Log.d("Deg", "rta: "
				+ response.getEntity());
	    if (resEntity != null) {
	      System.out.println(EntityUtils.toString(resEntity));
	    }
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
		
		
	}

	@Override
	protected Object doInBackground(Object... arg0){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
		try{
		HttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

	    HttpPost httppost = new HttpPost("http://184.169.163.121:8080/rafael.carrascal/upload");
	    File file = new File(dir+"/toUpload.jpg");
	    
	    
	    
	    MultipartEntity mpEntity = new MultipartEntity();
	    ContentBody cbFile = new FileBody(file, "image/jpeg");
	    mpEntity.addPart("userfile", cbFile);


	    
	    httppost.setEntity(mpEntity);
	    //reqEntity.setContentType("binary/octet-stream");
	    System.out.println("executing request " + httppost.getRequestLine());
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();

	    System.out.println(response.getStatusLine());
	    
	    Log.d("Deg", "rta: "
				+ response.getEntity());
	    if (resEntity != null) {
	      System.out.println(EntityUtils.toString(resEntity));
	    }
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
		}catch(ClientProtocolException e){
			e.printStackTrace();
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
	    return null;
	}

}
