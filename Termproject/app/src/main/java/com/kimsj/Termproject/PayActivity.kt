package com.kimsj.Termproject;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_pay.*
import kr.co.bootpay.*
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG

class PayActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var firebasedb: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = firebasedb.reference
    var tablenumber: Int? = null

    var menuqtt1: Int? = 0
    var menuqtt2: Int? = 0
    var menuqtt3: Int? = 0
    var menuqtt4: Int? = 0
    var menuqtt5: Int? = 0
    var totalprice: Int? = 0

    var stuck = 10
    var re: SwipeRefreshLayout? = null
    var b: Button? = null

    override fun onRefresh() {
        re!!.setRefreshing(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay);
        re = findViewById(R.id.Refresh);
        re!!.setOnRefreshListener(this);
        b = findViewById(R.id.button1)
        // 초기설정 - 해당 프로젝트(안드로이드)의 application id 값을 설정합니다. 결제와 통계를 위해 꼭 필요합니다.
        BootpayAnalytics.init(this, "5cbc1852b6d49c0a8f7825a2")

        if (intent.hasExtra("tableNo")) {
            tablenumber = intent.getIntExtra("tableNo", 0)
        }
        if (intent.hasExtra("qttmenu1")) {
            menuqtt1 = intent.getIntExtra("qttmenu1", 0)
        }
        if (intent.hasExtra("qttmenu2")) {
            menuqtt2 = intent.getIntExtra("qttmenu2", 0)
        }
        if (intent.hasExtra("qttmenu3")) {
            menuqtt3 = intent.getIntExtra("qttmenu3", 0)
        }
        if (intent.hasExtra("qttmenu4")) {
            menuqtt4 = intent.getIntExtra("qttmenu4", 0)
        }
        if (intent.hasExtra("qttmenu5")) {
            menuqtt5 = intent.getIntExtra("qttmenu5", 0)
        }
        if (intent.hasExtra("total_price")) {
            totalprice = intent.getIntExtra("total_price", 0)
        }

        card_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                var intent: Intent = Intent(this@PayActivity, Bill::class.java)

                intent.putExtra("tableNo", tablenumber!!.toInt())
                intent.putExtra("qttmenu1", menuqtt1)
                intent.putExtra("qttmenu2", menuqtt2)
                intent.putExtra("qttmenu3", menuqtt3)
                intent.putExtra("qttmenu4", menuqtt4)
                intent.putExtra("qttmenu5", menuqtt5)
                intent.putExtra("total_price", totalprice)

                startActivity(intent)
            }
        })
    }

    fun onClick_request(v: View) {
        //        결제호출
        var m: Method? = null
        when (v.getId()) {
            R.id.button1 -> {
                m = Method.CARD
            }
        }

        if (totalprice != null) {
            Bootpay.init(getFragmentManager())
                .setApplicationId("5cbc1852b6d49c0a8f7825a2") // 해당 프로젝트(안드로이드)의 application id 값
                .setPG(PG.INICIS) // 결제할 PG 사
                .setUserPhone("010-5119-9508") // 구매자 전화번호
                .setMethod(m!!) // 결제수단
                .setName("ORDERED APPLICATION") // 결제할 상품명
                .setOrderId("1234") //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
                .setPrice(totalprice!!) // 결제할 금액
                //.setAccountExpireAt("2018-09-22") // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. 오늘 날짜보다 더 뒤(미래)여야 합니다 )
                .addItem("마우스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                .addItem(
                    "키보드",
                    1,
                    "ITEM_CODE_KEYBOARD",
                    200,
                    "패션",
                    "여성상의",
                    "블라우스"
                ) // 주문정보에 담길 상품정보, 통계를 위해 사용
                .onConfirm(object : ConfirmListener {
                    override fun onConfirm(message: String?) {
                        if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                        else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                        Log.d("confirm", message);
                    }
                })
                .onDone(object : DoneListener {
                    override fun onDone(message: String?) {

                        Toast.makeText(this@PayActivity, "거래완료", Toast.LENGTH_SHORT).show()
                        Log.d("done", message)
                    }
                })
                .onReady(object : ReadyListener {
                    override fun onReady(message: String?) {

                        Log.d("ready", message)
                    }
                })
                .onCancel(object : CancelListener {
                    override fun onCancel(message: String?) {

                        Log.d("cancel", message)
                    }
                })
                .onError(object : ErrorListener {
                    override fun onError(message: String?) {

                        Log.d("error", message)
                    }
                })
                .onClose(object : CloseListener {
                    override fun onClose(message: String?) {
                        Log.d("close", "close")

                    }
                }).show()
        }
    }
}