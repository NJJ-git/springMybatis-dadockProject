const insertForm=document.forms.insertForm;
const checkIdUrl="/user/idCheck/";
const idHelp=document.getElementById("idHelp");
insertForm["user_id"].addEventListener("change",checkId);

async function checkId(){
	let formCheck=false;
	let v=insertForm["user_id"].value;
	if(v.length>3){
		let res=await fetch(checkIdUrl+v);
		let idCheckJson=await res.json();
		console.log(idCheckJson);
		if(idCheckJson.idCheck){
			idHelp.style.display="none";
			insertForm["user_id"].classList.add("is-invalid");
			insertForm["user_id"].classList.remove("is-valid");
			idHelp.classList.add("is-invalid");
			idHelp.classList.remove("is-valid");
		}else{
			idHelp.style.display="none";
			insertForm["user_id"].classList.remove("is-invalid");
			insertForm["user_id"].classList.add("is-valid");
			idHelp.classList.add("is-valid");
			idHelp.classList.remove("is-invalid");
			formCheck=true;
		}		
	}else{
		idHelp.style.display="none";
		insertForm["user_id"].classList.add("is-invalid");
		insertForm["user_id"].classList.remove("is-valid");
		idHelp.classList.add("is-invalid");
		idHelp.classList.remove("is-valid");

	}
	return formCheck;
}
insertForm["pw"].addEventListener("change",checkPw);
function checkPw(){
	let v=insertForm["pw"].value;
	let formCheck=false;
	if(v.length>3){
		formCheck=true;
		insertForm["pw"].classList.add("is-valid");
		insertForm["pw"].classList.remove("is-invalid");
	}else{
		insertForm["pw"].classList.add("is-invalid");
		insertForm["pw"].classList.remove("is-valid");
	}
	return formCheck;
}
insertForm.addEventListener("submit",async(e)=>{
	e.preventDefault();
	let inputId=await checkId();
	let inputPw=checkPw();
	if(inputId && inputPw){
		insertForm.submit();		
	}
});






