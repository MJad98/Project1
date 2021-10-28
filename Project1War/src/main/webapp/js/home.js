/**
 * 
 */
 console.log("home is linked");
 window.onload = function(){
	getSessUser();
	//add more function to show tickets(this will populate a table in the html doc), add tickets(this will end up utilizing a form)
	//Modal form storage for forms
}
 function getSessUser() {
	console.log("here before XMLHttpRequest");
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		console.log(xhttp.readyState);
		if(xhttp.readyState == 4 && xhttp.status == 200){
			let user = JSON.parse(xhttp.responseText);
			console.log(user);
			document.getElementById("welcome").innerText = `Welcome ${user.firstname}`;
		}
		
	}
	xhttp.open("GET", "http://localhost:8080/Project1War/getsessionemployee.json");
	
	xhttp.send();
	
}