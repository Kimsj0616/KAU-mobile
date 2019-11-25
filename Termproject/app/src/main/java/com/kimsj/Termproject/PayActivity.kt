package com.kimsj.Termproject;


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kr.co.bootpay.*
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG

class PayActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener
{
    var price = 0
    var stuck = 10;
    var re: SwipeRefreshLayout?=null;
    var b:Button?=null;
    override fun onRefresh() {
        b!!.setText("바뀌었다");

        re!!.setRefreshing(false);

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay);
        re = findViewById(R.id.Refresh);
        re!!.setOnRefreshListener(this);
        b=findViewById(R.id.button1);
        // 초기설정 - 해당 프로젝트(안드로이드)의 application id 값을 설정합니다. 결제와 통계를 위해 꼭 필요합니다.
        BootpayAnalytics.init(this, "5cbc1852b6d49c0a8f7825a2");

        if(intent.hasExtra("totalprice")){
            price = intent.getIntExtra("totalprice", 0)
        }
    }

   fun onClick_request( v:View) {
    //        결제호출
     var m:Method?=null ;
   when(v.getId())
    {

       R.id.button1->
        {
            m=Method.CARD;

        }
       R.id.button2->
        {

            m=Method.BANK;

        }
       R.id.button3->
        {

            m=Method.VBANK;
        }



    }


    Bootpay.init(getFragmentManager())
        .setApplicationId("5cbc1852b6d49c0a8f7825a2") // 해당 프로젝트(안드로이드)의 application id 값
        .setPG(PG.INICIS) // 결제할 PG 사
        .setUserPhone("010-5119-9508") // 구매자 전화번호
        .setMethod(m!!) // 결제수단
        .setName("ORDERED APPLICATION") // 결제할 상품명
        .setOrderId("1234") //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
        .setPrice(price) // 결제할 금액
        //.setAccountExpireAt("2018-09-22") // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. 오늘 날짜보다 더 뒤(미래)여야 합니다 )
        .addItem("마우스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
        .onConfirm(object : ConfirmListener {
            override fun onConfirm(message: String?) {
                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                Log.d("confirm", message);

            }
        })
        .onDone(object : DoneListener {
            override fun onDone(message: String?) {

                Toast.makeText(this@PayActivity,"거래완료",Toast.LENGTH_SHORT).show();
                Log.d("done", message);
            }
        })
        .onReady(object : ReadyListener {
            override fun onReady(message: String?) {

                Log.d("ready", message);
            }
        })
        .onCancel(object : CancelListener {
            override fun onCancel(message: String?) {

                Log.d("cancel", message);
            }
        })
        .onError(object : ErrorListener {
            override fun onError(message: String?) {

                Log.d("error", message);
            }
        })
        .onClose(object : CloseListener {
            override fun onClose(message: String?) {
                Log.d("close", "close");

            }
        }).show()







    }

}