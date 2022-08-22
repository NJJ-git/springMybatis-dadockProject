const signupForm=document.forms.signupForm;
const checkIdUrl="/user/idCheck/";
const idHelp=document.getElementById("idHelp");
signupForm["user_id"].addEventListener("change",checkId);

async function checkId(){
	let formCheck=false;
	let v=signupForm["user_id"].value;
	if(v.length>3){
		let res=await fetch(checkIdUrl+v);
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
	if(v.length>3){
		formCheck=true;
		signupForm["pw"].classList.add("is-valid");
		signupForm["pw"].classList.remove("is-invalid");
	}else{
		signupForm["pw"].classList.add("is-invalid");
		signupForm["pw"].classList.remove("is-valid");
	}
	return formCheck;
}
signupForm.addEventListener("submit",async(e)=>{
	e.preventDefault();
	let inputId=await checkId();
	let inputPw=checkPw();
	if(inputId && inputPw){
		signupForm.submit();		
	}
});






