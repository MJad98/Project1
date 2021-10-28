/**
 * This is the script which allows an employee to view all of their individual tickets
 */
 console.log("employee ticket view is linked")
 
 window.onload = function(){
	document.getElementById("ticketview").addEventListener('click', getEmployeeTicketList);
}

function getEmployeeTicketList(){
	
	console.log("button pushed");
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState==4 && xhttp.status==200){
			let employeeTicketList = JSON.parse(xhttp.responseText);
			console.log(employeeTicketList);
			buildTable(employeeTicketList);
		}
	}
	xhttp.open('GET', 'http://localhost:8080/Project1War/employeetickets.json');
	xhttp.send();
	
	
}
 function buildTable(data){
	let table = document.getElementById('ticketTable');
	table.innerHTML = "";
	for(let i = 0; i < data.length; i++){
		let row = `<tr class="p-5" scope="row">
					<td>${data[i].ticketid}</td>
					<td>${data[i].amount}</td>
					<td>${data[i].description}</td>
					<td>${data[i].authorid}</td>
					<td>${data[i].resolverid}</td>
					<td>${data[i].statusid}</td>
					<td>${data[i].typeid}</td>
					<tr>`
	
	table.innerHTML += row
	}
}