$(document).ready(function(){
	$("#marketPay").click(function(){
		marketPay(); // 버튼 클릭하면 호출
	});
})

// 버튼 클릭시 실행
$("#marketPay").click(function () {        
	var IMP = window.IMP; // 생략가능        
	IMP.init('imp08476032');         
	// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용        
	// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드        
	IMP.request_pay({           
		pg: 'html5_inicis',        
		//html5_inicis':이니시스(웹표준결제)             
      pay_method: 'card',               
      merchant_uid: 'merchant_' + new Date().getTime(),               
      name: '주문명:결제테스트',  //결제창에서 보여질 이름      
      amount: 100,             //가격  최소 결제가격 100원        
      buyer_email: 'iamport@siot.do',         
      buyer_name: '구매자이름',           
      buyer_tel: '010-1234-5678',        
      buyer_addr: '서울특별시 강남구 삼성동',           
      buyer_postcode: '123-456',          
      }, function (rsp) {     // 결제 응답 처리       
    	  console.log(rsp);          
    	  if (rsp.success) {          
    		  var msg = '결제가 완료되었습니다.';   
    		  msg += '고유ID : ' + rsp.imp_uid;     
    		  msg += '상점 거래ID : ' + rsp.merchant_uid;       
    		  msg += '결제 금액 : ' + rsp.paid_amount;            
    		  msg += '카드 승인번호 : ' + rsp.apply_num;  
    		  location.href = "marketOrder"        
    		  } else {               
    			  var msg = '결제에 실패하였습니다.';     
    			  msg += '에러내용 : ' + rsp.error_msg;            
    			  }
    	  alert(msg);       
   });    
});
    	