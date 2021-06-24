package com.example.tag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity {

    EditText etAddress;
    EditText etLat, etLng;

    double lat1, lng1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etAddress=findViewById(R.id.et_address);
        etLat=findViewById(R.id.et_lat);
        etLng=findViewById(R.id.et_lng);
    }

    public void clickBtn(View view) {
        // 주소 -> 좌표 (지오코딩)
        String addr= etAddress.getText().toString();

        //지오 코딩 작업을 수행하는 객체 생성
        Geocoder geocoder= new Geocoder(this, Locale.KOREA);

        //지오코더에게 지오코딩작업 요청
        try {
            List<Address> addresses=geocoder.getFromLocationName(addr,3); //최대 3개까지 받는데, 0~3개까지 있으면 받는다.

            StringBuffer buffer= new StringBuffer();
            for(Address t : addresses){
                buffer.append(t.getLatitude()+", "+t.getLongitude()+"\n");
            }

            lat1 = addresses.get(0).getLatitude();
            lng1 = addresses.get(0).getLongitude();

            //다이얼로그로 좌표들 보여주기
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(buffer.toString()).setPositiveButton("OK",null).create().show();

        } catch (IOException e) {
            Toast.makeText(this, "검색 실패", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickBtn2(View view) {
        // 좌표 -> 주소 (역지오코딩)
        double lat=Double.parseDouble(etLat.getText().toString());
        double lng=Double.parseDouble(etLng.getText().toString());

        Geocoder geocoder= new Geocoder(this, Locale.KOREA);

        try {
            List<Address> addresses=geocoder.getFromLocation(lat,lng,3);

            StringBuffer buffer= new StringBuffer();
            for(Address t : addresses){
                buffer.append(t.getCountryName()+"\n"); //나라이름
                buffer.append(t.getPostalCode()+"\n"); //우편번호
                buffer.append(t.getAddressLine(0)+"\n"); //주소 1
                buffer.append(t.getAddressLine(1)+"\n"); //주소 2 - 없으면 null
                buffer.append(t.getAddressLine(2)+"\n"); //주소 3 - 없으면 null
                buffer.append("---------------\n");
            }

            //다이얼로그로 결과 보여주기
            new AlertDialog.Builder(this).setMessage(buffer.toString()).setPositiveButton("OK",null).create().show();
        } catch (IOException e) {
            Toast.makeText(this, "검색 실패", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickShowMap(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("geo:"+lat1+""+lng1+"?z=16"+"&q="+lat1+","+lng1+"(aaa)");
        intent.setData(uri);

        starActivity(intent);
    }

    private void starActivity(Intent intent) {
    }
}
