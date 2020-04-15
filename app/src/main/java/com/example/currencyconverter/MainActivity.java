package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private String input="0";
    private String output="0";
    private TextView tv_input;
    private Button btnNumber0;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;
    private Button btnDot;
    private Button btnBS;
    private Button btnCE;

    TextView tv_currency_sign1, tv_currency_sign2,tv_converter, tv_output;

    //Liệt kê các item có trong list quy đổi tiền
    List<String> list_view;
    ArrayAdapter<String> adapter;
    Spinner spin1, spin2;

    // Khởi tạo các đối tượng quy đổi
    SpinnerModel dollar = new SpinnerModel("$",23440.0);
    SpinnerModel baht = new SpinnerModel("฿",718.80);
    SpinnerModel dong = new SpinnerModel("₫",1.0);
    SpinnerModel euro = new SpinnerModel("€",2561.1);

    SpinnerModel from = dollar;
    SpinnerModel to = dong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickViews();

        list_view = new ArrayList<>();
        list_view.add("Europe - Euro");
        list_view.add("United States - Dollar");
        list_view.add("ThaiLand - Baht");
        list_view.add("VietNam - Dong");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,list_view);

        spin1 = findViewById(R.id.spinner1);
        spin1.setAdapter(adapter);

        spin2 = findViewById(R.id.spinner2);
        spin2.setAdapter(adapter);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        from = euro;
                        break;
                    case 1:
                        from = dollar;
                        break;
                    case 2:
                        from = baht;
                        break;
                    default:
                        from = dong;
                        break;
                }
                tv_currency_sign1.setText(from.symbol);
                tv_converter.setText("1 "+from.symbol + "="+ from.getRate(to) + to.symbol);
                output = String.valueOf((double)Math.floor( Double.parseDouble(input)*from.getRate(to)*100)/100);
                tv_output.setText(output);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        to = euro;
                        break;
                    case 1:
                        to = dollar;
                        break;
                    case 2:
                        to = baht;
                        break;
                    default:
                        to = dong;
                        break;
                }
                tv_currency_sign2.setText(to.symbol);
                tv_converter.setText("1 "+from.symbol + "="+ from.getRate(to) + to.symbol);
                output = String.valueOf((double)Math.floor(Double.parseDouble(input)*from.getRate(to)*100)/100);
                tv_output.setText(output);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    // Gán id vào button tương ứng
    public void initWidget() {
        tv_currency_sign1 = findViewById(R.id.currencySign1);
        tv_currency_sign2 = findViewById(R.id.currencySign2);
        tv_converter = findViewById(R.id.converter);
        tv_input = findViewById(R.id.input);
        tv_output = findViewById(R.id.output);
        btnNumber0 =  findViewById(R.id.number0);
        btnNumber1 =  findViewById(R.id.number1);
        btnNumber2 =  findViewById(R.id.number2);
        btnNumber3 = findViewById(R.id.number3);
        btnNumber4 = findViewById(R.id.number4);
        btnNumber5 =  findViewById(R.id.number5);
        btnNumber6 = findViewById(R.id.number6);
        btnNumber7 = findViewById(R.id.number7);
        btnNumber8 =  findViewById(R.id.number8);
        btnNumber9 =  findViewById(R.id.number9);
        btnDot =  findViewById(R.id.dot);
        btnBS = findViewById(R.id.backspace);
        btnCE = findViewById(R.id.CE);
    }

    // Lắng nghe các sự kiện ấn nút
    public void setEventClickViews() {
        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnBS.setOnClickListener(this);
        btnCE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Xóa tất cả số trong input
            case R.id.CE:
                input="0";
                output="0";
                break;

            // Xóa số cuối
            case R.id.backspace:
                String newNumber = deleteLastNumber(input);
                input=newNumber;
                break;

            // Bỏ số 0 ở đầu toán hạng
            default:
                if(input.equals("0")){
                    input="";
                }
                //Nối số vào cuối input
                input+=((Button)v).getText().toString();
                break;
        }
        // xu ly input
        output = String.valueOf((double)Math.floor(Double.parseDouble(input)*from.getRate(to)*100)/100);
        //output
        tv_input.setText(input);
        tv_output.setText(output);
    }
    
    // Hàm xóa số cuối
    public String deleteLastNumber(String number) {
        if(number.length()>1) {
            String temp = number.substring(0, number.length() - 1);
            return temp;
        }
        else if(number.length() == 1){
            return "0";
        }
        return input ;
    }

}
