console.log("linked");

window.onload = function(){
	document.getElementById("formbutton").addEventListener('click', getTicketList);
}
 console.log("tickettable.js is linked my good fellow");
var ticketStorage;
 
function getTicketList(){
	
	console.log("button pushed");
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState==4 && xhttp.status==200){
			alert(xhttp.responseText + " " + xhttp.status);
			let ticketList = JSON.parse(xhttp.responseText);
			ticketStorage = ticketList;
			console.log(ticketList);
			buildTable(ticketList);
			
		}
	}
	
	xhttp.open('GET', 'http://localhost:8080/Project1War/ticketlist.json');
	xhttp.send();
}
 


 
 function buildTable(data){
	let table = document.getElementById('ticketTable');
	let filter = document.getElementById('dropdownfilter').value;
	table.innerHTML = "";
	for(let i = 0; i < data.length; i++){
		if(filter == "None" || filter == data[i].statusid){
		
		let row = `<tr class="p-5" scope="row">
					<td>${data[i].ticketid}</td>
					<td>${data[i].amount}</td>
					<td>${data[i].submitted}</td>
					<td>${data[i].resolved}</td>
					<td>${data[i].description}</td>
					<td>${data[i].authorid}</td>
					<td>${data[i].resolverid}</td>
					<td>${data[i].statusid}</td>
					<td>${data[i].typeid}</td>
					<button onclick="approveTicket(${data[i].ticketid})" id="approveBtn" type="button" class="m-2 btn btn-primary">Approve</button>
					<button onclick="denyTicket(${data[i].ticketid})" id="denyBtn" type="button" class="btn btn-primary">Deny</button>
					<tr><br>`
	
	table.innerHTML += row
	}
	}
}

	function approveTicket(ticketid){
		let ticketmodel; 
		console.log(ticketid);
		console.log(ticketStorage.length);
		for( let i = 0; i<ticketStorage.length-1; i++) {
			console.log(i);
			if(ticketStorage[i].ticketid === ticketid){
				console.log(ticketStorage[i].ticketid + "ticketStorage ticketid");
				console.log(ticketid + "ticketid");
				ticketmodel = ticketStorage[i];
				ticketmodel.statusid = 2;
			}	
		}
		
		let xhttp = new XMLHttpRequest();	
		
		ticketmodel = JSON.stringify(ticketmodel);
		console.log(ticketmodel);
		xhttp.open('POST', 'http://localhost:8080/Project1War/ticketupdate.json');
		xhttp.setRequestHeader('Content-type', 'application/json');
		xhttp.send(ticketmodel);
		
		xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState==4 && xhttp.status==200){
			alert(xhttp.responseText);
			
	}
	}
}
	function denyTicket(ticketid){
		let ticketmodel;
		console.log(ticketid);
		console.log(ticketStorage.length);
		for(let i = 0; i<ticketStorage.length-1; i++) {
			console.log(i + "index");
			if(ticketStorage[i].ticketid === ticketid){
				console.log(ticketStorage[i].ticketid + "ticketStorage ticketid");
				console.log(ticketid + "ticketid");
				ticketmodel = ticketStorage[i];
				
				ticketmodel.statusid = 3;
				console.log(ticketmodel);
			}
		}
		
		let xhttp = new XMLHttpRequest();
	
		
			
		ticketmodel = JSON.stringify(ticketmodel);
		console.log(ticketmodel);
	
		xhttp.open('POST', 'http://localhost:8080/Project1War/ticketupdate.json');
		xhttp.setRequestHeader('Content-type', 'application/json');
		xhttp.send(ticketmodel);
		
		xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState==4 && xhttp.status==200){
			alert(xhttp.responseText);
			
	}
}
}
