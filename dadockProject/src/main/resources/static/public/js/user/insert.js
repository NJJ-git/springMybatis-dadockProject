const signupForm=document.forms.signupForm;
const checkIdUrl="/user/idCheck/";
const idHelp=document.getElementById("idHelp");
signupForm["user_id"].addEventListener("change",checkId);

async function checkId(){
   let formCheck=false;
   let v=signupForm["user_id"].value;
   
    if(v.length>3){
      let res=await fetch(checkIdUrl+v);
      console.log(res);
      let idCheckJson=await res.json();
      
      console.log(idCheckJson);
      if(idCheckJson.idCheck){   
         idHelp.style.display="none";
         signupForm["user_id"].classList.add("is-invalid");
         signupForm["user_id"].classList.remove("is-valid");
         idHelp.classList.add("is-invalid");
         idHelp.classList.remove("is-valid");
      }else{
         idHelp.style.display="none";
         signupForm["user_id"].classList.remove("is-invalid");
         signupForm["user_id"].classList.add("is-valid");
         idHelp.classList.add("is-valid");
         idHelp.classList.remove("is-invalid");
         formCheck=true;
      }      
   }else{
      
      alert("아이디가 너무 짧습니다.");
      idHelp.style.display="none";
      signupForm["user_id"].classList.add("is-invalid");
      signupForm["user_id"].classList.remove("is-valid");
      idHelp.classList.add("is-invalid");
      idHelp.classList.remove("is-valid");

   
   }
   
   return formCheck;
}
signupForm["pw"].addEventListener("change",checkPw);
function checkPw(){
   let v=signupForm["pw"].value;
   let formCheck=false;
   if(v.length>5){
      formCheck=true;
      signupForm["pw"].classList.add("is-valid");
      signupForm["pw"].classList.remove("is-invalid");
      $()
   }else{
      alert("비밀번호가 너무 짧습니다.");
      signupForm["pw"].classList.add("is-invalid");
      signupForm["pw"].classList.remove("is-valid");
   }
   return formCheck;
}
signupForm.addEventListener("submit",async(e)=>{
   e.preventDefault();
   let inputId=await checkId();
   let inputPw=checkPw();
   console.log(inputId,inputPw);
   if(inputId && inputPw){
      signupForm.submit();      
   }

});
function checkAll() {
 
        var f = document.forms.signupForm; 
    
 
        if (f.user_id.value == "") {
            alert("아이디를 입력해주십시오");
            f.user_id.focus();
            return false;
        }
 
        if (f.pw.value == "") {
            alert("비밀번호를 입력해주십시오");
            f.pw.focus();
            
            return false;
        }
 
        if (f.pw.value != f.pw_check.value) {
            alert("비빌번호를 다르게 입력했습니다.");
            f.pw_check.select();
            return false;
        }
 
        if (f.name.value == "") {
            alert("이름을 입력해주십시오");
            f.name.focus();
            return false;
        }
 

        if (f.address.value == "") {
            alert("주소를 검색하여 입력해주십시오");
            f.address.focus();
            return false;
        }
 
        if (f.address_detail.value == "") {
            alert("상세주소를 입력해주십시오");
            f.address_detail.focus();
            return false;
        }
        
        if (f.phone.value == "") {
            alert("전화번호를 입력해주십시오");
            f.phone.focus();
            return false;
        }
 
 
        if (f.email.value == "") {
            alert("이메일을 입력해주십시오");
            f.email.focus();
            return false;
        }
        
            
    }   

//비밀번호 확인//////////////////////////////////////////////
$('.pw').keyup(function(){
      var pass1 = $("#pw1").val();
      var pass2 = $("#pw2").val();
      
       if(pass1 != "" || pass2 != "") {
         if(pass1 == pass2) {
            $("#checkPw").html('비밀번호가 일치합니다.');
            $("#checkPw").attr('color','green');
            return true;
         }else {
            $("#checkPw").html('비밀번호가 불일치합니다.');
            $("#checkPw").attr('color','red');
            return false;
         }
      }
   })

//이용약관////////////////////////////////////////////////
 $("input:checkbox").click(checkedChange);
function checkedChange() {
    if($(this).prop("checked")){
        $("label[for="+this.id+"]").text("동의되었습니다.");
        $("label[for="+this.id+"]").css("color","blue");
        return true;
    }else{
        $("label[for="+this.id+"]").text("동의가 필요합니다.");
        $("label[for="+this.id+"]").css("color","red");
        return false;
    }
} 

//이메일 검사/////////////////////////////////////////////
function fn_submit() {               
   var text = document.getElementById('text').value;
   var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
   if (regEmail.test(text) === true) {
      alert('사용 가능한 이메일 형식입니다.');
      return true;
   }else
      alert('실패! 올바르지 않은 이메일 형식입니다. (ex:Example@emali.com)');
      return false;
   }
   
//휴대폰 번호 검사//////////////////////////////////////////
function fn_submit2() {
   var text = document.getElementById('phone').value;
   var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
   if (regPhone.test(text) === true) {
      alert('사용 가능한 휴대폰 번호입니다.');
      return true;
   }else
      alert('실패! 올바르지 않은 휴대폰번호 형식입니다. (ex:010-0000-0000)');
      return false;
   }
   
//카카오톡 주소 ////////////////////////////////////////////////////////////////


window.onload = function(){
    document.getElementById("address_kakao").addEventListener("click", function(){
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById("address_kakao").value = data.address; 
                document.querySelector("input[name=address_detail]").focus(); 
            }
        }).open();
    });
}